package ru.nsu.nmedvedeva1.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Тест для структуры оценки.
 * */
class GradeTests {

    @Test
    void createGrade() {
        Grade grade = new Grade(ControlType.Экзамен, 5, 2);

        assertEquals(5, grade.getValue());
        assertEquals(2, grade.getSemester());
        assertEquals(ControlType.Экзамен, grade.getControlType());
    }
}