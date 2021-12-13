package model;

import java.util.ArrayList;

public class Team
{
  private ArrayList<Student> students = new ArrayList<>();
  private String teamName;

  public Team(String teamName, ArrayList<Student> students){
    this.students = students;
    this.teamName = teamName;
  }
  public String getTeamName(){
    return teamName;
  }

  public int getSize(){
    return students.size();
  }

  public ArrayList<Student> getStudents()
  {
    return students;
  }

}
