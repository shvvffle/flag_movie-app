package flag.pt.moviesapp.http.requests;

import android.content.Context;

import flag.pt.moviesapp.http.entities.UpcomingMoviesResponse;

/**
 * Created by Marina on 25/01/2017.
 */

public abstract class GetUpcomingMoviesAsyncTask extends ExecuteRequestAsyncTask<UpcomingMoviesResponse> {

    private static final String PATH = "/movie/upcoming";


    public GetUpcomingMoviesAsyncTask(Context context) {
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
    protected Class<UpcomingMoviesResponse> getResponseEntityClass() {
        return UpcomingMoviesResponse.class;
    }
}
