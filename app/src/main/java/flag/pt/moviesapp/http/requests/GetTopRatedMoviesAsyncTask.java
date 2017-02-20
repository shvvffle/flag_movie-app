package flag.pt.moviesapp.http.requests;

import android.content.Context;

import flag.pt.moviesapp.http.entities.TopRatedMoviesResponse;

/**
 * Created by Marina on 25/01/2017.
 */

public abstract class GetTopRatedMoviesAsyncTask extends ExecuteRequestAsyncTask<TopRatedMoviesResponse> {

    private static final String PATH = "/movie/top_rated";


    public GetTopRatedMoviesAsyncTask(Context context) {
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
    protected Class<TopRatedMoviesResponse> getResponseEntityClass() {
        return TopRatedMoviesResponse.class;
    }
}
