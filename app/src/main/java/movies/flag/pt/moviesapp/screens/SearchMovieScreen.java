package movies.flag.pt.moviesapp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.http.entities.MoviesResponse;
import movies.flag.pt.moviesapp.http.requests.GetSearchMovieAsyncTask;
import movies.flag.pt.moviesapp.utils.DLog;

/**
 * Created by Marina on 01/02/2017.
 */

public class SearchMovieScreen extends Screen {

    public static final String MOVIE_SEARCH = "movieSearch";
    private TextView movieResultSearch;
    private String movieSearchQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_movie_screen);
        Intent searchMovieIntent = getIntent();
        movieSearchQuery = searchMovieIntent.getStringExtra(MOVIE_SEARCH);
        executeRequestSearchMovie();
    }

    private void executeRequestSearchMovie() {

        new GetSearchMovieAsyncTask(this, movieSearchQuery) {

            @Override
            protected void onResponseSuccess(MoviesResponse moviesResponse) {
                DLog.d(tag, "onResponseSuccess " + moviesResponse);
                // Here i can create the adapter :)
                movieResultSearch = (TextView) findViewById(R.id.search_movie_screen_detail);
                movieResultSearch.setText(movieSearchQuery);
            }

            @Override
            protected void onNetworkError() {
                DLog.d(tag, "onNetworkError ");
                // Here i now that some error occur when processing the request,
                // possible my internet connection if turned off
            }
        }.execute();
    }

}
