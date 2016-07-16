package problem_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P04_MordorsCrueltyPlan {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] allFood = reader.readLine().toLowerCase().split("\\s+");

        Gandalf gandalf = new Gandalf();

        FoodFactory foodfactory = new FoodFactory();
        MoodFactory moodFactory = new MoodFactory();

        for (String foodName : allFood) {
            Food food = foodfactory.produceFood(foodName);
            gandalf.eat(food);
            Mood mood = moodFactory.produceMood(gandalf.getPoints());
            gandalf.setMood(mood);
        }

        System.out.println(gandalf.getPoints());
        System.out.println(gandalf.getMood());
    }
}
class Gandalf {
    private static final Integer DEFAULT_POINTS = 0;

    private Mood mood;
    private Integer points;

    public Gandalf() {
        this.points = DEFAULT_POINTS;
    }

    public Integer getPoints() {
        return this.points;
    }

    private void setPoints(Integer points) {
        this.points += points;
    }

    public String getMood() {
        return this.mood.getMoodName();
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public void eat(Food food) {
        this.setPoints(food.getPoints());
    }
}
class Food {

    private String foodName;
    private Integer points;



    public Food(String foodName, Integer points) {
        this.setFoodName(foodName);
        this.setPoints(points);
    }

    public Integer getPoints() {
        return this.points;
    }

    private void setPoints(Integer points) {
        this.points = points;
    }

    private void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodName() {
        return this.foodName;
    }
}
class Mood{

    private String moodName;

    public Mood(String moodName) {
        this.setMoodName(moodName);
    }

    public String getMoodName() {
        return this.moodName;
    }

    private void setMoodName(String moodName) {
        this.moodName = moodName;
    }


}
class FoodFactory {

    private Food food;

    public FoodFactory() {

    }

    public Food produceFood(String food) {
        if (food.equals("cram")) {
            this.food = new Food("Cram", 2);
        } else if (food.equals("lembas")) {
            this.food = new Food("Lembas", 3);
        } else if (food.equals("apple")) {
            this.food = new Food("Apple", 1);
        } else if (food.equals("melon")) {
            this.food = new Food("Melon", 1);
        } else if (food.equals("honeycake")) {
            this.food = new Food("HoneyCake", 5);
        } else if (food.equals("mushrooms")) {
            this.food = new Food("Mushrooms", -10);
        } else {
            this.food = new Food(food, -1);
        }
        return this.food;
    }
}
class MoodFactory {

    private Mood mood;

    public MoodFactory() {

    }

    public Mood produceMood(Integer points) {
        if (points < -5) {
            this.mood = new Mood("Angry");
        } else if (points >= -5 && points <= 0) {
            this.mood = new Mood("Sad");
        } else if (points > 0 && points <= 15) {
            this.mood = new Mood("Happy");
        } else {
            this.mood = new Mood("JavaScript");
        }
        return this.mood;
    }
}