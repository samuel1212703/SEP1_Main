package model;


public class Student
{

  private String fullName;
  private String firstName;
  private String lastName;
  private String email;
  private String username;

  public Student(String fullName, String firstName, String lastName,
      String username, String email)
  {
    this.fullName = fullName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
  }

  public String getUsername()
  {
    return username;
  }

  public String getEmail()
  {
    return email;
  }

  public String getName()
  {
    return fullName;
  }

  public String toString()
  {
    return String.format("Full name: %s ; First name: %s ; Last name: %s ; Username: %s ; Email: %s",
        fullName, firstName, lastName, username, email);
  }

  public Student copy(){
    Student copy = new Student(fullName, firstName, lastName, username, email);
    return copy;
  }

}