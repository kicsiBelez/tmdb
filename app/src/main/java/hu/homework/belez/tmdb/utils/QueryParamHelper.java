package hu.homework.belez.tmdb.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Belez on 2017. 07. 02..
 */

public class QueryParamHelper {

    public static Map<String, String> generateQuerzParamsForSearch(String lang, String keyWord, Integer page, Boolean includeAdult) {
        Map<String, String> params = new HashMap<>();

        params.put(Constatns.SEARCH_API_KEY, Constatns.TMDB_API_KEY);
        params.put(Constatns.SEARCH_LANGUAGE, lang != null ? lang : Constatns.SEARCH_DEFAULT_LANGUAGE);
        params.put(Constatns.SEARCH_QUERY, keyWord);
        params.put(Constatns.SEARCH_PAGE, page != null ? page.toString() : Constatns.SEARCH_DEFAULT_PAGE_NUM);
        params.put(Constatns.SEARCH_INCLUDE_ADULT, includeAdult != null ? includeAdult.toString() : Constatns.SEARCH_DEFAULT_INCLUDE_ADULTS);

        return params;
    }

    public static Map<String, String> generateQuerzParamsForDetail(String lang) {
        Map<String, String> params = new HashMap<>();

        params.put(Constatns.SEARCH_API_KEY, Constatns.TMDB_API_KEY);
        params.put(Constatns.SEARCH_LANGUAGE, lang != null ? lang : Constatns.SEARCH_DEFAULT_LANGUAGE);

        return params;
    }

}
