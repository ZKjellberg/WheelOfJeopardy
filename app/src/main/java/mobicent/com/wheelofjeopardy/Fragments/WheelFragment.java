package mobicent.com.wheelofjeopardy.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.myriadmobile.fortune.FortuneItem;
import com.myriadmobile.fortune.FortuneView;
import com.myriadmobile.fortune.R;

import java.util.ArrayList;
import java.util.Random;

import mobicent.com.wheelofjeopardy.Board;
import mobicent.com.wheelofjeopardy.Category;
import mobicent.com.wheelofjeopardy.MainActivity;
import mobicent.com.wheelofjeopardy.Player;
import mobicent.com.wheelofjeopardy.Question;


public class WheelFragment extends Fragment {
    FortuneView fortuneView;
    TextView txtPlayer;
    TextView txtResult;
    TextView txtScore;
    Board board;
    int spinCounter;
    int scoreModifier;
    Player[] player;
    int currentPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(mobicent.com.wheelofjeopardy.R.layout.fragment_wheel, container, false);

        fortuneView = (FortuneView) v.findViewById(R.id.dialView);
        txtResult = (TextView) v.findViewById(mobicent.com.wheelofjeopardy.R.id.txtResult);
        txtScore = (TextView) v.findViewById(mobicent.com.wheelofjeopardy.R.id.txtScore);
        txtPlayer = (TextView) v.findViewById(mobicent.com.wheelofjeopardy.R.id.player_turn_textview);

        board = ((MainActivity) getActivity()).getBoard();

        startGame(((MainActivity) getActivity()).getNumPlayers());

        ArrayList<FortuneItem> sectors = new ArrayList<>();
        // Reference samples for populating fields
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_always_landscape_portrait)));
//        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_save), FortuneItem.HingeType.Fixed));
        // Colored text doesn't work
