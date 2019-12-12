package com.dicoding.submission_5.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
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

import com.dicoding.submission_5.adapter.TVShowAdapter;
import com.dicoding.submission_5.item.TVShow;
import com.dicoding.submission_5.model.TVShowModel;
import com.dicoding.submission_5.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFragment extends Fragment {
    private ProgressBar progressBar;
    RecyclerView rvTVShow;
    TVShowAdapter adapter;
    TVShowModel tvShowModel;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    public TVShowFragment() {
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTVShow = view.findViewById(R.id.rv_fragment_tv_show);
        progressBar = view.findViewById(R.id.tvshow_progressBar);

        tvShowModel = ViewModelProviders.of(this).get(TVShowModel.class);
        tvShowModel.getTVShow().observe(this, getTVShow);

        adapter = new TVShowAdapter(getActivity());
        adapter.notifyDataSetChanged();

        rvTVShow.setHasFixedSize(true);
        rvTVShow.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvTVShow.setAdapter(adapter);

        tvShowModel.listTVShow();
        showLoading(false);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }

    private Observer<ArrayList<TVShow>> getTVShow = new Observer<ArrayList<TVShow>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TVShow> tvShow) {
            if (tvShow != null){
                adapter.setTVShow(tvShow);
                showLoading(true);
            }

        }
    };
}
