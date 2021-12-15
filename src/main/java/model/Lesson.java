package model;

import java.util.Calendar;

public class Lesson
{
  private Date dateLesson;
  private Classroom classroom;
  private Course course;
  private int moduleIndex;

  public Lesson(Course course, Date date, Classroom classroom, int moduleIndex)
  {
    this.course = course;
    this.dateLesson = date;
    this.classroom = classroom;
    this.moduleIndex = moduleIndex;
  }

  public Date getDateLesson()
  {
    return dateLesson;
  }

  public int getModuleIndex()
  {
    return moduleIndex;
  }

  public Classroom getClassroom()
  {
    return classroom;
  }

  public Course getCourse()
  {
    return course;
  }
}
