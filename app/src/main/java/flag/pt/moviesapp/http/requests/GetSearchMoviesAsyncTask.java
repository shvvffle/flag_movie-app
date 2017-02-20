package flag.pt.moviesapp.http.requests;

import android.content.Context;

import flag.pt.moviesapp.http.entities.NowPlayingMoviesResponse;

/**
 * Created by Marina on 01/02/2017.
 */

public abstract class GetSearchMoviesAsyncTask extends ExecuteRequestAsyncTask<NowPlayingMoviesResponse> {

    private static final String PATH = "/search/movie";
    private static final String QUERY = "query";

    private String movieSearched;


    public GetSearchMoviesAsyncTask(Context context, String movieSearched) {
        super(context);
        this.movieSearched = movieSearched;
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected void addQueryParams(StringBuilder sb) {
        addQueryParam(sb, QUERY, movieSearched);
    }

    @Override
    protected Class<NowPlayingMoviesResponse> getResponseEntityClass() {
        return NowPlayingMoviesResponse.class;
    }
}