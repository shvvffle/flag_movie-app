package movies.flag.pt.moviesapp.screens;

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

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.http.entities.Movie;

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

    public static final String MOVIE_DETAILS_TO_RANDOM = "MovieDetailsToRandom";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.random_movie_screen);
        findViews();
        getInfoIntent();
    }

    private void findViews() {

        randomMoviePoster = (ImageView) findViewById(R.id.random_movie_screen_cover);
        randomMovieTitle = (TextView) findViewById(R.id.random_movie_screen_title);
        randomMovieOverview = (TextView) findViewById(R.id.random_movie_screen_overview);
        randomMovieVote = (TextView) findViewById(R.id.random_movie_screen_vote);
        shareButton = (ImageView) findViewById(R.id.random_movie_screen_share_icon);
        loaderView = (ProgressBar) findViewById(R.id.random_movie_screen_loader);
    }

    private void getInfoIntent() {
        Intent movieRandomIntent = getIntent();
        Movie movie = movieRandomIntent.getParcelableExtra(MOVIE_DETAILS_TO_RANDOM);
        RandomMovieScreen.DownloadPosterPathAsyncTask task = new RandomMovieScreen.DownloadPosterPathAsyncTask();
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
            randomMoviePoster.setImageBitmap(bitmap);
        }
    }
}
