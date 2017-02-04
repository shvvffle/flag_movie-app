package movies.flag.pt.moviesapp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.http.entities.MoviesResponse;
import movies.flag.pt.moviesapp.http.requests.GetSearchMovieAsyncTask;
import movies.flag.pt.moviesapp.utils.DLog;

import static movies.flag.pt.moviesapp.screens.StartScreen.MOVIE_SEARCH;

/**
 * Created by Marina on 01/02/2017.
 */

public class SearchMovieScreen extends Screen {

    public TextView movieSearched;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executeRequestSearchMovie();

        setContentView(R.layout.search_movie_screen);

    }

    private void executeRequestSearchMovie() {
        // Example to request get now playing movies
        new GetSearchMovieAsyncTask(this) {

            @Override
            protected void onResponseSuccess(MoviesResponse moviesResponse) {
                DLog.d(tag, "onResponseSuccess " + moviesResponse);
                // Here i can create the adapter :)
                movieSearched = (TextView) findViewById(R.id.search_movie_screen_detail);
                Intent searchMovieIntent = getIntent();
                String movieSearchReceived = searchMovieIntent.getStringExtra(MOVIE_SEARCH);
                movieSearched.setText(movieSearchReceived);

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
