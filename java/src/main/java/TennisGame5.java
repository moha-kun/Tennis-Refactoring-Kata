import java.util.HashMap;
import java.util.Map;

public class TennisGame5 implements TennisGame {

    private final String player1Name;
    private final String player2Name;
    private int player1Score;
    private int player2Score;

    public TennisGame5(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            player1Score++;
        else if (playerName.equals(player2Name))
            player2Score++;
        else
            throw new IllegalArgumentException("Invalid player name.");
    }

    public String getScore() {
        int score1 = player1Score;
        int score2 = player2Score;

        if (score1 > 4 || score2 > 4) {
            int maxScore = Math.max(score1, score2);
            score1 = score1 - (maxScore - 4);
            score2 = score2 - (maxScore - 4);
        }

        var allPossibleResults = new HashMap<Map.Entry, String>();
        allPossibleResults.put(Map.entry(0, 0), "Love-All");
        allPossibleResults.put(Map.entry(0, 1), "Love-Fifteen");
        allPossibleResults.put(Map.entry(0, 2), "Love-Thirty");
        allPossibleResults.put(Map.entry(0, 3), "Love-Forty");
        allPossibleResults.put(Map.entry(0, 4), "Win for " + player2Name);
        allPossibleResults.put(Map.entry(1, 0), "Fifteen-Love");
        allPossibleResults.put(Map.entry(1, 1), "Fifteen-All");
        allPossibleResults.put(Map.entry(1, 2), "Fifteen-Thirty");
        allPossibleResults.put(Map.entry(1, 3), "Fifteen-Forty");
        allPossibleResults.put(Map.entry(1, 4), "Win for " + player2Name);
        allPossibleResults.put(Map.entry(2, 0), "Thirty-Love");
        allPossibleResults.put(Map.entry(2, 1), "Thirty-Fifteen");
        allPossibleResults.put(Map.entry(2, 2), "Thirty-All");
        allPossibleResults.put(Map.entry(2, 3), "Thirty-Forty");
        allPossibleResults.put(Map.entry(2, 4), "Win for " + player2Name);
        allPossibleResults.put(Map.entry(3, 0), "Forty-Love");
        allPossibleResults.put(Map.entry(3, 1), "Forty-Fifteen");
        allPossibleResults.put(Map.entry(3, 2), "Forty-Thirty");
        allPossibleResults.put(Map.entry(3, 3), "Deuce");
        allPossibleResults.put(Map.entry(3, 4), "Advantage " + player2Name);
        allPossibleResults.put(Map.entry(4, 0), "Win for " + player1Name);
        allPossibleResults.put(Map.entry(4, 1), "Win for " + player1Name);
        allPossibleResults.put(Map.entry(4, 2), "Win for " + player1Name);
        allPossibleResults.put(Map.entry(4, 3), "Advantage " + player1Name);
        allPossibleResults.put(Map.entry(4, 4), "Deuce");

        var entry = Map.entry(score1, score2);
        if (allPossibleResults.containsKey(entry)) {
            return allPossibleResults.get(entry);
        } else {
            throw new IllegalArgumentException("Invalid score.");
        }
    }
}
