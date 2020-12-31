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
    public String wordToFind = " ";


    public Gameplay(){
        //Konstruktor setzt alles auf 0 / Startwert
        System.out.println("Konstruktor Start");
        this.letter = ' ';
        this.allLetters = new char[MAXERRORS];
        Arrays.fill(allLetters, ' ');
        System.out.println(Arrays.toString(allLetters) + "Konstruktor");
        this.errorCount = 0;
        this.wordToFind = randomWord();
        System.out.println(wordToFind+ " klasse");
        //printLines(wordToFind);
        printLines();
        //this.lines = new char[wordToFind.length()];
        //System.out.println(lines + "klasse");
        //Arrays.fill(lines, '_');
        System.out.println(Arrays.toString(lines) + " klasse"); //checks for lines being set up
        System.out.println("Konstruktor Stopp");
    }

   /* public void reset() {
        //Methode setzt alles auf Anfang zurück; boolean übergeben???
        letter = ' ';
        allLetters = new char[MAXERRORS];
        Arrays.fill(allLetters, ' ');
        errorCount = 0;
        wordToFind = randomWord();
        //printLines(wordToFind);
        this.lines = new char[wordToFind.length()];

    }

    */
    public String randomWord() {
        //wählt ein zufälliges Wort aus Datenbank bzw. ArrayList aus
        //https://www.daniweb.com/programming/software-development/threads/168224/hangman-java-console-mode
        //https://www.hangmanwords.com/words
        String dictionary[] = {"abstraction", "ambiguous", "arithmetic", "backslash", "bitmap", "circumstance", "combination", "consequently",
                "consortium", "decrementing", "dependency", "disambiguate", "dynamic", "encapsulation", "equivalent", "expression", "facilitate",
                "fragment", "hexadecimal", "implementation", "indistinguishable", "leon", "inheritance", "internet", "java", "localization", "microprocessor",
                "navigation", "optimization", "parameter", "patrick", "pickle", "polymorphic", "rigorously", "simultaneously", "specification",
                "structure", "lexical", "likewise", "management", "manipulate", "mathematics", "hotjava", "vertex", "unsigned", "traditional",
                "girl", "mother", "father", "sister", "love", "wind", "water", "study", "ball",
                "puppy", "kitten", "apple", "pear", "lemon", "mango", "peach", "apricot", "chips", "steak", "fries", "cheese",
                "patatoe", "wedge", "heel", "hand", "foot", "nose", "face", "mouth", "tongue", "johannes", "sven", "fingers", "toes", "line", "space",
                "phone", "cord", "core", "grass", "trees", "birds", "animals", "lazy", "funny", "king", "queen", "heart", "heat", "cold",
                "moon", "movie", "theater", "hairy", "small", "large", "huge", "donkey", "chicken", "pizza", "bread", "stones",
                "sticks", "leaves", "letters", "alphabet", "soup", "hungry", "juergen", "michael", "tired","theresa", "sleepy", "noisy", "caring", "friends", "month", "light",
                "toothbrush", "savings", "bank", "account", "teller", "paper", "pencil", "coffee", "spirit", "ghost", "melon", "necklace",
                "screen", "baloon", "string", "calendar", "work", "toys", "kids", "school", "class", "campus", "freedom", "liberty", "happiness",
                "university", "message", "marker", "crayon", "eraser", "music", "lyrics", "songs", "ballads", "shapes", "triangle", "circle", "rectangle",
                "square", "oval", "show", "video", "player", "team", "sport", "basketball", "football", "soccer", "softball", "baseball", "tennis",
                "hockey", "lacrosse", "volleyball", "circut", "blade", "scratch", "home", "house", "safe", "safety", "number", "count", "bear",
                "goose", "lama", "panda", "lion", "tiger", "cheetah", "computer", "crackers", "rice", "shoes", "book", "story", "princess",
                "prince", "jester", "court", "jury", "judge", "bench", "scandal", "name", "newspaper", "sebastian", "press", "shove", "tear", "magic", "tricks",
                "cereal", "breakfast", "lunch", "dinner", "main", "course", "fork", "spoon","sara", "knife", "lamp", "desk", "bottle", "highlighter",
                "medicine", "seven", "flower", "rose", "petal", "abruptly", "absurd", "abyss", "affix", "askew", "avenue", "awkward", "axiom",
                "azure", "bagpipes", "bandwagon", "banjo", "bayou", "beekeeper", "bikini", "blizzard", "boggle", "bookworm", "boxcar", "boxful",
                "buckaroo", "buffalo", "buffoon", "buxom", "buzzard", "buzzing", "buzzwords", "caliph", "cobweb", "cockiness", "croquet", "crypt",
                "curacao", "cycle", "daiquiri", "dirndl", "disavow", "dizzying", "duplex", "dwarves", "embezzle", "equip", "espionage", "exodus",
                "faking", "fishhook", "fixable", "fjord", "flapjack", "flopping", "fluffiness", "flyby", "foxglove", "frazzled", "frizzled", "fuchsia",
                "funny", "gabby", "galaxy", "galvanize", "gazebo", "giaour", "gizmo", "glowworm", "glyph", "gnarly", "gnostic", "gossip", "grogginess",
                "haiku", "haphazard", "hyphen", "iatrogenic", "icebox", "injury", "ivory", "jackpot", "jaundice", "jawbreaker", "jaywalk", "jazziest",
                "jazzy", "jelly", "jigsaw", "jinx", "jiujitsu", "jockey", "jogging", "joking", "jovial", "joyful", "juicy", "jukebox", "jumbo", "kayak",
                "kazoo", "keyhole", "khaki", "kilobyte", "kiosk", "kitsch", "kiwifruit", "klutz", "knapsack", "larynx", "lengths", "lucky", "luxury", "lymph",
                "marquis", "matrix", "megahertz", "microwave", "mnemonic", "mystify", "naphtha", "nightclub", "nowadays", "numbskull", "nymph", "onyx",
                "ovary", "oxidize", "oxygen", "pajama", "peekaboo", "phlegm", "pixel", "pneumonia", "polka", "psyche", "puppy", "puzzling", "quartz", "queue",
                "quiz", "quizzes", "quorum", "rhythm", "rickshaw", "schnapps", "scratch", "shiv", "snazzy", "sphinx", "spritz", "squawk", "staff", "strength",
                "strengths", "stretch", "stronghold", "stymied", "subway", "swivel", "syndrome", "thriftless", "thumbscrew", "topaz", "transcript", "transgress",
                "transplant", "twelfth", "unknown", "unworthy", "unzip", "uptown", "vaporize", "vixen", "vodka", "voodoo", "vortex", "voyeurism", "walkway",
                "waltz", "wave", "wavy", "waxy", "wellspring", "wheezy", "whiskey", "whizzing", "whomever", "wimpy", "witchcraft", "wizard", "woozy", "wristwatch",
                "wyvern", "xylophone", "yachtsman", "yippee", "yoked", "youthful", "yummy", "zephyr", "zigzag", "zigzagging", "zilch", "zipper", "zodiac", "zombie"};
        Random random = new Random();
        int index = random.nextInt(dictionary.length);
        this.wordToFind = dictionary[index];
        return wordToFind;
    }

   public char[] printLines (){
        //Array of characters mit der Länge String Wort, printet ___
        this.lines = new char[wordToFind.length()];
        for(int i=0; i<wordToFind.length(); i++){
            lines[i] = '_';
        }
        //System.out.println(lines);
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

    public boolean checkForDuplicates(String input) {
        //user input Buchstabe übernehmen
        //mit bisher eingegebenen Buchstaben vergleichen
        //if doppelt true -> userInput
        //if doppelt false -> checkLetter
        this.letter = toLowerCase(input.charAt(0));
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
                    System.out.println(allLetters);
                    break;
                }
            }
        }
        return duplicate;
    }

    public boolean checkLetter(char c, String s) {
        //if letter correct
        //Linie an der Stelle des Buchstaben wird durch Buchstaben ersetzt
        //if letter incorrect
        //error count ++
        //ev. print && change o to x
        //Buchstabe muss mit den Buchstaben im Wort verglichen werden
        //char Array mit randomWort

        wordToFind = s;
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
        if (!test){
            //System.out.println("else");
            errorCount ++;
            System.out.println(lines);
            System.out.println("Oops! " + (MAXERRORS-errorCount) +" Lives left");
            //newasterisk += "_"; //newasterix generiert
        } return test;
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

