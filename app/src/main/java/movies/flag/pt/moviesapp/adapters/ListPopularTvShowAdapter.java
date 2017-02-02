package movies.flag.pt.moviesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.http.entities.ResultPopularTvShow;

/**
 * Created by Marina on 29/01/2017.
 */

public class ListPopularTvShowAdapter extends ArrayAdapter<ResultPopularTvShow> {

    private static final String TAG = ListPopularTvShowAdapter.class.getSimpleName();

    public ListPopularTvShowAdapter(Context context, List<ResultPopularTvShow> tvShows) {
        super( context, 0, tvShows );
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TvShowHolder holder;
        ResultPopularTvShow tvShowList = getItem( position );

        if (v == null) {
            v = LayoutInflater.from( getContext() ).inflate( R.layout.popular_tv_show_item, null );
            holder = new TvShowHolder();
            holder.tvShowItem = (TextView) v.findViewById( R.id.popular_tv_show_item );
            v.setTag( holder );
        } else {
            holder = (TvShowHolder) v.getTag();
        }

        holder.tvShowItem.setText( tvShowList.getName() );
        return v;
    }

    private class TvShowHolder {
        TextView tvShowItem;
    }
}
