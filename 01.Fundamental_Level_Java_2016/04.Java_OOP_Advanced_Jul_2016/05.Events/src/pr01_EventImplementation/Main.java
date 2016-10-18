package pr01_EventImplementation;

import pr01_EventImplementation.models.Dispatcher;
import pr01_EventImplementation.models.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Dispatcher dispatcher = new Dispatcher(null);
        Handler handler = new Handler();

        dispatcher.addNameChangeListener(handler);


        String name = reader.readLine();
        while (!name.equals("End")) {

            dispatcher.setName(name);
            name = reader.readLine();
        }
    }
}
