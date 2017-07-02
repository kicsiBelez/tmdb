package hu.homework.belez.tmdb.views.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
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
@OptionsMenu(R.menu.search_menu)
public class SearchActivity extends AppCompatActivity {

    @ViewById(R.id.search_list)
    ListView movieList;

    @ViewById(R.id.search_progress)
    ProgressBar progressBar;



    SearchListAdapter adapter;

    @AfterViews
    void afterViews() {
        progressBar.setVisibility(View.GONE);

        if (adapter == null) {
            adapter = new SearchListAdapter(SearchActivity.this, null);
            movieList.setAdapter(adapter);
            performSearch();
        }
    }

    @Background
    void performSearch() {
        startProgress();
        TMDBService.getInstance().searchMovie("en-US", "transformers", 1, false).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                stopProgress();
                if (response.isSuccessful()) {
                    adapter.addMovies(response.body().getResults());
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
