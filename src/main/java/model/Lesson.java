package model;

import java.util.Calendar;

public class Lesson
{
  private Date startTime;
  private Classroom classroom;
  private Course course;

  public Lesson(Course course, Date startTime, Classroom classroom)
  {
    this.course = course;
    this.startTime = startTime;
    this.classroom = classroom;
  }


 /* public Date getEndTime()
  {
    Date endTime = new Date(startTime.getDate().get(Calendar.YEAR),
        startTime.getDate().get(Calendar.MONTH),
        startTime.getDate().get(Calendar.DAY_OF_MONTH),
        startTime.getDate().get(Calendar.HOUR),
        startTime.getDate().get(Calendar.MINUTE));
    endTime.getDate().add(Calendar.MINUTE, duration);
    return endTime;
  }
*/
  public Date getDate()
  {
    return this.startTime;
  }

  public void setIdealClassroom()
  {
  }
}
