package movies.flag.pt.moviesapp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.http.entities.Movie;
import movies.flag.pt.moviesapp.http.requests.GetSearchMovieAsyncTask;
import movies.flag.pt.moviesapp.utils.DLog;

/**
 * Created by Marina on 01/02/2017.
 */

public class SearchMovieScreen extends Screen {

    public TextView movieSearched;

    public static final String MOVIE_SEARCH = "MovieSearch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        executeRequestSearchMovie();

        setContentView( R.layout.search_movie_screen );

        movieSearched = (TextView) findViewById( R.id.search_movie_screen_detail );

        Intent searchMovieIntent = getIntent();
        String movieSearchReceived = searchMovieIntent.getStringExtra( MOVIE_SEARCH );
        movieSearched.setText( movieSearchReceived );
    }
    private void executeRequestSearchMovie() {
        // Example to request get now playing movies
        new GetSearchMovieAsyncTask( this ) {

            @Override
            protected void onResponseSuccess(Movie movie) {
                DLog.d( tag, "onResponseSuccess " + movie );
                // Here i can create the adapter :)


            }

            @Override
            protected void onNetworkError() {
                DLog.d( tag, "onNetworkError " );
                // Here i now that some error occur when processing the request,
                // possible my internet connection if turned off
            }
        }.execute();
    }

}
