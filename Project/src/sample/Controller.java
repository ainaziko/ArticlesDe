package sample;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label art;

    @FXML
    private Label label;

    @FXML
    private Button dieButton;

    @FXML
    private Button derButton;

    @FXML
    private Button dasButton;

    @FXML
    private Button nextButton;

    @FXML
    private Label meaning;

    @FXML
    private Label plural;

    @FXML
    private ProgressBar progress;

    @FXML
    private Label emptyPlace;

    @FXML
    private Label finishLabel;

    @FXML
    private Text maxScore;

    @FXML
    void der(ActionEvent event) {

    }

    static double i = 0;
    static int counter = 0;
    static int max = 0;
    static Stack<String> stack = new Stack<String>();
    String curr;
    Queue<String> toRepeat = new LinkedList<String>();


    @FXML
    void initialize() {
        finishLabel.setVisible(false);
        emptyPlace.setVisible(false);
        derButton.setVisible(false);
        dieButton.setVisible(false);
        dasButton.setVisible(false);
        progress.setVisible(false);
        maxScore.setVisible(false);
        derButton.setOnAction(e -> der());
        dieButton.setOnAction(e -> die());
        dasButton.setOnAction(e -> das());
        nextButton.setOnAction(e -> update());
    }

    // updating and getting the next word
    public void update() {
        nextButton.setLayoutY(280);
        //icon.setImage(null);
        emptyPlace.setVisible(true);
        progress.setVisible(true);
        nextButton.setText("Next");
        nextButton.setVisible(false);
        dasButton.setVisible(true);
        derButton.setVisible(true);
        dieButton.setVisible(true);
        maxScore.setVisible(true);

        if(toRepeat.size() == 2 ){
            curr = toRepeat.poll();
        }else{
            curr = getRundomWord();
        }

        int stopEnglish = curr.indexOf("–");
        int stopGerman = curr.indexOf("~");

        meaning.setText(curr.substring(0, stopEnglish));
        label.setText(curr.substring(stopEnglish + 6, stopGerman));
        art.setText(curr.substring(stopEnglish + 2, stopEnglish + 5));
        plural.setText(curr.substring(stopGerman, curr.length()));
        plural.setVisible(false);
        art.setVisible(false);
        meaning.setVisible(false);
    }



    // function for the button "der"
    public void der() {
        emptyPlace.setVisible(false);
        String der = "Der";
        String answer = art.getText();
        art.setVisible(true);
        meaning.setVisible(true);
        plural.setVisible(true);
        dasButton.setVisible(false);
        derButton.setVisible(false);
        dieButton.setVisible(false);
        nextButton.setVisible(true);
        if (!der.equals(answer)) { // checking if the user's answer is true or false
            art.setTextFill(javafx.scene.paint.Color.RED);
            toRepeat.add(curr);
            resetScore();
        } else {
            if (i >= 1) {
                art.setVisible(false);
                derButton.setVisible(false);
                dieButton.setVisible(false);
                dasButton.setVisible(false);
                nextButton.setVisible(false);
                label.setVisible(false);
                meaning.setVisible(false);
                plural.setVisible(false);
                progress.setVisible(false);
                finishLabel.setVisible(true);
            } else {
                i += 0.001;
                counter++;
                progress.setProgress(i);
                setMaxScore();
                art.setTextFill(javafx.scene.paint.Color.GREEN);
            }
        }

    }

    // funtion for the button "die"
    public void die() {
        emptyPlace.setVisible(false);
        String die = "Die";
        String answer = art.getText();
        art.setVisible(true);
        meaning.setVisible(true);
        plural.setVisible(true);
        dasButton.setVisible(false);
        derButton.setVisible(false);
        dieButton.setVisible(false);
        nextButton.setVisible(true);
        if (!die.equals(answer)) {
            art.setTextFill(javafx.scene.paint.Color.RED);
            toRepeat.add(curr);
            resetScore();
        } else {
            if (i >= 1) {
                art.setVisible(false);
                derButton.setVisible(false);
                dieButton.setVisible(false);
                dasButton.setVisible(false);
                nextButton.setVisible(false);
                label.setVisible(false);
                meaning.setVisible(false);
                plural.setVisible(false);
                progress.setVisible(false);
                finishLabel.setVisible(true);
            } else {
                i += 0.001;
                progress.setProgress(i);
                counter++;
                setMaxScore();
                art.setTextFill(javafx.scene.paint.Color.GREEN);
            }
        }

    }

    // function for button 'das'
    public void das() {
        emptyPlace.setVisible(false);
        String d = "Das";
        String answer = art.getText();
        art.setVisible(true);
        meaning.setVisible(true);
        plural.setVisible(true);
        dasButton.setVisible(false);
        derButton.setVisible(false);
        dieButton.setVisible(false);
        nextButton.setVisible(true);
        if (!d.equals(answer)) {
            art.setTextFill(javafx.scene.paint.Color.RED);
            toRepeat.add(curr);
            resetScore();
        } else {
            if (i >= 1) {
                art.setVisible(false);
                derButton.setVisible(false);
                dieButton.setVisible(false);
                dasButton.setVisible(false);
                nextButton.setVisible(false);
                label.setVisible(false);
                meaning.setVisible(false);
                plural.setVisible(false);
                progress.setVisible(false);
                finishLabel.setVisible(true);
            } else {
                i += 0.001;
                progress.setProgress(i);
                counter++;
                setMaxScore();
                art.setTextFill(javafx.scene.paint.Color.GREEN);
            }
        }

    }


    //Getting a random word
    public String getRundomWord() {
        ArrayList listOfWords = new ArrayList();
        try {
            File myObj = new File("derdiedas.txt"); //reading a file
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                listOfWords.add(data);// adding every word to list
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //getting a random word from a list
        Random random = new Random();
        String word = (listOfWords.get(random.nextInt(listOfWords.size())) + " ");
        return word;
    }

    private int setMaxScore(){
        if(counter >= max){
            max = counter;
        }
        maxScore.setText("Max score: " + max);
        return max;
    }

    private void resetScore(){
        counter = 0;
    }
}