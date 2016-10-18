package problem_10.objects;

public class Archangel extends GameObjectAbstract<String, Integer> {

    public Archangel(String userName,
              int specialPoints,
              int level) {
        super(userName, specialPoints, level);
    }

    protected void setSpecialPoints(Integer specialPoints) {
        this.specialPoints = this.getLevel() * specialPoints;
    }

    @Override
    public String toString() {
        return super.toString() + this.getLevel() * this.specialPoints;
    }
}
