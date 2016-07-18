package problem_10;

import problem_10.objects.Archangel;
import problem_10.objects.Demon;
import problem_10.objects.GameObjectAbstract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] params = reader.readLine().split(" \\| ");
        String userName = params[0];
        String type = params[1];
        GameObjectAbstract hero = null;
        switch (type) {
            case "Archangel":
                hero = new Archangel(userName, Integer.valueOf(params[2]), Integer.valueOf(params[3]));
                String reversedName = new StringBuilder(userName).reverse().toString();
                hero.setHashedPassword(reversedName + hero.getUserName().length() * 21);
                break;
            case "Demon":
                hero = new Demon(userName, Double.valueOf(params[2]), Integer.valueOf(params[3]));
                hero.setHashedPassword(hero.getUserName().length() * 217);
                break;
        }

        System.out.println(hero.toString());
    }
}
