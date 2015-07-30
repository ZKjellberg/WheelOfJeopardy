package mobicent.com.wheelofjeopardy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mobicent.com.wheelofjeopardy.Category;
import mobicent.com.wheelofjeopardy.MainActivity;
import mobicent.com.wheelofjeopardy.R;


public class BoardFragment extends Fragment {

    TextView cat1, cat2, cat3, cat4, cat5, cat6;
    TextView cat1_100, cat1_200, cat1_300, cat1_400, cat1_500;
    TextView cat2_100, cat2_200, cat2_300, cat2_400, cat2_500;
    TextView cat3_100, cat3_200, cat3_300, cat3_400, cat3_500;
    TextView cat4_100, cat4_200, cat4_300, cat4_400, cat4_500;
    TextView cat5_100, cat5_200, cat5_300, cat5_400, cat5_500;
    TextView cat6_100, cat6_200, cat6_300, cat6_400, cat6_500;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_board, container, false);

        cat1 = (TextView) v.findViewById(R.id.category1title);
        cat2 = (TextView) v.findViewById(R.id.category2title);
        cat3 = (TextView) v.findViewById(R.id.category3title);
        cat4 = (TextView) v.findViewById(R.id.category4title);
        cat5 = (TextView) v.findViewById(R.id.category5title);
        cat6 = (TextView) v.findViewById(R.id.category6title);

        cat1_100 = (TextView) v.findViewById(R.id.category1_100);
        cat1_200 = (TextView) v.findViewById(R.id.category1_200);
        cat1_300 = (TextView) v.findViewById(R.id.category1_300);
        cat1_400 = (TextView) v.findViewById(R.id.category1_400);
        cat1_500 = (TextView) v.findViewById(R.id.category1_500);

        cat2_100 = (TextView) v.findViewById(R.id.category2_100);
        cat2_200 = (TextView) v.findViewById(R.id.category2_200);
        cat2_300 = (TextView) v.findViewById(R.id.category2_300);
        cat2_400 = (TextView) v.findViewById(R.id.category2_400);
        cat2_500 = (TextView) v.findViewById(R.id.category2_500);

        cat3_100 = (TextView) v.findViewById(R.id.category3_100);
        cat3_200 = (TextView) v.findViewById(R.id.category3_200);
        cat3_300 = (TextView) v.findViewById(R.id.category3_300);
        cat3_400 = (TextView) v.findViewById(R.id.category3_400);
        cat3_500 = (TextView) v.findViewById(R.id.category3_500);

        cat4_100 = (TextView) v.findViewById(R.id.category4_100);
        cat4_200 = (TextView) v.findViewById(R.id.category4_200);
        cat4_300 = (TextView) v.findViewById(R.id.category4_300);
        cat4_400 = (TextView) v.findViewById(R.id.category4_400);
        cat4_500 = (TextView) v.findViewById(R.id.category4_500);

        cat5_100 = (TextView) v.findViewById(R.id.category5_100);
        cat5_200 = (TextView) v.findViewById(R.id.category5_200);
        cat5_300 = (TextView) v.findViewById(R.id.category5_300);
        cat5_400 = (TextView) v.findViewById(R.id.category5_400);
        cat5_500 = (TextView) v.findViewById(R.id.category5_500);

        cat6_100 = (TextView) v.findViewById(R.id.category6_100);
        cat6_200 = (TextView) v.findViewById(R.id.category6_200);
        cat6_300 = (TextView) v.findViewById(R.id.category6_300);
        cat6_400 = (TextView) v.findViewById(R.id.category6_400);
        cat6_500 = (TextView) v.findViewById(R.id.category6_500);

        ArrayList<Category> categories = ((MainActivity) getActivity()).getBoard().getCategories();

        cat1.setText(categories.get(0).name);
        cat2.setText(categories.get(1).name);
        cat3.setText(categories.get(2).name);
        cat4.setText(categories.get(3).name);
        cat5.setText(categories.get(4).name);
        cat6.setText(categories.get(5).name);

        return v;
    }

    public void removeBox(int categoryNumber, int pointValue)
    {
        if(categoryNumber == 0)
        {
            if(pointValue == 100)
                cat1_100.setText("");
            else if(pointValue == 200)
                cat1_200.setText("");
            else if(pointValue == 300)
                cat1_300.setText("");
            else if(pointValue == 400)
                cat1_400.setText("");
            else if(pointValue == 500)
                cat1_500.setText("");
        }
        else if (categoryNumber == 1)
        {
            if(pointValue == 100)
                cat2_100.setText("");
            else if(pointValue == 200)
                cat2_200.setText("");
            else if(pointValue == 300)
                cat2_300.setText("");
            else if(pointValue == 400)
                cat2_400.setText("");
            else if(pointValue == 500)
                cat2_500.setText("");
        }
        else if (categoryNumber == 2)
        {
            if(pointValue == 100)
                cat3_100.setText("");
            else if(pointValue == 200)
                cat3_200.setText("");
            else if(pointValue == 300)
                cat3_300.setText("");
            else if(pointValue == 400)
                cat3_400.setText("");
            else if(pointValue == 500)
                cat3_500.setText("");
        }
        else if (categoryNumber == 3)
        {
            if(pointValue == 100)
                cat4_100.setText("");
            else if(pointValue == 200)
                cat4_200.setText("");
            else if(pointValue == 300)
                cat4_300.setText("");
            else if(pointValue == 400)
                cat4_400.setText("");
            else if(pointValue == 500)
                cat4_500.setText("");
        }
        else if (categoryNumber == 4)
        {
            if(pointValue == 100)
                cat5_100.setText("");
            else if(pointValue == 200)
                cat5_200.setText("");
            else if(pointValue == 300)
                cat5_300.setText("");
            else if(pointValue == 400)
                cat5_400.setText("");
            else if(pointValue == 500)
                cat5_500.setText("");
        }
        else if (categoryNumber == 5)
        {
            if(pointValue == 100)
                cat6_100.setText("");
            else if(pointValue == 200)
                cat6_200.setText("");
            else if(pointValue == 300)
                cat6_300.setText("");
            else if(pointValue == 400)
                cat6_400.setText("");
            else if(pointValue == 500)
                cat6_500.setText("");
        }
    }
}
