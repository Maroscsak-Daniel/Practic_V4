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

    public int securitySore() {
        return switch (type) {
            case LATE_ENTRY -> severity;
            case UNAUTHORIZED_ACCESS -> severity * 4;
            case PROPERTY_DAMAGE -> severity * 6;
            case FIRE_ALARM_TRIGGER -> severity * 3;
        };
    }
}
