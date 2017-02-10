package flag.pt.moviesapp.screens;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import flag.pt.moviesapp.R;
import flag.pt.moviesapp.adapters.ListPopularTvShowAdapter;
import flag.pt.moviesapp.http.entities.PopularTvShowResponse;
import flag.pt.moviesapp.http.entities.ResultPopularTvShow;
import flag.pt.moviesapp.http.requests.GetNowPopularTvShowAsyncTask;
import flag.pt.moviesapp.utils.DLog;

/**
 * Created by Marina on 25/01/2017.
 */

public class PopularTvShowScreen extends Screen {

    private static String REFRESH_TV_LOG;
    private ListView tvShowList;
    private ListPopularTvShowAdapter tvShowViewAdapter;
    private SwipeRefreshLayout swipeRefreshTvShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executeRequestPopularTvShows();

        setContentView(R.layout.popular_tv_show_screen);

        findViews();
        addListeners();
    }

    private void findViews() {
        tvShowList = (ListView) findViewById(R.id.popular_tv_show_screen_list_view);
        swipeRefreshTvShow = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshTV);
    }

    private void addListeners() {

        swipeRefreshTvShow.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(REFRESH_TV_LOG, "onRefresh called from SwipeRefreshLayout");
                        swipeRefreshTvShow.setRefreshing(true);
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        executeRequestPopularTvShows();
                        swipeRefreshTvShow.setRefreshing(false);
                    }
                }
        );


    }

    private void executeRequestPopularTvShows() {
        // Request get popular tv shows
        new GetNowPopularTvShowAsyncTask(this) {

            @Override
            protected void onResponseSuccess(PopularTvShowResponse popularTvShowResponse) {
                DLog.d(tag, "onResponseSuccess " + popularTvShowResponse);
                // Adapter
                List<ResultPopularTvShow> tvShows = popularTvShowResponse.getResults();
                tvShowViewAdapter = new ListPopularTvShowAdapter(PopularTvShowScreen.this, tvShows);
                tvShowList.setAdapter(tvShowViewAdapter);

            }

            @Override
            protected void onNetworkError() {
                DLog.d(tag, "onNetworkError ");
                // Here i now that some error occur when processing the request,
                // possible my internet connection if turned off
            }
        }.execute();
    }

}
