package flag.pt.moviesapp.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import flag.pt.moviesapp.R;
import flag.pt.moviesapp.http.entities.Movie;
import flag.pt.moviesapp.screens.DetailMovieScreen;


/**
 * Created by Marina on 26/01/2017.
 */

public class ListNowPlayingMoviesViewAdapter extends ArrayAdapter<Movie> {

    private static final String TAG = ListNowPlayingMoviesViewAdapter.class.getSimpleName();


    public ListNowPlayingMoviesViewAdapter(Context context, List<Movie> nowPlayingMovies) {
        super(context, 0, nowPlayingMovies);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final nowPlayingMoviesHolder holder;
        final Movie nowPlayingMovies = getItem(position);
        String movieReleaseDate = getContext().getResources().getString(R.string.release_date);


        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.now_playing_movies_item, null);
            holder = new nowPlayingMoviesHolder();
            holder.nowPlayingMoviesCardView = (CardView) v.findViewById(R.id.now_playing_movies_item_cardview);
            holder.nowPlayingMoviesItem = (TextView) v.findViewById(R.id.now_playing_movies_item);
            holder.nowPlayingMoviesItemReleaseDate = (TextView) v.findViewById(R.id.now_playing_movies_item_date);
            v.setTag(holder);
        } else {
            holder = (nowPlayingMoviesHolder) v.getTag();
        }


        holder.nowPlayingMoviesItem.setText(nowPlayingMovies.getTitle());
        holder.nowPlayingMoviesItemReleaseDate.setText(movieReleaseDate + " " + nowPlayingMovies.getReleaseDate());
        holder.nowPlayingMoviesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movieDetailIntent = new Intent(getContext(), DetailMovieScreen.class);
                movieDetailIntent.putExtra(DetailMovieScreen.MOVIE_DETAILS, nowPlayingMovies);
                getContext().startActivity(movieDetailIntent);
            }
        });
        return v;
    }

    private class nowPlayingMoviesHolder {
        TextView nowPlayingMoviesItem;
        TextView nowPlayingMoviesItemReleaseDate;
        CardView nowPlayingMoviesCardView;
    }


}
