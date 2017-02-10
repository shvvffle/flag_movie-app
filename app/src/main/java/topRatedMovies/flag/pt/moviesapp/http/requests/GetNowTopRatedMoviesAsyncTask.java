package topRatedMovies.flag.pt.moviesapp.http.requests;

import android.content.Context;

import topRatedMovies.flag.pt.moviesapp.http.entities.TopRatedMovieResponse;

/**
 * Created by Marina on 25/01/2017.
 */

public abstract class GetNowTopRatedMoviesAsyncTask extends ExecuteRequestAsyncTask<TopRatedMovieResponse> {

    private static final String PATH = "/movie/top_rated";


    public GetNowTopRatedMoviesAsyncTask(Context context) {
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
    protected Class<TopRatedMovieResponse> getResponseEntityClass() {
        return TopRatedMovieResponse.class;
    }
}
