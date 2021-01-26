package at.ac.fhcampuswien.hangmanwithjfx;

import at.ac.fhcampuswien.hangmanwithjfx.game.Gameplay;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class App extends Application  {


    public static void main(String[] args) throws IOException {
        System.out.println("blubb");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Initialisiert 1. Seite.
        Parent root = FXMLLoader.load(getClass().getResource("/playHangman.fxml"));
        primaryStage.setTitle("This is HANGMAN!");
        primaryStage.setScene(new Scene(root, 845, 600));

        //Setzt Fenster auf fixe Größe.
        primaryStage.setResizable(false);
        primaryStage.show();
    }



    }
