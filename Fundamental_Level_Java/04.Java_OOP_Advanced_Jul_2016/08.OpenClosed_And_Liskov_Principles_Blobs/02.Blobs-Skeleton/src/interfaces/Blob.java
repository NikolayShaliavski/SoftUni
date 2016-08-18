package interfaces;

public interface Blob {

    int getHealth();

    void setHealth(int health);

    int getDamage();

    void setDamage(int currentDamage);

    String getName();

    Behavior getBehavior();

    String update();

    void attack(Blob target);
}
