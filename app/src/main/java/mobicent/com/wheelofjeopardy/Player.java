package mobicent.com.wheelofjeopardy;

/**
 * Created by gkuruc on 6/28/15.
 */
public class Player {
    String name;
    int score;
    int roundScore;
    int tokens;

    public Player(String name) {
        this.name = name;
        score = 0;
        roundScore = 0;
        tokens = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int points) {
        score += points;
    }

    public int getTokens() {
        return tokens;
    }

    public void addToken() {
        tokens++;
    }

    public int getRoundScore() {
        return roundScore;
    }

    public void increaseRoundScore(int points) {
        roundScore += points;
    }

    public void decreaseRoundScore(int points) {
        roundScore -= points;
    }

    public void resetRoundScore() {
        roundScore = 0;
    }
}
