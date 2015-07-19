package mobicent.com.wheelofjeopardy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.io.InputStream;

import mobicent.com.wheelofjeopardy.Fragments.BoardFragment;
import mobicent.com.wheelofjeopardy.Fragments.WheelFragment;


public class MainActivity extends AppCompatActivity {

    static Board board;
    InputStream stream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Fragments
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.containerWheel, new WheelFragment())
                    .commit();
            getFragmentManager().beginTransaction()
                    .add(R.id.containerBoard, new BoardFragment())
                    .commit();
        }

        try {
            stream = getAssets().open("test.xml");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        board = new Board(stream);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public Board getBoard()
    {
        return board;
    }
}
