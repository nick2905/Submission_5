package com.dicoding.favorite;


import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dicoding.favorite.adapter.MovieAdapter;

import static com.dicoding.favorite.database.DatabaseContract.NoteColumns.CONTENT_URI_MOVIE;


public class MainActivity extends AppCompatActivity {
    private MovieAdapter movieAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onResume() {
        super.onResume();
        loadMovies();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.refresh_swipe);
        recyclerView = findViewById(R.id.rv_fragment_movie);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMovies();
            }
        });

        movieAdapter = new MovieAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);
    }

    private class LoadFavAsync extends AsyncTask<Void, Void, Cursor>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContentResolver().query(CONTENT_URI_MOVIE, null, null, null,null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            movieAdapter.setMovie(cursor);
            movieAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(movieAdapter);
        }
    }

    private void loadMovies(){
        new LoadFavAsync().execute();
        swipeRefreshLayout.setRefreshing(false);
    }
}
