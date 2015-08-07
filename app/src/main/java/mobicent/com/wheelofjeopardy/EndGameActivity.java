package mobicent.com.wheelofjeopardy;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;


public class EndGameActivity extends AppCompatActivity {

    TextView winnerTextView;
    TextView scores;
    int max = 0;
    String scoreString = "";
    boolean tieFlag = false;
    ArrayList<String> winners = new ArrayList<String>();
    String winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        winnerTextView = (TextView) findViewById(R.id.textView3);
        scores = (TextView) findViewById(R.id.textView4);

        Intent intent = getIntent();
        int numPlayer = intent.getIntExtra("NUM_PLAYERS", 2);
        scores.setLines(numPlayer);
        for(int i = 0; i < numPlayer; i++) {
            int score = intent.getIntExtra("PLAYER " + i, 0);
            if (score > max)
            {
                tieFlag = false;
                max = score;
                winner = "Player " + (i+1);
                winnerTextView.setText(winner + " Wins!!!");
            }
            else if (score == max)
            {
                tieFlag = true;
                if(!winners.contains(winner))
                    winners.add(winner);
                winners.add("Player " + (i+1));
                String winnersString = "";
                for(int j = 0; j < winners.size(); j++)
                    winnersString += (winners.get(j) + ", ");
                winnerTextView.setText(winnersString + " tied!");

            }
            //TODO Case when 2 or more players tie
            if (score == max)
            {
                // Set result as both players?
//                scoreString += ("Player " + (i+1) + " Score: " + score + "\n");
            }
            scoreString += ("Player " + (i+1) + " Score: " + score + "\n");
        }
        scores.setText(scoreString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eng_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
