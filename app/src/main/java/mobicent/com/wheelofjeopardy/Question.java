package mobicent.com.wheelofjeopardy;

import java.util.ArrayList;
import java.util.Random;
/**
 * Created by gkuruc on 7/15/15.
 */
public class Question
{
    String question, correctAnswer;
    ArrayList<String> incorrectAnswers = new ArrayList<>();
    int pointValue;
    int correctOption;

    public String getQuestion()
    {
        return question;
    }

    public CharSequence[] getOptions()
    {
        CharSequence[] options = new CharSequence[4];
        Random g = new Random();
        correctOption = g.nextInt(4);
        options[correctOption] = correctAnswer;

        int j = 0;
        for (int i = 0; i < 4; i++)
        {
            if (i != correctOption)
            {
                options[i] = incorrectAnswers.get(j);
                j++;
            }
        }
        return options;
    }

    public int getCorrectOption()
    {
        return correctOption;
    }

    public int getPointValue()
    {
        return pointValue;
    }
}
