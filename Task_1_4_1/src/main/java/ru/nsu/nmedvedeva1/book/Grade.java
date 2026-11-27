package ru.nsu.nmedvedeva1.book;

/**
 * Класс для структуры оценки: тип, в каком семестре получена и сама оценка.
 * */
public class Grade {
    final int value;
    final int semester;
    final ControlType controlType;

    /**
     * Метод для получения оценки (числа).
     *
     * @return оценка
     * */
    public int getValue() {
        return value;
    }

    /**
     * Метод для получения семестра, когда оценка была получена.
     *
     * @return номер семестра
     * */
    public int getSemester() {
        return semester;
    }

    /**
     * Метод для получения типа оценки.
     *
     * @return тип оценки
     * */
    public ControlType getControlType() {
        return  controlType;
    }

    /**
     * Конструктор для оценки.
     *
     * @param controlType тип оценки
     * @param value значение оценки
     * @param semester семестр, когда она получена
     * */
    public Grade(ControlType controlType, int value, int semester) {
        this.value = value;
        this.controlType = controlType;
        this.semester = semester;
    }
}
