package flag.pt.moviesapp.screens;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;

import flag.pt.moviesapp.R;
import flag.pt.moviesapp.http.entities.Movie;

/**
 * Created by Marina on 01/02/2017.
 */

public class DetailMovieScreen extends Screen {

    private TextView detailMovieTitle;
    private TextView detailMovieOverview;
    private TextView detailMovieVote;
    private ImageView detailMoviePoster;
    private ImageView shareButton;
    private ProgressBar loaderView;
    private TextView detailMovieDate;


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
        detailMovieDate = (TextView) findViewById(R.id.detail_movie_screen_date);
        shareButton = (ImageView) findViewById(R.id.detail_movie_screen_share_icon);
        loaderView = (ProgressBar) findViewById(R.id.detail_movie_screen_loader);

    }

    private void getInfoIntent() {
        Intent movieDetailIntent = getIntent();
        Movie movie = movieDetailIntent.getParcelableExtra(MOVIE_DETAILS);
        final String movieTitle = movie.getTitle().toUpperCase();
        detailMovieTitle.setText(movieTitle);
        DownloadPosterPathAsyncTask task = new DownloadPosterPathAsyncTask();
        String moviePosterPath = "https://image.tmdb.org/t/p/1280" + movie.getPosterPath();
        task.execute(moviePosterPath);
        String movieOverview = movie.getOverview();
        detailMovieOverview.setText(movieOverview);
        final double movieVote = movie.getVoteAverage();
        String movieVoteString = getResources().getString(R.string.movie_vote);
        detailMovieVote.setText(movieVoteString + " " +  (String.valueOf(movieVote)));
        detailMovieDate.setText(movie.getReleaseDate());

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareMovieTitle = movieTitle + ".";
                String shareBody = getResources().getString(R.string.share_body_movie);
                String shareVote = String.valueOf(movieVote);
                String shareUsing = getResources().getString(R.string.share_using);
                String shareVoteString = getResources().getString(R.string.share_vote);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody + " " + shareMovieTitle + " " + shareVoteString + " " + shareVote + "!");
                startActivity(Intent.createChooser(shareIntent, shareUsing));
            }
        });

    }

    private class DownloadPosterPathAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            loaderView.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            return downloadImage(url);
        }

        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.toString());
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            loaderView.setVisibility(View.GONE);
            detailMoviePoster.setImageBitmap(bitmap);
        }
    }

}