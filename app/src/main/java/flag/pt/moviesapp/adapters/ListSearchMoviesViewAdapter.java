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

public class ListSearchMoviesViewAdapter extends ArrayAdapter<Movie> {

    private static final String TAG = ListSearchMoviesViewAdapter.class.getSimpleName();

    public ListSearchMoviesViewAdapter(Context context, List<Movie> searchMovies) {
        super( context, 0, searchMovies );
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final searchMoviesHolder holder;
        final Movie searchedMovie = getItem( position );

        if (v == null) {
            v = LayoutInflater.from( getContext() ).inflate( R.layout.search_movies_item, null );
            holder = new searchMoviesHolder();
            holder.searchMoviesItem = (TextView) v.findViewById( R.id.search_movies_item );
            v.setTag( holder );
        } else {
            holder = (searchMoviesHolder) v.getTag();
        }

        holder.searchMoviesItem.setText( searchedMovie.getTitle() );
        holder.searchMoviesItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movieDetailIntent = new Intent(getContext(), DetailMovieScreen.class );
                movieDetailIntent.putExtra(DetailMovieScreen.MOVIE_DETAILS, searchedMovie);
                getContext().startActivity(movieDetailIntent);
            }
        } );
        return v;
    }

    private class searchMoviesHolder {
        TextView searchMoviesItem;
    }


}
