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
import movies.flag.pt.moviesapp.screens.SearchMovieScreen;


/**
 * Created by Marina on 26/01/2017.
 */

public class ListMovieSearchViewAdapter extends ArrayAdapter<Movie> {

    private static final String TAG = ListMovieSearchViewAdapter.class.getSimpleName();

    public ListMovieSearchViewAdapter(Context context, List<Movie> movies) {
        super( context, 0, movies );
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final MovieSearchHolder holder;
        final Movie movieSearchList = getItem( position );

        if (v == null) {
            v = LayoutInflater.from( getContext() ).inflate( R.layout.latest_movie_item, null );
            holder = new MovieSearchHolder();
            holder.movieItem = (TextView) v.findViewById( R.id.latest_movie_item );
            v.setTag( holder );
        } else {
            holder = (MovieSearchHolder) v.getTag();
        }

        holder.movieItem.setText( movieSearchList.getTitle() );
        holder.movieItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movieSearchDetailIntent = new Intent(getContext(), SearchMovieScreen.class );
                String movieTitle = movieSearchList.getTitle();
                movieSearchDetailIntent.putExtra(SearchMovieScreen.MOVIE_TITLE, movieTitle);
                getContext().startActivity(movieSearchDetailIntent);
            }
        } );
        return v;
    }

    private class MovieSearchHolder {
        TextView movieItem;
    }


}
