package hu.homework.belez.tmdb.views.activity;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InjectMenu;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;

import hu.homework.belez.tmdb.R;
import hu.homework.belez.tmdb.connectivity.TMDBService;
import hu.homework.belez.tmdb.model.Search;
import hu.homework.belez.tmdb.utils.ErrorHandler;
import hu.homework.belez.tmdb.utils.adapter.SearchListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_search)
public class SearchActivity extends AppCompatActivity {

    @ViewById(R.id.search_list)
    ListView movieList;

    @ViewById(R.id.search_progress)
    ProgressBar progressBar;

    SearchListAdapter adapter;

    @InstanceState
    String query;

    @InjectMenu
    void setMenu(Menu searchMenu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, searchMenu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) searchMenu.findItem(R.id.menu_item_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            performSearch(query, 1);
        }
    }

    @AfterViews
    void afterViews() {
        progressBar.setVisibility(View.GONE);

        if (adapter == null) {
            adapter = new SearchListAdapter(SearchActivity.this, null);
            movieList.setAdapter(adapter);
            movieList.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if((firstVisibleItem + visibleItemCount == totalItemCount)
                            && adapter.getPage() != null && adapter.getTotalPages() != null && (adapter.getPage() < adapter.getTotalPages())){
                        performSearch("transformers", adapter.getPage() + 1);
                    }
                }
            });

            if (query != null) {
                performSearch(query, 1);
            }
        }
    }

    void performSearch(String keyWord, Integer page) {
        startProgress();
        TMDBService.getInstance().searchMovie("en-US", keyWord, page, false).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                stopProgress();
                if (response.isSuccessful()) {
                    adapter.addMovies(response.body().getResults());
                    adapter.setTotalPages(response.body().getTotal_pages());
                    adapter.setPage(response.body().getPage());
                    adapter.notifyDataSetInvalidated();
                } else {
                    String errorMessage = null;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //Integer errorCode = response.body().getStatus_code();
                    handleError(errorMessage, null);
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                throw new RuntimeException(t.getMessage());
            }
        });
    }

    @UiThread
    void handleError(String message, Integer code) {
        AlertDialog dialog = ErrorHandler.handleException(message, code, SearchActivity.this, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }, null);
        dialog.show();
    }

    @UiThread
    void startProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @UiThread
    void stopProgress() {
        progressBar.setVisibility(View.GONE);
    }

}
