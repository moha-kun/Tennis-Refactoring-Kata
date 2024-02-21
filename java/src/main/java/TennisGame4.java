public class TennisGame4 implements TennisGame {

    private int serverScore;
    private int receiverScore;
    private final String server;
    private final String receiver;

    public TennisGame4(String player1, String player2) {
        this.server = player1;
        this.receiver = player2;
    }

    public void wonPoint(String playerName) {
        if (server.equals(playerName))
            this.serverScore += 1;
        else
            this.receiverScore += 1;
    }

    public String getScore() {
        ResultProvider defaultResult = new DefaultResult(this);
        ResultProvider advantageReceiver = new AdvantageReceiver(this, defaultResult);
        ResultProvider advantageServer = new AdvantageServer(this, advantageReceiver);
        ResultProvider gameReceiver = new GameReceiver(this, advantageServer);
        ResultProvider gameServer = new GameServer(this, gameReceiver);
        ResultProvider deuce = new Deuce(this, gameServer);
        TennisResult result = deuce.getResult();
        return result.format();
    }

    boolean receiverHasAdvantage() {
        return receiverScore >= 4 && (receiverScore - serverScore) == 1;
    }

    boolean serverHasAdvantage() {
        return serverScore >= 4 && (serverScore - receiverScore) == 1;
    }

    boolean receiverHasWon() {
        return receiverScore >= 4 && (receiverScore - serverScore) >= 2;
    }

    boolean serverHasWon() {
        return serverScore >= 4 && (serverScore - receiverScore) >= 2;
    }

    boolean isDeuce() {
        return serverScore >= 3 && receiverScore >= 3 && (serverScore == receiverScore);
    }

    public String getServer() {
        return server;
    }

    public String getReceiver() {
        return receiver;
    }

    public int getServerScore() {
        return serverScore;
    }

    public int getReceiverScore() {
        return receiverScore;
    }
}

class TennisResult {
    private final String serverScore;
    private final String receiverScore;

    TennisResult(String serverScore, String receiverScore) {
        this.serverScore = serverScore;
        this.receiverScore = receiverScore;
    }

    String format() {
        if (receiverScore.isEmpty())
            return serverScore;
        if (serverScore.equals(receiverScore))
            return serverScore + "-All";
        return serverScore + "-" + receiverScore;
    }
}

interface ResultProvider {
    TennisResult getResult();
}

class Deuce implements ResultProvider {
    private final TennisGame4 game;
    private final ResultProvider nextResult;

    public Deuce(TennisGame4 game, ResultProvider nextResult) {
        this.game = game;
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        if (game.isDeuce())
            return new TennisResult("Deuce", "");
        return nextResult.getResult();
    }
}

class GameServer implements ResultProvider {
    private final TennisGame4 game;
    private final ResultProvider nextResult;

    public GameServer(TennisGame4 game, ResultProvider nextResult) {
        this.game = game;
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        if (game.serverHasWon())
            return new TennisResult("Win for " + game.getServer(), "");
        return nextResult.getResult();
    }
}

class GameReceiver implements ResultProvider {
    private final TennisGame4 game;
    private final ResultProvider nextResult;

    public GameReceiver(TennisGame4 game, ResultProvider nextResult) {
        this.game = game;
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        if (game.receiverHasWon())
            return new TennisResult("Win for " + game.getReceiver(), "");
        return nextResult.getResult();
    }
}

class AdvantageServer implements ResultProvider {
    private final TennisGame4 game;
    private final ResultProvider nextResult;

    public AdvantageServer(TennisGame4 game, ResultProvider nextResult) {
        this.game = game;
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        if (game.serverHasAdvantage())
            return new TennisResult("Advantage " + game.getServer(), "");
        return nextResult.getResult();
    }
}

class AdvantageReceiver implements ResultProvider {

    private final TennisGame4 game;
    private final ResultProvider nextResult;

    public AdvantageReceiver(TennisGame4 game, ResultProvider nextResult) {
        this.game = game;
        this.nextResult = nextResult;
    }

    @Override
    public TennisResult getResult() {
        if (game.receiverHasAdvantage())
            return new TennisResult("Advantage " + game.getReceiver(), "");
        return this.nextResult.getResult();
    }
}

class DefaultResult implements ResultProvider {

    private static final String[] scores = {"Love", "Fifteen", "Thirty", "Forty"};

    private final TennisGame4 game;

    public DefaultResult(TennisGame4 game) {
        this.game = game;
    }

    @Override
    public TennisResult getResult() {
        return new TennisResult(scores[game.getServerScore()], scores[game.getReceiverScore()]);
    }
}
