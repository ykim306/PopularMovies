package app.com.example.android.popularmovies;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Class for asynchronous API calls
 */
public class TheMovieDbApi extends AsyncTask<Void, Void, ArrayList<Movie>> {

    private final String TAG_NAME = TheMovieDbApi.class.getSimpleName();

    private String sortOption;
    private ArrayAdapter<Movie> mMovieAdapter = null;

    public String getSortOption() {
        return sortOption;
    }

    TheMovieDbApi(String sortOption, ArrayAdapter<Movie> mMovieAdapter) {
        this.sortOption = sortOption;
        this.mMovieAdapter = mMovieAdapter;
    }

    protected ArrayList<Movie> getMovieDataFromJson(String json) {

        Gson gson = new Gson();
        JsonResponse result = gson.fromJson(json, JsonResponse.class);

        if (result == null) {
            Log.d(TAG_NAME, "tempMovie is NULL");
        } else {
            Log.d(TAG_NAME, result.getResults().toString());
        }

        return (ArrayList<Movie>) result.getResults();
    }

    @Override
    protected ArrayList<Movie> doInBackground(Void... params) {

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String jsonMovieResponse = null;
        String certificationCountry = "US";
        String apiKey = BuildConfig.THE_MOVIE_DB_API_KEY;

        try {
            // Construct the URL for the TheMovieDB query
            // Possible parameters are available at The Movie API page, at
            // http://openweathermap.org/API#forecast
            final String THE_MOVIE_DB_API_BASE_URL =
                    "https://api.themoviedb.org/3";
            final String DISCOVER_PATH = "discover";
            final String SUB_PATH = "movie";
            final String CERTIFICATION_COUNTRY_PARAM = "certification_country";
            final String MIN_RELEASE_DATE = "primary_release_date.gte";
            final String MAX_RELEASE_DATE = "primary_release_date.lte";
            final String SORT_BY__PARAM = "sort_by";
            final String API_KEY_PARAM = "api_key";

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -3);
            Date tempDate = cal.getTime();

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            Date today = Calendar.getInstance().getTime();

            String threeMonthBefore = df.format(tempDate);
            String currentDate = df.format(today);

            Uri builtUri = Uri.parse(THE_MOVIE_DB_API_BASE_URL).buildUpon()
                    .appendPath(DISCOVER_PATH)
                    .appendPath(SUB_PATH)
                    .appendQueryParameter(CERTIFICATION_COUNTRY_PARAM, certificationCountry)
                    .appendQueryParameter(MIN_RELEASE_DATE, threeMonthBefore)
                    .appendQueryParameter(MAX_RELEASE_DATE, currentDate)
                    .appendQueryParameter(SORT_BY__PARAM, this.getSortOption())
                    .appendQueryParameter(API_KEY_PARAM, apiKey)
                    .build();

            URL url = new URL(builtUri.toString());

            Log.d(TAG_NAME, "Built URI " + builtUri.toString());

            // Create the request to TheMovieDB API, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder sb = new StringBuilder();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            final String eol = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                sb.append(line + eol);
            }

            if (sb.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            jsonMovieResponse = sb.toString();

        } catch (IOException e) {
            Log.e(TAG_NAME, "Error ", e);
            // If the code didn't successfully get the API data, there's no point in attempting
            // to parse it.
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(TAG_NAME, "Error closing stream", e);
                }
            }
        }

        try {

            Log.d(TAG_NAME, jsonMovieResponse);
            return getMovieDataFromJson(jsonMovieResponse);

        } catch (Exception e) {
            Log.e(TAG_NAME, e.getMessage(), e);
            e.printStackTrace();
        }

        // This will only happen if there was an error getting or parsing the forecast.
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> result) {
        if (result != null) {

            mMovieAdapter.clear();

            for (Movie m : result) {
                mMovieAdapter.add(m);
            }

            mMovieAdapter.notifyDataSetChanged();

        }
    }

}
