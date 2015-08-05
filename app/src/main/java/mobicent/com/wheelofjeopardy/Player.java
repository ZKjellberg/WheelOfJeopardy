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
        tokens = 1;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getTokens() {
        return tokens;
    }

    public void addToken() {
        tokens++;
    }

    public void removeToken() { tokens--; }

    public int getRoundScore() {
        return roundScore;
    }

    public void increaseRoundScore(int points) {
        roundScore += points;
        score += points;
    }

    public void decreaseRoundScore(int points) {
        roundScore -= points;
        score -= points;
    }

    public void resetRoundScore() {
        roundScore = 0;
    }

    public void bankrupt() {
        score -= roundScore;
        roundScore = 0;
    }
}
