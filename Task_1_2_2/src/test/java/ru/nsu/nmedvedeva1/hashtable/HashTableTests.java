package ru.nsu.nmedvedeva1.hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.jupiter.api.Test;

class HashTableTests {

    @Test
    void emptyTableTest() {
        HashTable<String, Integer> table = new HashTable<>();

        assertNull(table.get("A"));
        assertFalse(table.containsKey("A"));
        assertFalse(table.remove("A", 10));
        assertFalse(table.replace("A", 20));
        assertEquals("{}", table.toString());

        assertFalse(table.iterator().hasNext());
        assertThrows(NoSuchElementException.class, () -> table.iterator().next());
    }

    @Test
    void putAndGetTest() {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("A", 10);
        table.put("B", 20);

        assertEquals(10, table.get("A"));
        assertEquals(20, table.get("B"));
        assertNull(table.get("C"));

        assertTrue(table.containsKey("A"));
        assertFalse(table.containsKey("C"));
    }

    @Test
    void putExistingKeyTest() {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("A", 10);
        table.put("A", 30);

        assertEquals(30, table.get("A"));

        int count = 0;
        for (Map.Entry<String, Integer> entry : table) {
            count++;
        }

        assertEquals(1, count);
    }

    @Test
    void replaceTest() {
        HashTable<String, Integer> table = new HashTable<>();

        assertFalse(table.replace("A", 50));

        table.put("A", 10);

        assertTrue(table.replace("A", 50));
        assertEquals(50, table.get("A"));
    }

    @Test
    void removeTest() {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("A", 10);
        table.put("B", 20);

        assertFalse(table.remove("A", 100));
        assertFalse(table.remove("C", 30));

        assertTrue(table.remove("A", 10));
        assertFalse(table.containsKey("A"));
        assertEquals(20, table.get("B"));

        assertFalse(table.remove("A", 10));
    }

    @Test
    void nullArgumentsTest() {
        HashTable<String, Integer> table = new HashTable<>();

        assertThrows(NullPointerException.class, () -> table.put(null, 10));

        assertThrows(NullPointerException.class, () -> table.put("A", null));

        assertThrows(NullPointerException.class, () -> table.get(null));

        assertThrows(NullPointerException.class, () -> table.containsKey(null));

        assertThrows(NullPointerException.class, () -> table.remove(null, 10));

        assertThrows(NullPointerException.class, () -> table.remove("A", null));

        assertThrows(NullPointerException.class, () -> table.replace(null, 10));

        assertThrows(NullPointerException.class, () -> table.replace("A", null));
    }

    @Test
    void equalsTest() {
        HashTable<String, Integer> a = new HashTable<>();
        HashTable<String, Integer> b = new HashTable<>();

        a.put("A", 10);
        a.put("B", 20);

        b.put("B", 20);
        b.put("A", 10);

        assertEquals(a, a);
        assertEquals(a, b);
        assertEquals(b, a);

        assertEquals(a.hashCode(), b.hashCode());

        assertNotEquals(a, null);
        assertNotEquals(a, "hello");
    }

    @Test
    void equalsDifferentSizesTest() {
        HashTable<String, Integer> a = new HashTable<>();
        HashTable<String, Integer> b = new HashTable<>();

        a.put("A", 10);

        b.put("A", 10);
        b.put("B", 20);

        assertNotEquals(a, b);
    }

    @Test
    void equalsDifferentValuesTest() {
        HashTable<String, Integer> a = new HashTable<>();
        HashTable<String, Integer> b = new HashTable<>();

        a.put("A", 10);
        b.put("A", 20);

        assertNotEquals(a, b);
    }

    @Test
    void equalsDifferentKeysTest() {
        HashTable<String, Integer> a = new HashTable<>();
        HashTable<String, Integer> b = new HashTable<>();

        a.put("A", 10);
        b.put("B", 10);

        assertNotEquals(a, b);
    }

    @Test
    void equalsDifferentCapacityTest() {
        HashTable<Integer, Integer> a = new HashTable<>();
        HashTable<Integer, Integer> b = new HashTable<>();

        for (int i = 0; i < 30; i++) {
            a.put(i, i * 10);
        }

        for (int i = 2; i < 30; i++) {
            assertTrue(a.remove(i, i * 10));
        }

        b.put(0, 0);
        b.put(1, 10);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void toStringTest() {
        HashTable<String, Integer> table = new HashTable<>();

        assertEquals("{}", table.toString());

        table.put("A", 10);
        table.put("B", 20);

        String result = table.toString();

        assertTrue(result.startsWith("{"));
        assertTrue(result.endsWith("}"));
        assertTrue(result.contains("A=10"));
        assertTrue(result.contains("B=20"));
        assertTrue(result.contains(", "));
    }

    @Test
    void iteratorTest() {
        HashTable<Integer, String> table = new HashTable<>();

        table.put(1, "A");
        table.put(2, "B");
        table.put(3, "C");

        Set<Integer> keys = new HashSet<>();

        for (Map.Entry<Integer, String> entry : table) {
            keys.add(entry.getKey());
            assertEquals(table.get(entry.getKey()), entry.getValue());
        }

        assertEquals(Set.of(1, 2, 3), keys);
    }

    @Test
    void iteratorNoSuchElementTest() {
        HashTable<String, Integer> table = new HashTable<>();

        table.put("A", 10);

        Iterator<Map.Entry<String, Integer>> iterator = table.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(10, iterator.next().getValue());

        assertFalse(iterator.hasNext());

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void concurrentModificationExceptionTest() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("A", 10);
        table.put("B", 20);
        table.put("C", 30);

        Iterator<Map.Entry<String, Integer>> iterator = table.iterator();

        assertTrue(iterator.hasNext());

        table.put("D", 40);

        assertThrows(ConcurrentModificationException.class, iterator::hasNext);
        assertThrows(ConcurrentModificationException.class, iterator::next);

        HashTable<String, Integer> table2 = new HashTable<>();
        table2.put("A", 10);

        Iterator<Map.Entry<String, Integer>> iterator2 = table2.iterator();
        assertTrue(iterator2.hasNext());

        assertTrue(table2.remove("A", 10));

        assertThrows(ConcurrentModificationException.class, iterator2::hasNext);
        assertThrows(ConcurrentModificationException.class, iterator2::next);
    }
}