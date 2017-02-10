package flag.pt.moviesapp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import flag.pt.moviesapp.R;
import flag.pt.moviesapp.adapters.ListMovieSearchViewAdapter;
import flag.pt.moviesapp.http.entities.Movie;
import flag.pt.moviesapp.http.entities.MoviesResponse;
import flag.pt.moviesapp.http.requests.GetSearchMovieAsyncTask;
import flag.pt.moviesapp.utils.DLog;

/**
 * Created by Marina on 01/02/2017.
 */

public class SearchMovieScreen extends Screen {

    public static final String MOVIE_SEARCH = "movieSearch";
    private static String REFRESH_SEARCH_LOG;
    private TextView movieResultSearch;
    private String movieSearchQuery;
    private ListView movieSearchList;
    private ListMovieSearchViewAdapter movieSearchViewAdapter;
    private SwipeRefreshLayout swipeRefreshSearchMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_movie_screen);
        Intent searchMovieIntent = getIntent();
        movieSearchQuery = searchMovieIntent.getStringExtra(MOVIE_SEARCH);
        executeRequestSearchMovie();

        findViews();
        addListeners();
    }

    private void findViews() {
        swipeRefreshSearchMovie = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshSearchMovie);
        movieResultSearch = (TextView) findViewById(R.id.search_movie_screen_detail);
        movieSearchList = (ListView) findViewById(R.id.search_movie__screen_list);
    }

    private void addListeners() {

        swipeRefreshSearchMovie.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(REFRESH_SEARCH_LOG, "onRefresh called from SwipeRefreshLayout");
                        swipeRefreshSearchMovie.setRefreshing(true);
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        executeRequestSearchMovie();
                        swipeRefreshSearchMovie.setRefreshing(false);
                    }
                }
        );


    }

    private void executeRequestSearchMovie() {
        // Request search movies
        new GetSearchMovieAsyncTask(this, movieSearchQuery) {

            @Override
            protected void onResponseSuccess(MoviesResponse moviesResponse) {
                DLog.d(tag, "onResponseSuccess " + moviesResponse);
                // Adapter
                String searchFor = getResources().getString(R.string.search_for);
                movieResultSearch.setText(searchFor + " " + movieSearchQuery);
                List<Movie> movies = moviesResponse.getMovies();
                movieSearchViewAdapter = new ListMovieSearchViewAdapter(SearchMovieScreen.this, movies);
                movieSearchList.setAdapter(movieSearchViewAdapter);
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
