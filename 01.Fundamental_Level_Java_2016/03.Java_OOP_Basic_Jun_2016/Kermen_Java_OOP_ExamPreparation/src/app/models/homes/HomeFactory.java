package app.models.homes;

import app.IO.Writer;
import app.models.devices.Device;
import app.models.people.Child;
import app.models.people.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nick on 8.7.2016 г..
 */
public class HomeFactory {

    //Factory have NOT contain any fields
    Person[] people = null;
    Device[] devices = null;
    Child[] children = null;

    public Home createNewHome(String homeType, String line) {

        Pattern pattern = Pattern.compile("[\\d.]+");
        Home home = null;

        try {
            switch (homeType) {
                case "YoungCouple":
                    fillYoungHome(pattern, line);
                    home = new YoungCoupleHome(this.people, this.devices);
                    break;
                case "YoungCoupleWithChildren":
                    String[] homeTokens = line.split(" Child\\(");
                    fillYoungHome(pattern, homeTokens[0]);
                    createChildren(homeTokens);
                    home = new YoungCoupleWithChildrenHome(this.people, this.devices, this.children);
                    break;
                case "OldCouple":
                    fillOldHome(pattern, line);
                    home = new OldCoupleHome(this.people, this.devices);
                    break;
                case "AloneYoung":
                    fillAloneYoungHome(pattern, line);
                    home = new AloneYoungHome(this.people, this.devices);
                    break;
                case "AloneOld":
                    fillAloneOldHome(pattern, line);
                    home = new AloneOldHome(this.people);
                    break;
            }
        } catch (IllegalArgumentException iaex) {
            Writer.write(iaex.getMessage());
        }
            return home;
        }

    private void fillYoungHome(Pattern pattern, String line) {
        Matcher matcher = pattern.matcher(line);
        List<Double> tokens = new ArrayList<>();

        while (matcher.find()) {
            tokens.add(Double.valueOf(matcher.group()));
        }

        this.people = new Person[]{new Person(tokens.get(0)), new Person(tokens.get(1))};
        this.devices = new Device[]{
                new Device(tokens.get(2)),
                new Device(tokens.get(3)),
                new Device(tokens.get(4)),
                new Device(tokens.get(4))
        };
    }

    private void createChildren(String[] homeTokens) {
        this.children = new Child[homeTokens.length - 1];
        for (int i = 0; i < this.children.length; i++) {
            this.children[i] = new Child(homeTokens[i + 1]);
        }
    }

    private void fillOldHome(Pattern pattern, String line) {
        Matcher matcher = pattern.matcher(line);
        List<Double> tokens = new ArrayList<>();

        while (matcher.find()) {
            tokens.add(Double.valueOf(matcher.group()));
        }

        this.people = new Person[]{new Person(tokens.get(0)), new Person(tokens.get(1))};
        this.devices = new Device[]{
                new Device(tokens.get(2)),
                new Device(tokens.get(3)),
                new Device(tokens.get(4))
        };
    }

    private void fillAloneYoungHome(Pattern pattern, String line) {
        Matcher matcher = pattern.matcher(line);
        List<Double> tokens = new ArrayList<>();

        while (matcher.find()) {
            tokens.add(Double.valueOf(matcher.group()));
        }

        this.people = new Person[]{new Person(tokens.get(0))};
        this.devices = new Device[]{new Device(tokens.get(1))};
    }

    private void fillAloneOldHome(Pattern pattern, String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            this.people = new Person[]{new Person(Double.valueOf(matcher.group()))};
        }
    }
}
