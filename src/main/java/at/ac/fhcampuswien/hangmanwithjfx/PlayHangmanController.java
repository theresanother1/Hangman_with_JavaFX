package at.ac.fhcampuswien.hangmanwithjfx;

import at.ac.fhcampuswien.hangmanwithjfx.game.Gameplay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import static java.lang.Character.toLowerCase;

public class PlayHangmanController implements Initializable {

    //set invisible - 1. page
    @FXML public TextField checkThisInputLetter;

    @FXML public Label wordToCheck;
    @FXML public Label enterLetterHere;
    @FXML public Label duplicate;
    @FXML public Label currentWordOutput;
    @FXML public Label errorNoLetter;

    //set visible - 1. page
    @FXML public Label messageForUser;

    //set invisible - 1. page
    @FXML public ImageView hangman0;
    @FXML public ImageView hangman1;
    @FXML public ImageView hangman2;
    @FXML public ImageView hangman3;
    @FXML public ImageView hangman4;
    @FXML public ImageView hangman5;
    @FXML public ImageView hangman6;
    @FXML public ImageView hangman7;
    @FXML public ImageView hangman8;
    @FXML public ImageView hangman9;
    @FXML public ImageView hangman10;
    @FXML public ImageView uWin;
    @FXML public ImageView uLost;


    //set visible - 1. Page
    @FXML private Button startButton;
    @FXML private Button exitButton;

    //set invisible - 1. Page
    @FXML public Button continueButton;
    @FXML public Button quitButton;
    @FXML public Button restartButton;

    //gamelogic
    protected Gameplay gameplay;
    public String thisWord;

    //generate a new object of gameplay, makes game repeatable
    protected void setNewGame(){
        this.gameplay = new Gameplay();
        this.thisWord = gameplay.wordToFind;
    }

    @FXML //Ends game, in the beginning, if wanted
    protected void exitGame(ActionEvent a) {
        Stage primaryStage = (Stage) exitButton.getScene().getWindow();
        primaryStage.close();
    }

    @FXML //Ends game after playing at least 1 time
    protected void quitGame(ActionEvent a) {
        Stage primaryStage = (Stage) quitButton.getScene().getWindow();
        primaryStage.close();
    }

    @FXML //starts game
    protected void pressToPlay(ActionEvent actionEvent) {

        setNewGame();

        //use enter as default key
        continueButton.setDefaultButton(true);

        //label is set with the current word searched for (used later on)
        wordToCheck.setText(thisWord);

        //outputs the word updated while playing
        currentWordOutput.setText(currentWordForOutput());

        //set visibility of the used parts (especially needed for replay)
        visibility();
    }


    @FXML //gameplay
    protected void pressToCheckLetter(ActionEvent actionEvent) {

        //set invisible after each letter check
        duplicate.setVisible(false);
        errorNoLetter.setVisible(false);

        //avoid I/O Error, if nothing is entered
        if (checkThisInputLetter.getText().isEmpty() || checkThisInputLetter.getText() == null) {
            errorNoLetter.setVisible(true);
        }
        //check entered letter
        else {
            //1. check if letter is a duplicate
            if (!gameplay.checkForDuplicates(checkThisInputLetter.getText())) {
                //2. check if letter is in word
                if (gameplay.checkLetter()){
                    //update the output for GUI
                    currentWordOutput.setText(currentWordForOutput());
                    //compares current word with searched word
                    if (compareWords()) {
                        youWonTheGame();
                    }
                }
                //letter is not in word
                else {
                    //actions, depending on errorcount
                    switch (gameplay.errorCount) {
                        case 1 -> hangman1.setVisible(true);
                        case 2 -> hangman2.setVisible(true);
                        case 3 -> hangman3.setVisible(true);
                        case 4 -> hangman4.setVisible(true);
                        case 5 -> hangman5.setVisible(true);
                        case 6 -> hangman6.setVisible(true);
                        case 7 -> hangman7.setVisible(true);
                        case 8 -> hangman8.setVisible(true);
                        case 9 -> hangman9.setVisible(true);
                        case 10 -> youLostTheGame();
                    }
                }
                //clear textfield for new letter
                checkThisInputLetter.clear();
            }
            //letter was a duplicate, no letter check
            else {
                duplicate.setVisible(true);
                checkThisInputLetter.clear();
            }
        }
    }

    //set visibility of components
    public void visibility() {

        //not needed for actual game
        startButton.setVisible(false);
        exitButton.setVisible(false);
        messageForUser.setVisible(false);

        //set invisible for restart
        hangman1.setVisible(false);
        hangman2.setVisible(false);
        hangman3.setVisible(false);
        hangman4.setVisible(false);
        hangman5.setVisible(false);
        hangman6.setVisible(false);
        hangman7.setVisible(false);
        hangman8.setVisible(false);
        hangman9.setVisible(false);
        hangman10.setVisible(false);
        uLost.setVisible(false);
        uWin.setVisible(false);
        quitButton.setVisible(false);
        restartButton.setVisible(false);
        wordToCheck.setVisible(false);

        //sets components of 2. Page visible
        enterLetterHere.setVisible(true);
        currentWordOutput.setVisible(true);
        continueButton.setVisible(true);
        checkThisInputLetter.setVisible(true);
        hangman0.setVisible(true);

    }

    //components for winning the game
    public void youWonTheGame() {

            uWin.setVisible(true);
            quitButton.setVisible(true);
            restartButton.setVisible(true);
            restartButton.setText("Too easy, one more!");
            wordToCheck.setVisible(true);

            currentWordOutput.setVisible(false);
            enterLetterHere.setVisible(false);
            continueButton.setVisible(false);
            checkThisInputLetter.setVisible(false);
        }


    //components for losing the game
    public void youLostTheGame() {

        hangman10.setVisible(true);
        quitButton.setVisible(true);
        restartButton.setVisible(true);
        restartButton.setText("Again in Zombiemode!");
        uLost.setVisible(true);
        wordToCheck.setVisible(true);

        currentWordOutput.setVisible(false);
        enterLetterHere.setVisible(false);
        continueButton.setVisible(false);
        checkThisInputLetter.setVisible(false);
    }

    //convert the current word into nice GUI output
    public String currentWordForOutput(){
        return gameplay.currentWord = Arrays.toString(gameplay.lines).replace("[", " ")
                .replace("]", " ")
                .replace(",", " ");
    }

    //convert the current word into comparable output
    public boolean compareWords(){
        return Arrays.toString(gameplay.lines).replace("[", "")
                .replace("]", "")
                .replace(" ", "")
                .replace(",", "").equals(thisWord);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}
