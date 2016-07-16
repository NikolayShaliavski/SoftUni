package problem_02.animals;

import java.text.DecimalFormat;

abstract class Mammal extends Animal {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.######");

    private String region;

    protected Mammal(String type, String name, Double weight, String region) {
        super(type, name, weight);
        this.setRegion(region);
    }

    protected String getRegion() {
        return this.region;
    }

    private void setRegion(String region) {
        if (region == null || region.length() == 0) {
            throw new IllegalArgumentException("Invalid region.");
        }
        this.region = region;
    }

    @Override
    public String toString() {
        String formatWeight = DECIMAL_FORMAT.format(this.getWeight());
        return String.format("%s[%s, %s, %s, %d]",
                this.getType(),
                this.getName(),
                formatWeight,
                this.getRegion(),
                this.getFoodEaten());
    }
}
