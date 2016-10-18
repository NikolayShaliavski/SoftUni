package pr01HarvestingFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Field[] fields = RichSoilLand.class.getDeclaredFields();

        String command = reader.readLine();

        while (!command.equals("HARVEST")) {
            printMethods(command, fields);
            command = reader.readLine();
        }
    }

    private static void printMethods(String command, Field[] fields) {
        for (int i = 0; i < fields.length; i++) {
            switch (command) {
                case "private":
                    if (fields[i].getModifiers() == Modifier.PRIVATE) {
                        System.out.printf("%s %s %s%n",
                                Modifier.toString(fields[i].getModifiers()),
                                fields[i].getType().getSimpleName(),
                                fields[i].getName());
                    }
                    break;
                case "protected":
                    if (fields[i].getModifiers() == Modifier.PROTECTED) {
                        System.out.printf("%s %s %s%n",
                                Modifier.toString(fields[i].getModifiers()),
                                fields[i].getType().getSimpleName(),
                                fields[i].getName());
                    }
                    break;
                case "public":
                    if (fields[i].getModifiers() == Modifier.PUBLIC) {
                        System.out.printf("%s %s %s%n",
                                Modifier.toString(fields[i].getModifiers()),
                                fields[i].getType().getSimpleName(),
                                fields[i].getName());
                    }
                    break;
                case "all":
                    System.out.printf("%s %s %s%n",
                            Modifier.toString(fields[i].getModifiers()),
                            fields[i].getType().getSimpleName(),
                            fields[i].getName());
                    break;
            }
        }
    }
}
