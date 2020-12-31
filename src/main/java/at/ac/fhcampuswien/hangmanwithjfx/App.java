package at.ac.fhcampuswien.hangmanwithjfx;

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
        //initialisiert 1. Seite
        Parent root = FXMLLoader.load(getClass().getResource("/playHangman.fxml"));
        primaryStage.setTitle("This is HANGMAN!");
        primaryStage.setScene(new Scene(root, 845, 600));

        //setzt Fenster auf fixe Größe
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    }
