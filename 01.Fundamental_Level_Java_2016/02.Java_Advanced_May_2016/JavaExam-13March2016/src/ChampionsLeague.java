import java.util.*;

public class ChampionsLeague {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        String line = console.nextLine();
        HashMap<String, TreeSet<String>> opponents = new HashMap<>();
        TreeMap<String, Integer> league = new TreeMap<>();

        while (!line.equals("stop")) {
            String[] play = line.split(" \\| ");
            String team1 = play[0];
            String team2 = play[1];
            String[] result1 = play[2].split("\\:");
            String[] result2 = play[3].split("\\:");

            int winTeam1 = 0;
            int winTeam2 = 0;

            int score1Home = Integer.parseInt(result1[0]);
            int score1Guest = Integer.parseInt(result2[1]);
            int score2Home = Integer.parseInt(result2[0]);
            int score2Guest = Integer.parseInt(result1[1]);

            if ((score1Home + score1Guest) > (score2Home + score2Guest)) {
                winTeam1 = 1;
            } else if ((score1Home + score1Guest) < (score2Home + score2Guest)) {
                winTeam2 = 1;
            } else {
                if (score1Guest > score2Guest) {
                    winTeam1 = 1;
                } else {
                    winTeam2 = 1;
                }
            }

            if (!opponents.containsKey(team1)) {
                opponents.put(team1, new TreeSet<>());
            }
            if (!opponents.containsKey(team2)) {
                opponents.put(team2, new TreeSet<>());
            }
            opponents.get(team1).add(team2);
            opponents.get(team2).add(team1);

            if (!league.containsKey(team1)) {
                league.put(team1, 0);
            }
            if (!league.containsKey(team2)) {
                league.put(team2, 0);
            }
            league.put(team1, league.get(team1) + winTeam1);
            league.put(team2, league.get(team2) + winTeam2);

            line = console.nextLine();
        }
        league.entrySet().stream().sorted((k1, k2) -> k2.getValue().compareTo(k1.getValue())).
                forEach(k -> System.out.printf("%s\n- Wins: %d\n- Opponents: %s\n", k.getKey(), k.getValue(),
                        opponents.get(k.getKey()).toString().replaceAll("\\[", "").replaceAll("\\]", "")));

    }
}
