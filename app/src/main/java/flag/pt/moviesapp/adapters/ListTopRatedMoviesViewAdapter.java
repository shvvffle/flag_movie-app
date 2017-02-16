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

public class ListTopRatedMoviesViewAdapter extends ArrayAdapter<Movie> {

    private static final String TAG = ListTopRatedMoviesViewAdapter.class.getSimpleName();

    public ListTopRatedMoviesViewAdapter(Context context, List<Movie> topRatedMovies) {
        super(context, 0, topRatedMovies);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final topRatedMoviesHolder holder;
        final Movie topRatedMovies = getItem(position);

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.top_rated_movies_item, null);
            holder = new topRatedMoviesHolder();
            holder.topRatedMoviesCardView = (CardView) v.findViewById(R.id.top_rated_movie_item_cardview);
            holder.topRatedMoviesItem = (TextView) v.findViewById(R.id.top_rated_movies_item);
            holder.topRatedMoviesItemRating = (TextView) v.findViewById(R.id.top_rated_movies_item_rating);
            v.setTag(holder);
        } else {
            holder = (topRatedMoviesHolder) v.getTag();
        }

        holder.topRatedMoviesItem.setText(topRatedMovies.getTitle());
        holder.topRatedMoviesItemRating.setText(String.valueOf(topRatedMovies.getVoteAverage()));
        holder.topRatedMoviesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movieTopRatedIntent = new Intent(getContext(), DetailMovieScreen.class);
                movieTopRatedIntent.putExtra(DetailMovieScreen.MOVIE_DETAILS, topRatedMovies);
                getContext().startActivity(movieTopRatedIntent);
            }
        });
        return v;
    }

    private class topRatedMoviesHolder {
        TextView topRatedMoviesItem;
        TextView topRatedMoviesItemRating;
        CardView topRatedMoviesCardView;
    }


}
