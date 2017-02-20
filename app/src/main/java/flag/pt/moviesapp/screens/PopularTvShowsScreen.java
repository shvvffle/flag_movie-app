package flag.pt.moviesapp.screens;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import java.util.List;

import flag.pt.moviesapp.R;
import flag.pt.moviesapp.adapters.ListPopularTvShowsViewAdapter;
import flag.pt.moviesapp.http.entities.PopularTvShowsResponse;
import flag.pt.moviesapp.http.entities.TvShow;
import flag.pt.moviesapp.http.requests.GetPopularTvShowsAsyncTask;
import flag.pt.moviesapp.utils.DLog;

/**
 * Created by Marina on 25/01/2017.
 */

public class PopularTvShowsScreen extends Screen {

    private static String REFRESH_TV_LOG;
    private ListView popularTvShowsList;
    private ListPopularTvShowsViewAdapter popularTvShowsViewAdapter;
    private SwipeRefreshLayout swipeRefreshTvShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executeRequestPopularTvShows();

        setContentView(R.layout.popular_tv_shows_screen);

        findViews();
        addListeners();
    }

    private void findViews() {
        popularTvShowsList = (ListView) findViewById(R.id.popular_tv_shows_screen_list_view);
        swipeRefreshTvShow = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshTV);
    }

    private void addListeners() {

        swipeRefreshTvShow.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        DLog.d(REFRESH_TV_LOG, "onRefresh called from SwipeRefreshLayout");
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
        new GetPopularTvShowsAsyncTask(this) {

            @Override
            protected void onResponseSuccess(PopularTvShowsResponse popularTvShowResponse) {
                DLog.d(tag, "onResponseSuccess " + popularTvShowResponse);
                // Adapter
                List<TvShow> tvShows = popularTvShowResponse.getResults();
                popularTvShowsViewAdapter = new ListPopularTvShowsViewAdapter(PopularTvShowsScreen.this, tvShows);
                popularTvShowsList.setAdapter(popularTvShowsViewAdapter);

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
