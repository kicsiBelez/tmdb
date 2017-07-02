package hu.homework.belez.tmdb.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hu.homework.belez.tmdb.R;
import hu.homework.belez.tmdb.model.Movie;

/**
 * Created by Belez on 2017. 07. 02..
 */

public class SearchListAdapter extends BaseAdapter {

    Context context;

    ArrayList<Movie> movies;

    public SearchListAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        if (movies == null) {
            this.movies = new ArrayList<Movie>();
        } else {
            this.movies = movies;
        }
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void addMovies(ArrayList<Movie> movies) {
        this.movies.addAll(movies);
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movie getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movies.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.view_movie_item, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.movie_item_title)).setText(getItem(position).getTitle());
        return convertView;
    }
}
