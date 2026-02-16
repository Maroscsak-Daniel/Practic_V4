package service;

import model.DisciplinaryAction;
import model.SecurityEvent;
import model.Student;

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


}
