package model;

public class SecurityEvent {
    private int id;
    private int studentId;
    private EventType type;
    private int severity;
    private int buldingCode;

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getSeverity() {
        return severity;
    }

    public EventType getType() {
        return type;
    }

    public int getBuldingCode() {
        return buldingCode;
    }
}
