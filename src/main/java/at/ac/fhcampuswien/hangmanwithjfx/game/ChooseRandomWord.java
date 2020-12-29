package at.ac.fhcampuswien.hangmanwithjfx.game;

import java.util.Random;

public class ChooseRandomWord {

    public String wordToFind;

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
    public void setWordToFind() {
        this.wordToFind = randomWord();
    }
    public String getWordToFind() {
        return wordToFind;
    }


}

