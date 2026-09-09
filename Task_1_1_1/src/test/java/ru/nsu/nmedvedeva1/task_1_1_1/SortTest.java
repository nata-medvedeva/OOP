package ru.nsu.nmedvedeva1.task_1_1_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;

import java.io.PrintStream;


class SortTest {

    //как бы перехватываем то, что программа собирается вывести в консоль
    //и с помощью этого можем протестировать мейн
    private String runMain(String... args) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        PrintStream testOut = new PrintStream(output);

        System.setOut(testOut);

        try {
            Main.main(args);
        } finally {
            testOut.flush();
            System.setOut(originalOut);
        }

        return output.toString();
    }

    @Test
    void sort_main_usual() {
        String result = runMain("5", "2", "4", "1", "3");

        assertEquals("1 2 3 4 5" + System.lineSeparator(), result);
    }


    @Test
    void sort_main_empty() {
        String result = runMain();

        assertEquals("", result);
    }

    @Test
    void sort_main_duplicates() {
        String result = runMain("2", "2", "2", "81", "2", "3", "4");

        assertEquals("2 2 2 2 3 4 81"+ System.lineSeparator(), result);
    }

    @Test
    void sort_main_1() {
        String result = runMain("8");

        assertEquals("8" + System.lineSeparator(), result);
    }


    @Test
    void sort_main_sorted() {
        String result = runMain("1", "2", "3", "4", "5");

        assertEquals("1 2 3 4 5"+ System.lineSeparator(), result);
    }

    @Test
    void empty_arr() {
        int[] array = {};
        Sort.heapsort(array);
        assertArrayEquals(new int[]{}, array);
    }

    @Test
    void sort_of_3() {
        int[] array = {1,3,2};
        Sort.heapsort(array);
        assertArrayEquals(new int[]{1,2,3}, array);
    }

    @Test
    void sort_of_1() {
        int[] array = {1};
        Sort.heapsort(array);
        assertArrayEquals(new int[]{1}, array);
    }

    @Test
    void sort_of_sorted() {
        int[] array = {1,2,3,4,5};
        Sort.heapsort(array);
        assertArrayEquals(new int[]{1,2,3,4,5}, array);
    }

    @Test
    void sort_with_duplicates() {
        int[] array = {1,1,1,1,1,1,1,2,3,4,5,6,6,7,8,8,8,8,9,9,2,43};
        Sort.heapsort(array);
        assertArrayEquals(new int[]{1,1,1,1,1,1,1,2,2,3,4,5,6,6,7,8,8,8,8,9,9,43}, array);
    }

    @Test
    void sort_usual() {
        int[] array = {11,245,2,4,6,78,9,3434,67899,1100000000};
        Sort.heapsort(array);
        assertArrayEquals(new int[]{2,4,6,9,11,78,245,3434,67899,1100000000}, array);
    }
}