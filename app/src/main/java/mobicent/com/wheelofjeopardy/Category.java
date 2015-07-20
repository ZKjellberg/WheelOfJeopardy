package mobicent.com.wheelofjeopardy;

import java.util.ArrayList;
/**
 * Created by gkuruc on 7/19/15.
 */
public class Category
{
    public String name;
    ArrayList<Question> questions = new ArrayList<Question>();
    int questionNumber = 0;

    public Category()
    {
    }

    public Question getNextQuestion()
    {
        if(questionNumber < 6) {
            questionNumber++;
            return questions.get(questionNumber);
        } else {
            return null;
        }
    }
}
