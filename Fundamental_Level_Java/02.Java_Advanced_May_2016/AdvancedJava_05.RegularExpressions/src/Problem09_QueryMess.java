import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Problem09_QueryMess {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (!line.equals("END")) {
            String[] queries = line.split("[&?]");
            LinkedHashMap<String, ArrayList<String>> keyValues = new LinkedHashMap<>();
            for (int i = 0; i < queries.length; i++) {
                int index = queries[i].indexOf('=');
                if (index == -1) {
                    continue;
                }
                String query = queries[i].replaceAll("\\+", " ").replaceAll("%20", " ").replaceAll("\\s+", " ");
                String[] current = query.split("=");
                String key = current[0].trim();
                String value = current[1].trim();
                if (!keyValues.containsKey(key)) {
                    keyValues.put(key, new ArrayList<>());
                }
                keyValues.get(key).add(value);
            }
            for (Map.Entry<String, ArrayList<String>> query : keyValues.entrySet()) {
                System.out.printf("%s=%s", query.getKey(), query.getValue().toString());
            }
            System.out.println();
            line = reader.readLine();
        }
    }
}
