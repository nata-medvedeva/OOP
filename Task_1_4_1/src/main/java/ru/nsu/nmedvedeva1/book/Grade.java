package ru.nsu.nmedvedeva1.book;

public class Grade {
    final int value;
    final int semester;
    final ControlType controlType;

    public int getValue() {
        return value;
    }

    public int getSemester() {
        return semester;
    }

    public ControlType getControlType() {
        return  controlType;
    }

    public Grade(ControlType controlType, int value, int semester) {
        this.value = value;
        this.controlType = controlType;
        this.semester = semester;
    }
}
