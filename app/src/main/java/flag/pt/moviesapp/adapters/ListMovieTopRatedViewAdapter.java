package flag.pt.moviesapp.adapters;


import android.content.Context;
import android.content.Intent;
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

public class ListMovieTopRatedViewAdapter extends ArrayAdapter<Movie> {

    private static final String TAG = ListMovieTopRatedViewAdapter.class.getSimpleName();

    public ListMovieTopRatedViewAdapter(Context context, List<Movie> topRatedMovies) {
        super(context, 0, topRatedMovies);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final TopRatedMovieHolder holder;
        final Movie topRatedMovies = getItem(position);

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.top_rated_movie_item, null);
            holder = new TopRatedMovieHolder();
            holder.movieTopRatedItem = (TextView) v.findViewById(R.id.top_rated_movie_item);
            holder.movieTopRatedItemRating = (TextView) v.findViewById(R.id.top_rated_movie_item_rating);
            v.setTag(holder);
        } else {
            holder = (TopRatedMovieHolder) v.getTag();
        }

        holder.movieTopRatedItem.setText(topRatedMovies.getTitle());
        holder.movieTopRatedItemRating.setText(String.valueOf(topRatedMovies.getVoteAverage()));
        holder.movieTopRatedItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movieTopRatedIntent = new Intent(getContext(), DetailMovieScreen.class);
                movieTopRatedIntent.putExtra(DetailMovieScreen.MOVIE_DETAILS, topRatedMovies);
                getContext().startActivity(movieTopRatedIntent);
            }
        });
        return v;
    }

    private class TopRatedMovieHolder {
        TextView movieTopRatedItem;
        TextView movieTopRatedItemRating;
    }


}
