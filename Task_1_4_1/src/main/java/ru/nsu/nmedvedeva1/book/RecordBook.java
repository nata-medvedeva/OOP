package ru.nsu.nmedvedeva1.book;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class RecordBook {
    List<Grade> grades = new ArrayList<>();
    int semester;
    StudyForm formOfStudying;

    public RecordBook(int semester, StudyForm formOfStudying) {
        this.semester = semester;
        this.formOfStudying = formOfStudying;
    }

    public OptionalDouble averageGrade() {
        OptionalDouble avg = grades.stream().mapToInt(Grade::getValue).filter(value -> value != 0).average();
        return avg;
    }

    public boolean possibilityToMove() {

        if (formOfStudying == StudyForm.Бюджетная) {
            System.out.println ("Вы уже обучаетесь на бюджете.");
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

    public boolean possibilityToGetRedDiploma() {
        boolean flag1 = grades.stream().filter(g -> g.controlType == ControlType.Экзамен).noneMatch(g -> g.getValue() == 2 || g.getValue() == 3)
                && grades.stream().filter(g -> g.controlType == ControlType.Дифференцированный_зачет).noneMatch(g -> g.getValue() == 2 || g.getValue() == 3);
        boolean flag2 = grades.stream().filter(g -> g.controlType == ControlType.Квалификационная_работа).allMatch(g -> g.getValue() == 5);

        OptionalDouble avgForExams = grades.stream()
                .filter(g -> g.controlType == ControlType.Экзамен || g.controlType == ControlType.Дифференцированный_зачет
                        || g.controlType == ControlType.Защита_отчета_по_практике || g.controlType == ControlType.Защита_вкр)
                .mapToInt(Grade::getValue)
                .average();
        boolean flag3 = avgForExams.isPresent() && (avgForExams.getAsDouble() >= 4.75);
        return flag1 && flag2 && flag3;
    }

    public boolean possibilityToHaveBigScholarship() {
        List<Grade> lastSession = grades.stream()
                .filter(g -> g.getSemester() == semester - 1)
                .collect(Collectors.toList());

        return !lastSession.isEmpty()
                && lastSession.stream().noneMatch(g -> g.getValue() == 2 || g.getValue() == 4 || g.getValue() == 3);
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public int getSemester() {
        return semester;
    }

    public StudyForm getFormOfStudying() {
        return formOfStudying;
    }
}
