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
import com.dicoding.submission_5.adapter.SearchTVShowAdapter;
import com.dicoding.submission_5.item.TVShow;
import com.dicoding.submission_5.model.TVShowModel;

import java.util.ArrayList;
import java.util.List;

public class SearchTVShowActivity extends AppCompatActivity {
    private TVShowModel tvModel;
    private SearchTVShowAdapter adapter;
    private ArrayList<TVShow> tvshow = new ArrayList<>();
    private SearchView search;

    private final String STATE_TV = "state_tv";
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tvshow);

        tvModel = ViewModelProviders.of(this).get(TVShowModel.class);
        tvModel.getTVShowSearch().observe(this, getTV);

        RecyclerView rvTVShow = findViewById(R.id.rv_search_tvshow);
        rvTVShow.setHasFixedSize(true);
        rvTVShow.setLayoutManager(new LinearLayoutManager( this));

        adapter = new SearchTVShowAdapter(this);
        adapter.setFilms(tvshow);
        rvTVShow.setAdapter(adapter);

        this.setTitle(R.string.search_tvshow);
        if(savedInstanceState != null){
            query = savedInstanceState.getString(STATE_TV);
            Log.i("savedInstance TV",query);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("onsavedstatetv", search.getQuery().toString());
        outState.putString(STATE_TV, search.getQuery().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tvshow_search, menu);
        MenuItem searchTVShowMenu = menu.findItem(R.id.action_search_tvshow);
        Log.i("onCreateOptionMenu","Sudah");
        search = (SearchView) searchTVShowMenu.getActionView();
        search.onActionViewExpanded();
        if(query != null){
            Log.i("setQueryTV",query);
            search.setQuery(query,false);
        }
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.i("onQueryTextSubmitTV",s);
                tvModel.cariTVShow(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private Observer<ArrayList<TVShow>> getTV = new Observer<ArrayList<TVShow>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TVShow> mov) {
            Log.i("onChanged","Ini adalah onChanged TVShow");
            if (mov != null){
                tvshow = mov;
                adapter.setTVShow(mov);
            }
        }
    };
}
