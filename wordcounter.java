import java.util.*;
import java.io.*;

public class WordCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Word Counter!");
        System.out.println("Enter '1' to input text, or '2' to provide a file:");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        String inputText = "";

        if (choice == 1) {
            System.out.println("Enter the text:");
            inputText = scanner.nextLine();
        } else if (choice == 2) {
            System.out.println("Enter the file path:");
            String filePath = scanner.nextLine();
            try {
                inputText = readFile(filePath);
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
                System.exit(1);
            }
        } else {
            System.out.println("Invalid choice. Please enter '1' or '2'.");
            System.exit(1);
        }

        String[] words = inputText.split("[\\s\\p{Punct}]+"); // Split text into words using space or punctuation as delimiters
        int wordCount = words.length;

        // Create a set to store unique words
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));

        // Count word frequencies using a HashMap
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        // Display word count
        System.out.println("Total word count: " + wordCount);

        // Display number of unique words
        System.out.println("Number of unique words: " + uniqueWords.size());

        // Display word frequencies
        System.out.println("Word frequencies:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        scanner.close();
    }

    // Helper method to read a file into a string
    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}

