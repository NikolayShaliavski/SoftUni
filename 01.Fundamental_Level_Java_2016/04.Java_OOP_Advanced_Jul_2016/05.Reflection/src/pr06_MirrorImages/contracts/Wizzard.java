package pr06_MirrorImages.contracts;

import pr06_MirrorImages.enums.Spells;

public interface Wizzard {

    void castSpell(Spells spellType);
    void castReflection();
    void castFireball();
    Wizzard searchWizzard(int ID);
    int getID();
}
