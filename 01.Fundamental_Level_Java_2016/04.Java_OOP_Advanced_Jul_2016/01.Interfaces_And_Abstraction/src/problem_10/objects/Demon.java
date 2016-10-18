package problem_10.objects;

public class Demon extends GameObjectAbstract<Integer, Double> {

    public Demon(String userName,
                 double specialPoints,
                 int level) {
        super(userName, specialPoints, level);
    }

    protected void setSpecialPoints(Double specialPoints) {
        this.specialPoints = this.getLevel() * specialPoints;
    }

    @Override
    public String toString() {
        return super.toString() + this.getLevel() * this.specialPoints;
    }
}
