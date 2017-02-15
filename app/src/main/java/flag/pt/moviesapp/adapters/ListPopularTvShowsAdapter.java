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
import flag.pt.moviesapp.http.entities.ResultsPopularTvShows;
import flag.pt.moviesapp.screens.DetailTvShowScreen;

/**
 * Created by Marina on 29/01/2017.
 */

public class ListPopularTvShowsAdapter extends ArrayAdapter<ResultsPopularTvShows> {

    private static final String TAG = ListPopularTvShowsAdapter.class.getSimpleName();

    public ListPopularTvShowsAdapter(Context context, List<ResultsPopularTvShows> popularTvShows) {
        super( context, 0, popularTvShows );
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        popularTvShowsHolder holder;
        final ResultsPopularTvShows popularTvShows = getItem( position );

        if (v == null) {
            v = LayoutInflater.from( getContext() ).inflate( R.layout.popular_tv_shows_item, null );
            holder = new popularTvShowsHolder();
            holder.popularTvShowsCardView = (CardView) v.findViewById(R.id.popular_tv_shows_item_cardview);
            holder.popularTvShowsItem = (TextView) v.findViewById( R.id.popular_tv_shows_item );
            holder.popularTvShowsItemAirDate = (TextView) v.findViewById(R.id.popular_tv_shows_item_date);
            v.setTag( holder );
        } else {
            holder = (popularTvShowsHolder) v.getTag();
        }

        holder.popularTvShowsItem.setText( popularTvShows.getName() );
        holder.popularTvShowsItemAirDate.setText(popularTvShows.getFirstAirDate());
        holder.popularTvShowsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TvShowDetailIntent = new Intent(getContext(), DetailTvShowScreen.class );
                TvShowDetailIntent.putExtra(DetailTvShowScreen.POPULAR_TV_SHOW_DETAILS, popularTvShows);
                getContext().startActivity(TvShowDetailIntent);
            }
        });
        return v;
    }

    private class popularTvShowsHolder {
        TextView popularTvShowsItem;
        TextView popularTvShowsItemAirDate;
        CardView popularTvShowsCardView;
    }
}
