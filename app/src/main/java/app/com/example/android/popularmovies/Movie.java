package app.com.example.android.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Movie model for detailed info
 */

public class Movie implements Parcelable{

    // TODO: Add Log TAG for debug
    private static final String TAG_NAME = Movie.class.getSimpleName();

    // TODO: Add member variables
    String mTitle;
    String mYear;
    String mTrailerPrimaryUri;
    String mThumbnail;

    // TODO: Add a list to hold the movies/films stored
    private List<Movie> Movies;

    // Constructor for class
    Movie(String strTitle, String strYear, String strTrailer, String strThumbnail) {
        this.mTitle = strTitle;
        this.mYear = strYear;
        this.mTrailerPrimaryUri = strTrailer;
        this.mThumbnail = strThumbnail;
    }

    // TODO: Start Parceable code
    private Movie(Parcel in) {
        this.mTitle = in.readString();
        this.mYear = in.readString();
        this.mTrailerPrimaryUri = in.readString();
        this.mThumbnail = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeString(this.mYear);
        dest.writeString(this.mTrailerPrimaryUri);
        dest.writeString(this.mThumbnail);
    }

    public final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }
        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };
// TODO: End Parceable code

}
