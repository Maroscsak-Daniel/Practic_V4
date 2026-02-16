package service;

import model.DisciplinaryAction;
import model.SecurityEvent;
import model.Student;
import model.StudentStatus;

import java.util.ArrayList;
import java.util.List;

public class UniService {
    private final List<Student> students;
    private final List<DisciplinaryAction> actions;
    private final List<SecurityEvent> events;

    public UniService(List<Student> students,
                          List<DisciplinaryAction> actions,
                          List<SecurityEvent> events) {

        this.students = new ArrayList<>(students);
        this.events = new ArrayList<>(events);
        this.actions = new ArrayList<>(actions);
    }

    public List<Student> getStudents() {return students;}
    public List<DisciplinaryAction> getActions() {return actions;}
    public List<SecurityEvent> getEvents() {return events;}

    // Filter students by faculty (case-insensitive) and exact StudentStatus
    public List<Student> filterStudentsByFacultyAndStatus(String faculty, StudentStatus status) {
        List<Student> result = new ArrayList<>();
        if (faculty == null || status == null) return result;

        for (Student s : students) {
            if (s.getFaculty() != null
                    && s.getFaculty().equalsIgnoreCase(faculty)
                    && s.getStatus() == status) {
                result.add(s);
            }
        }

        return result;
    }

}
