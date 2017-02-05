package movies.flag.pt.moviesapp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import movies.flag.pt.moviesapp.R;

import static movies.flag.pt.moviesapp.screens.SearchMovieScreen.MOVIE_SEARCH;


/**
 * Created by Marina on 26/01/2017.
 */

public class StartScreen extends Screen {

    private ImageView searchBarAction;
    private EditText searchBarInput;
    private Button getLatestMoviesButton;
    private Button getPopularTvShowButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start_screen);

        findViews();
        addListeners();
    }

    private void findViews() {

        searchBarAction = (ImageView) findViewById(R.id.all_screens_search_bar_button);
        searchBarInput = (EditText) findViewById(R.id.all_screens_search_bar_input);
        getLatestMoviesButton = (Button) findViewById(R.id.start_screen_latest_movie_screen_button);
        getPopularTvShowButton = (Button) findViewById(R.id.start_screen_popular_tv_show_screen_button);

    }

    private void addListeners() {
        getLatestMoviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(LatestMovieScreen.class);
            }
        });
        getPopularTvShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PopularTvShowScreen.class);
            }
        });
        searchBarAction.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent searchMovieIntent = new Intent(StartScreen.this, SearchMovieScreen.class);
                String movieSearched = searchBarInput.getText().toString();
                searchMovieIntent.putExtra(MOVIE_SEARCH, movieSearched);
                startActivity(searchMovieIntent);
            }
        });
    }

    private void startActivity(Class<?> activityClass) {
        Intent intent = new Intent(StartScreen.this, activityClass);
        startActivity(intent);
    }
}
