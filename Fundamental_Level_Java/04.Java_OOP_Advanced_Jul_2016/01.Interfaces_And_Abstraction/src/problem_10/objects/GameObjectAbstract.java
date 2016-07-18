package problem_10.objects;

import problem_10.interfaces.GameObject;

public abstract class GameObjectAbstract<T, E extends Number> implements GameObject<T> {

    private String userName;
    private T hashedPassword;
    protected E specialPoints;
    private int level;

    GameObjectAbstract(String userName,
                       E specialPoints,
                       int level) {
        this.setUserName(userName);
        this.setLevel(level);
        this.setSpecialPoints(specialPoints);
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    protected void setSpecialPoints(E specialPoints) {
        this.specialPoints = specialPoints;
    }

    @Override
    public Integer getLevel() {
        return this.level;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    @Override
    public T getHashedPassword() {
        return this.hashedPassword;
    }

    @Override
    public void setHashedPassword(T element) {
        this.hashedPassword = element;
    }

    @Override
    public String toString() {
        String type = this.getClass().getSimpleName();
        return String.format("\"%s\" | \"%s\" -> %s%n",
                this.userName,
                this.hashedPassword,
                type);
    }
}
