package hu.homework.belez.tmdb.connectivity;

import hu.homework.belez.tmdb.model.Movie;
import hu.homework.belez.tmdb.model.Search;
import hu.homework.belez.tmdb.utils.Constatns;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Belez on 2017. 07. 02..
 */

public class TMDBService {

    private static TMDBService instance = null;

    private Retrofit retrofit = null;

    private ITMDB service = null;

    public static TMDBService getInstance() {
        if(instance == null) {
            instance = new TMDBService();
        }
        return instance;
    }

    protected TMDBService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constatns.TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ITMDB.class);
    }

    public Call<Search> searchMovie(String apiKey, String lang, String keyWord, Integer page, Boolean includeAdult) {
        return service.search(apiKey, lang, keyWord, page, includeAdult);
    }

    public Call<Movie> getMovieDetail(Integer movieId, String apiKey, String lang) {
        return service.detail(movieId, apiKey, lang);
    }
}
