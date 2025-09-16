package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReportGenerator {
    public static void generateFailedTestReport(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("Failed Test Cases Report:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}