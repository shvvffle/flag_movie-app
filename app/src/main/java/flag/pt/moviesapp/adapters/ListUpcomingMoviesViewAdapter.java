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
            holder.upcomingMoviesCardView = (CardView) v.findViewById(R.id.upcoming_movies_item_cardview);
            holder.upcomingMoviesItem = (TextView) v.findViewById(R.id.upcoming_movies_item);
            holder.upcomingMoviesDate = (TextView) v.findViewById(R.id.upcoming_movies_item_date);
            v.setTag(holder);
        } else {
            holder = (upcomingMoviesHolder) v.getTag();
        }

        holder.upcomingMoviesItem.setText(upcomingMovies.getTitle());
        holder.upcomingMoviesDate.setText(upcomingMovies.getReleaseDate());
        holder.upcomingMoviesCardView.setOnClickListener(new View.OnClickListener() {
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
        TextView upcomingMoviesItem;
        TextView upcomingMoviesDate;
        CardView upcomingMoviesCardView;
    }


}
