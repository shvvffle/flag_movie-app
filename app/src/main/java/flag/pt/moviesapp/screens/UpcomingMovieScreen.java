package flag.pt.moviesapp.screens;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import flag.pt.moviesapp.R;
import flag.pt.moviesapp.adapters.ListUpcomingMoviesViewAdapter;
import flag.pt.moviesapp.http.entities.Movie;
import flag.pt.moviesapp.http.entities.UpcomingMoviesResponse;
import flag.pt.moviesapp.http.requests.GetUpcomingMoviesAsyncTask;
import flag.pt.moviesapp.utils.DLog;

/**
 * Created by Marina on 25/01/2017.
 */

public class UpcomingMovieScreen extends Screen {


    private static String REFRESH_LOG;
    private ListView upcomingMovieList;
    private ListUpcomingMoviesViewAdapter upcomingMovieViewAdapter;
    private SwipeRefreshLayout swipeRefreshUpcomingMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executeRequestUpcomingMovies();

        setContentView(R.layout.upcoming_movie_screen);

        findViews();
        addListeners();
    }


    private void findViews() {
        upcomingMovieList= (ListView) findViewById(R.id.upcoming_movie_screen_list_view);
        swipeRefreshUpcomingMovie = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshUpcomingMovie);
    }

    private void addListeners() {

        swipeRefreshUpcomingMovie.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(REFRESH_LOG, "onRefresh called from SwipeRefreshLayout");
                        swipeRefreshUpcomingMovie.setRefreshing(true);
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        executeRequestUpcomingMovies();
                        swipeRefreshUpcomingMovie.setRefreshing(false);
                    }
                }
        );


    }


    private void executeRequestUpcomingMovies() {
        // Request get now playing movies
        new GetUpcomingMoviesAsyncTask(this) {

            @Override
            protected void onResponseSuccess(UpcomingMoviesResponse upcomingMoviesResponse) {
                DLog.d(tag, "onResponseSuccess " + upcomingMoviesResponse);
                // Adapter
                List<Movie> upcomingMovies = upcomingMoviesResponse.getResults();
                upcomingMovieViewAdapter = new ListUpcomingMoviesViewAdapter(UpcomingMovieScreen.this, upcomingMovies);
                upcomingMovieList.setAdapter(upcomingMovieViewAdapter);
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