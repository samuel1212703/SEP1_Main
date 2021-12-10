package com.example.sep1_main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import parser.XmlJsonParser;

import java.io.File;
import java.lang.reflect.Array;
import java.util.*;

public class HelloController
{
    @FXML private TabPane tabPane;
    @FXML private BorderPane borderPane;
    @FXML private GridPane gridPane;

    @FXML private Button csvButton0;
    @FXML private Button csvButton1;
    @FXML private Button csvButton2;
    @FXML private TextField textField0;
    @FXML private TextField textField1;
    @FXML private TextField textField2;
    @FXML private TextField textField3;
    @FXML private TextField textField4;
    @FXML private ComboBox<String> comboBox0;
    @FXML private ComboBox<String> comboBox1;
    @FXML private ComboBox<String> comboBox2;
    @FXML private ComboBox<String> comboBox3;
    @FXML private Label menuLabel0;
    @FXML private Label menuLabel1;
    @FXML private Label menuLabel2;
    @FXML private TextField weekTextField;

    String[] rooms = {"C7.14", "C6.13", "C9.56"};
    String[] subjects = {"DMA", "RWD", "SDJ", "SEP"};
    String[] subjectColors = {"DDDDFF", "FFEDDD", "DFFFDD", "FFDDEF"};
    String[] teams = {"SW", "...", "..."};
    String[] lecturers = {"IOOD", "MIVI", "AHAN"};
    boolean[][] takenLessons = new boolean[5][4];
    int currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);

    public void initialize()
    {
        weekTextField.setText(currentWeek + "");
        weekTextField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    currentWeek = Integer.parseInt(weekTextField.getText());
                }
            }
        });

        comboBox0.getItems().addAll(teams);
        comboBox0.getSelectionModel().selectFirst();
        comboBox1.getItems().addAll(rooms);
        comboBox1.getSelectionModel().selectFirst();
        comboBox2.getItems().addAll(subjects);
        comboBox2.getSelectionModel().selectFirst();
        comboBox3.getItems().addAll(lecturers);
        comboBox3.getSelectionModel().selectFirst();

        int year = 2021; //Calendar.getInstance().get(Calendar.YEAR);
        // int week = 12; //Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        // int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        // int moduleIndex = 1;
        int week = currentWeek;

        textField0.setText("1");
        textField1.setText(year + "");
        textField2.setText(week + "");
        // textField3.setText(day + "");
        // textField4.setText(moduleIndex + "");

        csvButton0.setOnAction(event -> {
            Window stage = tabPane.getScene().getWindow();

            FileChooser csvFileChooser = new FileChooser();
            FileChooser.ExtensionFilter csvTextFilter = new FileChooser.ExtensionFilter(
                "csv files (*.csv)", "*.csv");
            csvFileChooser.getExtensionFilters().add(csvTextFilter);

            File csvfile = csvFileChooser.showOpenDialog(stage);
            if (csvfile != null)
            {
                menuLabel0.setText(csvfile.getPath());
            }
            else
            {
                menuLabel0.setText("Could not resolve file");
            }
        });

        csvButton1.setOnAction(event -> {
            Window stage = tabPane.getScene().getWindow();

            FileChooser csvFileChooser = new FileChooser();
            FileChooser.ExtensionFilter csvTextFilter = new FileChooser.ExtensionFilter(
                "csv files (*.csv)", "*.csv");
            csvFileChooser.getExtensionFilters().add(csvTextFilter);

            File csvfile = csvFileChooser.showOpenDialog(stage);
            if (csvfile != null)
            {
                menuLabel1.setText(csvfile.getPath());
            }
            else
            {
                menuLabel1.setText("Could not resolve file");
            }
        });

        csvButton2.setOnAction(event -> {
            Window stage = tabPane.getScene().getWindow();

            FileChooser csvFileChooser = new FileChooser();
            FileChooser.ExtensionFilter csvTextFilter = new FileChooser.ExtensionFilter(
                "csv files (*.csv)", "*.csv");
            csvFileChooser.getExtensionFilters().add(csvTextFilter);

            File csvfile = csvFileChooser.showOpenDialog(stage);
            if (csvfile != null)
            {
                menuLabel2.setText(csvfile.getPath());
            }
            else
            {
                menuLabel2.setText("Could not resolve file");
            }
        });
    }

    @FXML protected void weekForward()
    {
        currentWeek++;
        clearSchedule();
        drawSchedule();
        weekTextField.setText(currentWeek + "");
    }

    @FXML protected void weekBack()
    {
        currentWeek--;
        clearSchedule();
        drawSchedule();
        weekTextField.setText(currentWeek + "");
    }

    @FXML protected void setSideInformation1(String room, String subject,
        String lecturer, int year, int week, int day, int moduleIndex,
        int duration)
    {
        // Room, Subject and Lecturers
        int roomIndex = Arrays.asList(rooms).indexOf(room);
        int subjectsIndex = Arrays.asList(subjects).indexOf(subject);
        int lecturerIndex = Arrays.asList(lecturers).indexOf(lecturer);

        comboBox1.getSelectionModel().select(roomIndex);
        comboBox2.getSelectionModel().select(subjectsIndex);
        comboBox3.getSelectionModel().select(lecturerIndex);

        // Duration;
        textField0.setText(duration + "");

        // Start time
        textField1.setText(year + "");
        textField2.setText(week + "");
        textField3.setText(day + "");
        textField4.setText(moduleIndex + "");
    }

    @FXML private void setSideInformation(ActionEvent event)
    {
        Button button = (Button) event.getSource();
        String buttonID = button.getId();

        // Use buttonID to get data and send it to the setSideInformation function below
        System.out.println(buttonID);

        setSideInformation1("C7.14", "DMA", "IOOD", 2021, 12, 1, 1, 1);
    }

    @FXML private void createLesson()
    {
        String room = comboBox1.getSelectionModel().getSelectedItem();
        String subject = comboBox2.getSelectionModel().getSelectedItem();
        String lecturer = comboBox3.getSelectionModel().getSelectedItem();
        int duration = Integer.parseInt(textField0.getText());
        int year = Integer.parseInt(textField1.getText());
        int week = Integer.parseInt(textField2.getText());
        int day = Integer.parseInt(textField3.getText());
        int moduleIndex = Integer.parseInt(textField4.getText());

        int subjectsIndex = Arrays.asList(subjects).indexOf(subject);

        if (day - 1 >= 0 && day <= 5 && moduleIndex >= 1 && moduleIndex <= 4
            && !takenLessons[day - 1][moduleIndex - 1] && duration >= 1
            && duration <= 2 || (day >= 3 && duration == 2))
        {
            gridPane.setGridLinesVisible(
                false); // Jeg Hader JavaFX (1.3 ud af 10 stjerner)
            for (int i = 0; i < duration; i++)
            {
                Button lesson = new Button(subject);
                lesson.setPrefWidth(10000);
                lesson.setPrefHeight(10000);
                GridPane.setConstraints(lesson, duration, 0);
                lesson.setId("lesson" + (day) + "" + (moduleIndex + i) + "");
                lesson.setStyle(
                    "-fx-background-color: " + subjectColors[subjectsIndex]);

                lesson.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override public void handle(ActionEvent event)
                    {
                        setSideInformation(event);
                    }
                });

                takenLessons[day - 1][moduleIndex + i - 1] = true;
                gridPane.add(lesson, day - 1, (moduleIndex - 1 + i));
            }
            gridPane.setGridLinesVisible(true);
        }
    }

    @FXML public void cancelLesson(ActionEvent event)
    {
        int day = Integer.parseInt(textField3.getText());
        int moduleIndex = Integer.parseInt(textField4.getText());

        if (takenLessons[day - 1][moduleIndex - 1])
        {
            Button lesson = (Button) borderPane.lookup(
                "#lesson" + (day) + "" + (moduleIndex));
            takenLessons[day - 1][moduleIndex - 1] = false;
            gridPane.getChildren().remove(lesson);
        }

        int duration = Integer.parseInt(textField0.getText());

        if (takenLessons[day - 1][moduleIndex - 1 + 1] && duration == 2)
        {
            Button lesson = (Button) borderPane.lookup(
                "#lesson" + (day) + "" + (moduleIndex + 1));
            takenLessons[day - 1][moduleIndex - 1 + 1] = false;
            gridPane.getChildren().remove(lesson);
        }

        // Use button id to change backend data (Remove lesson-block)
    }

    @FXML protected void clearSchedule()
    {
        gridPane.getChildren().removeAll();
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                Button lesson = (Button) borderPane.lookup(
                    "#lesson" + (i) + "" + (j + 1));
                takenLessons[i][j] = false;
                gridPane.getChildren().remove(lesson);
            }
        }
    }

    @FXML protected void drawSchedule()
    {
        String[][] schedule = new String[5][4];
        schedule[0][0] = "DMA";
        schedule[4][3] = "SDJ";

        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if (!Objects.equals(schedule[i][j], null))
                {
                    int subjectsIndex = Arrays.asList(subjects)
                        .indexOf(schedule[i][j]);

                    Button lesson = new Button(schedule[i][j]);
                    lesson.setPrefWidth(10000);
                    lesson.setPrefHeight(10000);
                    GridPane.setConstraints(lesson, 1, 0);
                    lesson.setId("lesson" + (i) + "" + (j + 1) + "");
                    lesson.setStyle("-fx-background-color: "
                        + subjectColors[subjectsIndex]);

                    lesson.setOnAction(new EventHandler<ActionEvent>()
                    {
                        @Override public void handle(ActionEvent event)
                        {
                            setSideInformation(event);
                        }
                    });

                    takenLessons[i][j] = true;
                    gridPane.add(lesson, i, j);
                }
            }
        }
    }
}
