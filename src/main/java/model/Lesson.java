package model;

import java.util.Calendar;

public class Lesson
{
  private Date startTime;
  private int duration;
  private String classroom;
  private Course course;

  public Lesson(Course course, Date startTime)
  {
    this.course = course;
    this.startTime = startTime;
    this.duration = duration;
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
