package movies.flag.pt.moviesapp.http.requests;

import android.content.Context;

import movies.flag.pt.moviesapp.http.entities.MoviesResponse;
import movies.flag.pt.moviesapp.screens.StartScreen;

/**
 * Created by Marina on 01/02/2017.
 */

public abstract class GetSearchMovieAsyncTask extends ExecuteRequestAsyncTask<MoviesResponse> {

    private static final String PATH = "/search/movie";
    private static final String QUERY = "query";
    private static final String QUERY_VALUE = StartScreen.MOVIE_SEARCH;


    public GetSearchMovieAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected void addQueryParams(StringBuilder sb) {
        addQueryParam(sb, QUERY, QUERY_VALUE);
    }

    @Override
    protected Class<MoviesResponse> getResponseEntityClass() {
        return MoviesResponse.class;
    }
}