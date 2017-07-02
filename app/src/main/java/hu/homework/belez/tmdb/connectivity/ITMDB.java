package hu.homework.belez.tmdb.connectivity;

import hu.homework.belez.tmdb.model.Movie;
import hu.homework.belez.tmdb.model.Search;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Belez on 2017. 07. 02..
 */

public interface ITMDB {

    @GET("search/movie?api_key={apiKey}&language={lang}&query={keyWord}&page={page}&include_adult={includeAdult}")
    Call<Search> search(@Query("apiKey") String apiKey, @Query("lang") String lang, @Query("keyWord") String keyWord, @Query("page") Integer page, @Query("includeAdult") Boolean includeAdult);

    @GET("movie/{movieId}?api_key={apiKey}&language={lang}")
    Call<Movie> detail(@Query("movieId") Integer movieId, @Query("apiKey") String apiKey, @Query("lang") String lang);

}
