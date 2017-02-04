package movies.flag.pt.moviesapp.screens;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.adapters.ListMovieViewAdapter;
import movies.flag.pt.moviesapp.http.entities.Movie;
import movies.flag.pt.moviesapp.http.entities.MoviesResponse;
import movies.flag.pt.moviesapp.http.requests.GetNowPlayingMoviesAsyncTask;
import movies.flag.pt.moviesapp.utils.DLog;

/**
 * Created by Marina on 25/01/2017.
 */

public class LatestMovieScreen extends Screen {

    private ImageView searchBarAction;
    private EditText searchBarInput;
    private ListView movieList;
    private ListMovieViewAdapter movieViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        executeRequestLatestMovies();

        setContentView( R.layout.latest_movie_screen );

        findViews();
    }

    private void findViews() {
        searchBarAction = (ImageView) findViewById( R.id.all_screens_search_bar_button );
        searchBarInput = (EditText) findViewById( R.id.all_screens_search_bar_input );
        movieList = (ListView) findViewById( R.id.latest_movie_screen_list_view );
    }


    private void executeRequestLatestMovies() {
        // Example to request get now playing movies
        new GetNowPlayingMoviesAsyncTask( this ) {

            @Override
            protected void onResponseSuccess(MoviesResponse moviesResponse) {
                DLog.d( tag, "onResponseSuccess " + moviesResponse );
                // Here i can create the adapter :)
                List<Movie> movies = moviesResponse.getMovies();
                movieViewAdapter = new ListMovieViewAdapter(LatestMovieScreen.this, movies );
                movieList.setAdapter(movieViewAdapter);
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