package app.com.example.android.popularmovies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Populare Gridview with clickable movie posters
 */
public class MovieAdapter extends ArrayAdapter<Movie> {

    private static final String TAG_NAME = MovieAdapter.class.getSimpleName();
    Activity mActivity;
    ArrayList<Movie> mMovies;

    public MovieAdapter(Activity context, ArrayList<Movie> movies) {
        super(context, 0, movies);
        this.mActivity = context;
        this.mMovies = movies;
    }

    // ViewHolder pattern
    private static class ViewHolder {
        ImageView mImageViewMovie;
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Override
    public Movie getItem(int position) {
        return mMovies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Name: getView
     * Comment: Populate the GridView item
     * Tasks:
     * 1. If view not seen previously
     * 2. Inflate the view
     * 3. Inflate the imageView
     * 4. setTag - hold the new information
     * 5. Else
     * 6. getTag - access the existing information
     * 7. Fetch and load the image into the inflated
     imageView
     */

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;

        // TODO: Implement the ViewHolder pattern -
        //    see GridView bookmark StackOverflow
        if (view == null) {
            // Find the layout for gridview_image
            view = LayoutInflater.from(mActivity).inflate(
                    R.layout.layout_poster, null);
            // gridView.setLayoutParams(new GridView.LayoutParams(350,475));
            viewHolder = new ViewHolder();
            // Find the ImageView and populate
            viewHolder.mImageViewMovie = (ImageView)
                    view.findViewById(R.id.imageView_poster);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        // TODO: Use Picasso to fetch and load images into the ImageView
        Picasso.with(mActivity)
                .load(mMovies.get(position).mThumbnail)
                // .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(viewHolder.mImageViewMovie);
        return view;
    }

}
