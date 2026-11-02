package ru.nsu.nmedvedeva1.hashtable;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Хеш-таблица, хранящая пары ключ-значение.
 * Для разрешения коллизий использует метод цепочек.
 * При превышении коэффициента заполнения loadFactor, увеличиваем размер массива вдвое.
 *
 * @param <K> тип ключей
 * @param <V> тип значений
 */
public class HashTable<K, V> implements Iterable<Map.Entry<K, V>> {
    private Node<K, V>[] table;
    private int nodeCounter;
    private int capacityOfTable;
    private final float loadFactor;
    private int modCount;

    /**
     * Узел цепочки, содержащий одну пару ключ-значение и ссылку на следующий узел.
     *
     * @param <K> тип ключа
     * @param <V> тип значения
     */
    private static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * Возвращает хеш-код ключа.
     *
     * @param key ключ
     * @return хеш-код ключа
     */
    int hash(K key) {
        return key.hashCode();
    }

    /**
     * Создаёт пустую хеш-таблицу.
     */
    public HashTable() {
        nodeCounter = 0;
        capacityOfTable = 16;
        loadFactor = 0.75f;
        modCount = 0;
        table = (Node<K, V>[]) new Node[capacityOfTable];
    }

    /**
     * Вычисляет индекс корзины для ключа при заданной ёмкости.
     *
     * @param key ключ
     * @param capacityOfTable ёмкость массива
     * @return индекс корзины
     */
    private int indexF(K key, int capacityOfTable) {
        return Math.floorMod(hash(key), capacityOfTable);
    }

    /**
     * Добавляет пару ключ-значение в таблицу.
     * Если ключ уже существует, заменяет его значение.
     *
     * @param key ключ
     * @param value новое значение
     */
    public void put(K key, V value) {
        Objects.requireNonNull(key, "Key cannot be null");
        Objects.requireNonNull(value, "Value cannot be null");

        int lhash = hash(key);
        int lindex = indexF(key, capacityOfTable);

        Node<K, V> current = table[lindex];

        while (current != null) {
            if (current.hash == lhash && Objects.equals(current.key, key)) {
                current.value = value;
                return;
            }

            current = current.next;
        }

        table[lindex] = new Node<>(lhash, key, value, table[lindex]);
        nodeCounter++;
        modCount++;

        if (nodeCounter > loadFactor * capacityOfTable) {
            resize();
        }
    }

