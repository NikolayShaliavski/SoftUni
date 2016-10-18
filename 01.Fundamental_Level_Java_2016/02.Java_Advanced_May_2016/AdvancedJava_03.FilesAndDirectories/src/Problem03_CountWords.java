import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Problem03_CountWords {

    public static void main(String[] args) {

        String pathWords = "files\\Problem_03\\words3.txt";
        String pathText = "files\\Problem_03\\text3.txt";
        String pathOut = "files\\Problem_03\\03_CountOfWords.txt";

        File fileWords = new File(pathWords);
        File text = new File(pathText);

        HashMap<String, Integer> words = new HashMap<>();

        try (Scanner scanner1 = new Scanner(fileWords);
             Scanner scanner2 = new Scanner(text);
             BufferedWriter writer = new BufferedWriter(new FileWriter(pathOut))) {

            while (scanner1.hasNextLine()) {
                String[] line = scanner1.nextLine().split("\\s+");
                for (int i = 0; i < line.length; i++) {
                    words.put(line[i], 0);
                }
            }

            while (scanner2.hasNextLine()) {
                String[] line = scanner2.nextLine().split("\\W+");
                searchWordsInLine(line, words);
            }
            LinkedHashMap<String, Integer> sortedWords = sortMap(words);
            writeResult(sortedWords, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeResult(LinkedHashMap<String, Integer> sortedWords, BufferedWriter writer) throws IOException {
        for (Map.Entry<String, Integer> word : sortedWords.entrySet()) {
            String line = word.getKey() + " - " + word.getValue();
            writer.write(line);
            writer.newLine();
        }
    }

    private static LinkedHashMap<String,Integer> sortMap(HashMap<String, Integer> words) {
        LinkedHashMap<String, Integer> sortedWords = words.entrySet().stream().sorted((word1, word2) -> word2.getValue().compareTo(word1.getValue())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (k1, k2) ->//collect stream after sorting
                {throw new AssertionError();}, LinkedHashMap<String, Integer>::new));//and pushing it to a Map
        return sortedWords;
    }

    private static void searchWordsInLine(String[] line, HashMap<String, Integer> words) {
        for (int i = 0; i < line.length; i++) {
            String word = line[i];
            for (String s : words.keySet()) {
                if (s.equalsIgnoreCase(word)) {
                    int oldValue = words.get(s);
                    words.put(s, oldValue + 1);
                }
            }
        }
    }
}
