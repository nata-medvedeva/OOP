package ru.nsu.nmedvedeva1.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Тесты для зачетной книги.
 * */
class RecordBookTests {

    @Test
    void createBook() {
        RecordBook book = new RecordBook(3, StudyForm.Платная);

        assertEquals(3, book.getSemester());
        assertEquals(StudyForm.Платная, book.getFormOfStudying());
    }

    @Test
    void averageGrade() {
        RecordBook book = new RecordBook(3, StudyForm.Бюджетная);

        book.addGrade(new Grade(ControlType.Экзамен, 5, 1));
        book.addGrade(new Grade(ControlType.Экзамен, 4, 2));
        book.addGrade(new Grade(ControlType.Дифференцированный_зачет, 3, 3));

        assertEquals(4.0, book.averageGrade().getAsDouble(), 0.001);
    }

    @Test
    void averageWithCredit() {
        RecordBook book = new RecordBook(2, StudyForm.Бюджетная);

        book.addGrade(new Grade(ControlType.Экзамен, 5, 1));
        book.addGrade(new Grade(ControlType.Экзамен, 4, 2));
        book.addGrade(new Grade(ControlType.Зачет, 0, 2));

        assertEquals(4.5, book.averageGrade().getAsDouble(), 0.001);
    }

    @Test
    void emptyAverage() {
        RecordBook book = new RecordBook(1, StudyForm.Бюджетная);

        assertTrue(book.averageGrade().isEmpty());
    }

    @Test
    void onlyCredits() {
        RecordBook book = new RecordBook(2, StudyForm.Бюджетная);

        book.addGrade(new Grade(ControlType.Зачет, 0, 1));
        book.addGrade(new Grade(ControlType.Зачет, 0, 2));

        assertTrue(book.averageGrade().isEmpty());
    }

    @Test
    void canMove() {
        RecordBook book = new RecordBook(3, StudyForm.Платная);

        book.addGrade(new Grade(ControlType.Экзамен, 4, 1));
        book.addGrade(new Grade(ControlType.Экзамен, 5, 2));

        assertTrue(book.possibilityToMove());
    }

    @Test
    void cannotMove() {
        RecordBook book = new RecordBook(3, StudyForm.Платная);

        book.addGrade(new Grade(ControlType.Экзамен, 3, 1));
        book.addGrade(new Grade(ControlType.Экзамен, 5, 2));

        assertFalse(book.possibilityToMove());
    }

    @Test
    void failedExam() {
        RecordBook book = new RecordBook(3, StudyForm.Платная);

        book.addGrade(new Grade(ControlType.Экзамен, 5, 1));
        book.addGrade(new Grade(ControlType.Экзамен, 2, 2));

        assertFalse(book.possibilityToMove());
    }

    @Test
    void oldThree() {
        RecordBook book = new RecordBook(4, StudyForm.Платная);

        book.addGrade(new Grade(ControlType.Экзамен, 3, 1));
        book.addGrade(new Grade(ControlType.Экзамен, 4, 2));
        book.addGrade(new Grade(ControlType.Экзамен, 5, 3));

        assertTrue(book.possibilityToMove());
    }

    @Test
    void alreadyBudget() {
        RecordBook book = new RecordBook(3, StudyForm.Бюджетная);

        book.addGrade(new Grade(ControlType.Экзамен, 5, 1));
        book.addGrade(new Grade(ControlType.Экзамен, 5, 2));

        assertFalse(book.possibilityToMove());
    }

    @Test
    void redDiploma() {
        RecordBook book = new RecordBook(8, StudyForm.Бюджетная);

        book.addGrade(new Grade(ControlType.Экзамен, 5, 1));
        book.addGrade(new Grade(ControlType.Экзамен, 5, 2));
        book.addGrade(new Grade(ControlType.Экзамен, 5, 3));
        book.addGrade(new Grade(ControlType.Дифференцированный_зачет, 4, 4));
        book.addGrade(new Grade(ControlType.Квалификационная_работа, 5, 8));

        assertTrue(book.possibilityToGetRedDiploma());
    }

