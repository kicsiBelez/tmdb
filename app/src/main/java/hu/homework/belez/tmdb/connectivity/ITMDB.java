package hu.homework.belez.tmdb.connectivity;

import hu.homework.belez.tmdb.model.Movie;
import hu.homework.belez.tmdb.model.Search;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Belez on 2017. 07. 02..
 */

public interface ITMDB {

    @GET("search/movie?api_key={apiKey}&language={lang}&query={keyWord}&page={page}&include_adult={includeAdult}")
    Call<Search> search(@Path("apiKey") String apiKey, @Path("lang") String lang, @Path("keyWord") String keyWord, @Path("page") Integer page, @Path("includeAdult") Boolean includeAdult);

    @GET("movie/{movieId}?api_key={apiKey}&language={lang}")
    Call<Movie> detail(@Path("movieId") Integer movieId, @Path("apiKey") String apiKey, @Path("lang") String lang);

}
