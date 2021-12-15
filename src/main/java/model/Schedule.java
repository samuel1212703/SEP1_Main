package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Schedule
{
  private ArrayList<Lesson> lessons = new ArrayList<>();

  public void Schedule(){}

  public void setLessons(ArrayList<Lesson> lessons){
    this.lessons = lessons;
  }
  public ArrayList<Lesson> getLessons(){
    return lessons;
  }

}
