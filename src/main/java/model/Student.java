package model;

public class Student {
    private int id;
    private String name;
    private String faculty;
    private StudentStatus status;
    private int yearLevel;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public int getYearLevel() {
        return yearLevel;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name + " | " + faculty + " | " + status + " | year=" + yearLevel;
    }

}
