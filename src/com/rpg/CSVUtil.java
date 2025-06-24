package com.rpg;
import java.io.*;
import java.util.*;
public class CSVUtil {
    public static List<String[]> readCSV(String path) {
        List<String[]> data = new ArrayList<>();
        int maxCols = 0;
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
                int cols = line.split(",", -1).length;
                if (cols > maxCols) maxCols = cols;
            }
            for (String l : lines) {
                String[] arr = l.split(",", -1);
                if (arr.length < maxCols) {
                    arr = Arrays.copyOf(arr, maxCols);
                    for (int i = 0; i < maxCols; i++)
                        if (arr[i] == null) arr[i] = "";
                }
                data.add(arr);
            }
        } catch(IOException e) { }
        return data;
    }
}