    /**
     * Находит значение по заданному ключу.
     *
     * @param key ключ
     * @return значение или null, если ключ отсутствует
     */
    public V get(K key) {
        Objects.requireNonNull(key, "Key cannot be null");

        int lindex = indexF(key, capacityOfTable);

        Node<K, V> current = table[lindex];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    /**
     * Удаляет пару при совпадении ключа и значения.
     *
     * @param key ключ удаляемой записи
     * @param value ожидаемое значение
     * @return true, если пара удалена, иначе false
     */
    public boolean remove(K key, V value) {
        Objects.requireNonNull(key, "Key cannot be null");
        Objects.requireNonNull(value, "Value cannot be null");

        int lindex = indexF(key, capacityOfTable);

        Node<K, V> previous = null;
        Node<K, V> current = table[lindex];

        while (current != null) {
            if (Objects.equals(current.key, key) && Objects.equals(current.value, value)) {
                if (previous == null) {
                    table[lindex] = current.next;
                } else {
                    previous.next = current.next;
                }

                nodeCounter--;
                modCount++;
                return true;
            }
            previous = current;
            current = current.next;
        }

        return false;
    }

    /**
     * Проверяет наличие записи с заданным ключом.
     *
     * @param key проверяемый ключ
     * @return true, если ключ существует, иначе false
     */
    public boolean containsKey(K key) {
        Objects.requireNonNull(key, "Key cannot be null");

        int lindex = indexF(key, capacityOfTable);

        Node<K, V> current = table[lindex];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    /**
     * Изменяет значение существующего ключа.
     * Если ключ отсутствует, таблица не изменяется.
     *
     * @param key ключ
     * @param newValue новое значение
     * @return true при успешном обновлении, иначе false
     */
    public boolean replace(K key, V newValue) {
        Objects.requireNonNull(key, "Key cannot be null");
        Objects.requireNonNull(newValue, "Value cannot be null");

        int lindex = indexF(key, capacityOfTable);

        Node<K, V> current = table[lindex];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                current.value = newValue;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    /**
     * Увеличивает ёмкость таблицы в два раза.
     */
    private void resize() {
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[capacityOfTable * 2];

        for (int i = 0; i < capacityOfTable; i++) {
            Node<K, V> current = table[i];

            while (current != null) {
                Node<K, V> next = current.next;

                int index = indexF(current.key, capacityOfTable * 2);

                current.next = newTable[index];
                newTable[index] = current;
                current = next;
            }
        }
        table = newTable;
        capacityOfTable = capacityOfTable * 2;
    }

    /**
     * Сравнивает две хеш-таблицы по содержимому, не учитывая порядок.
     *
     * @param obj объект для сравнения
     * @return true, если таблицы содержат одинаковые пары
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        //Если переданный объект не является хеш-таблицей, возвращаем false.
        //Если является — создаём переменную other,
        //через которую можем обращаться к нему как к таблице.
        if (!(obj instanceof HashTable<?, ?> other)) {
            return false;
        }

        if (nodeCounter != other.nodeCounter) {
            return false;
        }

        for (int i = 0; i < capacityOfTable; i++) {
            Node<K, V> current = table[i];

            while (current != null) {
                K key = current.key;
                int lhash = hash(key);
                int lindex = indexF(key, other.capacityOfTable);

                Node<?, ?> otherCurrent = other.table[lindex];

                boolean flagFound = false;
                while (otherCurrent != null) {
                    if (otherCurrent.hash == lhash && Objects.equals(otherCurrent.key, key)) {
                        if (! Objects.equals(current.value, otherCurrent.value)) {
                            return false;
                        }
                        flagFound = true;
                        break;
                    }

                    otherCurrent = otherCurrent.next;
                }
                if (!flagFound) {
                    return false;
                }
                current = current.next;
            }
        }
        return true;
    }

    /**
     * Возвращает строковое представление таблицы в виде {key1=value1, key2=value2}.
     *
     * @return строка со всеми парами таблицы
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");

        boolean first = true;

        for (int i = 0; i < capacityOfTable; i++) {
            Node<K, V> current = table[i];

            while (current != null) {
                if (!first) {
                    result.append(", ");
                }

                result.append(current.key);
                result.append("=");
                result.append(current.value);

                first = false;
                current = current.next;
            }
        }
        result.append("}");
        return result.toString();
    }

    /**
     * Вычисляет хеш-код таблицы по всем парам ключ-значение.
     *
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        int result = 0;

        for (int i = 0; i < capacityOfTable; i++) {
            Node<K, V> current = table[i];

            while (current != null) {
                result += Objects.hashCode(current.key) + Objects.hashCode(current.value);
                current = current.next;
            }
        }

        return result;
    }

    /**
     * Создаёт итератор для обхода пар ключ-значение.
     *
     * @return итератор по элементам таблицы
     */
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashTableIterator();
    }

    /**
     * Итератор по цепочкам хеш-таблицы.
     * Обнаруживает структурные изменения таблицы во время обхода.
     */
    private class HashTableIterator implements Iterator<Map.Entry<K, V>> {
        private int index = 0;
        private Node<K, V> current;
        private final int expectedModCount = modCount;

        /**
         * Создаёт итератор и вызывает findNextBucket, чтобы найти первый доступный узел.
         */
        HashTableIterator() {
            findNextBucket();
        }

        /**
         * Находит следующую непустую корзину, начиная с текущего индекса.
         */
        private void findNextBucket() {
            while (index < capacityOfTable && table[index] == null) {
                index++;
            }

            if (index < capacityOfTable) {
                current = table[index];
            } else {
                current = null;
            }
        }

        /**
         * Проверяет, не изменилась ли структура таблицы после создания итератора.
         *
         * @throws ConcurrentModificationException если обнаружено структурное изменение
         */
        private void checkModification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        /**
         * Проверяет наличие следующего элемента.
         *
         * @return true, если следующий элемент существует
         * @throws ConcurrentModificationException если таблица структурно изменена
         */
        @Override
        public boolean hasNext() {
            checkModification();
            return current != null;
        }

        /**
         * Возвращает следующую пару ключ-значение и передвигает итератор.
         *
         * @return следующая пара
         * @throws NoSuchElementException если элементы закончились
         * @throws ConcurrentModificationException если таблица структурно изменена
         */
        @Override
        public Map.Entry<K, V> next() {
            checkModification();

            if (current == null) {
                throw new NoSuchElementException();
            }

            Node<K, V> result = current;

            if (current.next != null) {
                current = current.next;
            } else {
                index++;
                findNextBucket();
            }

            return Map.entry(result.key, result.value);
        }
    }
}