//        sectors.add(new FortuneItem(Color.BLACK, 1));

        // TODO: Create new images for categories? How to handle this on screen.
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_rotate)));  // Spin Again
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_1)));                             // Question 1
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_2)));                             // Question 2
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_3)));                             // Question 3
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_4)));                             // Question 4
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_5)));                             // Question 5
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_6)));                             // Question 6
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_close_clear_cancel)));  // Lose Turn
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_add)));                 // Free Turn
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_revert)));              // Bankrupt
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_help)));                // Choose Category
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_info_details)));        // Opponent Choice

        fortuneView.addFortuneItems(sectors);

        v.findViewById(R.id.btRandom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinWheel();
            }
        });

        return v;
    }

    private void startGame(int playerCount) {
        spinCounter = 50;
        scoreModifier = 1;
        currentPlayer = 0;
        txtPlayer.setText("Player: 1");

        // Using single user to start with
//        player = new Player("One");

        // Multiple players
        player = new Player[playerCount];
        for (int i = 0; i < playerCount; i++) {
            player[i] = new Player(""+i);
        }

        // How to initialize multiple players, establish a queue
    }

    private void spinWheel() {
        if (--spinCounter == 0) {
            // If 50 spins have occurred, Start Round 2
            scoreModifier = 2;
            spinCounter = 50;
            // TODO: Add all Player roundScore's to their score
        }
        // Reset to Player 1 if all players have gone.
        if (currentPlayer == player.length) {
            currentPlayer = 0;
        }
        // TODO: When to currentPlayer++; If done now, it will offset the wheelAction index

        // Spin Wheel for result
        final int spinResult = new Random().nextInt(fortuneView.getTotalItems());
        fortuneView.setSelectedItem(spinResult);
        txtResult.setText("Spin Result: " + spinResult);

        // One second delay before handling action for result
        new CountDownTimer(1000, 1000) {
            public void onFinish() {
                calculateWheelAction(spinResult);
            }
            public void onTick(long millisUntilFinished) {

            }
        }.start();
    }

    private void calculateWheelAction(int spinResult) {
        switch (spinResult) {
            // One “spin again” sector. When this sector comes up, the player must spin again.
            case 0:
                Toast.makeText(getActivity(), "Spin again sector. Spinning wheel again", Toast.LENGTH_SHORT).show();
                spinWheel();
                break;

            // One sector for each of the six categories on the Jeopardy! board. When the player spins one of these sectors, he or she must answer the next question in that category.
            // The questions are answered in the order of increasing point value. If the player answers correctly, he or she is awarded the corresponding points and gets to spin again.
            // If incorrect, the corresponding points are subtracted from the player’s score, and the player loses his turn. (Negative scores are possible.)
            // If all of the questions in the selected category have been answered, the player must spin again.
            case 1: // Question Category 1
                Toast.makeText(getActivity(), "Question Category 1", Toast.LENGTH_SHORT).show();
                createDialog(0);
                break;
            case 2: // Question Category 2
                Toast.makeText(getActivity(), "Question Category 2", Toast.LENGTH_SHORT).show();
                createDialog(1);
                break;
            case 3: // Question Category 3
                createDialog(2);
                Toast.makeText(getActivity(), "Question Category 3", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getActivity(), "Question Category 4", Toast.LENGTH_SHORT).show();
                createDialog(3);
                break;
            case 5: // Question Category 5
                Toast.makeText(getActivity(), "Question Category 5", Toast.LENGTH_SHORT).show();
                createDialog(4);
                break;
            case 6: // Question Category 6
                Toast.makeText(getActivity(), "Question Category 6", Toast.LENGTH_SHORT).show();
                createDialog(5);
                break;
            // QUESTIONS END

            // One “lose turn” sector.
            case 7:
                Toast.makeText(getActivity(), "Lose turn sector.", Toast.LENGTH_SHORT).show();
                // No action. Inform user and move onto next Player
                nextPlayer();
                break;

            // One “free turn” sector. When this sector comes up, the player gets a token for a free turn later in the game.
            // The token could be used if the player loses his turn by spinning a “lose turn” or answering a question incorrectly in a future turn.
            // If this happens, the player could redeem the token and would get to spin the wheel again. The number of tokens is unlimited.
            case 8:
                Toast.makeText(getActivity(), "Free turn for Player " + player[currentPlayer].getName(), Toast.LENGTH_SHORT).show();
                player[currentPlayer].addToken();
                spinWheel();
                break;

            // One “bankrupt” sector. When this sector comes up, the player loses all of his or her points for the current round.
            // The player loses his turn, and can’t use a token for a second chance.
            case 9: // Bankrupt
                Toast.makeText(getActivity(), "Bankrupt sector. Player " + player[currentPlayer] + " loses his score for this round.", Toast.LENGTH_SHORT).show();
                player[currentPlayer].resetRoundScore();
                nextPlayer();
                break;

            // One “player’s choice” sector. When this sector comes up, the player gets to choose which category to answer.
            case 10:
                Toast.makeText(getActivity(), "Player's choice. Please choose your category.", Toast.LENGTH_SHORT).show();
                chooseCategoryDialog();
                break;

            // One “opponents’ choice” sector. When this sector comes up, the player’s opponents get to select the category.
            case 11:
                Toast.makeText(getActivity(), "Opponent's choice. Please choose your opponent's category.", Toast.LENGTH_SHORT).show();
                // TODO: Same as question 10, but ask user to hand phone to opponent to choose category.
                chooseCategoryDialog();
                break;

            default:
                Toast.makeText(getActivity(), "Invalid spin detected. Spinning wheel again.", Toast.LENGTH_SHORT).show();
                spinWheel();
                break;
        }
    }

    public void createDialog(int catNumber) {
        final Category currentCategory = board.getCategory(catNumber);
        final Question currentQuestion = currentCategory.getNextQuestion();
        if (currentQuestion == null)
        {
            spinWheel();
        }
        CharSequence[] items = currentQuestion.getOptions();

        // TODO: Add timer to dialog.
        // TODO: Implement token for timeout retry
//        new CountDownTimer(1000, 1000) {
//            public void onFinish() {
//                killDialog?
//            }
//            public void onTick(long millisUntilFinished) {
//                updateDialogTimer
//            }
//        }.start();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(currentQuestion.getQuestion())
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        if (which == currentQuestion.getCorrectOption()) {
                            Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();
                            player[currentPlayer].increaseRoundScore(currentQuestion.getPointValue());
                        }
                        else {
                            Toast.makeText(getActivity(), "Wrong!", Toast.LENGTH_SHORT).show();
                            player[currentPlayer].decreaseRoundScore(currentQuestion.getPointValue());
                        }
                        nextPlayer();
                        ((MainActivity) getActivity()).removeBoxFromBoard(currentCategory.getCategoryNumber(), currentQuestion.getPointValue());
                    }
                });
        builder.create().show();
    }

    public void nextPlayer()
    {
        currentPlayer = (currentPlayer+1)%player.length;
        txtPlayer.setText("Player: " + (currentPlayer+1));
    }

    public void chooseCategoryDialog() {
        ArrayList<Category> categories = board.getCategories();

        // There is probably an easier way to convert Categories into a CharSequence
        // Below comment seems to do it, but gives an error because it returns object type Categories, not the names
        // CharSequence[] items = categories.toArray(new CharSequence[categories.size()]);;
        // Start conversion using alt method
        final CharSequence[] items = new CharSequence[categories.size()];
        int index = 0;
        for (Category category : categories) {
            items[index++] = category.name;
        }
        // End of conversion

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose category")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        Toast.makeText(getActivity(), ""+items[which], Toast.LENGTH_SHORT).show();
                        // TODO: which returns the index, how do we handle the scenario of
                        // TODO: createDialog only accepts int, not Strings
                        createDialog(which);
                        // Below would return name of Category (String)
//                         createDialog(""+items[which]);
                    }
                });
        builder.create().show();
    }
    // TODO: Reuse this dialog for when opponent chooses?
}
