package mobicent.com.wheelofjeopardy;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class EndGameActivity extends ActionBarActivity {

    TextView winner;
    TextView scores;
    int max = 0;
    String scoreString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        winner = (TextView) findViewById(R.id.textView3);
        scores = (TextView) findViewById(R.id.textView4);

        Intent intent = getIntent();
        int numPlayer = intent.getIntExtra("NUM_PLAYERS", 2);
        scores.setLines(numPlayer);
        for(int i = 0; i < numPlayer; i++) {
            int score = intent.getIntExtra("PLAYER " + i, 0);
            if (score >= max)
            {
                max = score;
                winner.setText("Player " + (i+1) + " Wins!!!");
            }
            //TODO Case when 2 or more players tie
            if (score == max)
            {

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
