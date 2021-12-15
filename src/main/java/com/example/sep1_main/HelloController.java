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
import model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class HelloController
{
    @FXML private TabPane tabPane;
    @FXML private BorderPane borderPane;
    @FXML private GridPane gridPane;

    @FXML private Button csvButton0;
    @FXML private Button csvButton1;
    @FXML private Button csvButton2;
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

    Schedule schedule = new Schedule();
    ArrayList<Team> teams = new ArrayList<>();
    ArrayList<Classroom> classrooms = new ArrayList<>();
    ArrayList<String> allLecturers = new ArrayList<>();
    ArrayList<Course> courses = new ArrayList<>();
    List<File> teamFiles;
    List<File> classroomFiles;
    List<File> coursesFiles;
    String line = "";
    final String splitBy = ";";

    ArrayList<String> classroomsArrayList = new ArrayList<>();
    ArrayList<String> teamNames = new ArrayList<>();
    String[] subjects = {"DMA", "RWD", "SDJ", "SEP"};
    String[] subjectColors = {"DDDDFF", "FFEDDD", "DFFFDD", "FFDDEF"};
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

        int year = 2021; //Calendar.getInstance().get(Calendar.YEAR);
        // int week = 12; //Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        // int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        // int moduleIndex = 1;
        int week = currentWeek;

        textField1.setText(year + "");
        textField2.setText(week + "");
        // textField3.setText(day + "");
        // textField4.setText(moduleIndex + "");

        csvButton0.setOnAction(event -> {
            Window stage = tabPane.getScene().getWindow();
            FileChooser openMultipleTeams = new FileChooser();
            openMultipleTeams.setTitle("Open Multiple Files Dialog");
            FileChooser.ExtensionFilter csvTextFilter = new FileChooser.ExtensionFilter(
                "csv files (*.csv)", "*.csv");
            openMultipleTeams.getExtensionFilters().add(csvTextFilter);
            teamFiles = openMultipleTeams.showOpenMultipleDialog(stage);
            for (File file : teamFiles)
            {
                if (file == null)
                {
                    menuLabel0.setText("Could not resolve file");
                }
                String fileName = file.getName();
                String[] fileNameArray = fileName.split("\\.");
                String fileNameFinal = fileNameArray[0];
                try
                {
                    BufferedReader reader = new BufferedReader(
                        new FileReader(file));
                    ArrayList<Student> students = new ArrayList<>();
                    while ((line = reader.readLine()) != null)
                    {
                        String[] student = line.split(splitBy);
                        students.add(
                            new Student(student[0], student[1], student[2],
                                student[3], student[4]));
                    }
                    students.remove(0);
                    teams.add(new Team(fileNameFinal, students));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            System.out.println(teams.get(0).getTeamName() + " , " + teams.get(0)
                .getStudents());
            System.out.println(teams.get(1).getTeamName() + " , " + teams.get(1)
                .getStudents());
            for (Team team : teams)
            {
                teamNames.add(team.getTeamName());
            }
            comboBox0.getItems().addAll(teamNames);
            comboBox0.getSelectionModel().selectFirst();
        });

        csvButton1.setOnAction(event -> {
            Window stage = tabPane.getScene().getWindow();
            FileChooser csvFileChooser = new FileChooser();
            FileChooser.ExtensionFilter csvTextFilter = new FileChooser.ExtensionFilter(
                "csv files (*.csv)", "*.csv");
            csvFileChooser.getExtensionFilters().add(csvTextFilter);

            File file = csvFileChooser.showOpenDialog(stage);
            if (file == null)
            {
                menuLabel1.setText("Could not resolve file");
            }
            else
            {
                menuLabel1.setText(file.getPath());
                try
                {
                    BufferedReader reader = new BufferedReader(
                        new FileReader(file));
                    while ((line = reader.readLine()) != null)
                    {
                        String[] classroom = line.split(splitBy);
                        classrooms.add(new Classroom(classroom[0],
                            Integer.parseInt(classroom[1])));
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                System.out.println(classrooms);
                for (Classroom classroom : classrooms)
                {
                    classroomsArrayList.add(classroom.getName());
                }
            }
            comboBox1.getItems().addAll(classroomsArrayList);
            comboBox1.getSelectionModel().selectFirst();
        });

        csvButton2.setOnAction(event -> {
            Window stage = tabPane.getScene().getWindow();

            FileChooser csvFileChooser = new FileChooser();
            FileChooser.ExtensionFilter csvTextFilter = new FileChooser.ExtensionFilter(
                "csv files (*.csv)", "*.csv");
            csvFileChooser.getExtensionFilters().add(csvTextFilter);

            File file = csvFileChooser.showOpenDialog(stage);
            if (file == null)
            {
                menuLabel2.setText("Could not resolve file");
            }
            else
            {
                menuLabel2.setText(file.getPath());
                try
                {
                    BufferedReader reader = new BufferedReader(
                        new FileReader(file));
                    while ((line = reader.readLine()) != null)
                    {
                        String[] courseLine = line.split(splitBy);
                        if (!(allLecturers.contains(courseLine[3])))
                        {
                            allLecturers.add(courseLine[3]);
                        }
                        Course temp = null;
                        boolean courseTeam = false;
                        for (Course c : courses)
                        {
                            courseTeam = c.isCourseTeam(courseLine[1],
                                courseLine[2]);
                            if (courseTeam)
                            {
                                temp = c;
                                break;
                            }
                        }
                        if (temp != null)
                        {
                            temp.getLecturers()
                                .add(new Lecturer(courseLine[3]));
                        }
                        else
                        {
                            ArrayList<Lecturer> lecturersCourse = new ArrayList<>();
                            lecturersCourse.add(new Lecturer(courseLine[3]));
                            courses.add(new Course(courseLine[2],
                                Integer.parseInt(courseLine[4]), courseLine[1],
                                lecturersCourse));
                        }
                    }
                    for (Course c : courses)
                    {
                        for (Team team : teams)
                        {
                            if (team.getTeamName().equals(c.getTeamNames()))
                            {
                                c.setTeam(team);
                            }
                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                System.out.println(courses.get(0).getCourseName());
            }
            comboBox3.getItems().addAll(allLecturers);
            comboBox3.getSelectionModel().selectFirst();
        });

        comboBox2.getItems().addAll(subjects);
        comboBox2.getSelectionModel().selectFirst();

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
        String lecturer, int year, int week, int day, int moduleIndex)
    {
        // Room, Subject and Lecturers
        int roomIndex = classroomsArrayList.indexOf(room);
        int subjectsIndex = Arrays.asList(subjects).indexOf(subject);
        int lecturerIndex = allLecturers.indexOf(lecturer);

        comboBox1.getSelectionModel().select(roomIndex);
        comboBox2.getSelectionModel().select(subjectsIndex);
        comboBox3.getSelectionModel().select(lecturerIndex);

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

        setSideInformation1("C7.14", "DMA", "IOOD", 2021, 12, 1, 1);
    }

    @FXML private void createLesson()
    {
        String room = comboBox1.getSelectionModel().getSelectedItem();
        String subject = comboBox2.getSelectionModel().getSelectedItem();
        String lecturer = comboBox3.getSelectionModel().getSelectedItem();
        Classroom currentClassroom = new Classroom();
        int year = Integer.parseInt(textField1.getText());
        int week = Integer.parseInt(textField2.getText());
        int day = Integer.parseInt(textField3.getText());
        int hour = 0;
        int minute = 0;
        int moduleIndex = Integer.parseInt(textField4.getText());
        String team = comboBox0.getSelectionModel().getSelectedItem();
        if (moduleIndex == 1)
        {
            hour = 8;
            minute = 20;
        }
        if (moduleIndex == 2)
        {
            hour = 10;
            minute = 5;
        }
        if (moduleIndex == 3)
        {
            hour = 12;
            minute = 45;
        }
        if (moduleIndex == 4)
        {
            hour = 14;
            minute = 20;
        }
        model.Date date = new model.Date(year, week, day, hour, minute);

        for (Classroom classroom : classrooms)
        {
            if (classroom.getName().equals(room))
            {
                currentClassroom = classroom;
            }
        }

        //create lesson object
        Course currentCourse = new Course();
        for (Course course : courses)
        {
            if (course.getTeam().getTeamName().equals(team)
                && course.getCourseName().equals(subject)
                && course.getLecturers().equals(lecturer))
            {
                currentCourse = course;
            }
        }
        schedule.getLessons()
            .add(new Lesson(currentCourse, date, currentClassroom));
        System.out.println(schedule.getLessons());
        int subjectsIndex = Arrays.asList(subjects).indexOf(subject);

        if (day - 1 >= 0 && day <= 5 && moduleIndex >= 1 && moduleIndex <= 4
            && !takenLessons[day - 1][moduleIndex - 1])
        {
            gridPane.setGridLinesVisible(false);
            Button lesson = new Button(subject);
            lesson.setPrefWidth(10000);
            lesson.setPrefHeight(10000);
            GridPane.setConstraints(lesson, 1, 0);
            lesson.setId("lesson" + (day) + "" + (moduleIndex) + "");
            lesson.setStyle(
                "-fx-background-color: " + subjectColors[subjectsIndex]);

            lesson.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override public void handle(ActionEvent event)
                {
                    setSideInformation(event);
                }
            });

            takenLessons[day - 1][moduleIndex - 1] = true;
            gridPane.add(lesson, day - 1, (moduleIndex - 1));
        }
        gridPane.setGridLinesVisible(true);
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

        if (takenLessons[day - 1][moduleIndex - 1 + 1])
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
