package model;

public class Classroom
{

  private String name;
  private int capacity;

  public Classroom(String name, int capacity)
  {
    this.name = name;
    this.capacity = capacity;
  }

  public Classroom(){}

  public String getName()
  {
    return name;
  }

  public int getCapacity()
  {
    return capacity;
  }

}
