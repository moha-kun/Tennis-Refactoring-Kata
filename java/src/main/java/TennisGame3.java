
public class TennisGame3 implements TennisGame {

    private int player1Score;
    private int player2Score;
    private final String player1Name;
    private final String player2Name;

    public TennisGame3(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        String score;
        if (player1Score < 4 && player2Score < 4 && (player1Score + player2Score) != 6) {
            score = getResult(player1Score);
            if (player1Score == player2Score)
                return score + "-All";
            return score + "-" + getResult(player2Score);
        }
        if (player1Score == player2Score)
            return "Deuce";
        score = player1Score > player2Score ? player1Name : player2Name;
        if ((player1Score - player2Score) * (player1Score - player2Score) == 1)
            return "Advantage " + score;
        return "Win for " + score;
    }

    private String getResult(int score) {
        String[] results = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
        return results[score];
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            this.player1Score += 1;
        else
            this.player2Score += 1;

    }

}
