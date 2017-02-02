package movies.flag.pt.moviesapp.http.requests;

import android.content.Context;

import movies.flag.pt.moviesapp.http.entities.Movie;

/**
 * Created by Marina on 01/02/2017.
 */

public abstract class GetSearchMovieAsyncTask extends ExecuteRequestAsyncTask<Movie> {

    private static final String PATH = "/search/movie";


    public GetSearchMovieAsyncTask(Context context) {
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
    protected Class<Movie> getResponseEntityClass() {
        return Movie.class;
    }
}