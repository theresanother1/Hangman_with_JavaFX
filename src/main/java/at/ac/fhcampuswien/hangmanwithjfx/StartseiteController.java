package at.ac.fhcampuswien.hangmanwithjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class StartseiteController {

    @FXML
    public Button startButton;

    @FXML //annotation wieder, in fxml Datei mit onAction = "#methodenname"
    protected void pressToPlay(ActionEvent actionEvent){

        //makes new Windowsite open on Action : Check Button clicked
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
