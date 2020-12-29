package at.ac.fhcampuswien.hangmanwithjfx;

import at.ac.fhcampuswien.hangmanwithjfx.game.Gameplay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class StartseiteController {

    @FXML
    public Button startButton;

    public Gameplay gameplay;

    @FXML //annotation wieder, in fxml Datei mit onAction = "#methodenname"
    protected void pressToPlay(ActionEvent actionEvent){
        gameplay = new Gameplay();
        Stage newStage = (Stage) startButton.getScene().getWindow();
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
