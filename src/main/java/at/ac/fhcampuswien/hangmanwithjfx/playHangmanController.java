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

    @FXML public Label wordToCheck;
    @FXML public Label enterLetterHere;
    @FXML public Label messageForUser;
    @FXML public ImageView hangman1;
    @FXML public ImageView hangman2;
    @FXML public ImageView hangman3;
    @FXML public Label duplicate;
    @FXML private Button startButton;
  //  @FXML AnchorPane anchorpane;
    @FXML private Button exitButton; //to give chance to end game
    //from gamepagecontroller
    @FXML public Button continueButton;
    @FXML public Label currentWordOutput;
    @FXML public TextField checkThisInputLetter;
    //@FXML public TextArea status;
    protected Gameplay gameplay = new Gameplay();
    public String thisWord = gameplay.wordToFind;
   // public static char[] lines;

    @FXML //lets user exit the game upfront
    protected void exitGame(ActionEvent a){
        Stage primaryStage = (Stage) exitButton.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    protected void pressToPlay(ActionEvent actionEvent) throws Exception{
        //gameplay = new Gameplay();
       // thisWord = gameplay.randomWord(); //get the random word for the game from gameplay
        //this.thisWord = gameplay.wordToFind;
        //lines = gameplay.printLines(thisWord);

        System.out.println(thisWord); //prints the random word to console
        System.out.println(Arrays.toString(gameplay.lines) + " pressToPlay"); //print out lines array for word

        wordToCheck.setText(thisWord);
        currentWordOutput.setText(Arrays.toString(gameplay.lines).replace("[", "")
                .replace("]", "")
                //.replace(" ", "")
                .replace(",", ""));

        Stage newStage = (Stage) startButton.getScene().getWindow();
        startButton.setVisible(false);
        exitButton.setVisible(false);
        messageForUser.setVisible(false);

        wordToCheck.setVisible(true);
        enterLetterHere.setVisible(true);
        currentWordOutput.setVisible(true);
        continueButton.setVisible(true);
        checkThisInputLetter.setVisible(true);
    }


    @FXML
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
                        hangman1.setVisible(true);
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
