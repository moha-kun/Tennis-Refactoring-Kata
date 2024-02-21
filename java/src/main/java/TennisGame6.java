public class TennisGame6 implements TennisGame {
    private final String player1Name;
    private final String player2Name;
    private int player1Score;
    private int player2Score;

    public TennisGame6(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            player1Score++;
        else
            player2Score++;

    }

    public String getScore() {
        String result;

        // tie score
        if (player1Score == player2Score)
            return getTieScore();

        // end-game score
        if (player1Score >= 4 || player2Score >= 4)
            return getEndGameScore();

        // regular score
        return getRegularScore(player1Score) + "-" + getRegularScore(player2Score);
    }

    private String getTieScore() {
        return switch (player1Score) {
            case 0 -> "Love-All";
            case 1 -> "Fifteen-All";
            case 2 -> "Thirty-All";
            default -> "Deuce";
        };
    }

    private String getEndGameScore() {
        if (player1Score - player2Score == 1) {
            return "Advantage " + player1Name;
        } else if (player1Score - player2Score == -1) {
            return "Advantage " + player2Name;
        } else if (player1Score - player2Score >= 2) {
            return "Win for " + player1Name;
        } else {
            return "Win for " + player2Name;
        }
    }

    private String getRegularScore(int score) {
        return switch (score) {
            case 0 -> "Love";
            case 1 -> "Fifteen";
            case 2 -> "Thirty";
            default -> "Forty";
        };
    }
}
