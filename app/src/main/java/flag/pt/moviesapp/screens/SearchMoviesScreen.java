package flag.pt.moviesapp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import flag.pt.moviesapp.R;
import flag.pt.moviesapp.adapters.ListSearchMoviesViewAdapter;
import flag.pt.moviesapp.http.entities.Movie;
import flag.pt.moviesapp.http.entities.NowPlayingMoviesResponse;
import flag.pt.moviesapp.http.requests.GetSearchMoviesAsyncTask;
import flag.pt.moviesapp.utils.DLog;

/**
 * Created by Marina on 01/02/2017.
 */

public class SearchMoviesScreen extends Screen {

    public static final String MOVIE_SEARCH = "movieSearch";
    private static String REFRESH_SEARCH_LOG;
    private TextView moviesResultSearch;
    private String movieSearchQuery;
    private ListView moviesSearchList;
    private ListSearchMoviesViewAdapter moviesSearchViewAdapter;
    private SwipeRefreshLayout swipeRefreshSearchMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_movies_screen);
        Intent searchMovieIntent = getIntent();
        movieSearchQuery = searchMovieIntent.getStringExtra(MOVIE_SEARCH);
        executeRequestSearchMovies();

        findViews();
        addListeners();
    }

    private void findViews() {
        swipeRefreshSearchMovie = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshSearchMovie);
        moviesResultSearch = (TextView) findViewById(R.id.search_movies_screen_detail);
        moviesSearchList = (ListView) findViewById(R.id.search_movies_screen_list);
    }

    private void addListeners() {

        swipeRefreshSearchMovie.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        DLog.d(REFRESH_SEARCH_LOG, "onRefresh called from SwipeRefreshLayout");
                        swipeRefreshSearchMovie.setRefreshing(true);
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        executeRequestSearchMovies();
                        swipeRefreshSearchMovie.setRefreshing(false);
                    }
                }
        );


    }

    private void executeRequestSearchMovies() {
        // Request search movies
        new GetSearchMoviesAsyncTask(this, movieSearchQuery) {

            @Override
            protected void onResponseSuccess(NowPlayingMoviesResponse nowPlayingMoviesResponse) {
                DLog.d(tag, "onResponseSuccess " + nowPlayingMoviesResponse);
                // Adapter
                String searchFor = getResources().getString(R.string.search_for);
                moviesResultSearch.setText(searchFor + " " + movieSearchQuery);
                List<Movie> movies = nowPlayingMoviesResponse.getMovies();
                moviesSearchViewAdapter = new ListSearchMoviesViewAdapter(SearchMoviesScreen.this, movies);
                moviesSearchList.setAdapter(moviesSearchViewAdapter);
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
