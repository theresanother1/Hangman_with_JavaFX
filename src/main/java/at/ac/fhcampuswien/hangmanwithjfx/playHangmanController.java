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

    //set invisible - Startlayout
    @FXML public Label wordToCheck;
    @FXML public Label enterLetterHere;
    @FXML public Label duplicate;
    @FXML public Label currentWordOutput;
    @FXML public Label errorNoLetter;
    //set visible - Startlayout
    @FXML public Label messageForUser;

    //set invisible - Startlayout
    @FXML public ImageView hangman0;
    @FXML public ImageView hangman1;
    @FXML public ImageView hangman2;
    @FXML public ImageView hangman3;
    @FXML public ImageView hangman4;
    @FXML public ImageView hangman5;
    @FXML public ImageView hangman6;
    @FXML public ImageView hangman7;
    @FXML public ImageView hangman8;
    @FXML public ImageView hangman9;
    @FXML public ImageView hangman10;
    @FXML public ImageView uWin;
    @FXML public ImageView uLost;

    //set invisible - Startlayout
    @FXML public TextField checkThisInputLetter;


    //set visible - Startlayout
    @FXML private Button startButton;
    @FXML private Button exitButton; //to give chance to end game
    //set invisible - Startlayout
    @FXML public Button continueButton;
    @FXML public Button quit;
    @FXML public Button restartButton;

    //für Spiellogik
    protected Gameplay gameplay;
    public String thisWord;

    //erstellt Objekt der Klasse Gameplay, wird in pressToPlay aufgerufen
    protected void setNewGame(){
        this.gameplay = new Gameplay();
        this.thisWord = gameplay.wordToFind;
    }




    @FXML //Beendet das Spiel gleich am Anfang, wenn man möchte
    protected void exitGame(ActionEvent a){
        Stage primaryStage = (Stage) exitButton.getScene().getWindow();
        primaryStage.close();
    }

    @FXML //Beendet das Spiel nach Durchlauf
    protected void quitGame(ActionEvent a){
        Stage primaryStage = (Stage) quit.getScene().getWindow();
        primaryStage.close();
    }

    @FXML //startet das Spiel
    protected void pressToPlay(ActionEvent actionEvent) throws Exception{
        //System.out.println(thisWord); //prints the random word to console
        //System.out.println(Arrays.toString(gameplay.lines) + " pressToPlay"); //print out lines array for word

        //initialisiert ein Objekt der Klasse Gameplay
        setNewGame();

        //um zu überprüfen, ob Spiel funktioniert
        wordToCheck.setText(thisWord);

        //konvertiert Inhalt des aktuellen Wortes in lines Array in leserlichen Output für GUI
        currentWordOutput.setText(Arrays.toString(gameplay.lines).replace("[", " ")
                .replace("]", " ")
                .replace(",", " "));

        //setzt alles für Spielanfang nicht notwendig invisible
        startButton.setVisible(false);
        exitButton.setVisible(false);
        messageForUser.setVisible(false);
        hangman1.setVisible(false);
        hangman2.setVisible(false);
        hangman3.setVisible(false);
        hangman4.setVisible(false);
        hangman5.setVisible(false);
        hangman6.setVisible(false);
        hangman7.setVisible(false);
        hangman8.setVisible(false);
        hangman9.setVisible(false);
        hangman10.setVisible(false);
        uLost.setVisible(false);
        uWin.setVisible(false);
        quit.setVisible(false);
        restartButton.setVisible(false);

        //für Spiel selbst auf invisible setzen, nur für Testzwecke!!
        wordToCheck.setVisible(true);

        //setzt alles für Anfang gebrauchte auf visible
        enterLetterHere.setVisible(true);
        currentWordOutput.setVisible(true);
        continueButton.setVisible(true);
        checkThisInputLetter.setVisible(true);
        hangman0.setVisible(true);
    }


    @FXML //Spiel wird gespielt, Buchstabe überprüft
    protected void pressToCheckLetter(ActionEvent actionEvent) {
        //Errormeldungen auf invisible setzen
        duplicate.setVisible(false);
        errorNoLetter.setVisible(false);

        //wenn nichts eingegeben wird
        if(checkThisInputLetter.getText() == ""){
            //checkThisInputLetter.getText().concat(" ");
            errorNoLetter.setVisible(true);
        }

        //ansonsten kann Buchstabenüberprüfung losgehen
        else {
            //System.out.println("Checked Letter: " + checkThisInputLetter.getText());

            //zunächst überprüfen, ob eingegebener Buchstabe ein Duplikat ist
            if (!gameplay.checkForDuplicates(checkThisInputLetter.getText())) {
                //System.out.println("checked for duplicate");

                //nun schauen, ob der Buchstabe im Wort ist
                if (gameplay.checkLetter(checkThisInputLetter.getText().charAt(0), thisWord)) {
                    //System.out.println("checked if letter in word");

                    //gibt aktuelles Wort in GUI aus mit Unterstrichen für fehlende Buchstaben
                    currentWordOutput.setText(Arrays.toString(gameplay.lines).replace("[", " ")
                            .replace("]", " ")
                            .replace(",", " "));

                         //überprüft, ob das Wort aus lines == thisWord
                          if ((Arrays.toString(gameplay.lines).replace("[", "")
                            .replace("]", "")
                            .replace(" ", "")
                            .replace(",", "").equals(thisWord))) {
                             //System.out.println("you win!");

                             //aktiviert uWin Bild und Buttons für quitGame & restart
                             uWin.setVisible(true);
                             quit.setVisible(true);
                             restartButton.setVisible(true);

                             //setzt alles, was nicht mehr gesehen werden soll, auf invisible
                             currentWordOutput.setVisible(false);
                             enterLetterHere.setVisible(false);
                             continueButton.setVisible(false);
                             checkThisInputLetter.setVisible(false);
                    }
                }

                //je nach Anzahl der Errors wird ein neues Hangmanbild auf visible gesetzt
                else {
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
                        case 4:
                            hangman4.setVisible(true);
                            break;
                        case 5:
                            hangman5.setVisible(true);
                            break;
                        case 6:
                            hangman6.setVisible(true);
                            break;
                        case 7:
                            hangman7.setVisible(true);
                            break;
                        case 8:
                            hangman8.setVisible(true);
                            break;
                        case 9:
                            hangman9.setVisible(true);
                            break;

                        //verloren!
                        case 10:
                            //alles benötigte auf visible setzen
                            hangman10.setVisible(true);
                            quit.setVisible(true);
                            restartButton.setVisible(true);
                            uLost.setVisible(true);

                            //nicht benötigtes auf invisible setzen
                            currentWordOutput.setVisible(false);
                            enterLetterHere.setVisible(false);
                            continueButton.setVisible(false);
                            checkThisInputLetter.setVisible(false);
                            //System.out.println("you lost!");
                            break;
                    }
                   // System.out.println("letter was not in word");
                   // System.out.println(gameplay.errorCount);
                }
                //löscht den letzten Buchstaben aus dem Textfeld für neuen Input
                checkThisInputLetter.clear();
            }
            //Buchstabe war ein Duplikat, Buchstabenüberprüfung wird nicht durchgeführt, Errormeldung ausgeben
            else {
                duplicate.setVisible(true);
                checkThisInputLetter.clear();
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
