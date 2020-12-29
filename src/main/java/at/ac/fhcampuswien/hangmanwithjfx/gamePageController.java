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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import static java.lang.Character.toLowerCase;


public class gamePageController {

    //Variables of Game Logic
    public char letter;
    public String wordToFind;



    //Variables of Fields/Buttons usw
    @FXML
    public Button continueButton;

    @FXML
    public Label outputOfLines;

    public Gameplay gameplay;

    @FXML
    public TextField checkThisInputLetter;






    @FXML //annotation wieder, in fxml Datei mit onAction = "#methodenname"
    protected void pressToCheckLetter(ActionEvent actionEvent){


        System.out.println("Checked Letter: " + checkThisInputLetter.getText());
        this.letter =  toLowerCase(checkThisInputLetter.getCharacters().charAt(0));


        //System.out.println(letter);
        //makes new Windowsite open on Action : Check Button clicked
        Stage newStage = (Stage) continueButton.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gamePageHangman.fxml"));
            newStage.setTitle("This is still HANGMAN!");
            newStage.setScene(new Scene(root, 845, 600));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
