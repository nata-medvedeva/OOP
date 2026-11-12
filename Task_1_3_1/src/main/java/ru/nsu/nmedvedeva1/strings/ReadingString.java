package ru.nsu.nmedvedeva1.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ReadingString {

    long offset;
    private List<Long> res = new ArrayList<>();

    public void reading(String filename, String str) {
        res.clear();
        offset = 0;

        if (str == null || str.isEmpty()) {
            System.err.println("Искомая строка пустая");
            return;
        }

        try (Reader r = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {

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
                workingWithString(chunk, str, start);
                offset += n;

                tailLen = Math.min(chunk.length(), str.length() - 1);
                chunk.getChars(chunk.length() - tailLen, chunk.length(), tail, 0);
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            return;
        }
        printResult();
    }

    public void workingWithString (StringBuilder line, String str, long start) {
        int idx = line.indexOf(str, 0);
        while (idx != -1) {
            res.add(start + idx);

            idx = line.indexOf(str, idx + 1);
        }
    }

    public void printResult () {
        if (res.isEmpty()) {
            System.out.println("[]");
            return;
        }

        StringBuilder s = new StringBuilder("[");

        int i = 0;
        s.append(res.get(i));
        for (i = 1; i < res.size(); i++) {
            s.append( ", " + res.get(i));
        }
        s.append("]");
        System.out.println(s);
    }
}
