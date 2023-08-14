import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javafx.scene.control.ColorPicker;

public class MainScreenController {

    @FXML
    private Button addStudentButton;

    @FXML
    private Rectangle deskBox1;

    @FXML
    private Rectangle deskBox2;

    @FXML
    private Rectangle deskBox3;

    @FXML
    private Rectangle deskBox4;

    @FXML
    private Rectangle deskBox5;

    @FXML
    private Rectangle deskBox6;

    @FXML
    private Rectangle deskBox7;

    @FXML
    private Rectangle deskBox8;

    @FXML
    private Rectangle deskBox9;


    @FXML
    private Label errorLabel;

    @FXML
    private Label studentNameLabel1;

    @FXML
    private Label studentNameLabel2;

    @FXML
    private Label studentNameLabel3;

    @FXML
    private Label studentNameLabel4;

    @FXML
    private Label studentNameLabel5;

    @FXML
    private Label studentNameLabel6;

    @FXML
    private Label studentNameLabel7;

    @FXML
    private Label studentNameLabel8;

    @FXML
    private Label studentNameLabel9;


    @FXML
    private TextField studentNameField;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    public void initialize() {
        // Initialize the color picker with white color
        colorPicker.setValue(Color.WHITE);
        // Clear any previous error messages
        errorLabel.setText("");
    }

    @FXML
    public void onAddStudentClick(ActionEvent event) {
        // Retrieve the entered student name
        String studentName = studentNameField.getText();

        // Lists to store UI elements
        ArrayList<Label> studentNameLabels = new ArrayList<>();
        ArrayList<Rectangle> studentDeskBoxes = new ArrayList<>();

        // Sets to manage assigned desks, names, and colors
        Set<Color> assignedDesks = new HashSet<>();
        Set<Label> assignedNames = new HashSet<>();
        Set<Color> usedColors = new HashSet<>();

        // Add all student name label and desk box variables to respective lists
        studentNameLabels.add(studentNameLabel1);
        studentNameLabels.add(studentNameLabel2);
        studentNameLabels.add(studentNameLabel3);
        studentNameLabels.add(studentNameLabel4);
        studentNameLabels.add(studentNameLabel5);
        studentNameLabels.add(studentNameLabel6);
        studentNameLabels.add(studentNameLabel7);
        studentNameLabels.add(studentNameLabel8);
        studentNameLabels.add(studentNameLabel9);


        studentDeskBoxes.add(deskBox1);
        studentDeskBoxes.add(deskBox2);
        studentDeskBoxes.add(deskBox3);
        studentDeskBoxes.add(deskBox4);
        studentDeskBoxes.add(deskBox5);
        studentDeskBoxes.add(deskBox6);
        studentDeskBoxes.add(deskBox7);
        studentDeskBoxes.add(deskBox8);
        studentDeskBoxes.add(deskBox9);


        // Get the selected color from the color picker
        Color selectedColor = colorPicker.getValue();

        // Error handling for no color selected
        if (selectedColor == null) {
            errorLabel.setText("ERROR: You did not choose any color");
            return;
        }

        // Error handling for empty student name field
        if (studentNameField.getText().trim().isEmpty()) {
            errorLabel.setText("ERROR: Student name field cannot be blank");
            return;
        } else if (studentNameField.getText().trim().length() < 3) {
            errorLabel.setText("ERROR: Student name must be at least three characters");
            return;
        }

        // Check for duplicate student names
        for (int i = 0; i < studentNameLabels.size(); i++) {
            errorLabel.setText("");
            if (studentNameLabels.get(i).getText().equals(studentName)) {
                errorLabel.setText("ERROR: Student name '" + studentName + "' already exists.");
                return;
            }
        }

        for (int i = 0; i < studentNameLabels.size(); i++) {
            if(studentNameLabels.get(i).getText().isEmpty()){
                assignedNames.add(studentNameLabels.get(i));
                usedColors.add((Color)studentDeskBoxes.get(i).getFill());
            }else if(studentNameLabels.get(i).getText().equals(studentName)){
                return;
            }else{
                assignedDesks.add((Color)studentDeskBoxes.get(i).getFill());
                usedColors.add((Color)studentDeskBoxes.get(i).getFill());
            }
        }

        if(!assignedNames.isEmpty()){
            if(usedColors.contains(selectedColor)) {
                errorLabel.setText("ERROR: White can not be Chosen");
                return;
            }
            Random random = new Random();
            Label randomLabel = assignedNames.stream().skip(random.nextInt(assignedNames.size())).findFirst().orElse(null);
            if(randomLabel != null){
                randomLabel.setText(studentName);
                assignedNames.add(randomLabel);
            }
            int assignedLabelIndex = studentNameLabels.indexOf(randomLabel);
            Rectangle emptyDesk = studentDeskBoxes.get(assignedLabelIndex);
            emptyDesk.setFill(selectedColor);
            assignedDesks.add(selectedColor);
        }

        int numStudents = 0;
        for (int i = 0; i < studentNameLabels.size(); i++) {
            if (!studentNameLabels.get(i).getText().isEmpty()) {
                numStudents++;
            }
        }

        if (numStudents >= 9)
        {

            error.setText("The class is full!");
            error.setTextFill(Color.GREEN);
            pColor.setValue(Color.WHITE);
            return;
        }

    }
}
