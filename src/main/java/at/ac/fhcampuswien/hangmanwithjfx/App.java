package at.ac.fhcampuswien.hangmanwithjfx;

import at.ac.fhcampuswien.hangmanwithjfx.game.Gameplay;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application  {


    public static void main(String[] args){
        System.out.println("blubb");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/startHangman.fxml"));
            primaryStage.setTitle("This is HANGMAN!");
            primaryStage.setScene(new Scene(root, 845, 600));
            primaryStage.show();
        }
    }
