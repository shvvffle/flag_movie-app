package flag.pt.moviesapp.screens;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import flag.pt.moviesapp.R;
import flag.pt.moviesapp.adapters.ListNowPlayingMoviesViewAdapter;
import flag.pt.moviesapp.http.entities.Movie;
import flag.pt.moviesapp.http.entities.NowPlayingMoviesResponse;
import flag.pt.moviesapp.http.requests.GetNowPlayingMoviesAsyncTask;
import flag.pt.moviesapp.utils.DLog;

/**
 * Created by Marina on 25/01/2017.
 */

public class NowPlayingMoviesScreen extends Screen {


    private static String REFRESH_LOG;
    private ListView movieList;
    private ListNowPlayingMoviesViewAdapter movieViewAdapter;
    private SwipeRefreshLayout swipeRefreshMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executeRequestLatestMovies();

        setContentView(R.layout.now_playing_movies_screen);

        findViews();
        addListeners();
    }


    private void findViews() {
        movieList = (ListView) findViewById(R.id.now_playing_movies_screen_list_view);
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
            protected void onResponseSuccess(NowPlayingMoviesResponse nowPlayingMoviesResponse) {
                DLog.d(tag, "onResponseSuccess " + nowPlayingMoviesResponse);
                // Adapter
                List<Movie> movies = nowPlayingMoviesResponse.getMovies();
                movieViewAdapter = new ListNowPlayingMoviesViewAdapter(NowPlayingMoviesScreen.this, movies);
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