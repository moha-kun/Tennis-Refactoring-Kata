import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TennisGame1 implements TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        if (m_score1 == m_score2) {
            switch (m_score1) {
                case 0 -> {
                    return "Love-All";
                }
                case 1 -> {
                    return "Fifteen-All";
                }
                case 2 -> {
                    return "Thirty-All";
                }
                default -> {
                    return "Deuce";
                }
            }
        }

        if (m_score1 >= 4 || m_score2 >= 4) {
            int scoreDelta = m_score1 - m_score2;
            if (scoreDelta == 1) return "Advantage player1";
            else if (scoreDelta == -1) return "Advantage player2";
            else if (scoreDelta >= 2) return "Win for player1";
            else return "Win for player2";
        }

        return Stream.of(m_score1, m_score2)
                .map(score -> switch(score) {
                    case 0 -> "Love";
                    case 1 -> "Fifteen";
                    case 2 -> "Thirty";
                    default -> "Forty";
                })
                .collect(Collectors.joining("-"));
    }
}
