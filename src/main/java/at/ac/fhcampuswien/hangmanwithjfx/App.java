package at.ac.fhcampuswien.hangmanwithjfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args){
        System.out.println("blubb");
        launch(args);
        /*Gameplay game1 = new Gameplay();

        //Test reset
        game1.errorCount = 1;
        System.out.println(game1.errorCount);
        game1.reset();
        System.out.println(game1.errorCount);
        System.out.println(game1.allLetters);


        //Test randomWord
        String testWord = game1.randomWord();
        System.out.println(testWord);

        //mit boolean gemacht, damit die winOrLoose zum Schluss nicht zweimal ausgeführt wird.
        boolean playing = true;

        game1.printLines(testWord);
        do {
            game1.userInput();
            game1.checkForDuplicates();
            game1.checkLetter();
            playing = game1.winOrLose(testWord);
        }while (playing);

         */
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/starthangman.fxml"));
            primaryStage.setTitle("This is HANGMAN!");
            primaryStage.setScene(new Scene(root, 845, 600));
            primaryStage.show();
        }
    }
