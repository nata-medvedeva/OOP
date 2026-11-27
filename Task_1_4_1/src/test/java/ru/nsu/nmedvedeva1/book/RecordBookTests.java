package ru.nsu.nmedvedeva1.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Тесты для Ungraded_creditной книги.
 * */
class RecordBookTests {

    @Test
    void createBook() {
        RecordBook book = new RecordBook(3, StudyForm.Contract_based, 35);

        assertEquals(3, book.getSemester());
        assertEquals(StudyForm.Contract_based, book.getFormOfStudying());
    }

    @Test
    void averageGrade() {
        RecordBook book = new RecordBook(3, StudyForm.Budget_funded, 35);

        book.addGrade(new Grade(ControlType.Exam, 5, 1));
        book.addGrade(new Grade(ControlType.Exam, 4, 2));
        book.addGrade(new Grade(ControlType.Graded_credit, 3, 3));

        assertEquals(4.0, book.averageGrade().getAsDouble(), 0.001);
    }

    @Test
    void averageWithCredit() {
        RecordBook book = new RecordBook(2, StudyForm.Budget_funded, 35);

        book.addGrade(new Grade(ControlType.Exam, 5, 1));
        book.addGrade(new Grade(ControlType.Exam, 4, 2));
        book.addGrade(new Grade(ControlType.Ungraded_credit, 0, 2));

        assertEquals(4.5, book.averageGrade().getAsDouble(), 0.001);
    }

    @Test
    void emptyAverage() {
        RecordBook book = new RecordBook(1, StudyForm.Budget_funded, 35);

        assertTrue(book.averageGrade().isEmpty());
    }

    @Test
    void onlyCredits() {
        RecordBook book = new RecordBook(2, StudyForm.Budget_funded, 35);

        book.addGrade(new Grade(ControlType.Ungraded_credit, 0, 1));
        book.addGrade(new Grade(ControlType.Ungraded_credit, 0, 2));

        assertTrue(book.averageGrade().isEmpty());
    }

    @Test
    void canMove() {
        RecordBook book = new RecordBook(3, StudyForm.Contract_based, 35);

        book.addGrade(new Grade(ControlType.Exam, 4, 1));
        book.addGrade(new Grade(ControlType.Exam, 5, 2));

        assertTrue(book.possibilityToMove());
    }

    @Test
    void cannotMove() {
        RecordBook book = new RecordBook(3, StudyForm.Contract_based, 35);

        book.addGrade(new Grade(ControlType.Exam, 3, 1));
        book.addGrade(new Grade(ControlType.Exam, 5, 2));

        assertFalse(book.possibilityToMove());
    }

    @Test
    void failedExam() {
        RecordBook book = new RecordBook(3, StudyForm.Contract_based, 35);

        book.addGrade(new Grade(ControlType.Exam, 5, 1));
        book.addGrade(new Grade(ControlType.Exam, 2, 2));

        assertFalse(book.possibilityToMove());
    }

    @Test
    void oldThree() {
        RecordBook book = new RecordBook(4, StudyForm.Contract_based, 35);

        book.addGrade(new Grade(ControlType.Exam, 3, 1));
        book.addGrade(new Grade(ControlType.Exam, 4, 2));
        book.addGrade(new Grade(ControlType.Exam, 5, 3));

        assertTrue(book.possibilityToMove());
    }

    @Test
    void alreadyBudget() {
        RecordBook book = new RecordBook(3, StudyForm.Budget_funded, 35);

        book.addGrade(new Grade(ControlType.Exam, 5, 1));
        book.addGrade(new Grade(ControlType.Exam, 5, 2));

        assertFalse(book.possibilityToMove());
    }

    @Test
    void scholarship() {
        RecordBook book = new RecordBook(3, StudyForm.Budget_funded, 35);

        book.addGrade(new Grade(ControlType.Exam, 5, 2));
        book.addGrade(new Grade(ControlType.Graded_credit, 5, 2));
        book.addGrade(new Grade(ControlType.Ungraded_credit, 0, 2));

        assertTrue(book.possibilityToHaveBigScholarship());
    }

    @Test
    void noScholarship() {
        RecordBook book = new RecordBook(3, StudyForm.Budget_funded, 35);

        book.addGrade(new Grade(ControlType.Exam, 5, 2));
        book.addGrade(new Grade(ControlType.Exam, 4, 2));

        assertFalse(book.possibilityToHaveBigScholarship());
    }

    @Test
    void oldGrades() {
        RecordBook book = new RecordBook(3, StudyForm.Budget_funded, 35);

        book.addGrade(new Grade(ControlType.Exam, 3, 1));
        book.addGrade(new Grade(ControlType.Exam, 4, 1));
        book.addGrade(new Grade(ControlType.Exam, 5, 2));

        assertTrue(book.possibilityToHaveBigScholarship());
    }

    @Test
    void emptySession() {
        RecordBook book = new RecordBook(3, StudyForm.Budget_funded, 35);

        book.addGrade(new Grade(ControlType.Exam, 5, 1));

        assertFalse(book.possibilityToHaveBigScholarship());
    }

    @Test
    void redDiploma() {
        RecordBook book = new RecordBook(8, StudyForm.Budget_funded, 4);

        book.addGrade(new Grade(ControlType.Exam, 5, 1));
        book.addGrade(new Grade(ControlType.Exam, 5, 2));
        book.addGrade(new Grade(ControlType.Exam, 5, 3));
        book.addGrade(new Grade(ControlType.Graded_credit, 4, 4));
        book.addGrade(new Grade(ControlType.Qualifying_work, 5, 8));

        assertTrue(book.possibilityToGetRedDiploma());
    }

    @Test
    void tooManyFours() {
        RecordBook book = new RecordBook(8, StudyForm.Budget_funded, 4);

        book.addGrade(new Grade(ControlType.Exam, 5, 1));
        book.addGrade(new Grade(ControlType.Exam, 5, 2));
        book.addGrade(new Grade(ControlType.Exam, 4, 3));
        book.addGrade(new Grade(ControlType.Exam, 4, 4));

        assertFalse(book.possibilityToGetRedDiploma());
    }

    @Test
    void examThree() {
        RecordBook book = new RecordBook(3, StudyForm.Budget_funded, 35);

        book.addGrade(new Grade(ControlType.Exam, 3, 1));

        assertFalse(book.possibilityToGetRedDiploma());
    }

    @Test
    void emptyDiploma() {
        RecordBook book = new RecordBook(1, StudyForm.Budget_funded, 35);

        assertTrue(book.possibilityToGetRedDiploma());
    }

    @Test
    void emptyBook() {
        RecordBook book = new RecordBook(1, StudyForm.Contract_based, 35);

        assertTrue(book.averageGrade().isEmpty());
        assertFalse(book.possibilityToMove());
        assertTrue(book.possibilityToGetRedDiploma());
        assertFalse(book.possibilityToHaveBigScholarship());
    }
}