package vip.dengwj.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    public static void  saveText(String path, String txt) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        bw.write(txt);
        bw.close();
    }

    public static String readText(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));

        StringBuilder sb = new StringBuilder();
        String data;
        while ((data = br.readLine()) != null) {
            sb.append("\n").append(data);
        }

        br.close();
        return sb.toString();
    }
}
