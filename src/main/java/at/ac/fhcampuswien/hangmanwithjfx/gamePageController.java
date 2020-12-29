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
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import static java.lang.Character.toLowerCase;


public class gamePageController extends StartseiteController implements Initializable {

    //Variables of Fields/Buttons usw
    @FXML
    public Button continueButton;
    @FXML
    public Label currentWordOutput;
    @FXML
    public TextField checkThisInputLetter;
    @FXML
    public TextArea status;




    @FXML
    protected void pressToCheckLetter(ActionEvent actionEvent){
        System.out.println("Checked Letter: " + checkThisInputLetter.getText());
        //gameplay.letter =  toLowerCase(checkThisInputLetter.getCharacters().charAt(0));
        System.out.println(lines);
        //initialize();
        //makes new Windowsite open on Action : Check Button clicked
        if (!gameplay.checkForDuplicates(checkThisInputLetter.getText())){
            if (gameplay.checkLetter(gameplay.letter, thisWord)){
               //no output yet
                status.setText("Oooof, good guess.");
            } else {
                //no output yet
                status.setText("Oops! Only" + (gameplay.MAXERRORS-gameplay.errorCount) +" Lives left");
            }
        } else {
            //no output yet
            status.setText("This was a duplicate!");
        }

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

    @FXML //text is not printed in output yet
    private void initialize(){currentWordOutput.setText(thisWord);}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
