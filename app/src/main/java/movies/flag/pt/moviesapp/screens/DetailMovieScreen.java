package movies.flag.pt.moviesapp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.http.entities.Movie;

/**
 * Created by Marina on 01/02/2017.
 */

public class DetailMovieScreen extends Screen {

    private TextView detailMovieTitle;
    private TextView detailMovieOverview;
    private TextView detailMovieVote;
    private ImageView detailMoviePoster;
    private ImageView shareButton;


    public static final String MOVIE_DETAILS = "MovieDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_movie_screen);

        findViews();
        getInfoIntent();

    }

    private void findViews() {

        detailMoviePoster = (ImageView) findViewById(R.id.detail_movie_screen_cover);
        detailMovieTitle = (TextView) findViewById(R.id.detail_movie_screen_title);
        detailMovieOverview = (TextView) findViewById(R.id.detail_movie_screen_overview);
        detailMovieVote = (TextView) findViewById(R.id.detail_movie_screen_vote);
        shareButton = (ImageView) findViewById(R.id.detail_movie_screen_share_icon);

    }

    private void getInfoIntent() {
        Intent movieDetailIntent = getIntent();
        Movie movie = movieDetailIntent.getParcelableExtra(MOVIE_DETAILS);
        final String movieTitle = movie.getTitle();
        detailMovieTitle.setText(movieTitle);
        String movieOverview = movie.getOverview();
        detailMovieOverview.setText(movieOverview);
        final int movieVote = movie.getVoteCount();
        String movieVoteString = getResources().getString(R.string.movie_vote);
        detailMovieVote.setText(movieVoteString + (String.valueOf(movieVote)));

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareMovieTitle = movieTitle;
                String shareBody = getResources().getString(R.string.share_body);
                String shareVote = String.valueOf(movieVote);
                String shareUsing = getResources().getString(R.string.share_using);
                String shareVoteString = getResources().getString(R.string.share_vote);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody + " " + shareMovieTitle + " " + shareVoteString + " " + shareVote);
                startActivity(Intent.createChooser(shareIntent, shareUsing));
            }
        });

    }

}