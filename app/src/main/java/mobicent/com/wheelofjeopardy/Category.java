package mobicent.com.wheelofjeopardy;

import java.util.ArrayList;
/**
 * Created by gkuruc on 7/19/15.
 */
public class Category
{
    public String name;
    ArrayList<Question> questions = new ArrayList<>();
    int questionNumber = 0;
    int category;

    public Category(int cat)
    {
        category = cat;
    }

    public Question getNextQuestion()
    {
        if(questionNumber < 5) {
            Question nextQuestion = questions.get(questionNumber);
            questionNumber++;
            return nextQuestion;
        } else {
            return null;
        }
    }

    public int getCategoryNumber()
    {
        return category;
    }
}
