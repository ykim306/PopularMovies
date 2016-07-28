package app.com.example.android.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    // TODO: Add Log TAG for debug
    private static final String TAG_NAME = DetailFragment.class.getSimpleName();

    private static final String MAX_VOTE_AVERAGE = " / 5.0";
    //"poster_sizes": ["w92","w154","w185","w342","w500","w780","original"]
    private static final String POSTER_PATH = "w342";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        Movie mMovie = bundle.getParcelable("movie_detail");

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_detail_poster);
        TextView content_title = (TextView) view.findViewById(R.id.textView_title);
        TextView content_release_date = (TextView) view.findViewById(R.id.textView_release_date);
        TextView content_vote_average = (TextView) view.findViewById(R.id.textView_user_rating);
        TextView content_overview = (TextView) view.findViewById(R.id.textView_overview);
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar_vote_average);

        content_title.setText(mMovie.getOriginal_title());
        content_release_date.setText(mMovie.getRelease_date());
        content_vote_average.setText(Float.toString(mMovie.getVote_average())+MAX_VOTE_AVERAGE);
        content_overview.setText(mMovie.getOverview());
        ratingBar.setRating(mMovie.getVote_average());


        // TODO: Use Picasso to fetch and load images into the ImageView
        Picasso.with(getActivity())
                .load(mMovie.getPoster_path(POSTER_PATH))
                // .memoryPolicy(MemoryPolicy.NO_CACHE)
                .placeholder(R.drawable.blank)
                .error(R.drawable.blank)
                .into(imageView);

        Log.d(TAG_NAME, mMovie.getPoster_path(POSTER_PATH));

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
