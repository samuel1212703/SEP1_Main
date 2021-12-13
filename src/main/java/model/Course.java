package model;

import java.util.ArrayList;

public class Course
{
  private String courseName;
  private int ects;
  private String team;
  private ArrayList<Lecturer> lecturers = new ArrayList<>();
  private Team teamCourse;

  public Course(String courseName, int ects, String team, ArrayList<Lecturer> lecturers)
  {
    this.courseName = courseName;
    this.ects = ects;
    this.team = team;
    this.lecturers = lecturers;
  }

  public Course(){}

  public boolean isCourseTeam(String team, String course) {
    return this.courseName.equals(course) && this.team.equals(team);
  }

  public void setLecturers(ArrayList<Lecturer> lecturers)
  {
    this.lecturers = lecturers;
  }
  public void addLecturers(Lecturer lecturer)
  {
    this.lecturers.add(lecturer);
  }

  public String getTeamNames()
  {
    return team;
  }

  public void setTeam(Team team)
  {
    this.teamCourse = team;
  }

  public Team getTeam(){
    return teamCourse;
  }

  public ArrayList<Lecturer> getLecturers(){
   return lecturers;
  }

  public ArrayList<String> getLecturersAsString()
  {
    ArrayList<String> lecturers = new ArrayList<>();
    for (int i = 0; i < lecturers.size(); i++)
    {
      lecturers.add(this.lecturers.get(i).getInitials());
    }
    return lecturers;
  }

  public int getEcts()
  {
    return ects;
  }

  public String getCourseName()
  {
    return courseName;
  }

  public String toString ()
  {
    return "\n" + "Coursename: " + courseName + " ; ECTS: " + ects + " ; model.Team: " + team + " ; Lecturers: " + lecturers;
  }
}
