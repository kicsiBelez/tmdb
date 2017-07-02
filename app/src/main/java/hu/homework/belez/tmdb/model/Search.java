package hu.homework.belez.tmdb.model;

import java.util.ArrayList;

/**
 * Created by Belez on 2017. 07. 02..
 */

public class Search {

    Integer page;

    Integer total_results;

    Integer total_pages;

    ArrayList<Movie> results;

    public Search() {
        super();
        this.results = new ArrayList<Movie>();
    }

    public Search(Integer page, Integer total_results, Integer total_pages, ArrayList<Movie> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }
}
