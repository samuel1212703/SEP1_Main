package model;

import java.util.Calendar;

public class Date
{
  private Calendar date = Calendar.getInstance();
  private int dayOfWeek;
  private int week;

  public Date(int year, int week, int dayOfWeek){
    this.date.set(Calendar.YEAR, year);
    this.date.set(Calendar.WEEK_OF_YEAR, week);
    this.date.set(Calendar.DAY_OF_WEEK, dayOfWeek);
    this.dayOfWeek = dayOfWeek;
    this.week = week;
  }

  public int getWeek()
  {
    return week;
  }

  public int getDayOfWeek()
  {
    return dayOfWeek;
  }

  public Calendar getDate(){
    return date;
  }
}
