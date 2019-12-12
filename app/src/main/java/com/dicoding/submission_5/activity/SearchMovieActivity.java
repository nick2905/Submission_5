package com.dicoding.submission_5.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.dicoding.submission_5.R;
import com.dicoding.submission_5.adapter.SearchMovieAdapter;
import com.dicoding.submission_5.item.Movie;
import com.dicoding.submission_5.model.MovieModel;

import java.util.ArrayList;

public class SearchMovieActivity extends AppCompatActivity {
    private MovieModel movieModel;
    private SearchMovieAdapter adapter;
    private ArrayList<Movie> movies = new ArrayList<>();
    private SearchView search;

    private final String STATE_QUERY = "state_query";
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        movieModel = ViewModelProviders.of(this).get(MovieModel.class);
        movieModel.getMovieSearch().observe(this, getFilm);

        RecyclerView rvMovie = findViewById(R.id.rv_search_movie);
        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SearchMovieAdapter(this);
        adapter.setFilm(movies);
        rvMovie.setAdapter(adapter);

        this.setTitle(R.string.search_movie);
        if(savedInstanceState != null){
            query = savedInstanceState.getString(STATE_QUERY);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("onsavedstatemovie",search.getQuery().toString());
        outState.putString(STATE_QUERY, search.getQuery().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_search, menu);
        MenuItem searchMovieMenu = menu.findItem(R.id.action_search_movie);
        search = (SearchView) searchMovieMenu.getActionView();
        search.onActionViewExpanded();
        if(query != null){
            Log.i("setQueryMovie",query);
            search.setQuery(query,false);
        }
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.i("onquerytextsubmitMovie",s);
                movieModel.cariMovie(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private Observer<ArrayList<Movie>> getFilm = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Movie> mov) {
            Log.i("onChanged","ini Movie");
            if (mov != null){
                movies = mov;
                adapter.setMovie(mov);
            }
        }
    };
}
