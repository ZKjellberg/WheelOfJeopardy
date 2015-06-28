package mobicent.com.wheelofjeopardy;

/**
 * Created by gkuruc on 6/28/15.
 */
public class Team
{
    String name;
    int score;
    int tokens;

    public Team(String n)
    {
        name = n;
        score = 0;
        tokens = 0;
    }

    public String getName()
    {
        return name;
    }

    public int getScore()
    {
        return score;
    }

    public void updateScore(int points)
    {
        score += points;
    }

    public int getTokens()
    {
        return tokens;
    }

    public void addToken()
    {
        tokens++;
    }
}
