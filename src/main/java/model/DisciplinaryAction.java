package model;

public class DisciplinaryAction {
    private int id;
    private int studentId;
    private ActionReason reason;
    private int penaltyPoints;
    private int buildingCode;

    public int getStudentId() {
        return studentId;
    }

    public ActionReason getReason() {
        return reason;
    }

    public int getPenaltyPoints() {
        return penaltyPoints;
    }

    public int getBuildingCode() {
        return buildingCode;
    }

    public int getId() {
        return id;
    }
}
