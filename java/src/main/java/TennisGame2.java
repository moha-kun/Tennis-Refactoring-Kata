import java.util.Map;

public class TennisGame2 implements TennisGame {
    public int player1Score = 0;
    public int player2Score = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        if (player1Score == player2Score) {
            if (player1Score < 3)
                return getResult(player1Score) + "-All";
            return "Deuce";
        }

        if (player1Score < 4 && player2Score < 4)
            return getResult(player1Score) + "-" + getResult(player2Score);

        if (Math.abs(player1Score - player2Score) >= 2)
            return "Win for " + getWinner();

        if (player1Score > player2Score)
            return "Advantage " + player1Name;

        return "Advantage " + player2Name;
    }

    // change name from setP1Score to addPlayer1Score
    public void addPlayer1Score(int number) {
        player1Score += number;
    }

    // change name from setP2Score to addPlayer2Score
    public void addPlayer2Score(int number) {
            player2Score += number;
    }

    public void wonPoint(String player) {
        if (player.equals(player1Name))
            player1Score++;
        else
            player2Score++;
    }

    private String getWinner() {
        if (player1Score > player2Score)
            return player1Name;
        return player2Name;
    }

    private String getResult(int score) {
        Map<Integer, String> map = Map.of(
            0, "Love",
            1, "Fifteen",
            2, "Thirty",
            3, "Forty"
        );
        return map.get(score);
    }
}