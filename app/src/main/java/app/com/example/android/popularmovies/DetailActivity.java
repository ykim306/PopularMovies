package app.com.example.android.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class DetailActivity extends AppCompatActivity
        implements DetailFragment.OnFragmentInteractionListener {

    private static final String TAG_NAME = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // displays the back button on the Action bar, for older versions
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Movie mMovie = (Movie) getIntent().getParcelableExtra("movie_detail");
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie_detail",mMovie);

        // TODO: Add fragment initiation
        if (savedInstanceState == null) {
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    // TODO: Call to the DetailFragment code
                    .add(R.id.detail_container, detailFragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    //NavUtils.navigateUpTo(this, upIntent);

                    // finish calls the last activity on stack
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

        Log.d(TAG_NAME,"onFragmentInteraction called");

        return;
    }
}
