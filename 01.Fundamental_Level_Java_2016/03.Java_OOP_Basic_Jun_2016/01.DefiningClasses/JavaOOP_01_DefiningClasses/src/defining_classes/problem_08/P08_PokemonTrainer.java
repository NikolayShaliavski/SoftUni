package defining_classes.problem_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P08_PokemonTrainer {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<Trainer> trainers = new LinkedHashSet<>();

        String line = reader.readLine();
        while (!line.equals("Tournament")) {
            String[] tokens = line.split("[\\s]+");
            String trainerName = tokens[0];
            String pokemonName = tokens[1];
            String element = tokens[2];
            int health = Integer.valueOf(tokens[3]);

            Pokemon pokemon = new Pokemon(pokemonName, element, health);

            boolean trainerNotExist = true;
            for (Trainer currentTrainer : trainers) {
                if (currentTrainer.getName().equals(trainerName)) {
                    currentTrainer.getPokemons().add(pokemon);
                    trainerNotExist = false;
                }
            }
            if (trainerNotExist) {
                Trainer trainer = new Trainer(trainerName);
                trainer.getPokemons().add(pokemon);
                trainers.add(trainer);
            }
            line = reader.readLine();
        }
        line = reader.readLine();
        while (!line.equals("End")) {
            boolean decreaseHealth = true;
            for (Trainer trainer : trainers) {
                for (Pokemon pokemon : trainer.getPokemons()) {
                    if (pokemon.getElement().equals(line)) {
                        trainer.setBadges(trainer.getBadges() + 1);
                        decreaseHealth = false;
                        break;
                    }
                }
                if (decreaseHealth) {
                    for (Pokemon pokemon : trainer.getPokemons()) {
                        pokemon.setHealth(pokemon.getHealth() - 10);
                    }
                }
            }
            List<Pokemon> pokemonsToRemove = new ArrayList<>();
            for (Trainer trainer : trainers) {
                for (Pokemon pokemon : trainer.getPokemons()) {
                    if (pokemon.getHealth() <= 0) {
                        pokemonsToRemove.add(pokemon);
                    }
                }
                trainer.getPokemons().removeAll(pokemonsToRemove);
            }
            line = reader.readLine();
        }

        trainers.stream()
                .sorted((trainer1, trainer2) -> Integer.compare(trainer2.getBadges(), trainer1.getBadges()))
                .forEach(trainer -> System.out.printf("%s %d %d%n",
                        trainer.getName(),
                        trainer.getBadges(),
                        trainer.getPokemons().size()));
    }
}

class Trainer {
    String name;
    int badges;
    Set<Pokemon> pokemons;

    public Trainer(String name) {
        this.name = name;
        this.badges = 0;
        this.pokemons = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public int getBadges() {
        return badges;
    }

    public Set<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setBadges(int badges) {
        this.badges = badges;
    }
}

class Pokemon {
    String name;
    String element;
    int health;

    public Pokemon(String name, String element, int health) {
        this.name = name;
        this.element = element;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public String getElement() {
        return element;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}