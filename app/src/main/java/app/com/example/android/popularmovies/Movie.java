package app.com.example.android.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.List;

/**
 * Movie model for detailed info
 */

public class Movie implements Parcelable {

    // TODO: Add Log TAG for debug
    private static final String TAG_NAME = Movie.class.getSimpleName();

    //"poster_sizes": ["w92","w154","w185","w342","w500","w780","original"]
    private static final String THUMBNAIL_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";

    // TODO: Add member variables
    private String id;
    private String original_title;
    private String poster_path;
    private String overview;
    private String release_date;
    private float vote_average;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getPoster_path(String size) {
        return THUMBNAIL_IMAGE_BASE_URL + size + poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() { return overview; }

    public void setOverview(String overview) { this.overview = overview; }

    public String getRelease_date() { return release_date; }

    public void setRelease_date(String release_date) { this.release_date = release_date; }

    public float getVote_average() {
        // return divided by 2 to scale for five start rating
        return round(vote_average/2,1); }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public void setVote_average(float vote_average) { this.vote_average = vote_average; }

    // TODO: Add a list to hold the movies/films stored
    private List<Movie> Movies;

    public List<Movie> getMovies() {
        return this.Movies;
    }

    public void setMovies(List<Movie> movies) {
        this.Movies = movies;
    }

    // no arg Constructor
    Movie() {
    }

    // Constructor for class
    Movie(String id, String original_title, String poster_path,
          String overview, String release_date, float vote_average) {
        this.id = id;
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }

    // TODO: Start Parcelable code
    private Movie(Parcel in) {
        this.id = in.readString();
        this.original_title = in.readString();
        this.poster_path = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.vote_average = in.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.original_title);
        dest.writeString(this.poster_path);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeFloat(this.vote_average);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };
// TODO: End Parcelable code

}
