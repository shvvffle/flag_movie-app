package flag.pt.moviesapp.http.requests;

import android.content.Context;

import flag.pt.moviesapp.http.entities.PopularTvShowResponse;

/**
 * Created by Marina on 25/01/2017.
 */

public abstract class GetNowPopularTvShowAsyncTask extends ExecuteRequestAsyncTask<PopularTvShowResponse> {

    private static final String PATH = "/tv/popular";


    public GetNowPopularTvShowAsyncTask(Context context) {
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
    protected Class<PopularTvShowResponse> getResponseEntityClass() {
        return PopularTvShowResponse.class;
    }
}
