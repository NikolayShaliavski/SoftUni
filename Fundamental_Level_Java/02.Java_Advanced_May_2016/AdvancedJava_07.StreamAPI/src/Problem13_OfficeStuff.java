import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Problem13_OfficeStuff {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfLines = Integer.valueOf(reader.readLine());
        TreeMap<String, LinkedHashMap<String, Integer>> office = new TreeMap<>();

        for (int i = 0; i < numberOfLines; i++) {
            String[] line = reader.readLine().split("[\\- ]+");
            String company = line[0].substring(1);
            int amount = Integer.valueOf(line[1]);
            String product = line[2].substring(0, line[2].length() - 1);

            if (!office.containsKey(company)) {
                office.put(company, new LinkedHashMap<>());
            }
            if (!office.get(company).containsKey(product)) {
                office.get(company).put(product, amount);
            } else {
                int oldAmount = office.get(company).get(product);
                office.get(company).put(product, (amount + oldAmount));
            }
        }
        for (Map.Entry<String, LinkedHashMap<String, Integer>> company : office.entrySet()) {
            System.out.printf("%s: ", company.getKey());

            StringBuilder toPrint = new StringBuilder();
            for (Map.Entry<String, Integer> products : company.getValue().entrySet()) {
                toPrint.append(String.format("%s-%d, ", products.getKey(), products.getValue()));
            }
            toPrint.delete(toPrint.length() - 2, toPrint.length() - 1);
            System.out.println(toPrint.toString());
        }
    }
}
