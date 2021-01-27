package at.ac.fhcampuswien.hangmanwithjfx;

import at.ac.fhcampuswien.hangmanwithjfx.game.Gameplay;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class App extends Application  {


    public static void main(String[] args) throws IOException {
        System.out.println("blubb");

        /*

        Skript zur Erstellung der Datei mit Wörtern drin

        //https://www.daniweb.com/programming/software-development/threads/168224/hangman-java-console-mode
        //https://www.hangmanwords.com/words
        String[] dictionary = {"abstraction", "ambiguous", "arithmetic", "backslash", "bitmap", "circumstance", "combination", "cafebabe",
                "consortium", "decrement", "dependency", "dynamic", "encapsulate", "equivalent", "expression", "facilitate", "micro",
                "fragment", "decimal", "implement", "distinguish", "leon", "inheritance", "internet", "java", "localize",
                "navigation", "optimization", "parameter", "patrick", "pickle", "polymorphic", "rigorously", "specific",
                "structure", "lexical", "likewise", "management", "manipulate", "maths", "hotjava", "vertex", "unsigned", "traditional",
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



        Path myPath = Paths.get(".././Hangman_with_JavaFX/src/main/resources/Dictionary.txt");
        //Erstellt das File am PC, falls es noch nicht vorhanden ist.
        //1x als Skript ausführen, dann löschen, weil Datei ja dann im Projekt drinnen ist?

        try {
            //Checkt, ob die Datei schon vorhanden ist.
            if (new File(String.valueOf(myPath)).isFile()) {
                System.out.println("File already exists.");
            }
            //Wenn nicht, wird die Datei erstellt und Inhalt des Arrays hineingeschrieben.
            else {
                FileWriter writeIt = new FileWriter(new File(String.valueOf(myPath)));
                for (String s : dictionary) {
                    writeIt.write(s + "\n");
                }
                writeIt.close();
            }
        } catch (IOException e) {
            System.out.println("File writing or creating Error.");
            e.printStackTrace();
        }

         */


        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Initialisiert 1. Seite.
        Parent root = FXMLLoader.load(getClass().getResource("/playHangman.fxml"));
        primaryStage.setTitle("This is HANGMAN!");
        primaryStage.setScene(new Scene(root, 845, 600));

        //Setzt Fenster auf fixe Größe.
        primaryStage.setResizable(false);
        primaryStage.show();
    }



    }
