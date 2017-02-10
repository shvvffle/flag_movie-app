package movies.flag.pt.moviesapp.screens;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.adapters.ListMovieTopRatedViewAdapter;
import movies.flag.pt.moviesapp.http.entities.Movie;
import movies.flag.pt.moviesapp.http.entities.MoviesResponse;
import movies.flag.pt.moviesapp.http.requests.GetNowPlayingMoviesAsyncTask;
import movies.flag.pt.moviesapp.utils.DLog;

/**
 * Created by Marina on 25/01/2017.
 */

public class TopRatedMovieScreen extends Screen {


    private static String REFRESH_LOG;
    private ListView movieTopRatedList;
    private ListMovieTopRatedViewAdapter movieTopRatedViewAdapter;
    private SwipeRefreshLayout swipeRefreshTopRatedMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executeRequestTopRatedMovies();

        setContentView(R.layout.top_rated_movie_screen);

        findViews();
        addListeners();
    }


    private void findViews() {
        movieTopRatedList = (ListView) findViewById(R.id.top_rated_movie_screen_list_view);
        swipeRefreshTopRatedMovie = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshMovie);
    }

    private void addListeners() {

        swipeRefreshTopRatedMovie.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(REFRESH_LOG, "onRefresh called from SwipeRefreshLayout");
                        swipeRefreshTopRatedMovie.setRefreshing(true);
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        executeRequestTopRatedMovies();
                        swipeRefreshTopRatedMovie.setRefreshing(false);
                    }
                }
        );


    }


    private void executeRequestTopRatedMovies() {
        // Request get now playing movies
        new GetNowPlayingMoviesAsyncTask(this) {

            @Override
            protected void onResponseSuccess(MoviesResponse moviesResponse) {
                DLog.d(tag, "onResponseSuccess " + moviesResponse);
                // Adapter
                List<Movie> movies = moviesResponse.getMovies();
                movieTopRatedViewAdapter = new ListMovieTopRatedViewAdapter(TopRatedMovieScreen.this, movies);
                movieTopRatedList.setAdapter(movieTopRatedViewAdapter);
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