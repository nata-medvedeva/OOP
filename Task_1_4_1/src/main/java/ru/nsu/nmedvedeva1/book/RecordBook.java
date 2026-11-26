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

    /**
     * Конструктор зачетной книги.
     *
     * @param semester текущий семестр обучения
     * @param formOfStudying форма обучения студента
     * */
    public RecordBook(int semester, StudyForm formOfStudying) {
        this.semester = semester;
        this.formOfStudying = formOfStudying;
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

        if (formOfStudying == StudyForm.Бюджетная) {
            System.out.println("Вы уже обучаетесь на бюджете.");
            return false;
        }

        List<Integer> lastTwoSemesters = grades.stream()
                .filter(g -> g.controlType == ControlType.Экзамен)
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
                .filter(g -> g.controlType == ControlType.Экзамен && g.semester >= fromSemester)
                .noneMatch(g -> g.value == 3 || g.value == 2);
    }

    /**
     * Проверяет возможность получения диплома с отличием на текущем этапе обучения.
     * Учитывает отсутствие 2 и 3 в итоговых оценках.
     *
     * @return true, если текущие оценки удовлетворяют условиям, иначе false
     */
    public boolean possibilityToGetRedDiploma() {
        boolean flag1 = grades.stream().filter(g -> g.controlType == ControlType.Экзамен)
                .noneMatch(g -> g.getValue() == 2 || g.getValue() == 3)
                && grades.stream().filter(g -> g.controlType == ControlType.Дифференцированный_зачет)
                .noneMatch(g -> g.getValue() == 2 || g.getValue() == 3);
        boolean flag2 = grades.stream().filter(g -> g.controlType == ControlType.Квалификационная_работа)
                .allMatch(g -> g.getValue() == 5);

        OptionalDouble avgForExams = grades.stream()
                .filter(g -> g.controlType == ControlType.Экзамен
                        || g.controlType == ControlType.Дифференцированный_зачет
                        || g.controlType == ControlType.Защита_отчета_по_практике
                        || g.controlType == ControlType.Защита_вкр)
                .mapToInt(Grade::getValue)
                .average();
        boolean flag3 = avgForExams.isPresent() && (avgForExams.getAsDouble() >= 4.75);
        return flag1 && flag2 && flag3;
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
