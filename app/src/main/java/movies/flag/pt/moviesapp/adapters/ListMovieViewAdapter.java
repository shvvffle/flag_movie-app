package movies.flag.pt.moviesapp.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.http.entities.Movie;
import movies.flag.pt.moviesapp.screens.DetailMovieScreen;


/**
 * Created by Marina on 26/01/2017.
 */

public class ListMovieViewAdapter extends ArrayAdapter<Movie> {

    private static final String TAG = ListMovieViewAdapter.class.getSimpleName();

    public ListMovieViewAdapter(Context context, List<Movie> movies) {
        super( context, 0, movies );
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final MovieHolder holder;
        final Movie movie = getItem( position );

        if (v == null) {
            v = LayoutInflater.from( getContext() ).inflate( R.layout.latest_movie_item, null );
            holder = new MovieHolder();
            holder.movieItem = (TextView) v.findViewById( R.id.latest_movie_item );
            v.setTag( holder );
        } else {
            holder = (MovieHolder) v.getTag();
        }

        holder.movieItem.setText( movie.getTitle() );
        holder.movieItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movieDetailIntent = new Intent(getContext(), DetailMovieScreen.class );
                movieDetailIntent.putExtra(DetailMovieScreen.MOVIE_DETAILS, movie);
                getContext().startActivity(movieDetailIntent);
            }
        } );
        return v;
    }

    private class MovieHolder {
        TextView movieItem;
    }


}
