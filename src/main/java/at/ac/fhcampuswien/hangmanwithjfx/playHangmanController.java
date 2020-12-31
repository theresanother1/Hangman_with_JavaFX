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

    @FXML public Button continueButton;

    @FXML public TextField checkThisInputLetter;

    //set visible - Startlayout
    @FXML public Label messageForUser;
    @FXML private Button startButton;
    @FXML private Button exitButton; //to give chance to end game
    @FXML public Button quit;

    //for accessing Game Logic
    protected Gameplay gameplay = new Gameplay();
    public String thisWord = gameplay.wordToFind;

    @FXML //lets user exit the game upfront
    protected void exitGame(ActionEvent a){
        Stage primaryStage = (Stage) exitButton.getScene().getWindow();
        primaryStage.close();
    }

    @FXML //lets user exit the game upfront
    protected void quitGame(ActionEvent a){
        Stage primaryStage = (Stage) quit.getScene().getWindow();
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
        hangman0.setVisible(true);
    }


    @FXML //continues the game, checks letter
    //win or lose fehlt noch - derzeit ins Unendliche spielbar
    protected void pressToCheckLetter(ActionEvent actionEvent) {
        duplicate.setVisible(false);
        if(checkThisInputLetter.getText() == ""){
            //checkThisInputLetter.getText().concat(" ");
        }
        else {
            System.out.println("Checked Letter: " + checkThisInputLetter.getText());
            if (!gameplay.checkForDuplicates(checkThisInputLetter.getText())) {
                System.out.println("checked for duplicate");
                if (gameplay.checkLetter(checkThisInputLetter.getText().charAt(0), thisWord)) {
                    System.out.println("checked if letter in word");
                    currentWordOutput.setText(Arrays.toString(gameplay.lines).replace("[", " ")
                            .replace("]", " ")
                            .replace(",", " "));
                    if ((Arrays.toString(gameplay.lines).replace("[", "")
                            .replace("]", "")
                            .replace(" ", "")
                            .replace(",", "").equals(thisWord))) {
                        System.out.println("you win!");
                        uWin.setVisible(true);
                        //startButton.setVisible(true);
                        //exitButton.setVisible(true);
                        quit.setVisible(true);
                        currentWordOutput.setVisible(false);
                        enterLetterHere.setVisible(false);
                        continueButton.setVisible(false);
                        checkThisInputLetter.setVisible(false);
                        hangman0.setVisible(false);


                    }
                } else {
                    int errorCount = gameplay.errorCount;
                    switch (gameplay.errorCount) {
                        case 1:
                            hangman1.setVisible(true);
                            break;
                        case 2:
                            hangman2.setVisible(true);
                            break;
                        case 3:
                            hangman3.setVisible(true);
                            break;
                        case 4:
                            hangman4.setVisible(true);
                            break;
                        case 5:
                            hangman5.setVisible(true);
                            break;
                        case 6:
                            hangman6.setVisible(true);
                            break;
                        case 7:
                            hangman7.setVisible(true);
                            break;
                        case 8:
                            hangman8.setVisible(true);
                            break;
                        case 9:
                            hangman9.setVisible(true);
                            break;
                        case 10:
                            hangman10.setVisible(true);
                            // auf you lose gehen
                            System.out.println("you lost!");
                            quit.setVisible(true);
                            currentWordOutput.setVisible(false);
                            enterLetterHere.setVisible(false);
                            continueButton.setVisible(false);
                            checkThisInputLetter.setVisible(false);
                            hangman0.setVisible(false);
                            uLost.setVisible(true);
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
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
