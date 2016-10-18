package pr06_MirrorImages;

import pr06_MirrorImages.contracts.Wizzard;
import pr06_MirrorImages.enums.Spells;
import pr06_MirrorImages.io.ConsoleReader;
import pr06_MirrorImages.models.WizzardImpl;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String[] initWizzardParams = ConsoleReader.read().split("[\\s]+");
        String name = initWizzardParams[0];
        int power = Integer.valueOf(initWizzardParams[1]);
        Wizzard wizzard = new WizzardImpl(name, power);

        String command = ConsoleReader.read();
        while (!command.equals("END")) {
            String[] commandParams = command.split("[\\s]+");
            int ID = Integer.valueOf(commandParams[0]);
            Spells spellType = Spells.valueOf(commandParams[1]);

            Wizzard currentWizzard = wizzard.searchWizzard(ID);
            currentWizzard.castSpell(spellType);
            command = ConsoleReader.read();
        }
    }
}
