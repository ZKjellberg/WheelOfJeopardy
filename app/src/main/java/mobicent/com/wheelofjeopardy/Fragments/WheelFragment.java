package mobicent.com.wheelofjeopardy.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.myriadmobile.fortune.FortuneItem;
import com.myriadmobile.fortune.FortuneView;
import com.myriadmobile.fortune.GrooveListener;
import com.myriadmobile.fortune.R;

import java.util.ArrayList;
import java.util.Random;

import mobicent.com.wheelofjeopardy.Board;
import mobicent.com.wheelofjeopardy.Category;
import mobicent.com.wheelofjeopardy.Question;
import mobicent.com.wheelofjeopardy.MainActivity;


public class WheelFragment extends Fragment {

    FortuneView fortuneView;
    TextView txtResult;

    Board board;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(mobicent.com.wheelofjeopardy.R.layout.fragment_wheel, container, false);

        fortuneView = (FortuneView) v.findViewById(R.id.dialView);
        txtResult = (TextView) v.findViewById(mobicent.com.wheelofjeopardy.R.id.txtResult);

        board = ((MainActivity) getActivity()).getBoard();

        ArrayList<FortuneItem> sectors = new ArrayList<>();
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_always_landscape_portrait)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_add)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_agenda)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_camera)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_compass)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_help)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_mapmode)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_save)));

//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_always_landscape_portrait), FortuneItem.HingeType.Fixed));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_add), FortuneItem.HingeType.Fixed));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_agenda), FortuneItem.HingeType.Fixed));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_camera), FortuneItem.HingeType.Fixed));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_compass), FortuneItem.HingeType.Fixed));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_help), FortuneItem.HingeType.Fixed));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_mapmode), FortuneItem.HingeType.Fixed));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_save), FortuneItem.HingeType.Fixed));

        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_0)));
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_1)));
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_2)));
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_3)));
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_4)));
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_5)));
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_6)));
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_7)));
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_8)));
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_9)));

        // These do not work
//        sectors.add(new FortuneItem(Color.BLACK, 1));
//        sectors.add(new FortuneItem(Color.BLUE, 1));
//        sectors.add(new FortuneItem(Color.RED, 1));
//        sectors.add(new FortuneItem(Color.GREEN, 1));
//        sectors.add(new FortuneItem(Color.MAGENTA, 1));

        fortuneView.addFortuneItems(sectors);

        // Below seems to only respond to touch events, not spin results
        fortuneView.setGrooveListener(new GrooveListener() {
            @Override
            public void onGrooveChange(int index) {
                Toast.makeText(getActivity(), "Test", Toast.LENGTH_LONG).show();
            }
        });

        v.findViewById(R.id.btRandom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinWheel();
            }
        });

        return v;
    }

    private void spinWheel() {
        Random ran = new Random();
//        int spinResult = (int) (Math.random()*12);   // This does not depend on Fortune Wheel
        int spinResult = ran.nextInt(fortuneView.getTotalItems());
        fortuneView.setSelectedItem(spinResult);
//                Toast.makeText(getActivity(), "Simulating wheel spin " + spinResult, Toast.LENGTH_SHORT).show();
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
                createDialog(0);
                Toast.makeText(getActivity(), "Question Category 1", Toast.LENGTH_SHORT).show();

                break;
            case 2 :
                createDialog(1);
                Toast.makeText(getActivity(), "Question Category 2", Toast.LENGTH_SHORT).show();
                // Question Category 2
                break;
            case 3 :
                createDialog(2);
                Toast.makeText(getActivity(), "Question Category 3", Toast.LENGTH_SHORT).show();
                // Question Category 3
                break;
            case 4 :
                createDialog(3);
                Toast.makeText(getActivity(), "Question Category 4", Toast.LENGTH_SHORT).show();
                // Question Category 4
                break;
            case 5 :
                createDialog(4);
                Toast.makeText(getActivity(), "Question Category 5", Toast.LENGTH_SHORT).show();
                // Question Category 5
                break;
            case 6 :
                createDialog(5);
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

    public void createDialog(int catNumber) {

        Category currentCategory = board.getCategory(catNumber);
        Question currentQuestion = currentCategory.getNextQuestion();
        CharSequence[] items = currentQuestion.getOptions();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(currentQuestion.getQuestion())
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                    }
                });
        builder.create().show();
    }
}
