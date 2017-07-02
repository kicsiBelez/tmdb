package hu.homework.belez.tmdb.views.activity;

import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import hu.homework.belez.tmdb.R;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @Click(R.id.mainView)
    void onMainActivityTap() {
        SearchActivity_.intent(MainActivity.this).start();
    }

}
