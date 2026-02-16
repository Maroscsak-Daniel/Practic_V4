package service;

import model.DisciplinaryAction;
import model.SecurityEvent;
import model.Student;
import model.StudentStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

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

    // Task 4 (simple): Save provided students list to a file (no sorting here)
    public void writeStudents(Path out,
                              List<Student> list) {
        try {
            Files.write(out,
                    list.stream()
                            .map(Student::toString)
                            .toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Task 5
    public List<String> firstNComputedSeverityScore(int n) {
        return events.stream()
                .limit(n)
                .map(ev -> "Event " + ev.getId() + " -> rawPoints=" + ev.getSeverity()
                        + " -> computedPoints=" + ev.securitySore())
                .toList();
    }

    // Task 6
    public List<RankRow> ranking() {

        Map<Integer, Integer> riskSum =
                events.stream()
                        .collect(Collectors.groupingBy(
                                SecurityEvent::getStudentId,
                                Collectors.summingInt(
                                        SecurityEvent::securitySore)));

        Map<Integer, Integer> fineSum =
                actions.stream()
                        .collect(Collectors.groupingBy(
                                DisciplinaryAction::getStudentId,
                                Collectors.summingInt(
                                        DisciplinaryAction::getPenaltyPoints)));

        List<RankRow> rows = new ArrayList<>();

        for (Student s : students) {
            int risk = riskSum.getOrDefault(s.getId(), 0);
            int fine = fineSum.getOrDefault(s.getId(), 0);

            rows.add(new RankRow(
                    s.getName(),
                    risk - fine
            ));
        }

        rows.sort(Comparator
                .comparingInt(RankRow::totalRisk)
                .thenComparing(
                        Comparator.comparing(RankRow::name)
                                .reversed()));

        return rows;
    }
    public record RankRow(String name,
                          int totalRisk) {}

}
