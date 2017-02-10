package topRatedMovies.flag.pt.moviesapp.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import topRatedMovies.flag.pt.moviesapp.R;
import topRatedMovies.flag.pt.moviesapp.http.entities.Movie;
import topRatedMovies.flag.pt.moviesapp.screens.RandomMovieScreen;


/**
 * Created by Marina on 26/01/2017.
 */

public class RandomMovieViewAdapter extends ArrayAdapter<Movie> {

    private static final String TAG = RandomMovieViewAdapter.class.getSimpleName();

    public RandomMovieViewAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final MovieHolder holder;
        final Movie movie = getItem(position);

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.latest_movie_item, null);
            holder = new MovieHolder();
            holder.movieItem = (TextView) v.findViewById(R.id.latest_movie_item);
            v.setTag(holder);
        } else {
            holder = (MovieHolder) v.getTag();
        }

        holder.movieItem.setText(movie.getTitle());
        holder.movieItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movieRandomIntent = new Intent(getContext(), RandomMovieScreen.class);
                movieRandomIntent.putExtra(RandomMovieScreen.MOVIE_DETAILS_TO_RANDOM, movie);
                getContext().startActivity(movieRandomIntent);
            }
        });
        return v;
    }

    private class MovieHolder {
        TextView movieItem;
    }


}
