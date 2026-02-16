package service;

import model.DisciplinaryAction;
import model.SecurityEvent;
import model.Student;
import model.StudentStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

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

    // Task 3: Sort students by faculty ascending (case-insensitive)
    // and for equal faculty sort by yearLevel descending
    public List<Student> sortStudentsByFacultyAscThenYearLevelDesc() {
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort(Comparator
                .comparing((Student s) -> s.getFaculty() == null ? "" : s.getFaculty(), String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Comparator.comparingInt(Student::getYearLevel).reversed())
        );
        return sorted;
    }

}