    @Test
    void tooManyFours() {
        RecordBook book = new RecordBook(4, StudyForm.Бюджетная);

        book.addGrade(new Grade(ControlType.Экзамен, 5, 1));
        book.addGrade(new Grade(ControlType.Экзамен, 5, 2));
        book.addGrade(new Grade(ControlType.Экзамен, 4, 3));
        book.addGrade(new Grade(ControlType.Экзамен, 4, 4));

        assertFalse(book.possibilityToGetRedDiploma());
    }

    @Test
    void examThree() {
        RecordBook book = new RecordBook(3, StudyForm.Бюджетная);

        for (int i = 0; i < 7; i++) {
            book.addGrade(new Grade(ControlType.Экзамен, 5, 1));
        }

        book.addGrade(new Grade(ControlType.Экзамен, 3, 2));

        assertFalse(book.possibilityToGetRedDiploma());
    }

    @Test
    void diplomaCreditThree() {
        RecordBook book = new RecordBook(3, StudyForm.Бюджетная);

        for (int i = 0; i < 7; i++) {
            book.addGrade(new Grade(ControlType.Экзамен, 5, 1));
        }

        book.addGrade(new Grade(ControlType.Дифференцированный_зачет, 3, 2));

        assertFalse(book.possibilityToGetRedDiploma());
    }

    @Test
    void qualificationFour() {
        RecordBook book = new RecordBook(8, StudyForm.Бюджетная);

        book.addGrade(new Grade(ControlType.Экзамен, 5, 1));
        book.addGrade(new Grade(ControlType.Квалификационная_работа, 4, 8));

        assertFalse(book.possibilityToGetRedDiploma());
    }

    @Test
    void unfinishedDiploma() {
        RecordBook book = new RecordBook(2, StudyForm.Бюджетная);

        book.addGrade(new Grade(ControlType.Экзамен, 5, 1));
        book.addGrade(new Grade(ControlType.Дифференцированный_зачет, 5, 2));

        assertTrue(book.possibilityToGetRedDiploma());
    }


    @Test
    void scholarship() {
        RecordBook book = new RecordBook(3, StudyForm.Бюджетная);

        book.addGrade(new Grade(ControlType.Экзамен, 5, 2));
        book.addGrade(new Grade(ControlType.Дифференцированный_зачет, 5, 2));
        book.addGrade(new Grade(ControlType.Зачет, 0, 2));

        assertTrue(book.possibilityToHaveBigScholarship());
    }

    @Test
    void noScholarship() {
        RecordBook book = new RecordBook(3, StudyForm.Бюджетная);

        book.addGrade(new Grade(ControlType.Экзамен, 5, 2));
        book.addGrade(new Grade(ControlType.Экзамен, 4, 2));

        assertFalse(book.possibilityToHaveBigScholarship());
    }

    @Test
    void oldGrades() {
        RecordBook book = new RecordBook(3, StudyForm.Бюджетная);

        book.addGrade(new Grade(ControlType.Экзамен, 3, 1));
        book.addGrade(new Grade(ControlType.Экзамен, 4, 1));
        book.addGrade(new Grade(ControlType.Экзамен, 5, 2));

        assertTrue(book.possibilityToHaveBigScholarship());
    }

    @Test
    void emptySession() {
        RecordBook book = new RecordBook(3, StudyForm.Бюджетная);

        book.addGrade(new Grade(ControlType.Экзамен, 5, 1));

        assertFalse(book.possibilityToHaveBigScholarship());
    }


    @Test
    void emptyBook() {
        RecordBook book = new RecordBook(1, StudyForm.Платная);

        assertTrue(book.averageGrade().isEmpty());
        assertFalse(book.possibilityToMove());
        assertFalse(book.possibilityToGetRedDiploma());
        assertFalse(book.possibilityToHaveBigScholarship());
    }
}