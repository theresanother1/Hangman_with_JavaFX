package at.ac.fhcampuswien.hangmanwithjfx.game;

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

    //Konstruktor - setzt alles auf 0 oder benötigte Startwerte.
    public Gameplay() {
        this.curLetter = ' ';
        this.allEnteredLetters = new char[MAXERRORS];
        Arrays.fill(allEnteredLetters, ' ');
        this.errorCount = 0;
        this.wordToFind = randomWord();
        lines();
    }

    //Wählt das Wort aus String-Array aus.
    protected String randomWord() {
        //https://www.daniweb.com/programming/software-development/threads/168224/hangman-java-console-mode
        //https://www.hangmanwords.com/words
        final String[] dictionary = {"abstraction", "ambiguous", "arithmetic", "backslash", "bitmap", "circumstance", "combination", "cafebabe",
                "consortium", "decrementing", "dependency", "disambiguate", "dynamic", "encapsulate", "equivalent", "expression", "facilitate", "micro",
                "fragment", "hexadecimal", "implement", "distinguish", "leon", "inheritance", "internet", "java", "localize",
                "navigation", "optimization", "parameter", "patrick", "pickle", "polymorphic", "rigorously", "specific",
                "structure", "lexical", "likewise", "management", "manipulate", "mathematics", "hotjava", "vertex", "unsigned", "traditional",
                "girl", "mother", "father", "sister", "love", "wind", "water", "study", "ball",
                "puppy", "kitten", "apple", "pear", "lemon", "mango", "peach", "apricot", "chips", "steak", "fries", "cheese",
                "potato", "wedge", "heel", "hand", "foot", "nose", "face", "mouth", "tongue", "johannes", "sven", "fingers", "toes", "line", "space",
                "phone", "cord", "core", "grass", "trees", "birds", "animals", "lazy", "funny", "king", "queen", "heart", "heat", "cold",
                "moon", "movie", "theater", "hairy", "small", "large", "huge", "donkey", "chicken", "pizza", "bread", "stones",
                "sticks", "leaves", "letters", "alphabet", "soup", "hungry", "juergen", "michael", "tired", "theresa", "sleepy", "noisy", "caring", "friends", "month", "light",
                "toothbrush", "savings", "bank", "account", "teller", "paper", "pencil", "coffee", "spirit", "ghost", "melon", "necklace",
                "screen", "balloon", "string", "calendar", "work", "toys", "kids", "school", "class", "campus", "freedom", "liberty", "happiness",
                "university", "message", "marker", "crayon", "eraser", "music", "lyrics", "songs", "ballads", "shapes", "triangle", "circle", "rectangle",
                "square", "oval", "show", "video", "player", "team", "sport", "basketball", "football", "soccer", "softball", "baseball", "tennis",
                "hockey", "lacrosse", "volleyball", "circuit", "blade", "scratch", "home", "house", "safe", "safety", "number", "count", "bear",
                "goose", "lama", "panda", "lion", "tiger", "cheetah", "computer", "crackers", "rice", "shoes", "book", "story", "princess",
                "prince", "jester", "court", "jury", "judge", "bench", "scandal", "name", "newspaper", "sebastian", "press", "shove", "tear", "magic", "tricks",
                "cereal", "breakfast", "lunch", "dinner", "main", "course", "fork", "spoon", "sara", "knife", "lamp", "desk", "bottle", "highlighter",
                "medicine", "seven", "flower", "rose", "petal", "abruptly", "absurd", "abyss", "affix", "askew", "avenue", "awkward", "axiom",
                "azure", "bagpipes", "bandwagon", "banjo", "bayou", "beekeeper", "bikini", "blizzard", "boggle", "bookworm", "boxcar", "boxful",
                "buckaroo", "buffalo", "buffoon", "buxom", "buzzard", "buzzing", "buzzwords", "caliph", "cobweb", "cockiness", "croquet", "crypt",
                "curacao", "cycle", "daiquiri", "dirndl", "disavow", "dizzying", "duplex", "dwarves", "embezzle", "equip", "espionage", "exodus",
                "faking", "fishhook", "fixable", "fjord", "flapjack", "flopping", "fluffiness", "flyby", "foxglove", "frazzled", "frizzled", "fuchsia",
                "funny", "gabby", "galaxy", "galvanize", "gazebo", "gizmo", "glowworm", "glyph", "gnarly", "gnostic", "gossip", "grogginess",
                "haiku", "haphazard", "hyphen", "iatrogenic", "icebox", "injury", "ivory", "jackpot", "jaundice", "jawbreaker", "jaywalk", "jazziest",
                "jazzy", "jelly", "jigsaw", "jinx", "jiujitsu", "jockey", "jogging", "joking", "jovial", "joyful", "juicy", "jukebox", "jumbo", "kayak",
                "kazoo", "keyhole", "khaki", "kilobyte", "kiosk", "kitsch", "kiwifruit", "klutz", "knapsack", "larynx", "lengths", "lucky", "luxury", "lymph",
                "marquis", "matrix", "megahertz", "microwave", "mnemonic", "mystify", "naphtha", "nightclub", "nowadays", "idiot", "nymph", "onyx",
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

