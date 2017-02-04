package movies.flag.pt.moviesapp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import movies.flag.pt.moviesapp.R;

/**
 * Created by Marina on 01/02/2017.
 */

public class DetailMovieScreen extends Screen {

    private TextView detailMovieTitle;
    private TextView detailMovieOverview;
    private TextView detailMovieDate;
    private ImageView detailMoviePoster;

    public static final String MOVIE_TITLE = "MovieTitle";
    public static final String MOVIE_OVERVIEW = "MovieOverview";
    public static final String MOVIE_POSTER = "MoviePoster";
    public static final String MOVIE_DATE = "MovieDate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_movie_screen);

        detailMoviePoster = (ImageView) findViewById(R.id.detail_movie_screen_cover);
        detailMovieTitle = (TextView) findViewById(R.id.detail_movie_screen_title);
        detailMovieOverview = (TextView) findViewById(R.id.detail_movie_screen_overview);
        detailMovieDate = (TextView) findViewById(R.id.detail_movie_screen_date);

        Intent movieDetailIntent = getIntent();
        String movieTitle = movieDetailIntent.getStringExtra(MOVIE_TITLE);
        detailMovieTitle.setText(movieTitle);
        String movieOverview = movieDetailIntent.getStringExtra(MOVIE_OVERVIEW);
        detailMovieOverview.setText(movieOverview);
        String movieDate = movieDetailIntent.getStringExtra(MOVIE_DATE);
        detailMovieDate.setText(R.string.release_date + movieDate);
    }


}