package com.napier.sem.reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Saves report text as Markdown files in the ./reports/ folder.
 */
public class ReportMDWriter {

    /**
     * Writes the given text to a Markdown file, wrapped in a code block.
     * Creates the ./reports/ folder if it does not exist.
     * Does nothing if the text is null.
     *
     * @param filename name of the file to create eg "WorldPopulation.md"
     * @param text     report text to save in the file
     */
    public static void saveAsMarkdown(String filename, String text) {
        // Nothing to save
        if (text == null) {
            return;
        }

        // Make sure the reports folder exists
        new File("./reports/").mkdirs();

        // Write the text inside a ``` code block
        try (BufferedWriter w = new BufferedWriter(new FileWriter("./reports/" + filename))) {
            w.write("```\n");
            w.write(text);
            w.write("\n```\n"); // new line first so the closing ``` is on its own line
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}