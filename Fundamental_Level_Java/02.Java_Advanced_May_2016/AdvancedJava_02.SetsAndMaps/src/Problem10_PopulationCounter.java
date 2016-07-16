import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem10_PopulationCounter {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        LinkedHashMap<String, LinkedHashMap<String, Integer>> report = new LinkedHashMap<>();
        String line = null;

        while (!(line = scan.nextLine()).equals("report")) {
            String[] currentLine = line.split("\\|");

            String country = currentLine[1];
            String city = currentLine[0];
            Integer population = Integer.parseInt(currentLine[2]);

            if (!report.containsKey(country)) {
                report.put(country, new LinkedHashMap<>());
            }
            report.get(country).put(city, population);
        }
        report.entrySet().stream().sorted((k1, k2) -> {
            Integer result = getPopulation(k2.getValue()).compareTo(getPopulation(k1.getValue()));
            return result;
        }).forEach(k1 -> printCities(k1.getKey(), k1.getValue()));

    }

    private static Long getPopulation(LinkedHashMap<String, Integer> currentCountry) {
        Long population = 0L;
        for (Integer people : currentCountry.values()) {
            population += people;
        }
        return population;
    }

    private static void printCities(String country, LinkedHashMap<String, Integer> cities) {
        System.out.printf("%s (total population: %d)%n", country, getPopulation(cities));

        cities.entrySet().stream().sorted((k1, k2) -> k2.getValue().compareTo(k1.getValue())).
                forEach(k1 -> System.out.printf("=>%s: %d%n", k1.getKey(), k1.getValue()));
    }
}
