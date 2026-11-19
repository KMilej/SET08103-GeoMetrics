package com.napier.sem.reports;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ReportMDWriter.
 */
public class ReportMDWriterTest {

    /**
     * Checks that the file is created in ./reports/.
     */
    @Test
    void createsFile() {
        String name = "TestCreate.md";
        ReportMDWriter.saveAsMarkdown(name, "Welcome to the dark side! We have a cookies!!");
        assertTrue(new File("./reports/" + name).exists());
    }

    /**
     * Checks that the file contains exactly the text wrapped in a code block.
     *
     * @throws IOException if the file cannot be read
     */
    @Test
    void fileContainsText() throws IOException {
        String name = "TestContains.md";
        ReportMDWriter.saveAsMarkdown(name, "Welcome to the dark side..");
        String content = Files.readString(Path.of("./reports/" + name));
        assertEquals("```\nWelcome to the dark side..\n```\n", content);
    }

    /**
     * Checks that the text is wrapped in a ``` code block.
     *
     * @throws IOException if the file cannot be read
     */
    @Test
    void wrappedInCodeBlock() throws IOException {
        String name = "TestCodeBlock.md";
        ReportMDWriter.saveAsMarkdown(name, ".. we have a cookies!!");
        String content = Files.readString(Path.of("./reports/" + name));
        assertTrue(content.startsWith("```"));
        assertTrue(content.trim().endsWith("```"));
    }

    /**
     * Checks that empty text does not cause an error.
     */
    @Test
    void emptyText() {
        assertDoesNotThrow(() -> ReportMDWriter.saveAsMarkdown("TestEmpty.md", ""));
    }

    /**
     * Checks that null text does not cause an error.
     */
    @Test
    void nullText() {
        assertDoesNotThrow(() -> ReportMDWriter.saveAsMarkdown("TestNull.md", null));
    }
}