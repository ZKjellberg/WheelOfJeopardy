package mobicent.com.wheelofjeopardy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {
    TextView txtResult;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        Button button = (Button) v.findViewById(R.id.btnSpinWheel);
        button.setOnClickListener(this);

        txtResult = (TextView) v.findViewById(R.id.txtResult);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSpinWheel :
                spinWheel();
                break;
            default:
                break;
        }
    }

    private void spinWheel() {
        int spinResult = (int) (Math.random()*12);
//        Toast.makeText(getActivity(), "Simulating wheel spin " + spinResult, Toast.LENGTH_SHORT).show();
        txtResult.setText("Spin Result: " + spinResult);
        calculateWheelAction(spinResult);
    }

    private void calculateWheelAction(int spinResult) {
        switch (spinResult) {
            // One sector for each of the six categories on the Jeopardy! board. When the player spins one of these sectors, he or she must answer the next question in that category.
            // The questions are answered in the order of increasing point value. If the player answers correctly, he or she is awarded the corresponding points and gets to spin again.
            // If incorrect, the corresponding points are subtracted from the player’s score, and the player loses his turn. (Negative scores are possible.)
            // If all of the questions in the selected category have been answered, the player must spin again.
            case 1 :
                // Question Category 1
                Toast.makeText(getActivity(), "Question Category 1", Toast.LENGTH_SHORT).show();
                break;
            case 2 :
                Toast.makeText(getActivity(), "Question Category 2", Toast.LENGTH_SHORT).show();
                // Question Category 2
                break;
            case 3 :
                Toast.makeText(getActivity(), "Question Category 3", Toast.LENGTH_SHORT).show();
                // Question Category 3
                break;
            case 4 :
                Toast.makeText(getActivity(), "Question Category 4", Toast.LENGTH_SHORT).show();
                // Question Category 4
                break;
            case 5 :
                Toast.makeText(getActivity(), "Question Category 5", Toast.LENGTH_SHORT).show();
                // Question Category 5
                break;
            case 6 :
                Toast.makeText(getActivity(), "Question Category 6", Toast.LENGTH_SHORT).show();
                // Question Category 6
                break;

            // One “lose turn” sector.
            case 7 :
                Toast.makeText(getActivity(), "Lose turn sector.", Toast.LENGTH_SHORT).show();
                // Lose Turn
                break;

            // One “free turn” sector. When this sector comes up, the player gets a token for a free turn later in the game.
            // The token could be used if the player loses his turn by spinning a “lose turn” or answering a question incorrectly in a future turn.
            // If this happens, the player could redeem the token and would get to spin the wheel again. The number of tokens is unlimited.
            case 8 :
                Toast.makeText(getActivity(), "Free turn sector", Toast.LENGTH_SHORT).show();
                // Free Turn
                // playerTurn++
                break;

           // One “bankrupt” sector. When this sector comes up, the player loses all of his or her points for the current round.
           // The player loses his turn, and can’t use a token for a second chance.
            case 9 :
                Toast.makeText(getActivity(), "Bankrupt sector", Toast.LENGTH_SHORT).show();
                break;

            // One “player’s choice” sector. When this sector comes up, the player gets to choose which category to answer.
            case 10 :
                Toast.makeText(getActivity(), "Player's choice. Please choose your category.", Toast.LENGTH_SHORT).show();
                // prompt a dialog with the 6 question categories.
                break;

            // One “opponents’ choice” sector. When this sector comes up, the player’s opponents get to select the category.
            case 11 :
                Toast.makeText(getActivity(), "Opponent's choice.", Toast.LENGTH_SHORT).show();
                // Same as question 10, but ask user to hand phone to opponent to choose category.
                break;

            // One “spin again” sector. When this sector comes up, the player must spin again.
            case 12 :
                Toast.makeText(getActivity(), "Spin again sector. Spinning wheel again", Toast.LENGTH_SHORT).show();
                spinWheel();
                break;

            default :
                Toast.makeText(getActivity(), "Invalid spin detected. Spinning wheel again.", Toast.LENGTH_SHORT).show();
                spinWheel();
                break;
        }
    }
}
