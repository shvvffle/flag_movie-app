package topRatedMovies.flag.pt.moviesapp.http.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Marina on 25/01/2017.
 */

public class ResultPopularTvShow implements Parcelable {
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;
    @SerializedName("origin_country")
    @Expose
    private List<String> originCountry = null;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("original_name")
    @Expose
    private String originalName;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterPath);
        dest.writeValue(this.popularity);
        dest.writeValue(this.id);
        dest.writeString(this.backdropPath);
        dest.writeValue(this.voteAverage);
        dest.writeString(this.overview);
        dest.writeString(this.firstAirDate);
        dest.writeStringList(this.originCountry);
        dest.writeList(this.genreIds);
        dest.writeString(this.originalLanguage);
        dest.writeValue(this.voteCount);
        dest.writeString(this.name);
        dest.writeString(this.originalName);
    }

    public ResultPopularTvShow() {
    }

    protected ResultPopularTvShow(Parcel in) {
        this.posterPath = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.backdropPath = in.readString();
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.overview = in.readString();
        this.firstAirDate = in.readString();
        this.originCountry = in.createStringArrayList();
        this.genreIds = new ArrayList<Integer>();
        in.readList(this.genreIds, Integer.class.getClassLoader());
        this.originalLanguage = in.readString();
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.originalName = in.readString();
    }

    public static final Parcelable.Creator<ResultPopularTvShow> CREATOR = new Parcelable.Creator<ResultPopularTvShow>() {
        @Override
        public ResultPopularTvShow createFromParcel(Parcel source) {
            return new ResultPopularTvShow(source);
        }

        @Override
        public ResultPopularTvShow[] newArray(int size) {
            return new ResultPopularTvShow[size];
        }
    };
}
