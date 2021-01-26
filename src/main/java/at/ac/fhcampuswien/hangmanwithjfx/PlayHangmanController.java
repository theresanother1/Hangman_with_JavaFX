package at.ac.fhcampuswien.hangmanwithjfx;

import at.ac.fhcampuswien.hangmanwithjfx.game.Gameplay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import static java.lang.Character.toLowerCase;

public class PlayHangmanController implements Initializable {

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

    //set invisible - Startlayout (Textfeld für Buchstaben der eingegeben wird.)
    @FXML public TextField checkThisInputLetter;

    //set visible - Startlayout
    @FXML private Button startButton;
    @FXML private Button exitButton;
    //set invisible - Startlayout
    @FXML public Button continueButton;
    @FXML public Button quitButton;
    @FXML public Button restartButton;

    //für Spiellogik
    protected Gameplay gameplay;
    public String thisWord;

    //Erstellt Objekt der Klasse Gameplay, wird in pressToPlay aufgerufen.
    protected void setNewGame(){
        this.gameplay = new Gameplay();
        this.thisWord = gameplay.wordToFind;
    }

    @FXML //Beendet das Spiel gleich am Anfang, wenn man möchte.
    protected void exitGame(ActionEvent a) {
        Stage primaryStage = (Stage) exitButton.getScene().getWindow();
        primaryStage.close();
    }

    @FXML //Beendet das Spiel nach Durchlauf.
    protected void quitGame(ActionEvent a) {
        Stage primaryStage = (Stage) quitButton.getScene().getWindow();
        primaryStage.close();
    }

    @FXML //Startet das Spiel.
    protected void pressToPlay(ActionEvent actionEvent) {

        //Initialisiert ein Objekt der Klasse Gameplay.
        setNewGame();

        //Ermöglicht Buchstabeneingabe über Enter.
        continueButton.setDefaultButton(true);

        //Übergibt das gesuchte Wort an das Label.
        wordToCheck.setText(thisWord);

        //Konvertiert Inhalt des aktuellen Wortes in lines Array in leserlichen Output für GUI.
        currentWordOutput.setText(Arrays.toString(gameplay.lines).replace("[", " ")
                .replace("]", " ")
                .replace(",", " "));

        //Setzt die Sichtbarkeit für die einzelnen GUI Komponenten.
        visibility();
    }


    @FXML //Spiel wird gespielt, Buchstabe überprüft.
    protected void pressToCheckLetter(ActionEvent actionEvent) {

        //Errormeldungen in jedem Durchlauf auf invisible setzen.
        duplicate.setVisible(false);
        errorNoLetter.setVisible(false);

        //Wenn nichts eingegeben wird, läuft das Spiel nicht weiter.
        if (checkThisInputLetter.getText().isEmpty() || checkThisInputLetter.getText() == null) {

            //Gibt Errormeldung für Spieler/in aus.
            errorNoLetter.setVisible(true);
        }

        //Ansonsten kann Buchstabenüberprüfung losgehen.
        else {

            //Zunächst überprüfen, ob eingegebener Buchstabe ein Duplikat ist.
            if (!gameplay.checkForDuplicates(checkThisInputLetter.getText())) {

                //Nun schauen, ob der Buchstabe im Wort ist.
                if (gameplay.checkLetter(checkThisInputLetter.getText().charAt(0), thisWord)) {

                    //Gibt aktuelles Wort in GUI aus mit Unterstrichen für fehlende Buchstaben.
                    currentWordOutput.setText(Arrays.toString(gameplay.lines).replace("[", " ")
                            .replace("]", " ")
                            .replace(",", " "));

                    //Überprüft, ob das Wort aus lines == thisWord.
                    if ((Arrays.toString(gameplay.lines).replace("[", "")
                            .replace("]", "")
                            .replace(" ", "")
                            .replace(",", "").equals(thisWord))) {
                        youWonTheGame();
                    }
                }
                //Wenn der Buchstabe nicht im Wort ist, wird else-Teil aufgerufen.
                //Je nach Anzahl der Errors wird ein neues Hangmanbild auf visible gesetzt.
                else {

                    //Setzt zu Errorcount zugehöriges Bild auf visible, bei Errorcount = 10 werden Aktionen in
                    //youLostTheGame gesetzt.
                    switch (gameplay.errorCount) {
                        case 1 -> hangman1.setVisible(true);
                        case 2 -> hangman2.setVisible(true);
                        case 3 -> hangman3.setVisible(true);
                        case 4 -> hangman4.setVisible(true);
                        case 5 -> hangman5.setVisible(true);
                        case 6 -> hangman6.setVisible(true);
                        case 7 -> hangman7.setVisible(true);
                        case 8 -> hangman8.setVisible(true);
                        case 9 -> hangman9.setVisible(true);
                        case 10 -> youLostTheGame();
                    }
                }
                //Löscht den zuletzt eingegebenen Input aus dem Textfeld.
                checkThisInputLetter.clear();
            }
            //Buchstabe war ein Duplikat, Buchstabenüberprüfung wird nicht durchgeführt,
            //Errormeldung duplicate wird ausgegeben.
            else {
                duplicate.setVisible(true);
                checkThisInputLetter.clear();
            }
        }
    }

    //Setzt die jeweiligen Komponenten auf Visible & Invisible.
    public void visibility() {

        //Setzt alles für Spielanfang nicht Notwendige invisible
        startButton.setVisible(false);
        exitButton.setVisible(false);
        messageForUser.setVisible(false);

        //Bilder/Buttons/Gifs/Labels für eventuellen Restart auf invisible setzen
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
        quitButton.setVisible(false);
        restartButton.setVisible(false);
        wordToCheck.setVisible(false);

        //Setzt alles für Anfang Gebrauchte auf visible.
        enterLetterHere.setVisible(true);
        currentWordOutput.setVisible(true);
        continueButton.setVisible(true);
        checkThisInputLetter.setVisible(true);
        hangman0.setVisible(true);

    }

    //Setzt jeweilige Komponenten visible/invisible, wenn man gewonnen hat.
    public void youWonTheGame() {

            //Aktiviert uWin Bild und Buttons für quitGame & restart
            uWin.setVisible(true);
            quitButton.setVisible(true);
            restartButton.setVisible(true);
            restartButton.setText("Too easy, one more!");
            wordToCheck.setVisible(true);

            //Setzt alles, was nicht mehr gesehen werden soll, auf invisible.
            currentWordOutput.setVisible(false);
            enterLetterHere.setVisible(false);
            continueButton.setVisible(false);
            checkThisInputLetter.setVisible(false);
        }


    //Setzt jeweilige Komponenten visible/invisible.
    public void youLostTheGame() {

        //Alles Benötigte auf visible setzen.
        hangman10.setVisible(true);
        quitButton.setVisible(true);
        restartButton.setVisible(true);
        restartButton.setText("Again in Zombiemode!");
        uLost.setVisible(true);
        wordToCheck.setVisible(true);

        //Nicht Benötigtes auf invisible setzen.
        currentWordOutput.setVisible(false);
        enterLetterHere.setVisible(false);
        continueButton.setVisible(false);
        checkThisInputLetter.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}
