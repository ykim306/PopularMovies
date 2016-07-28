package app.com.example.android.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements MoviesFragment.OnFragmentInteractionListener {

    // TODO: Add Log TAG for debug
    private static final String TAG_NAME = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Add fragment initiation
        if (savedInstanceState == null) {
            MoviesFragment mainFragment = new MoviesFragment();
            mainFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    // TODO: Call to the MoviesFragment code
                    .add(R.id.movies_container, mainFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //creates a menu inflater
        MenuInflater inflater = getMenuInflater();
        //generates a Menu from a menu resource file
        //R.menu.menu_main represents the ID of the XML resource file
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(this, MainActivity.class);

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.sort_by_popular:
                intent.putExtra("sortBy", "popularity.desc");

                finish();
                startActivity(intent);

                return true;
            case R.id.sort_by_highly_rated:
                Log.d(TAG_NAME,"Sort by Highly Rated Clicked");

                intent.putExtra("sortBy", "vote_average.desc");

                finish();
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // TODO: Be sure to add this to manage fragment event
    @Override
    public void onFragmentInteraction(View view) {

        Log.d(TAG_NAME,"onFragmentInteraction called");

        return;
    }

}
