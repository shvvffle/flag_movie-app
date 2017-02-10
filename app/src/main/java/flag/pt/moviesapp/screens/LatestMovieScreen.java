package flag.pt.moviesapp.screens;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import flag.pt.moviesapp.R;
import flag.pt.moviesapp.adapters.ListMovieViewAdapter;
import flag.pt.moviesapp.http.entities.Movie;
import flag.pt.moviesapp.http.entities.MoviesResponse;
import flag.pt.moviesapp.http.requests.GetNowPlayingMoviesAsyncTask;
import flag.pt.moviesapp.utils.DLog;

/**
 * Created by Marina on 25/01/2017.
 */

public class LatestMovieScreen extends Screen {


    private static String REFRESH_LOG;
    private ListView movieList;
    private ListMovieViewAdapter movieViewAdapter;
    private SwipeRefreshLayout swipeRefreshMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executeRequestLatestMovies();

        setContentView(R.layout.latest_movie_screen);

        findViews();
        addListeners();
    }


    private void findViews() {
        movieList = (ListView) findViewById(R.id.latest_movie_screen_list_view);
        swipeRefreshMovie = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshMovie);
    }

    private void addListeners() {

        swipeRefreshMovie.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(REFRESH_LOG, "onRefresh called from SwipeRefreshLayout");
                        swipeRefreshMovie.setRefreshing(true);
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        executeRequestLatestMovies();
                        swipeRefreshMovie.setRefreshing(false);
                    }
                }
        );


    }


    private void executeRequestLatestMovies() {
        // Request get now playing movies
        new GetNowPlayingMoviesAsyncTask(this) {

            @Override
            protected void onResponseSuccess(MoviesResponse moviesResponse) {
                DLog.d(tag, "onResponseSuccess " + moviesResponse);
                // Adapter
                List<Movie> movies = moviesResponse.getMovies();
                movieViewAdapter = new ListMovieViewAdapter(LatestMovieScreen.this, movies);
                movieList.setAdapter(movieViewAdapter);
            }

            @Override
            protected void onNetworkError() {
                DLog.d(tag, "onNetworkError ");
                // Error occur when processing the request,
                // possible my internet connection if turned off
            }
        }.execute();
    }

}