package at.ac.fhcampuswien.hangmanwithjfx;

import at.ac.fhcampuswien.hangmanwithjfx.game.Gameplay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.chrono.IsoEra;
import java.util.Arrays;
import java.util.ResourceBundle;

import static java.lang.Character.toLowerCase;

public class playHangmanController implements Initializable{

    //set invisible in the beginning
    @FXML public Label wordToCheck;
    @FXML public Label enterLetterHere;
    @FXML public Label duplicate;
    @FXML public Label currentWordOutput;

    @FXML public ImageView hangman1;
    @FXML public ImageView hangman2;
    @FXML public ImageView hangman3;
    @FXML public ImageView hangman10;
    @FXML public ImageView hangmanTest;

    @FXML public Button continueButton;

    @FXML public TextField checkThisInputLetter;

    //set visible - Startlayout
    @FXML public Label messageForUser;
    @FXML private Button startButton;
    @FXML private Button exitButton; //to give chance to end game

    //for accessing Game Logic
    protected Gameplay gameplay = new Gameplay();
    public String thisWord = gameplay.wordToFind;

    @FXML //lets user exit the game upfront
    protected void exitGame(ActionEvent a){
        Stage primaryStage = (Stage) exitButton.getScene().getWindow();
        primaryStage.close();
    }

    @FXML //launches the game
    protected void pressToPlay(ActionEvent actionEvent) throws Exception{

        System.out.println(thisWord); //prints the random word to console
        System.out.println(Arrays.toString(gameplay.lines) + " pressToPlay"); //print out lines array for word

        wordToCheck.setText(thisWord);
        currentWordOutput.setText(Arrays.toString(gameplay.lines).replace("[", " ")
                .replace("]", " ")
                .replace(",", " "));

        //Stage newStage = (Stage) startButton.getScene().getWindow();
        startButton.setVisible(false);
        exitButton.setVisible(false);
        messageForUser.setVisible(false);

       // wordToCheck.setVisible(true);
        enterLetterHere.setVisible(true);
        currentWordOutput.setVisible(true);
        continueButton.setVisible(true);
        checkThisInputLetter.setVisible(true);
    }


    @FXML //continues the game, checks letter
    //win or lose fehlt noch - derzeit ins Unendliche spielbar
    protected void pressToCheckLetter(ActionEvent actionEvent) {
        duplicate.setVisible(false);
        System.out.println("Checked Letter: " + checkThisInputLetter.getText());
        if (!gameplay.checkForDuplicates(checkThisInputLetter.getText())) {
            System.out.println("checked for duplicate");
            if (gameplay.checkLetter(checkThisInputLetter.getText().charAt(0), thisWord)) {
                System.out.println("checked if letter in word");
                currentWordOutput.setText(Arrays.toString(gameplay.lines).replace("[", "")
                        .replace("]", "")
                        //.replace(" ", "")
                        .replace(",", ""));
            } else {
                int errorCount = gameplay.errorCount;
                switch (gameplay.errorCount) {
                    case 1:
                        hangmanTest.setVisible(true);
                        //hangman10.setVisible(true);
                        //hangman1.setVisible(true);
                        break;
                    case 2:
                        hangman2.setVisible(true);
                        break;
                    case 3:
                        hangman3.setVisible(true);
                        break;
                }
                System.out.println("letter was not in word");
                System.out.println(gameplay.errorCount);
            }
            checkThisInputLetter.clear();
        } else {
            duplicate.setVisible(true);
            checkThisInputLetter.clear();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
