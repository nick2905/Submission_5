package com.dicoding.submission_5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;


import com.dicoding.submission_5.activity.NotificationActivity;
import com.dicoding.submission_5.activity.SearchMovieActivity;
import com.dicoding.submission_5.activity.SearchTVShowActivity;
import com.dicoding.submission_5.fragment.FavoriteFragment;
import com.dicoding.submission_5.fragment.MovieFragment;
import com.dicoding.submission_5.fragment.TVShowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private final String EXTRA_TITLE = "extra_title";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView mView = findViewById(R.id.navigation);
        mView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            setActionBarTitle(title);
            mView.setSelectedItemId(R.id.navigation_movie);
        } else {
            title = savedInstanceState.getString(EXTRA_TITLE);
            setActionBarTitle(title);
        }
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }

    public String title = "FilmKu";
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment mFrag;
            switch (menuItem.getItemId()) {
                case R.id.navigation_movie:
                    mFrag = new MovieFragment();
                    title = getString(R.string.movie);
                    setActionBarTitle(title);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_layout, mFrag, mFrag.getClass().getSimpleName())
                            .commit();
                    return true;

                case R.id.navigation_tvshow:
                    mFrag = new TVShowFragment();
                    title = getString(R.string.tv_show);
                    setActionBarTitle(title);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_layout, mFrag, mFrag.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.navigation_favorite:
                    mFrag = new FavoriteFragment();
                    title = getString(R.string.favorite);
                    setActionBarTitle(title);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_layout, mFrag, mFrag.getClass().getSimpleName())
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_change_settings:
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
                break;
            case R.id.action_search_movie:
                Intent searchMvIntent = new Intent(this, SearchMovieActivity.class);
                startActivity(searchMvIntent);
                break;
            case R.id.action_search_tvshow:
                Intent searchTvIntent = new Intent(this, SearchTVShowActivity.class);
                startActivity(searchTvIntent);
                break;
            case R.id.action_reminder:
                Intent notifIntent = new Intent(this, NotificationActivity.class);
                startActivity(notifIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA_TITLE, title);
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
