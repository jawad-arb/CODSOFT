package org.example.task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter '1' to input text manually, '2' to input from a file: ");
        String userChoice = scanner.nextLine();

        String inputText = "";

        if (userChoice.equals("1")) {
            // Input text manually
            System.out.println("Enter the text: ");
            inputText = scanner.nextLine();
        } else if (userChoice.equals("2")) {
            // Input text from a file
            inputText = readFromFile();
            if (inputText == null) {
                System.out.println("No file selected or unable to read the file. Exiting.");
                return;
            }
        } else {
            System.out.println("Invalid choice. Exiting.");
            return;
        }

        // Count words and get word frequency
        Map<String, Integer> wordFrequency = countWords(inputText);

        // Display the total count of words
        System.out.println("Total number of words: " + wordFrequency.size());

        // Display word frequency
        System.out.println("\nWord Frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static String readFromFile() {
        System.out.println("Select a text file.");
        try {
            Scanner fileScanner = new Scanner(new BufferedReader(new FileReader(getFilePath())));
            StringBuilder content = new StringBuilder();
            while (fileScanner.hasNext()) {
                content.append(fileScanner.nextLine()).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getFilePath() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file path: ");
        return scanner.nextLine();
    }

    private static Map<String, Integer> countWords(String text) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        String[] words = text.split("\\s+");

        for (String word : words) {
            // Remove leading and trailing punctuation
            word = word.replaceAll("^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", "");
            // Convert to lowercase for case-insensitive counting
            word = word.toLowerCase();

            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        return wordFrequency;
    }
}
