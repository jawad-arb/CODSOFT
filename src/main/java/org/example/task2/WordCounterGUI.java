package org.example.task2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounterGUI extends JFrame {
    private JTextArea textArea;
    private JTextField filePathField;
    private JButton analyzeButton;

    public WordCounterGUI() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Word Counter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Enter '1' to input text manually, '2' to input from a file: ");
        JTextField choiceField = new JTextField();
        inputPanel.add(label, BorderLayout.WEST);
        inputPanel.add(choiceField, BorderLayout.CENTER);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        filePathField = new JTextField();
        analyzeButton = new JButton("Analyze");

        JPanel filePanel = new JPanel();
        filePanel.setLayout(new BorderLayout());
        filePanel.add(new JLabel("Enter the file path: "), BorderLayout.WEST);
        filePanel.add(filePathField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(analyzeButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(filePanel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.SOUTH);

        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAnalyzeButtonClicked();
            }
        });
    }

    private void onAnalyzeButtonClicked() {
        String userChoice = textArea.getText().trim();

        String inputText = "";

        if (userChoice != null && userChoice.equals("1")) {
            inputText = JOptionPane.showInputDialog("Enter the text:");
        } else if (userChoice != null && userChoice.equals("2")) {
            inputText = readFromFile();
            if (inputText == null) {
                JOptionPane.showMessageDialog(this, "No file selected or unable to read the file. Exiting.");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid choice. Exiting.");
            return;
        }

        // Count words and get word frequency
        Map<String, Integer> wordFrequency = countWords(inputText);

        // Display the total count of words
        JOptionPane.showMessageDialog(this, "Total number of words: " + wordFrequency.size());

        // Display word frequency
        StringBuilder frequencyOutput = new StringBuilder("\nWord Frequency:\n");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            frequencyOutput.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(this, frequencyOutput.toString());
    }

    private String readFromFile() {
        String filePath = filePathField.getText().trim();
        try {
            Scanner fileScanner = new Scanner(new BufferedReader(new FileReader(filePath)));
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

    private Map<String, Integer> countWords(String text) {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WordCounterGUI wordCounterGUI = new WordCounterGUI();
            wordCounterGUI.setVisible(true);
        });
    }
}
