package mobicent.com.wheelofjeopardy;

/**
 * Created by gkuruc on 7/15/15.
 */
public class Question
{
    String question, correctAnswer, incorrectAnswer1, incorrectAnswer2, incorrectAnswer3;
    int pointValue;

    public Question(String question, String correctAnswer,
                    String incorrectAnswer1,String incorrectAnswer2, String incorrectAnswer3,
                    int pointValue)
    {
        question = this.question;
        correctAnswer = this.correctAnswer;
        incorrectAnswer1 = this.incorrectAnswer1;
        incorrectAnswer2 = this.incorrectAnswer2;
        incorrectAnswer3 = this.incorrectAnswer3;
        pointValue = this.pointValue;
    }

    
}
