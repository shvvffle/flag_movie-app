package flag.pt.moviesapp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import flag.pt.moviesapp.R;

import static flag.pt.moviesapp.screens.SearchMovieScreen.MOVIE_SEARCH;


/**
 * Created by Marina on 26/01/2017.
 */

public class StartScreen extends Screen {

    private SearchView searchView;
    private Button getLatestMoviesButton;
    private Button getPopularTvShowButton;
    private Button getUpcomingMoviesButton;
    private Button getTopRatedMovieButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start_screen);

        findViews();
        addListeners();
    }

    private void findViews() {
        searchView = (SearchView) findViewById(R.id.search_view);
        getLatestMoviesButton = (Button) findViewById(R.id.start_screen_latest_movie_screen_button);
        getPopularTvShowButton = (Button) findViewById(R.id.start_screen_popular_tv_show_screen_button);
        getUpcomingMoviesButton = (Button) findViewById(R.id.start_screen_upcoming_movies_screen_button);
        getTopRatedMovieButton = (Button) findViewById(R.id.start_screen_top_rated_movie_screen_button);

    }

    private void addListeners() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent searchMovieIntent = new Intent(StartScreen.this, SearchMovieScreen.class);
                String movieSearched = searchView.getQuery().toString();
                searchMovieIntent.putExtra(MOVIE_SEARCH, movieSearched);
                startActivity(searchMovieIntent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        getLatestMoviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(LatestMovieScreen.class);
            }
        });
        getTopRatedMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TopRatedMovieScreen.class);
            }
        });
        getPopularTvShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PopularTvShowScreen.class);
            }
        });
        getUpcomingMoviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(UpcomingMovieScreen.class);
            }
        });

    }

    private void startActivity(Class<?> activityClass) {
        Intent intent = new Intent(StartScreen.this, activityClass);
        startActivity(intent);
    }
}
