package at.ac.fhcampuswien.hangmanwithjfx.game;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Character.toLowerCase;

public class Gameplay {

    //derzeitiger Buchstabe
    public char curLetter;

    public final int MAXERRORS = 10;

    //Zählt Fehleranzahl im Laufe des Spiels hoch.
    public int errorCount;

    //Speichert alle zuvor eingegebenen Buchstaben ab.
    public char[] allEnteredLetters;

    //Buchstaben des gesuchten Wortes mit Unterstrichen ersetzt in Array.
    public char[] lines;

    //gesuchtes RandomWord
    public String wordToFind = " ";

    public String[] dictionary;
    //Konstruktor - setzt alles auf 0 oder benötigte Startwerte.
    public Gameplay() {
        this.curLetter = ' ';
        this.allEnteredLetters = new char[MAXERRORS];
        Arrays.fill(allEnteredLetters, ' ');
        this.errorCount = 0;
        this.wordToFind = randomWord();
        lines();
    }

    //Wählt das Wort aus der Datei Dictionary aus.
    public String randomWord(){

        //Zählt die Zeilenanzahl in der Datei. Sollte erhalten bleiben, falls Datei ergänzt oder verkleinert wird.
        long linesInFile = 0;
        Path myPath = Paths.get(".././Hangman_with_JavaFX/src/main/resources/Dictionary.txt");
        try {
            linesInFile = Files.lines(myPath).count();
            System.out.println("Counted lines in file.");
        } catch (IOException e){
            System.out.println("Troubles counting lines.");
            e.printStackTrace();
        }
        System.out.println(linesInFile);

        //Random intialisieren, um eine zufällige Zeile zu ermitteln.
        Random random = new Random();
        int index = random.nextInt((int) linesInFile);

        //Ordnet dem String specificWord das Wort aus der zufällig ermittelten Zeile zu.
        try {
            this.wordToFind = Files.readAllLines(myPath).get(index);
            System.out.println(wordToFind);
            System.out.println("read the word from the file.");
        } catch (IOException f) {
            System.out.println("File reading Error. ");
            f.printStackTrace();
        }
        return wordToFind;
    }

    //Array of characters mit der Länge des String wordToFind, einzelne chars mit Unterstrichen ersetzt.
    public char[] lines() {
        this.lines = new char[wordToFind.length()];
        for (int i = 0; i < wordToFind.length(); i++) {
            lines[i] = '_';
        }
        //System.out.println(lines);
        return lines;
    }

    //Es wird geprüft, ob Buchstabe schon einmal eingegeben wurdex.
    public boolean checkForDuplicates(String input) {
        this.curLetter = toLowerCase(input.charAt(0));
        char checkLetter = this.curLetter;
        boolean duplicate = false;

        for (int i = 0; i < allEnteredLetters.length; i++) {
            if (checkLetter == allEnteredLetters[i]) {
                duplicate = true;
                //System.out.println("This is a duplicate!");
                break;
            }
        }
        if (!duplicate) {
            for (int i = 0; i < MAXERRORS; i++) {
                if (allEnteredLetters[i] == ' ') {
                    allEnteredLetters[i] = checkLetter;
                    //System.out.println(allEnteredLetters);
                    break;
                }
            }
        }
        return duplicate;
    }

    //Überprüft, ob der Buchstabe im gesuchten Wort ist.
    public boolean checkLetter(char c, String s) {
        wordToFind = s;
        char noDuplicate = this.curLetter;
        boolean test = false;
        for (int i = 0; i < wordToFind.length(); i++) {
            if (wordToFind.charAt(i) == noDuplicate) {
                test = true;
                lines[i] = noDuplicate;
            }
        }
        if (!test) {
            errorCount++;
        }
        return test;
    }
}

