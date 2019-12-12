package com.dicoding.submission_5.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dicoding.submission_5.adapter.MoviesAdapter;
import com.dicoding.submission_5.item.Movie;
import com.dicoding.submission_5.model.MovieModel;
import com.dicoding.submission_5.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    public static final String MOVIE = "Movie";
    private ProgressBar progressBar;
    MoviesAdapter adapter;
    RecyclerView rvMovie;
    MovieModel movieModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public MovieFragment() {
        // Required empty public constructor
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_fragment_movie);
        progressBar = view.findViewById(R.id.movie_progressBar);

        movieModel = ViewModelProviders.of(this).get(MovieModel.class);
        movieModel.getMovie().observe(this, getMovies);

        adapter = new MoviesAdapter(getActivity());
        adapter.notifyDataSetChanged();

        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvMovie.setAdapter(adapter);

        movieModel.listMovie();
        showLoading(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }

    private Observer<ArrayList<Movie>> getMovies = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Movie> movies) {
            if (movies != null) {
                adapter.setMovie(movies);
                showLoading(true);
            }
        }
    };

}
