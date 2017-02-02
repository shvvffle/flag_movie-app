package movies.flag.pt.moviesapp.screens;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.adapters.ListPopularTvShowAdapter;
import movies.flag.pt.moviesapp.http.entities.PopularTvShowResponse;
import movies.flag.pt.moviesapp.http.entities.ResultPopularTvShow;
import movies.flag.pt.moviesapp.http.requests.GetNowPopularTvShowAsyncTask;
import movies.flag.pt.moviesapp.utils.DLog;

/**
 * Created by Marina on 25/01/2017.
 */

public class PopularTvShowScreen extends Screen {

    private ImageView searchBarAction;
    private EditText searchBarInput;
    private ListView tvShowList;
    private ListPopularTvShowAdapter tvShowViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        executeRequestPopularTvShows();

        setContentView( R.layout.popular_tv_show_screen );

        findViews();
    }

    private void findViews() {
        searchBarAction = (ImageView) findViewById( R.id.all_screens_search_bar_button );
        searchBarInput = (EditText) findViewById( R.id.all_screens_search_bar_input );
        tvShowList = (ListView) findViewById( R.id.popular_tv_show_screen_list_view );
    }

    private void executeRequestPopularTvShows() {
        // Example to request get now playing movies
        new GetNowPopularTvShowAsyncTask( this ) {

            @Override
            protected void onResponseSuccess(PopularTvShowResponse popularTvShowResponse) {
                DLog.d( tag, "onResponseSuccess " + popularTvShowResponse );
                // Here i can create the adapter :)
                List<ResultPopularTvShow> tvShows = popularTvShowResponse.getResults();
                tvShowViewAdapter = new ListPopularTvShowAdapter( PopularTvShowScreen.this, tvShows );
                tvShowList.setAdapter( tvShowViewAdapter );

            }

            @Override
            protected void onNetworkError() {
                DLog.d( tag, "onNetworkError " );
                // Here i now that some error occur when processing the request,
                // possible my internet connection if turned off
            }
        }.execute();
    }

}
