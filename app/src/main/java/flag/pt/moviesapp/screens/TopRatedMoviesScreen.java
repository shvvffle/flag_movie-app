package flag.pt.moviesapp.screens;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import java.util.List;

import flag.pt.moviesapp.R;
import flag.pt.moviesapp.adapters.ListTopRatedMoviesViewAdapter;
import flag.pt.moviesapp.http.entities.Movie;
import flag.pt.moviesapp.http.entities.TopRatedMoviesResponse;
import flag.pt.moviesapp.http.requests.GetTopRatedMoviesAsyncTask;
import flag.pt.moviesapp.utils.DLog;

/**
 * Created by Marina on 25/01/2017.
 */

public class TopRatedMoviesScreen extends Screen {


    private static String REFRESH_LOG;
    private ListView topRatedMoviesList;
    private ListTopRatedMoviesViewAdapter topRatedViewAdapter;
    private SwipeRefreshLayout swipeRefreshTopRatedMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executeRequestTopRatedMovies();

        setContentView(R.layout.top_rated_movies_screen);

        findViews();
        addListeners();
    }


    private void findViews() {
        topRatedMoviesList = (ListView) findViewById(R.id.top_rated_movies_screen_list_view);
        swipeRefreshTopRatedMovie = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshTopRatedMovie);
    }

    private void addListeners() {

        swipeRefreshTopRatedMovie.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        DLog.d(REFRESH_LOG, "onRefresh called from SwipeRefreshLayout");
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
        // Request get top rated movies
        new GetTopRatedMoviesAsyncTask(this) {

            @Override
            protected void onResponseSuccess(TopRatedMoviesResponse topRatedMovieResponse) {
                DLog.d(tag, "onResponseSuccess " + topRatedMovieResponse);
                // Adapter
                List<Movie> topRatedMovies = topRatedMovieResponse.getResultTopRatedMovies();
                topRatedViewAdapter = new ListTopRatedMoviesViewAdapter(TopRatedMoviesScreen.this, topRatedMovies);
                topRatedMoviesList.setAdapter(topRatedViewAdapter);
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