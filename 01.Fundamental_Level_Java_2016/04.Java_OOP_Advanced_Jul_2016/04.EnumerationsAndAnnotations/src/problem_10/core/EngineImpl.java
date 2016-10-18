package problem_10.core;

import problem_10.IO.ConsoleReader;
import problem_10.IO.ConsoleWriter;
import problem_10.annotations.ClassInfo;
import problem_10.contracts.*;
import problem_10.enums.GemsEnum;
import problem_10.enums.WeaponsEnum;
import problem_10.factories.GemFactory;
import problem_10.factories.WeaponFactory;
import problem_10.models.InfernoRepository;
import problem_10.models.weapons.WeaponImpl;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Created by Nikolay Shalyavski on 24.7.2016 г..
 */
public class EngineImpl implements Engine {

    private Inferno infernoRepository;
    private Reader reader;
    private Writer writer;
    private boolean isRunning;

    public EngineImpl() {
        this.isRunning = true;
    }

    @Override
    public void run() {

        this.init();
        while (this.isRunning) {
            try {
                String commandParams = this.readCommand();
                this.executeCommand(commandParams);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init() {

        this.infernoRepository = new InfernoRepository();
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
    }

    @Override
    public String readCommand() throws IOException {
        return this.reader.read();
    }

    @Override
    public void executeCommand(String commandParams) {

        String[] params = commandParams.split(";");
        String command = params[0];
        switch (command) {
            case "Create":
                try {
                    WeaponsEnum weaponType = WeaponsEnum.valueOf(params[1]);
                    String weaponName = params[2];
                    Weapon weapon = WeaponFactory.createWeapon(weaponName, weaponType);
                    this.infernoRepository.addWeapon(weapon);
                } catch (NoSuchElementException nsex) {
                    return;
                }
                break;
            case "Add":
                try {
                    GemsEnum gemType = GemsEnum.valueOf(params[3]);
                    Gem gem = GemFactory.createGem(gemType);
                    String weaponName = params[1];
                    int index = Integer.valueOf(params[2]);
                    this.infernoRepository.addGemsToWeapon(weaponName, index, gem);
                } catch (NoSuchElementException nsex) {
                    return;
                }
                break;
            case "Remove":
                String weaponName = params[1];
                int index = Integer.valueOf(params[2]);
                this.infernoRepository.removeGemsFromWeapon(weaponName, index);
                break;
            case "Print":
                String name = params[1];
                String weaponStats = this.infernoRepository.reportWeapon(name);
                if (!weaponStats.equals("Weapon doesn't exists.")) {
                    this.writer.write(weaponStats);
                }
                break;
            case "Compare":
                String firstWeapon = params[1];
                String secondWeapon = params[2];
                String winner = this.infernoRepository.compareWeapons(firstWeapon, secondWeapon);
                if (!winner.equals("Weapons don't exist.")) {
                    this.writer.write(winner);
                }
                break;
            case "Author":
                ClassInfo classInfo = WeaponImpl.class.getAnnotation(ClassInfo.class);
                String author = classInfo.author();
                this.writer.write("Author: " + author);
                break;
            case "Revision":
                classInfo = WeaponImpl.class.getAnnotation(ClassInfo.class);
                int revision = classInfo.revision();
                this.writer.write("Revision: " + revision);
                break;
            case "Description":
                classInfo = WeaponImpl.class.getAnnotation(ClassInfo.class);
                String description = classInfo.description();
                this.writer.write("Class description: " + description);
                break;
            case "Reviewers":
                classInfo = WeaponImpl.class.getAnnotation(ClassInfo.class);
                String[] reviewersArr = classInfo.reviewers();
                String reviewers = String.join(", ", reviewersArr);
                this.writer.write("Reviewers: " + reviewers);
                break;
            case "END":
                this.isRunning = false;
                break;
        }
    }
}
