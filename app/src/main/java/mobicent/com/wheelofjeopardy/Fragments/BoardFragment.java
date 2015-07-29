package mobicent.com.wheelofjeopardy.Fragments;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_board, container, false);

        cat1 = (TextView) v.findViewById(R.id.category1title);
        cat2 = (TextView) v.findViewById(R.id.category2title);
        cat3 = (TextView) v.findViewById(R.id.category3title);
        cat4 = (TextView) v.findViewById(R.id.category4title);
        cat5 = (TextView) v.findViewById(R.id.category5title);
        cat6 = (TextView) v.findViewById(R.id.category6title);

        ArrayList<Category> categories = ((MainActivity) getActivity()).getBoard().getCategories();

        cat1.setText(categories.get(0).name);
        cat2.setText(categories.get(1).name);
        cat3.setText(categories.get(2).name);
        cat4.setText(categories.get(3).name);
        cat5.setText(categories.get(4).name);
        cat6.setText(categories.get(5).name);

        return v;
    }
}
