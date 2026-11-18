package com.napier.sem.reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportMDWriter {

    public static void saveAsMarkdown(String filename, String text) {
        new File("./reports/").mkdirs();
        try (BufferedWriter w = new BufferedWriter(new FileWriter("./reports/" + filename))) {
            w.write("```\n");
            w.write(text);
            w.write("```\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}