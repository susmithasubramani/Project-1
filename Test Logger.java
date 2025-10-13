package com.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import java.io.File;

public class TestLogger {
    private static final List<Map<String,String>> results = new ArrayList<>();

    public static void init() {
        results.clear();
    }

    public static void log(String testName, String status, String message) {
        Map<String, String> entry = new HashMap<>();
        entry.put("test", testName);
        entry.put("status", status);
        entry.put("message", message);
        entry.put("timestamp", String.valueOf(System.currentTimeMillis()));
        results.add(entry);
    }

    public static void writeResultsToFile() {
        try {
            ObjectMapper m = new ObjectMapper();
            File out = new File("target/test-results.json");
            out.getParentFile().mkdirs();
            m.writerWithDefaultPrettyPrinter().writeValue(out, results);
            System.out.println("Test results written to " + out.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Failed writing test results: " + e.getMessage());
        }
    }
}
