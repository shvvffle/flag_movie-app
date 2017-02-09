package movies.flag.pt.moviesapp.screens;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import movies.flag.pt.moviesapp.R;

/**
 * Created by Marina on 09/02/2017.
 */

public class RandomMovieScreen extends Screen {

    private TextView randomMovieTitle;
    private TextView randomMovieOverview;
    private TextView randomMovieVote;
    private ImageView randomMoviePoster;
    private ImageView shareButton;
    private ProgressBar loaderView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.random_movie_screen);
        findViews();
    }

    private void findViews() {

        randomMoviePoster = (ImageView) findViewById(R.id.random_movie_screen_cover);
        randomMovieTitle = (TextView) findViewById(R.id.random_movie_screen_title);
        randomMovieOverview = (TextView) findViewById(R.id.random_movie_screen_overview);
        randomMovieVote = (TextView) findViewById(R.id.random_movie_screen_vote);
        shareButton = (ImageView) findViewById(R.id.random_movie_screen_share_icon);
        loaderView = (ProgressBar) findViewById(R.id.random_movie_screen_loader);
    }
}
