package problem_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P06_FootballTeamGenerator {

    static List<Team> teams = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();
        while (!line.equals("END")) {
            String[] tokens = line.split(";");
            String command = tokens[0];
            String teamName = tokens[1];
            switch (command) {
                case "Team":
                    try {
                        Team team = new Team(teamName);
                        teams.add(team);
                    } catch (IllegalArgumentException iaex) {
                        System.out.println(iaex.getMessage());
                    }
                    break;
                case "Add":
                    Team addToTeam = getTeam(teamName);
                    if (addToTeam == null) {
                        System.out.printf("Team %s does not exist.%n", teamName);
                    } else {
                        String playerToAdd = tokens[2];
                        int endurance = Integer.valueOf(tokens[3]);
                        int sprint = Integer.valueOf(tokens[4]);
                        int dribble = Integer.valueOf(tokens[5]);
                        int passing = Integer.valueOf(tokens[6]);
                        int shooting = Integer.valueOf(tokens[7]);
                        try {
                            Stats stats = new Stats(endurance, sprint, dribble, passing, shooting);
                            double playerStat = stats.getAverageStat();
                            Player player = new Player(playerToAdd, playerStat);
                            addToTeam.addPlayer(player);
                        } catch (IllegalArgumentException iaex) {
                            System.out.println(iaex.getMessage());
                        }
                    }
                    break;
                case "Remove":
                    Team removeFromTeam = getTeam(teamName);
                    if (removeFromTeam == null) {
                        System.out.printf("Team %s does not exist.%n", teamName);
                    } else {
                        String playerToRemove = tokens[2];
                        removeFromTeam.removePlayer(playerToRemove);
                    }
                    break;
                case "Rating":
                    Team team = getTeam(teamName);
                    if (team == null) {
                        System.out.printf("Team %s does not exist.%n", teamName);
                    } else {
                        double teamRating = team.getRating();
                        System.out.printf("%s - %d%n", team.getName(), Math.round(teamRating));
                    }
                    break;
            }
            line = reader.readLine();
        }
    }

    private static Team getTeam(String teamName) {
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                return team;
            }
        }
        return null;
    }
}

class Team {

    private String name;
    private List<Player> players;

    public Team(String name) {
        this.setName(name);
        this.players = new ArrayList<>();
    }

    private void setName(String name) {
        if (name.isEmpty() || name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private List<Player> getPlayers() {
        return this.players;
    }

    public void addPlayer(Player newPlayer) {
        this.players.add(newPlayer);
    }

    public void removePlayer(String playerName) {
        for (Player player : this.getPlayers()) {
            if (player.getName().equals(playerName)) {
                this.players.remove(player);
                return;
            }
        }
        System.out.printf("Player %s is not in %s team.%n", playerName, this.getName());
    }

    public double getRating() {
        if (this.getPlayers().size() == 0) {
            return 0;
        }
        double allPlayersStats = 0;
        for (Player player : players) {
            allPlayersStats += player.getStat();
        }
        return allPlayersStats / this.getPlayers().size();
    }
}

class Player {

    private String name;
    private double stat;

    public Player(String name, double stat) {
        this.setName(name);
        this.setStat(stat);
    }

    private void setName(String name) {
        if (name.isEmpty() || name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    private void setStat(double stat) {
        if (stat < 0) {
            throw new IllegalArgumentException("Player stat cannot be negative.");
        }
        this.stat = stat;
    }

    public String getName() {
        return this.name;
    }

    public double getStat() {
        return this.stat;
    }
}

class Stats {

    private int endurance;
    private int sprint;
    private int dribble;
    private int passing;
    private int shooting;

    public Stats(int endurance, int sprint, int dribble, int passing, int shooting) {
        this.setEndurance(endurance);
        this.setSprint(sprint);
        this.setDribble(dribble);
        this.setPassing(passing);
        this.setShooting(shooting);
    }

    private void setEndurance(int endurance) {
        if (endurance < 0 || endurance > 100) {
            throw new IllegalArgumentException("Endurance should be between 0 and 100.");
        }
        this.endurance = endurance;
    }

    private void setSprint(int sprint) {
        if (sprint < 0 || sprint > 100) {
            throw new IllegalArgumentException("Sprint should be between 0 and 100.");
        }
        this.sprint = sprint;
    }

    private void setDribble(int dribble) {
        if (dribble < 0 || dribble > 100) {
            throw new IllegalArgumentException("Dribble should be between 0 and 100.");
        }
        this.dribble = dribble;
    }

    private void setPassing(int passing) {
        if (passing < 0 || passing > 100) {
            throw new IllegalArgumentException("Passing should be between 0 and 100.");
        }
        this.passing = passing;
    }

    private void setShooting(int shooting) {
        if (shooting < 0 || shooting > 100) {
            throw new IllegalArgumentException("Shooting should be between 0 and 100.");
        }
        this.shooting = shooting;
    }

    public double getAverageStat() {
        return (this.endurance + this.sprint + this.dribble + this.passing + this.shooting) / 5.0;
    }
}