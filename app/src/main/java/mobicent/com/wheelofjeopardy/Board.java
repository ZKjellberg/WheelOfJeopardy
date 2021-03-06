package mobicent.com.wheelofjeopardy;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by gkuruc on 7/19/15.
 */
public class Board
{
    ArrayList<Category> categories = new ArrayList<>();

    public Board(InputStream stream)
    {
        XmlPullParserFactory pullParserFactory;
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(stream, null);

            parseXML(parser);
        }
        catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseXML(XmlPullParser parser) throws XmlPullParserException,IOException
    {
        int eventType = parser.getEventType();
        Category currentCategory = null;
        Question currentQuestion = null;

        int i = 1;

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("category")){
                        currentCategory = new Category(i);
                        i++;
                    }
                    else if (currentCategory != null)
                    {
                        if (name.equalsIgnoreCase("name")){
                            currentCategory.name = parser.nextText();
                        } else if (name.equalsIgnoreCase("box")){
                            currentQuestion = new Question();
                        }
                        else if (currentQuestion != null)
                        {
                            if (name.equalsIgnoreCase("points")){
                                currentQuestion.pointValue = Integer.parseInt(parser.nextText());
                            }
                            else if (name.equalsIgnoreCase("question")){
                                currentQuestion.question = parser.nextText();
                            }
                            else if (name.equalsIgnoreCase("correct")){
                                currentQuestion.correctAnswer = parser.nextText();
                            }
                            else if (name.equalsIgnoreCase("incorrect")){
                                currentQuestion.incorrectAnswers.add(parser.nextText());
                            }
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("box") && currentQuestion != null){
                        currentCategory.questions.add(currentQuestion);
                    }
                    else if (name.equalsIgnoreCase("category") && currentCategory != null){
                        categories.add(currentCategory);
                    }
            }
            eventType = parser.next();
        }
    }

    public ArrayList<Category> getCategories()
    {
        return categories;
    }

    public Category getCategory(int catNumber)
    {
        return categories.get(catNumber);
    }
}
