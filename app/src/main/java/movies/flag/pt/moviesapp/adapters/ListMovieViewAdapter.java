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
        final Movie moviesList = getItem( position );

        if (v == null) {
            v = LayoutInflater.from( getContext() ).inflate( R.layout.latest_movie_item, null );
            holder = new MovieHolder();
            holder.movieItem = (TextView) v.findViewById( R.id.latest_movie_item );
            //holder.detailMovieCover = (ImageView) v.findViewById( R.id.detail_movie_screen_cover );
            //holder.detailMovieTitle = (TextView) v.findViewById( R.id.detail_movie_screen_title );
            //holder.detailMovieOverview = (TextView) v.findViewById( R.id.detail_movie_screen_overview );
            v.setTag( holder );
        } else {
            holder = (MovieHolder) v.getTag();
        }

        holder.movieItem.setText( moviesList.getTitle() );
        holder.movieItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movieDetailIntent = new Intent(getContext(), DetailMovieScreen.class );
                String movieTitle = moviesList.getTitle();
                String movieOverview = moviesList.getOverview();
                movieDetailIntent.putExtra(DetailMovieScreen.MOVIE_TITLE, movieTitle);
                movieDetailIntent.putExtra(DetailMovieScreen.MOVIE_OVERVIEW, movieOverview);
                getContext().startActivity(movieDetailIntent);
                //holder.detailMovieCover.setImageBitmap( moviesList.getPosterPath() );
                //holder.detailMovieTitle.setText( moviesList.getTitle() );
               // holder.detailMovieOverview.setText( moviesList.getOverview() );
            }
        } );
        return v;
    }

    private class MovieHolder {
        TextView movieItem;
        //TextView detailMovieTitle;
        //TextView detailMovieOverview;
        //ImageView detailMovieCover;
    }


}
