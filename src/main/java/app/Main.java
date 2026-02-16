package app;

import model.*;
import util.JsonUtil;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
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

        System.out.println("Students loaded: " + students.size());
        System.out.println("Actions loaded: " + actions.size());
        System.out.println("Events loaded: " + events.size());
        for (Student s : students) {
            System.out.println(s);
        }
    }
}