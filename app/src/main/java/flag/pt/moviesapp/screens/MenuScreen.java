package flag.pt.moviesapp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

import flag.pt.moviesapp.R;

import static flag.pt.moviesapp.screens.SearchMoviesScreen.MOVIE_SEARCH;


/**
 * Created by Marina on 26/01/2017.
 */

public class MenuScreen extends Screen {

    private SearchView searchView;
    private LinearLayout getNowPlayingMovies;
    private LinearLayout getPopularTvShows;
    private LinearLayout getUpcomingMovies;
    private LinearLayout getTopRatedMovies;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_screen);

        findViews();
        addListeners();
    }

    private void findViews() {

        searchView = (SearchView) findViewById(R.id.search_view);
        getNowPlayingMovies = (LinearLayout) findViewById(R.id.menu_screen_linear_layout_now_playing_movies);
        getPopularTvShows = (LinearLayout) findViewById(R.id.menu_screen_linear_layout_popular_tv_shows);
        getUpcomingMovies = (LinearLayout) findViewById(R.id.menu_screen_linear_layout_upcoming_movies);
        getTopRatedMovies = (LinearLayout) findViewById(R.id.menu_screen_linear_layout_top_rated_movies);

    }

    private void addListeners() {
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent searchMovieIntent = new Intent(MenuScreen.this, SearchMoviesScreen.class);
                String movieSearched = searchView.getQuery().toString();
                searchMovieIntent.putExtra(MOVIE_SEARCH, movieSearched);
                startActivity(searchMovieIntent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        getNowPlayingMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(NowPlayingMoviesScreen.class);
            }
        });
        getTopRatedMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TopRatedMoviesScreen.class);
            }
        });
        getPopularTvShows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PopularTvShowsScreen.class);
            }
        });
        getUpcomingMovies.setOnClickListener(new View.OnClickListener() {
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
