package mobicent.com.wheelofjeopardy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.io.InputStream;

import mobicent.com.wheelofjeopardy.fragment.BoardFragment;

public class MainActivity extends AppCompatActivity {

    static Board board;
    InputStream stream;
    int numPlayers;

    static final int NUM_PLAYERS_REQUEST = 1;

    PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Wheel"));
        tabLayout.addTab(tabLayout.newTab().setText("Board"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        try {
            stream = getAssets().open("round1.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        board = new Board(stream);

        Intent welcomeScreen = new Intent(this, WelcomeScreenActivity.class);
        startActivityForResult(welcomeScreen, NUM_PLAYERS_REQUEST);
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

    public int getNumPlayers()
    {
        return numPlayers;
    }

    public void removeBoxFromBoard(int categoryNumber, int pointValue)
    {
        BoardFragment fragment = (BoardFragment) adapter.getRegisteredFragment(1);
        fragment.removeBox(categoryNumber, pointValue);
    }

    public void resetForRoundTwo()
    {
        BoardFragment fragment = (BoardFragment) adapter.getRegisteredFragment(1);
        try {
            stream = getAssets().open("round2.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        board = new Board(stream);

        fragment.resetAndDouble(board);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == NUM_PLAYERS_REQUEST) {
            Bundle extras = data.getExtras();
            numPlayers = extras.getInt("PLAYER_NUMBER");
            Snackbar.make(findViewById(android.R.id.content), "Game will start with " + numPlayers + " players.", Snackbar.LENGTH_LONG).show();
        }
    }

    // Hijack Back button to prevent user from dismissing Dialogs
    @Override
    public void onBackPressed() {
    }
}
