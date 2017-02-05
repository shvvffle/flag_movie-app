package movies.flag.pt.moviesapp.http.requests;

import android.content.Context;

import movies.flag.pt.moviesapp.http.entities.MoviesResponse;

/**
 * Created by Marina on 01/02/2017.
 */

public abstract class GetSearchMovieAsyncTask extends ExecuteRequestAsyncTask<MoviesResponse> {

    private static final String PATH = "/search/movie";
    private static final String QUERY = "query";
    public static String movieSearched;


    public GetSearchMovieAsyncTask(Context context, String movieSearched) {
        super(context, movieSearched);
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
    protected Class<MoviesResponse> getResponseEntityClass() {
        return MoviesResponse.class;
    }
}