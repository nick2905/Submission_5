package com.dicoding.submission_5.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dicoding.submission_5.adapter.TVShowAdapter;
import com.dicoding.submission_5.helper.FavHelper;
import com.dicoding.submission_5.item.TVShow;
import com.dicoding.submission_5.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFavoriteFragment extends Fragment {
    private static final String EXTRA_TVSHOW = "tvshow";
    private ArrayList<TVShow> listTVShow = new ArrayList<>();
    private RecyclerView rvTVshow;
    private ProgressBar progressBar;
    private TVShowAdapter listTVShowAdapter;
    private FavHelper favoriteHelper;
    private Bundle State;


    public TVShowFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow_favorite, container, false);
    }

    @Override
    public void onStart() {
        rvTVshow.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTVshow.setHasFixedSize(true);
        progressBar.setVisibility(View.VISIBLE);
        favoriteHelper = FavHelper.getInstance(getContext());
        favoriteHelper.open();

        listTVShowAdapter = new TVShowAdapter(getContext());
        rvTVshow.setAdapter(listTVShowAdapter);

        if (State == null) {
            listTVShow.clear();
            listTVShow.addAll(favoriteHelper.getAllFavTv());
            if (listTVShow != null) {
                listTVShowAdapter.setTVShow(listTVShow);
            } else {
                Toast.makeText(getContext(), getResources().getString(R.string.empty_data), Toast.LENGTH_SHORT).show();
            }
        } else {
            ArrayList<TVShow> list = State.getParcelableArrayList(EXTRA_TVSHOW);
            if (list != null) {
                listTVShowAdapter.setTVShow(list);
            }
        }
        super.onStart();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTVshow = view.findViewById(R.id.rv_fragment_tv_show_favorite);
        progressBar = view.findViewById(R.id.tvshow_progressBar);
        if (savedInstanceState != null) {
            State = savedInstanceState;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_TVSHOW, listTVShowAdapter.getTvShow());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        favoriteHelper.close();
    }
}
