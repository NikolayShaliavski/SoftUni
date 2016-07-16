package problem_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P03_TemperatureConverter {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (!line.equals("End")) {
            String[] tokens = line.split("[\\s]+");
            int temperature = Integer.parseInt(tokens[0]);
            String celsiusOrFahrenheit = tokens[1];

            if (celsiusOrFahrenheit.equals("Celsius")) {
                double tempInFahrenheit = Converter.convertToFahrenheit(temperature);
                System.out.printf("%.2f Fahrenheit%n", tempInFahrenheit);
            } else {
                double tempInCelsius = Converter.convertToCelsius(temperature);
                System.out.printf("%.2f Celsius%n", tempInCelsius);
            }

            line = reader.readLine();
        }
    }
}
class Converter {

    public static double convertToFahrenheit(int temperature) {
        return temperature * 1.8 + 32;
    }

    public static double convertToCelsius(int temperature) {
        return (temperature - 32) / 1.8;
    }
}