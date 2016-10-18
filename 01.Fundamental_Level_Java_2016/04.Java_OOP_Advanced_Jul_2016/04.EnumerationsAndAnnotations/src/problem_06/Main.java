package problem_06;

import problem_06.annotations.CustomEnumAnnotation;
import problem_06.enumerations.Rank;
import problem_06.enumerations.Suit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String category = reader.readLine();

        if (category.equalsIgnoreCase("rank")) {
            Annotation annotation = Rank.class.getAnnotation(CustomEnumAnnotation.class);
            CustomEnumAnnotation customAnnotation = (CustomEnumAnnotation) annotation;
            System.out.println(String.format("Type = %s, Description = %s%n",
                    customAnnotation.type(),
                    customAnnotation.description()));
        } else if (category.equalsIgnoreCase("suit")) {
            Annotation annotation = Suit.class.getAnnotation(CustomEnumAnnotation.class);
            CustomEnumAnnotation customAnnotation = (CustomEnumAnnotation) annotation;
            System.out.println(String.format("Type = %s, Description = %s%n",
                    customAnnotation.type(),
                    customAnnotation.description()));
        }
    }
}
