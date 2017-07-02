package hu.homework.belez.tmdb.connectivity;

import java.util.Map;

import hu.homework.belez.tmdb.model.Movie;
import hu.homework.belez.tmdb.model.Search;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Belez on 2017. 07. 02..
 */

public interface ITMDB {

    @GET("search/movie")
    Call<Search> search(@QueryMap Map<String, String> options); //@Query("apiKey") String apiKey, ("lang") String lang, @Query("keyWord") String keyWord, @Query("page") Integer page, @Query("includeAdult") Boolean includeAdult);

    @GET("movie/{movieId}")
    Call<Movie> detail(@Path("movieId") Integer movieId, @QueryMap Map<String, String> options); //@Query("apiKey") String apiKey, @Query("lang") String lang);

}
