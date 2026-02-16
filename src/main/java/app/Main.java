package app;

import model.*;
import service.UniService;
import util.JsonUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("===== Smart City Traffic Control =====\n");

        List<Student> students =
                JsonUtil.readList(Path.of("C:\\FMI Facultate shit\\UBB FMI Anul II\\Semestrul I\\Pregatire Examen MAP\\Modele\\V4_UNI\\data\\students.json"),
                        Student.class);

        List<DisciplinaryAction> actions =
                JsonUtil.readList(Path.of("C:\\FMI Facultate shit\\UBB FMI Anul II\\Semestrul I\\Pregatire Examen MAP\\Modele\\V4_UNI\\data\\actions.json"),
                        DisciplinaryAction.class);

        List<SecurityEvent> events =
                JsonUtil.readList(Path.of("C:\\FMI Facultate shit\\UBB FMI Anul II\\Semestrul I\\Pregatire Examen MAP\\Modele\\V4_UNI\\data\\events.json"),
                        SecurityEvent.class);

        // Create service
        UniService service = new UniService(students, actions, events);


        //Task 1: Load data + display student data
        System.out.println("Task 1: Load data + display student data");
        System.out.println("Students loaded: " + students.size());
        System.out.println("Actions loaded: " + actions.size());
        System.out.println("Events loaded: " + events.size());
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("Task 2 completed\n");

//
//        //Task 2: Filter students by faculty and status
//        System.out.println("Task 2: Filter students by faculty and status");
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter faculty: ");
//        String facultyInput = scanner.nextLine().trim();
//
//        System.out.print("Enter status (ACTIVE or SUSPENDED): ");
//        String statusInput = scanner.nextLine().trim().toUpperCase();
//
//        StudentStatus status = StudentStatus.valueOf(statusInput);
//
//        List<Student> matched = service.filterStudentsByFacultyAndStatus(facultyInput, status);
//
//        System.out.println("\nStudents matching faculty='" + facultyInput + "' and status='" + status + "':");
//        for (Student s : matched) {
//            System.out.println(s);
//        }
//        System.out.println("Task 2 completed\n");


        // Task 3: Sort students by faculty asc then yearLevel desc
        System.out.println("Task 3: Sort students by faculty asc then yearLevel desc");
        List<Student> sorted = service.sortStudentsByFacultyAscThenYearLevelDesc();
        for (Student s : sorted) {
            System.out.println(s);
        }
        System.out.println("Task 3 completed\n");


        // Task 4: Save sorted students to file (no try/catch)
        System.out.println("Task 4: Write sorted file");
        service.writeStudents(
                Path.of("students_sorted.txt"),
                sorted);
        System.out.println("Task 4 completed\n");


        // Task 5: Compute severity score for each student and return first N lines of output
        System.out.println("Task 5: Points calculation: ");
        for (String line : service.firstNComputedSeverityScore(5)) {
            System.out.println(line);
        }
        System.out.println("Task 5 completed.\n");

        // TASK 6
        System.out.println("Task 6: Ranking");
        var ranking = service.ranking();

        System.out.println("Top 5 Vehicles:");
        for (int i = 0; i < 5; i++) {
            var r = ranking.get(i);
            System.out.println((i + 1) + ". "
                    + r.name()
                    + " -> "
                    + r.totalRisk());
        }
    }
}