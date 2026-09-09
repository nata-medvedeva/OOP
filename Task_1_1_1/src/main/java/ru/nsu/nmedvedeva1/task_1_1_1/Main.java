package ru.nsu.nmedvedeva1.task_1_1_1;

/** Класс main считывает массив array, осуществляет вызов класса
* сортировки и выводит полученный отсортированный массив в одну строку.
*/

public class Main {

    /** Принимает числа из командной строки, сортирует их и выводит
    * в отсортированном порядке.
    * @param args это аргументы командной строки (числа для сортировки)
    */

    public static void main(String[] args) {
        if(args.length == 0){
            return;
        }

        int[] array = new int[args.length];
        for (int i = 0; i< array.length; i++) {
            array[i]=Integer.parseInt(args[i]);
        }
        Sort.heapsort(array);

        for (int i = 0; i< array.length; i++) {
            System.out.print(array[i]);

            if (i < array.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
