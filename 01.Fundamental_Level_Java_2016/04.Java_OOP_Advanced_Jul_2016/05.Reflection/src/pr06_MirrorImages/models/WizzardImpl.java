package pr06_MirrorImages.models;

import pr06_MirrorImages.contracts.Wizzard;
import pr06_MirrorImages.enums.Spells;
import pr06_MirrorImages.io.ConsoleWriter;

import java.util.ArrayList;
import java.util.List;

public class WizzardImpl implements Wizzard {

    private static int totalNumberOfReflections = 0;

    private String name;
    private int ID;
    private int power;

    private List<Wizzard> mirrors;

    public WizzardImpl(String name,
                       int power) {
        this.name = name;
        this.power = power;
        this.mirrors = new ArrayList<>();
        this.ID = totalNumberOfReflections;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public Wizzard searchWizzard(int ID) {
        Wizzard wizzard = null;
        if (this.getID() == ID) {
            wizzard = this;
            return wizzard;
        }
        for (Wizzard mirror : this.mirrors) {
            wizzard = mirror.searchWizzard(ID);
            if (wizzard != null) {
                return wizzard;
            }
        }
        return wizzard;
    }

    @Override
    public void castSpell(Spells spellType) {
        switch (spellType) {
            case REFLECTION:
                this.castReflection();
                break;
            case FIREBALL:
                this.castFireball();
                break;
        }
    }

    @Override
    public void castReflection() {
        ConsoleWriter.write(String.format("%s %d casts Reflection",
                this.name,
                this.ID));
        if (this.mirrors.size() < 2) {
            this.totalNumberOfReflections++;
            Wizzard firstReflection = new WizzardImpl(
                    this.name, this.power / 2);
            this.totalNumberOfReflections++;
            Wizzard secondReflection = new WizzardImpl(
                    this.name, this.power / 2);
            this.mirrors.add(firstReflection);
            this.mirrors.add(secondReflection);
            return;
        }
        for (Wizzard mirror : this.mirrors) {
            mirror.castReflection();
        }
    }

    @Override
    public void castFireball() {
        ConsoleWriter.write(String.format("%s %d casts Fireball for %d damage",
                this.name,
                this.ID,
                this.power));
        for (Wizzard mirror : this.mirrors) {
            mirror.castFireball();
        }
    }
}
