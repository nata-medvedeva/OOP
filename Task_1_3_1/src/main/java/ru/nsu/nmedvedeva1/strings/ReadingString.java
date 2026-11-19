package ru.nsu.nmedvedeva1.strings;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Ищем подстроку в UTF-8 файле, используя блочное чтение.
 * */
public class ReadingString {

    /**
     * Находит все вхождения подстроки в файле.
     *
     * @param filename путь к входному файлу
     * @param str искомая подстрока
     * @return список индексов начала вхождений
     * @throws IOException при ошибке чтения файла
     * @throws IllegalArgumentException если подстрока пустая или null
     */
    public List<Long> reading(String filename, String str) throws IOException {
        List<Long> res = new ArrayList<>();
        long offset = 0;

        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Искомая строка пустая");
        }

        try (Reader r = new BufferedReader(new InputStreamReader(new FileInputStream(filename),
                StandardCharsets.UTF_8))) {

            int n;
            int bufSize = 8 * 1024;

            if (str.length() - 1 > bufSize) {
                bufSize = str.length() - 1;
            }

            char[] buf = new char[bufSize];
            int tailLen = 0;

            char[] tail = new char[str.length() - 1];

            while ((n = r.read(buf)) != -1) {
                StringBuilder chunk = new StringBuilder();

                long start = offset - tailLen;
                chunk.append(tail, 0, tailLen);
                chunk.append(buf, 0, n);
                workingWithString(chunk, str, start, res);
                offset += n;

                tailLen = Math.min(chunk.length(), str.length() - 1);
                chunk.getChars(chunk.length() - tailLen, chunk.length(), tail, 0);
            }
        }
        return res;
    }

    /**
     * Выполняет поиск подстроки в текущем блоке и добавляет индексы в результат.
     *
     * @param line текущий блок с хвостом предыдущего блока
     * @param str искомая подстрока
     * @param start индекс начала объединённого блока в файле
     * @param res список найденных индексов
     */
    private void workingWithString(StringBuilder line, String str, long start, List<Long> res) {
        int idx = line.indexOf(str, 0);
        while (idx != -1) {
            res.add(start + idx);

            idx = line.indexOf(str, idx + 1);
        }
    }
}
