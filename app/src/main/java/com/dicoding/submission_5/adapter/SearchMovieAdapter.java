package com.dicoding.submission_5.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.submission_5.BuildConfig;
import com.dicoding.submission_5.R;
import com.dicoding.submission_5.activity.MovieDetailActivity;
import com.dicoding.submission_5.item.Movie;

import java.util.ArrayList;
import java.util.List;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.ListViewHolder> {
    private ArrayList<Movie> movies;
    private List<Movie> films = new ArrayList<>();
    private Context context;

    public SearchMovieAdapter(Context context) {
        this.context = context;
    }

    public void setFilm(ArrayList<Movie> movies){
        this.movies = movies;
    }

    public void setMovie(ArrayList<Movie> data){
        this.films.clear();
        this.films.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {
        final Movie movie = films.get(position);
        Glide.with(holder.itemView.getContext())
                .load(films.get(position).getMv_photo())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.m_photo);
        holder.m_name.setText(movie.getMv_name());
        holder.m_rate.setText(movie.getMv_rate());
        holder.m_description.setText(movie.getMv_description());
        holder.m_date.setText(movie.getMv_date());
        holder.m_ori_lang.setText(movie.getMv_ori_lang());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveObjectIntent = new Intent(holder.itemView.getContext(), MovieDetailActivity.class);
                Movie movieset = new Movie();
                movieset.setId(films.get(position).getId());
                Log.i("idfav", String.valueOf(films.get(position).getId()));
                movieset.setMv_name(films.get(position).getMv_name());
                movieset.setMv_rate(films.get(position).getMv_rate());
                movieset.setMv_description(films.get(position).getMv_description());
                movieset.setMv_date(films.get(position).getMv_date());
                movieset.setMv_ori_lang(films.get(position).getMv_ori_lang());
                moveObjectIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
                holder.itemView.getContext().startActivity(moveObjectIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i("getItemCount", String.valueOf(films.size()));
        return films.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{
        TextView m_name, m_rate, m_description, m_date, m_ori_lang;
        ImageView m_photo;

        ListViewHolder(@NonNull View v){
            super(v);
            m_name = v.findViewById(R.id.txt_name);
            m_rate = v.findViewById(R.id.txt_rate);
            m_description = v.findViewById(R.id.txt_description);
            m_date = v.findViewById(R.id.txt_date);
            m_ori_lang = v.findViewById(R.id.txt_original_lang);
            m_photo = v.findViewById(R.id.img_photo);
        }
    }
}
