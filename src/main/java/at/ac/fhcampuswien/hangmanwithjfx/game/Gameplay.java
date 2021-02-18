package at.ac.fhcampuswien.hangmanwithjfx.game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Character.toLowerCase;

public class Gameplay {

    //current Letter
    public char curLetter;

    public final int MAXERRORS = 10;

    //updated with each error
    public int errorCount;

    //saves all entered letters for duplicate control
    public char[] allEnteredLetters;

    //converts word into char array for the word with underlines
    public char[] lines;

    //the random word
    public String wordToFind = " ";

    public String currentWord;


    public Gameplay() {
        this.curLetter = ' ';
        this.allEnteredLetters = new char[MAXERRORS];
        Arrays.fill(allEnteredLetters, ' ');
        this.errorCount = 0;
        this.wordToFind = randomWord();
        lines();
    }

    //choose random word from Dictionary.txt
    public String randomWord(){
        long linesInFile = 0;
        Path myPath = Paths.get(".././Hangman_with_JavaFX/src/main/resources/Dictionary.txt");
        //counts lines in file
        try {
            linesInFile = Files.lines(myPath).count();
            System.out.println("Counted lines in file.");
        } catch (IOException e){
            System.out.println("Troubles counting lines.");
            e.printStackTrace();
        }
        System.out.println(linesInFile);

        //get a random line
        Random random = new Random();
        int index = random.nextInt((int) linesInFile);

        //get string from random line
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

    //Array of characters with underlines
    public void lines() {
        this.lines = new char[wordToFind.length()];
        for (int i = 0; i < wordToFind.length(); i++) {
            lines[i] = '_';
        }
        //System.out.println(lines);
    }

    //checks current letter for duplicate
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

    //checks if current letter is in the word
    public boolean checkLetter() {
        boolean test = false;
        for (int i = 0; i < wordToFind.length(); i++) {
            if (wordToFind.charAt(i) == curLetter) {
                test = true;
                lines[i] = curLetter;
            }
        }
        if (!test) {
            errorCount++;
        }
        return test;
    }
}

