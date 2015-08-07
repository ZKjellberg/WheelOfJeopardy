package mobicent.com.wheelofjeopardy;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class WelcomeScreenActivity extends AppCompatActivity {

    EditText numPlayersEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        numPlayersEditText = (EditText) findViewById(R.id.num_players_edittext);

        // Init the four player modes
        findViewById(R.id.playersOne).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(1);
            }
        });
        findViewById(R.id.playersTwo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(2);
            }
        });
        findViewById(R.id.playersThree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(3);
            }
        });
        findViewById(R.id.playersFour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(4);
            }
        });

        // TODO: Delete old method & invisible UI components once comfortable with the drawable buttons.
        Button startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(numPlayersEditText.getText().toString());
                startGame(num);
            }
        });
    }

    public void startGame(int playerCount) {
        Intent intent = new Intent();
        // TODO: Why do we have a setResult & a putExtra for same amount?
        intent.putExtra("PLAYER_NUMBER", playerCount);
        setResult(playerCount,intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome_screen, menu);
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
