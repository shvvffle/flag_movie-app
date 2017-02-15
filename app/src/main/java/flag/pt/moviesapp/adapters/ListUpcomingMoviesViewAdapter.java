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

public class ListUpcomingMoviesViewAdapter extends ArrayAdapter<Movie> {

    private static final String TAG = ListUpcomingMoviesViewAdapter.class.getSimpleName();

    public ListUpcomingMoviesViewAdapter(Context context, List<Movie> upcomingMovies) {
        super(context, 0, upcomingMovies);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final upcomingMoviesHolder holder;
        final Movie upcomingMovies = getItem(position);

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.upcoming_movies_item, null);
            holder = new upcomingMoviesHolder();
            holder.upcomingMovieCardView = (CardView) v.findViewById(R.id.upcoming_movie_item_cardview);
            holder.upcomingMovieItem = (TextView) v.findViewById(R.id.upcoming_movie_item);
            holder.upcomingMovieDate = (TextView) v.findViewById(R.id.upcoming_movie_item_date);
            v.setTag(holder);
        } else {
            holder = (upcomingMoviesHolder) v.getTag();
        }

        holder.upcomingMovieItem.setText(upcomingMovies.getTitle());
        holder.upcomingMovieDate.setText(upcomingMovies.getReleaseDate());
        holder.upcomingMovieCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent upcomingMoviesIntent = new Intent(getContext(), DetailMovieScreen.class);
                upcomingMoviesIntent.putExtra(DetailMovieScreen.MOVIE_DETAILS, upcomingMovies);
                getContext().startActivity(upcomingMoviesIntent);
            }
        });
        return v;
    }

    private class upcomingMoviesHolder {
        TextView upcomingMovieItem;
        TextView upcomingMovieDate;
        CardView upcomingMovieCardView;
    }


}
