package flag.pt.moviesapp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import flag.pt.moviesapp.R;

import static flag.pt.moviesapp.screens.SearchMoviesScreen.MOVIE_SEARCH;


/**
 * Created by Marina on 26/01/2017.
 */

public class MenuScreen extends Screen {

    private SearchView searchView;
    private Button getLatestMoviesButton;
    private Button getPopularTvShowButton;
    private Button getUpcomingMoviesButton;
    private Button getTopRatedMovieButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_screen);

        findViews();
        addListeners();
    }

    private void findViews() {
        searchView = (SearchView) findViewById(R.id.search_view);
        getLatestMoviesButton = (Button) findViewById(R.id.menu_screen_now_playing_movies_screen_button);
        getPopularTvShowButton = (Button) findViewById(R.id.menu_screen_popular_tv_shows_screen_button);
        getUpcomingMoviesButton = (Button) findViewById(R.id.menu_screen_upcoming_movies_screen_button);
        getTopRatedMovieButton = (Button) findViewById(R.id.menu_screen_top_rated_movies_screen_button);

    }

    private void addListeners() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent searchMovieIntent = new Intent(MenuScreen.this, SearchMoviesScreen.class);
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
                startActivity(NowPlayingMoviesScreen.class);
            }
        });
        getTopRatedMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TopRatedMoviesScreen.class);
            }
        });
        getPopularTvShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PopularTvShowsScreen.class);
            }
        });
        getUpcomingMoviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(UpcomingMoviesScreen.class);
            }
        });

    }

    private void startActivity(Class<?> activityClass) {
        Intent intent = new Intent(MenuScreen.this, activityClass);
        startActivity(intent);
    }
}
