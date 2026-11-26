package ru.nsu.nmedvedeva1.book;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GradeTests {

    @Test
    void createGrade() {
        Grade grade = new Grade(ControlType.Экзамен, 5, 2);

        assertEquals(5, grade.getValue());
        assertEquals(2, grade.getSemester());
        assertEquals(ControlType.Экзамен, grade.getControlType());
    }
}