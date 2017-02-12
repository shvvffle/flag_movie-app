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
import flag.pt.moviesapp.http.entities.ResultPopularTvShow;

/**
 * Created by Marina on 01/02/2017.
 */

public class DetailTvShowScreen extends Screen {

    private TextView detailTvShowTitle;
    private TextView detailTvShowOverview;
    private TextView detailTvShowVote;
    private ImageView detailTvShowPoster;
    private TextView detailTvShowDate;
    private ImageView shareButton;
    private ProgressBar loaderView;


    public static final String TV_SHOW_DETAILS = "TvShowDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_tv_show_screen);

        findViews();
        getInfoIntent();

    }

    private void findViews() {

        detailTvShowPoster = (ImageView) findViewById(R.id.detail_tv_show_screen_cover);
        detailTvShowTitle = (TextView) findViewById(R.id.detail_tv_show_screen_title);
        detailTvShowOverview = (TextView) findViewById(R.id.detail_tv_show_screen_overview);
        detailTvShowVote = (TextView) findViewById(R.id.detail_tv_show_screen_vote);
        detailTvShowDate = (TextView) findViewById(R.id.detail_tv_show_screen_date);
        shareButton = (ImageView) findViewById(R.id.detail_tv_show_screen_share_icon);
        loaderView = (ProgressBar) findViewById(R.id.detail_tv_show_screen_loader);

    }

    private void getInfoIntent() {
        Intent TvShowDetailIntent = getIntent();
        ResultPopularTvShow tvShow = TvShowDetailIntent.getParcelableExtra(TV_SHOW_DETAILS);
        final String tvShowTitle = tvShow.getName().toUpperCase();
        detailTvShowTitle.setText(tvShowTitle);
        DownloadPosterPathAsyncTask task = new DownloadPosterPathAsyncTask();
        String tvShowPosterPath = "https://image.tmdb.org/t/p/w1280" + tvShow.getPosterPath();
        task.execute(tvShowPosterPath);
        String tvShowDate = tvShow.getFirstAirDate();
        detailTvShowDate.setText(tvShowDate);
        String tvShowOverview = tvShow.getOverview();
        detailTvShowOverview.setText(tvShowOverview);
        final double tvShowVote = tvShow.getVoteAverage();
        String tvShowVoteString = getResources().getString(R.string.tv_show_vote);
        detailTvShowVote.setText(tvShowVoteString + " " + (String.valueOf(tvShowVote)));

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareTvShowTitle = tvShowTitle + ".";
                String shareBody = getResources().getString(R.string.share_body_tv);
                String shareVote = String.valueOf(tvShowVote);
                String shareUsing = getResources().getString(R.string.share_using);
                String shareVoteString = getResources().getString(R.string.share_vote);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody + " " + shareTvShowTitle + " " + shareVoteString + " " + shareVote + "!");
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
            detailTvShowPoster.setImageBitmap(bitmap);
        }
    }

}