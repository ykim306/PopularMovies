package app.com.example.android.popularmovies;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
            getSupportFragmentManager().beginTransaction()
            // TODO: Call to the MoviesFragment code
            .add(R.id.movies_container, new MoviesFragment())
            .commit();
        }
    }

    // TODO: Be sure to add this to manage fragment event
    @Override
    public void onFragmentInteraction(Uri uri) { return; }

}
