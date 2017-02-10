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
            v = LayoutInflater.from( getContext() ).inflate( R.layout.search_movie_item, null );
            holder = new MovieSearchHolder();
            holder.searchMovieItem = (TextView) v.findViewById( R.id.search_movie_item );
            v.setTag( holder );
        } else {
            holder = (MovieSearchHolder) v.getTag();
        }

        holder.searchMovieItem.setText( movieSearchList.getTitle() );
        holder.searchMovieItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movieDetailIntent = new Intent(getContext(), DetailMovieScreen.class );
                movieDetailIntent.putExtra(DetailMovieScreen.MOVIE_DETAILS, movieSearchList);
                getContext().startActivity(movieDetailIntent);
            }
        } );
        return v;
    }

    private class MovieSearchHolder {
        TextView searchMovieItem;
    }


}
