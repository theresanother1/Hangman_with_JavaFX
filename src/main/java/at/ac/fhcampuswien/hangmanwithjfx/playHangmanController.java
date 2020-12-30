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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import static java.lang.Character.toLowerCase;

public class playHangmanController implements Initializable{

    @FXML public Label wordToCheck;
    @FXML public Label enterLetterHere;
    @FXML public Label messageForUser;
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
    protected void pressToCheckLetter(ActionEvent actionEvent){
        System.out.println("Checked Letter: " + checkThisInputLetter.getText());
        if (!gameplay.checkForDuplicates(checkThisInputLetter.getText())){
            System.out.println("Not a duplicate");
        }
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
