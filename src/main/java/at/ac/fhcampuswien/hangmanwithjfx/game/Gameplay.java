package at.ac.fhcampuswien.hangmanwithjfx.game;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import static java.lang.Character.toLowerCase;

public class Gameplay {
    public char letter;
    public final int MAXERRORS = 10;
    public char[] allLetters;
    public int errorCount;
    public char[] lines;
    boolean restart = true;

    //word extra definiert, glaube, das brauchen wir als allgemeine Variable
    public String wordToFind;
    //public static String asterisk;

    public void reset() {
        //Methode setzt alles auf Anfang zurück; boolean übergeben???
        letter = ' ';
        allLetters = new char[MAXERRORS];
        for(int i = 0; i<allLetters.length; i++){
            allLetters[i] = ' ';
        }
        errorCount = 0;
        wordToFind = "";

    }

    public String randomWord() {
        //wählt ein zufälliges Wort aus Datenbank bzw. ArrayList aus
        //https://www.daniweb.com/programming/software-development/threads/168224/hangman-java-console-mode
        String dictionary[] = {"abstraction", "ambiguous", "arithmetic", "backslash", "bitmap", "circumstance", "combination", "consequently",
                "consortium", "decrementing", "dependency", "disambiguate", "dynamic", "encapsulation", "equivalent", "expression", "facilitate",
                "fragment", "hexadecimal", "implementation", "indistinguishable", "inheritance", "internet", "java", "localization", "microprocessor",
                "navigation", "optimization", "parameter", "patrick", "pickle", "polymorphic", "rigorously", "simultaneously", "specification",
                "structure", "lexical", "likewise", "management", "manipulate", "mathematics", "hotjava", "vertex", "unsigned", "traditional",
                "boy", "girl", "mother", "father", "sister", "love", "sky", "wind", "water", "study", "ball",
                "cat", "dog", "puppy", "kitten", "apple", "pear", "lemon", "mango", "peach", "apricot", "chips", "steak", "fries", "cheese",
                "patatoe", "wedge", "heel", "hand", "foot", "arm", "leg", "nose", "face", "mouth", "tongue", "fingers", "toes", "line", "space",
                "phone", "cord", "core", "grass", "trees", "birds", "animals", "lazy", "funny", "king", "queen", "heart", "heat", "cold", "sun",
                "moon", "movie", "theater", "hairy", "big", "small", "large", "huge", "pig", "donkey", "cow", "chicken", "pizza", "bread", "stones",
                "sticks", "leaves", "letters", "alphabet", "soup", "hungry", "tired", "sleepy", "noisy", "caring", "friends", "month", "day", "light",
                "toothbrush", "savings", "bank", "account", "teller", "paper", "pencil", "tea", "coffee", "spirit", "ghost", "can", "melon", "necklace",
                "screen", "baloon", "string", "calendar", "work", "toys", "kids", "school", "class", "campus", "freedom", "liberty", "happiness",
                "university", "message", "marker", "crayon", "eraser", "music", "lyrics", "songs", "ballads", "shapes", "triangle", "circle", "rectangle",
                "square", "oval", "show", "video", "player", "team", "sport", "basketball", "football", "soccer", "softball", "baseball", "tennis",
                "hockey", "lacrosse", "volleyball", "circut", "blade", "scratch", "hit", "home", "house", "safe", "safety", "number", "count", "bear",
                "goose", "lama", "panda", "lion", "tiger", "cheetah", "computer", "crackers", "rice", "fan", "shoes", "book", "story", "princess",
                "prince", "jester", "court", "jury", "judge", "bench", "scandal", "name", "newspaper", "press", "shove", "tear", "cry", "magic", "tricks",
                "cereal", "breakfast", "lunch", "dinner", "main", "course", "fork", "spoon", "knife", "lamp", "desk", "bottle", "highlighter", "cap",
                "medicine", "six", "seven", "flower", "rose", "petal"};
        Random random = new Random();
        int index = random.nextInt(dictionary.length);
        wordToFind = dictionary[index];
        return wordToFind;
    }

