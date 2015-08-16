package mobicent.com.wheelofjeopardy.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.myriadmobile.fortune.FortuneItem;
import com.myriadmobile.fortune.FortuneView;
import com.myriadmobile.fortune.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import mobicent.com.wheelofjeopardy.Board;
import mobicent.com.wheelofjeopardy.Category;
import mobicent.com.wheelofjeopardy.EndGameActivity;
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
    int roundSpins = 10;    // This should be 50 in the release

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

        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_1)));                             // Question 1
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_rotate)));              // Spin Again
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_2)));                             // Question 2
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_add)));                 // Free Turn
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_3)));                             // Question 3
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_close_clear_cancel)));  // Lose Turn
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_4)));                             // Question 4
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_revert)));              // Bankrupt
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_5)));                             // Question 5
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_help)));                // Choose Category
        sectors.add(new FortuneItem(BitmapFactory.decodeResource(getResources(), R.drawable.image_6)));                             // Question 6
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
        spinCounter = roundSpins;
        scoreModifier = 1;
        currentPlayer = 0;
        txtPlayer.setText("Player: 1");
        txtScore.setText("Score: 0");
        txtResult.setText("Spins Remaining: " + spinCounter);

        // Multiple players
        player = new Player[playerCount];
        for (int i = 0; i < playerCount; i++) {
            player[i] = new Player(""+ (i+1));
        }
    }

    private void spinWheel() {
        // Reset to Player 1 if all players have gone.
        if (currentPlayer == player.length) {
            currentPlayer = 0;
        }

        // Spin Wheel for result
        final int spinResult = new Random().nextInt(fortuneView.getTotalItems());
        fortuneView.setSelectedItem(spinResult);

        // One second delay before handling action for result
        new CountDownTimer(2000, 1000) {
            public void onFinish() {
                calculateWheelAction(spinResult);
            }
            public void onTick(long millisUntilFinished) {

            }
        }.start();
    }

    // One sector for each of the six categories on the Jeopardy! board. When the player spins one of these sectors, he or she must answer the next question in that category.
    // The questions are answered in the order of increasing point value. If the player answers correctly, he or she is awarded the corresponding points and gets to spin again.
    // If incorrect, the corresponding points are subtracted from the player’s score, and the player loses his turn. (Negative scores are possible.)
    // If all of the questions in the selected category have been answered, the player must spin again.
    private void calculateWheelAction(int spinResult) {
        // TODO: If the user presses the button too fast, it will spin twice and the first one will get skipped...we should make sure this can't happen somehow
        switch (spinResult) {

            // Question Category 1
            case 0:
                createDialog(0);
                break;

            // One “spin again” sector. When this sector comes up, the player must spin again.
            case 1:
                Snackbar.make(getView(), "Spin again sector. Spinning wheel again", Snackbar.LENGTH_LONG).show();
                checkEndGameOrRound();
                spinWheel();
                break;

            // Question Category 2
            case 2:
                createDialog(1);
                break;

            // One “free turn” sector. When this sector comes up, the player gets a token for a free turn later in the game.
            // The token could be used if the player loses his turn by spinning a “lose turn” or answering a question incorrectly in a future turn.
            // If this happens, the player could redeem the token and would get to spin the wheel again. The number of tokens is unlimited.
            case 3:
                Snackbar.make(getView(), "Free token for Player " + player[currentPlayer].getName() + ". Round continues for Player " + player[currentPlayer].getName(), Snackbar.LENGTH_LONG).show();
                player[currentPlayer].addToken();
                checkEndGameOrRound();
                spinWheel();
                break;

            // Question Category 3
            case 4:
                createDialog(2);
                break;

            // One “lose turn” sector.
            case 5:
                Snackbar.make(getView(), "Lose turn sector.", Snackbar.LENGTH_LONG).show();

                // No action. Inform user and move onto next Player
                if(player[currentPlayer].getTokens() >= 1)
                {
                    freeToken();
                }
                else {
                    nextPlayer();
                }
                setTxtScore();
                checkEndGameOrRound();
                break;

            // Question Category 4
            case 6:
                createDialog(3);
                break;

            // One “bankrupt” sector. When this sector comes up, the player loses all of his or her points for the current round.
            // The player loses his turn, and can’t use a token for a second chance.
            case 7:
                Snackbar.make(getView(), "Bankrupt sector. Player " + player[currentPlayer].getName() + " loses his score for this round.", Snackbar.LENGTH_LONG).show();
                player[currentPlayer].bankrupt();
                nextPlayer();
                setTxtScore();
                checkEndGameOrRound();
                break;

            // Question Category 5
            case 8:
                createDialog(4);
                break;

            // One “player’s choice” sector. When this sector comes up, the player gets to choose which category to answer.
            case 9:
                Snackbar.make(getView(), "Player's choice. Please choose your category.", Snackbar.LENGTH_LONG).show();
                chooseCategoryDialog("Choose category");
                break;

            // Question Category 6
            case 10:
                createDialog(5);
                break;

            // One “opponents’ choice” sector. When this sector comes up, the player’s opponents get to select the category.
            case 11:
                Snackbar.make(getView(), "Opponent's choice. Please choose your opponent's category!", Snackbar.LENGTH_LONG).show();
                // Same as question Player's Choice, but ask user to hand phone to opponent to choose category.
                chooseCategoryDialog("Opponent chooses your category");
                break;

            default:
                Toast.makeText(getActivity(), "Invalid spin detected. Spinning wheel again.", Toast.LENGTH_SHORT).show();
                checkEndGameOrRound();
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

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(currentQuestion.getQuestion())
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        if (which == currentQuestion.getCorrectOption()) {
                            Snackbar.make(getView(), "Correct! Player " + player[currentPlayer].getName() + " gained " + currentQuestion.getPointValue() + " points.", Snackbar.LENGTH_LONG).show();
                            if (scoreModifier == 2)
                                player[currentPlayer].increaseRoundScore(currentQuestion.getPointValue());
                            player[currentPlayer].increaseRoundScore(currentQuestion.getPointValue());
                        } else {
                            Snackbar.make(getView(), "Wrong! Player " + player[currentPlayer].getName() + " lost " + currentQuestion.getPointValue() + " points.", Snackbar.LENGTH_LONG).show();

                            if (scoreModifier == 2)
                                player[currentPlayer].decreaseRoundScore(currentQuestion.getPointValue());
                            player[currentPlayer].decreaseRoundScore(currentQuestion.getPointValue());
                            if (player[currentPlayer].getTokens() >= 1) {
                                freeToken();
                            } else {
                                nextPlayer();
                            }
                        }
                        setTxtScore();
                        ((MainActivity) getActivity()).removeBoxFromBoard(currentCategory.getCategoryNumber(), currentQuestion.getPointValue());
                        checkEndGameOrRound();
                    }
                });
        builder.create();
        final AlertDialog dialog = builder.show();

        new CountDownTimer(9000, 1000) {
            public void onFinish() {
                if(dialog.isShowing()) {
                    dialog.dismiss();
                    Snackbar.make(getView(), "Player " + player[currentPlayer].getName() + " ran out of time to answer question.", Snackbar.LENGTH_LONG).show();
                    player[currentPlayer].decreaseRoundScore(currentQuestion.getPointValue());
                    if(player[currentPlayer].getTokens() >= 1) {
                        freeToken();
                    }
                    else {
                        nextPlayer();
                    }
                    setTxtScore();

                    ((MainActivity) getActivity()).removeBoxFromBoard(currentCategory.getCategoryNumber(), currentQuestion.getPointValue());
                    checkEndGameOrRound();
                }
            }
            public void onTick(long millisUntilFinished) {

            }
        }.start();
    }

    public void nextPlayer() {
        currentPlayer = (currentPlayer+1)%player.length;
        txtPlayer.setText("Player: " + (currentPlayer + 1));
    }

    public void setTxtScore()
    {
        txtScore.setText("Score: " + player[currentPlayer].getScore());
    }

    public void chooseCategoryDialog(String title) {
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

        AlertDialog.Builder builder;
        if (title.equals("Choose category")) {
            builder = new AlertDialog.Builder(getActivity(), mobicent.com.wheelofjeopardy.R.style.UserChoice);
        } else {
            builder = new AlertDialog.Builder(getActivity(), mobicent.com.wheelofjeopardy.R.style.OpponentChoice);
        }

        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // The 'which' argument contains the index position of the selected item
                Snackbar.make(getView(), "The Player chose the " + items[which] + " category.", Snackbar.LENGTH_LONG).show();
                createDialog(which);
            }
        });
        builder.create().show();
    }

    public void freeToken() {
        AlertDialog.Builder builder =  new  AlertDialog.Builder(getActivity())
                .setTitle("Player " + player[currentPlayer].getName() + " has a token available, would they like to use it to continue their turn?")
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                player[currentPlayer].removeToken();
                            }
                        }
                )
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                nextPlayer();
                                dialog.dismiss();
                            }
                        }
                );
        builder.create().show();
    }

    private void checkEndGameOrRound()  {
        txtResult.setText("Spins Remaining: " + spinCounter);

        if (--spinCounter <= 0) {

            //Game is over, show end screen
            if (scoreModifier == 2)
            {
                Intent intent = new Intent(getActivity(), EndGameActivity.class);
                intent.putExtra("NUM_PLAYERS", player.length);
                for(int i = 0; i < player.length; i++)
                    intent.putExtra("PLAYER " + i, player[i].getScore());
                startActivity(intent);
            }
            else {
                Snackbar.make(getView(), "Round 2 is beginning!", Snackbar.LENGTH_LONG).show();

                ((MainActivity) getActivity()).resetForRoundTwo();

                InputStream stream = null;
                try {
                    stream = getActivity().getAssets().open("round2.xml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                board = new Board(stream);

                for (int i = 0; i < player.length; i++) {
                    player[i].resetRoundScore();
                }

                // If 50 spins have occurred in Round 1, Start Round 2
                scoreModifier = 2;
                spinCounter = roundSpins;
                txtResult.setText("Spin Result: " + spinCounter);
            }
        }
    }
}
