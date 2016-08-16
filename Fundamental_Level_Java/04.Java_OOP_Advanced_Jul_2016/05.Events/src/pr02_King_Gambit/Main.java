package pr02_King_Gambit;

import pr02_King_Gambit.contracts.Observable;
import pr02_King_Gambit.contracts.Observer;
import pr02_King_Gambit.models.Footman;
import pr02_King_Gambit.models.King;
import pr02_King_Gambit.models.RoyalGuard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String kingName = reader.readLine();

        Observable king = new King(kingName);

        String[] royalGuardParams = reader.readLine().split(" ");
        for (String royalGuardParam : royalGuardParams) {
            Observer royalGuard = new RoyalGuard(royalGuardParam, king);
            king.addObserver(royalGuard);
        }
        String[] footMenParams = reader.readLine().split(" ");
        for (String footMenParam : footMenParams) {
            Observer footman = new Footman(footMenParam, king);
            king.addObserver(footman);
        }

        String command = reader.readLine();
        while (!command.equals("End")) {
            if (command.equals("Attack King")) {
                king.fireAttack();
            } else {
                String[] commandParams = command.split(" ");
                String hitObserverName = commandParams[1];
                Observer hitObserver = king.getObservers().stream().
                        filter(o -> o.getName().equals(hitObserverName)).
                        findFirst().get();
                hitObserver.wasHit();
            }
            command = reader.readLine();
        }
    }
}
