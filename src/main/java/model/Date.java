package model;

import java.util.Calendar;

public class Date
{
  private Calendar date = Calendar.getInstance();

  public Date(int year, int week, int day, int hour, int minute){
    this.date.set(Calendar.YEAR, year);
    this.date.set(Calendar.WEEK_OF_YEAR, week);
    this.date.set(Calendar.DAY_OF_MONTH, day);
    this.date.set(Calendar.HOUR_OF_DAY, hour);
    this.date.set(Calendar.MINUTE, minute);
    this.date.set(Calendar.SECOND, 0);
    this.date.set(Calendar.MILLISECOND, 0);
  }

  public Calendar getDate(){
    return date;
  }
}
