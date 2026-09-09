package ru.nsu.nmedvedeva1.task_1_1_1;

/** Класс Sort это и есть сортировка кучей.
*/
public class Sort {
    /** Функция перестановки двух переменных по индексам.
     *
    * @param array принимает массив в котором будут осуществляться перестановки
    *
     * @param индекс i первого элемента
    *
     * @param индекс j второго элемента
    */
    private static void swap(int[] array, int i, int j) {
        int val = array[i];
        array[i] = array[j];
        array[j] = val;
    }

    /** Функция просеивания для восстановления порядка в куче.
     * Просеиваем элементы вниз по куче, если потомок меньше элемента, то меняем их местами и продолжаем просеивание.
     *
     * @param n количество элементов в куче.
     *
     * @param idx индекс элемента, который просеивается.
     *
     * @param array массив (куча).
     */
    private static void siftdown(int n, int idx, int[] array) {
        while (true) {
            int l = 2 * idx + 1;
            int r = l + 1;
            if (l >= n) {
                return;
            }
            int max_idx = idx;
            if (array[l] > array[max_idx]) {
                max_idx = l;
            }
            if (r < n && array[r] > array[max_idx]) {
                max_idx = r;
            }
            if (idx != max_idx) {
                swap(array, idx, max_idx);
                idx = max_idx;
            } else {
                return;
            }
        }
    }

    /** Функция самой сортировки кучей.
     * Проверяем, есть ли элементы в массиве, если есть, то продолжаем процесс.
     * С помощью первого цикла строим max heap, где родитель больше(=) потомков, а максимум в корне.
     * Второй цикл поэлементно просеивает массив(кучу), делая его упорядоченным.
     *
     * @param array массив (куча).
     */
    public static void heapsort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            siftdown(n, i, array);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(array, i, 0);
            siftdown(i, 0, array);
        }
    }
}
