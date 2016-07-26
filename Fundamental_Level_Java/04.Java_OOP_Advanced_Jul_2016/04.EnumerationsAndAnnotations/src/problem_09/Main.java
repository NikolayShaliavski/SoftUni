package problem_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] lightValues = reader.readLine().split("[\\s]+");
        Light[] lights = new Light[lightValues.length];

        for (int i = 0; i < lights.length; i++) {
            lights[i] = Light.valueOf(lightValues[i]);
        }
        int timesToChangeLights = Integer.valueOf(reader.readLine());

        for (int i = 0; i < timesToChangeLights; i++) {
            for (int j = 0; j < lights.length; j++) {
                lights[j] = lights[j].next();
                System.out.print(lights[j] + " ");
            }
            System.out.println();
        }
    }
}
