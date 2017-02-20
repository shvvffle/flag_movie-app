package flag.pt.moviesapp.http.requests;

import android.content.Context;

import flag.pt.moviesapp.http.entities.PopularTvShowsResponse;

/**
 * Created by Marina on 25/01/2017.
 */

public abstract class GetPopularTvShowsAsyncTask extends ExecuteRequestAsyncTask<PopularTvShowsResponse> {

    private static final String PATH = "/tv/popular";


    public GetPopularTvShowsAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected void addQueryParams(StringBuilder sb) {

    }

    @Override
    protected Class<PopularTvShowsResponse> getResponseEntityClass() {
        return PopularTvShowsResponse.class;
    }
}
