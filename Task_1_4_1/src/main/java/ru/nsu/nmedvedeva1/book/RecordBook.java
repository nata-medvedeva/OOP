package ru.nsu.nmedvedeva1.book;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * Зачетная книжка студента.
 * Хранит оценки, текущий семестр и форму обучения.
 * Позволяет вычислять средний балл, возможность перевода на бюджет,
 * получения красного диплома и повышенной стипендии.
 */
public class RecordBook {
    List<Grade> grades = new ArrayList<>();
    int semester;
    StudyForm formOfStudying;
    private final int totalGrades;

    /**
     * Конструктор зачетной книги.
     *
     * @param semester текущий семестр обучения
     * @param formOfStudying форма обучения студента
     * @param totalGrades общее количество итоговых числовых оценок по учебному плану
     */
    public RecordBook(int semester, StudyForm formOfStudying, int totalGrades) {
        if (totalGrades <= 0) {
            throw new IllegalArgumentException("Количество итоговых оценок должно быть положительным.");
        }

        if (semester <= 0) {
            throw new IllegalArgumentException("Семестр должен быть положительным числом.");
        }

        this.semester = semester;
        this.formOfStudying = formOfStudying;
        this.totalGrades = totalGrades;
    }

    /**
     * Вычисляет средний балл за все время обучения, не учитывая обычные зачеты.
     *
     * @return средний балл или пустой OptionalDouble при отсутствии числовых оценок
     */
    public OptionalDouble averageGrade() {
        OptionalDouble avg = grades.stream().mapToInt(Grade::getValue)
                .filter(value -> value != 0).average();
        return avg;
    }

    /**
     * Проверяет возможность перевода с платной формы обучения на бюджетную.
     * Учитывает отсутствие 3 и 2 за экзамены в двух последних семестрах с экзаменами.
     *
     * @return true, если перевод возможен, иначе false
     */
    public boolean possibilityToMove() {

        if (formOfStudying == StudyForm.Budget_funded) {
            return false;
        }

        List<Integer> lastTwoSemesters = grades.stream()
                .filter(g -> g.controlType == ControlType.Exam)
                .map(Grade::getSemester)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        if (lastTwoSemesters.isEmpty()) {
            return false;
        }

        int fromSemester = lastTwoSemesters.size() >= 2
                ? lastTwoSemesters.get(lastTwoSemesters.size() - 2)
                : lastTwoSemesters.get(0);

        return grades.stream()
                .filter(g -> g.controlType == ControlType.Exam && g.semester >= fromSemester)
                .noneMatch(g -> g.value == 3 || g.value == 2);
    }

    /**
     * Проверяет возможность получения диплома с отличием на текущем этапе обучения.
     * Учитывает отсутствие 2 и 3 в итоговых оценках.
     *
     * @return true, если текущие оценки удовлетворяют условиям, иначе false
     */
    public boolean possibilityToGetRedDiploma() {
        List<Grade> finalGrades = grades.stream()
                .filter(g -> g.controlType == ControlType.Exam
                        || g.controlType == ControlType.Graded_credit
                        || g.controlType == ControlType.Internship_report_defense
                        || g.controlType == ControlType.Final_qualification_work_defense)
                .toList();

        boolean noThrees = finalGrades.stream().noneMatch(g -> g.getValue() == 2 || g.getValue() == 3);

        boolean excQualifyingWork = grades.stream()
                .filter(g -> g.controlType == ControlType.Qualifying_work
                || g.controlType == ControlType.Final_qualification_work_defense)
                .allMatch(g -> g.getValue() == 5);

        long excellentGrades = finalGrades.stream()
                .filter(g -> g.getValue() == 5)
                .count();

        int futureGrades = totalGrades - finalGrades.size();

        if (futureGrades < 0) {
            return false;
        }
        double maxPercent = (double) (excellentGrades + futureGrades)
                / totalGrades * 100;

        return maxPercent >= 75 && excQualifyingWork && noThrees;
    }

    /**
     * Проверяет возможность получения повышенной стипендии
     * в текущем семестре по результатам предыдущего семестра.
     * Проверяет наличие оценок и отсутствие оценок 2, 3 и 4.
     *
     * @return true, если получение повышенной стипендии возможно, иначе false
     */
    public boolean possibilityToHaveBigScholarship() {
        List<Grade> lastSession = grades.stream()
                .filter(g -> g.getSemester() == semester - 1)
                .collect(Collectors.toList());

        return !lastSession.isEmpty()
                && lastSession.stream().noneMatch(g -> g.getValue() == 2
                || g.getValue() == 4 || g.getValue() == 3);
    }

    /**
     * Добавляет оценку в зачетную книжку.
     *
     * @param grade добавляемая оценка
     */
    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    /**
     * Возвращает текущий семестр обучения.
     *
     * @return номер текущего семестра
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Возвращает текущую форму обучения студента.
     *
     * @return форма обучения
     */
    public StudyForm getFormOfStudying() {
        return formOfStudying;
    }
}
