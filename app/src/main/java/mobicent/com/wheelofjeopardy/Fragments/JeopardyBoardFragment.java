package mobicent.com.wheelofjeopardy.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobicent.com.wheelofjeopardy.R;


public class JeopardyBoardFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_jeopardy_board, container, false);

        return v;
    }
}