    public char[] printLines (String word){
        //___ für Zeichenanzahl von randomWord
        //Array of characters oder String mit der Länge String Word
        //printet ___

        //asterisk = new String(new char[wordToFind.length()]).replace("\0", "_");
        //System.out.println(asterisk);

        this.lines = new char[word.length()];
        for(int i=0; i<word.length(); i++){
            lines[i] = '_';
        }
        System.out.println(lines);
        return lines;
    }

    public void userInput() {
        //Scanner
        //Eingabeaufforderung
        //Methode soll sich beschränkt oft wiederholen
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter a letter: ");
        this.letter = toLowerCase(input.next().charAt(0));
        //return inputword;
    }

    public boolean checkForDuplicates() {
        //user input Buchstabe übernehmen
        //mit bisher eingegebenen Buchstaben vergleichen
        //if doppelt true -> userInput
        //if doppelt false -> checkLetter
        char checkLetter = this.letter;
        boolean duplicate = false;

        for (int i = 0; i<allLetters.length; i++){
            if (checkLetter == allLetters[i]) {
                duplicate = true;
                System.out.println("This is a duplicate!");
                break;
            }
        }
        if (!duplicate) {
            for (int i = 0; i < MAXERRORS; i++) {
                if (allLetters[i] == ' ') {
                    allLetters[i] = checkLetter;
                    break;
                }
            }
        }
        return duplicate;
    }

    public void checkLetter() {
        //if letter correct
        //Linie an der Stelle des Buchstaben wird durch Buchstaben ersetzt
        //if letter incorrect
        //error count ++
        //ev. print && change o to x
        //Buchstabe muss mit den Buchstaben im Wort verglichen werden
        //char Array mit randomWort

        char noDuplicate = this.letter;
        //System.out.println("start");
        boolean test = false;
        for (int i = 0; i < wordToFind.length(); i++) {
            //System.out.println("for");
            if (wordToFind.charAt(i) == noDuplicate) {
                //System.out.println("if");
                test = true;
                lines[i] = noDuplicate;
                System.out.println(lines);
                continue;
                //newasterisk += guess.charAt(0); //wenn richtig dann wird newasterix diese buchstabe bekommen
            }
            /*else if (asterisk.charAt(i) != '_') {
                newasterisk += wordToFind.charAt(i);
            }*/
        }
        if (test == false){
            //System.out.println("else");
            errorCount ++;
            System.out.println(lines);
            System.out.println("Oops! " + (MAXERRORS-errorCount) +" Lives left");
            //newasterisk += "_"; //newasterix generiert
        }
        //System.out.println("end");

        /* win or lose
        if (asterisk.equals(newasterisk)) { //wenns falsch ist
            errorCount++;
            System.out.println(newasterisk);
            System.out.println("Oops! " + (MAXERRORS-errorCount) +"Lives left");
        } else { //wenns richtig ist
            asterisk = newasterisk; //lösung wird mit neuenasterisk ersetzt für den nächsten vergleich
            System.out.println(newasterisk);
        }
        if (asterisk.equals(wordToFind)) { // finito
            System.out.println("Correct! You win! The word was " + wordToFind);
        }*/



    }

    public boolean winOrLose(String wordToFind) {
        //if word user == word computer && errors != max errors
        //win!1!
        //if errors == max errors && word user != word computer
        //loser!1!
        //if error != max error && word != word computer
        //continue

        //String userWord = Ergebnis aus Patricks Funktion
        String userWord = Arrays.toString(lines).replace("[", "")
                .replace("]", "")
                .replace(" ", "")
                .replace(",", "");
        //  System.out.println(userWord);
        if (userWord.equals(wordToFind) && this.errorCount != this.MAXERRORS) {
            System.out.println("Correct! You win! The word was " + wordToFind);
            restart = false;
        }
        if (!userWord.equals(wordToFind) && this.errorCount == this.MAXERRORS) {
            System.out.println("Wrong! You lost! The word was " + wordToFind);
            //restartGame();
            restart = false;
        }
      /*  else if (!userWord.equals(wordToFind) && errorCount != MAXERRORS) {
            //userInput();
        }

       */
        return restart;
    }

    //Hier eventuell Printmethode einfügen


    public boolean restartGame() {
        //User fragen, ob sie das Spiel beenden oder weitermachen möchten
        //neues Spiel starten bzw. beenden
        return true;
    }
}

