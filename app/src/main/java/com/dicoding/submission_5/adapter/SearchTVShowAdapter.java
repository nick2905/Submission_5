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
import com.dicoding.submission_5.activity.TVDetailActivity;
import com.dicoding.submission_5.item.TVShow;

import java.util.ArrayList;
import java.util.List;

public class SearchTVShowAdapter extends RecyclerView.Adapter<SearchTVShowAdapter.ListViewHolder> {
    private ArrayList<TVShow> tvshow;
    private List<TVShow> films = new ArrayList<>();
    private Context context;

    public SearchTVShowAdapter(Context context){
        this.context = context;
    }

    public void setFilms(ArrayList<TVShow> tShow){
        this.tvshow = tShow;
    }

    public void setTVShow(ArrayList<TVShow> data){
        Log.i("setTVShow","Done");
        this.films.clear();
        this.films.addAll(data);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tvshow_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {
        final TVShow tvShow = films.get(position);
        Glide.with(holder.itemView.getContext())
                .load(films.get(position).getTv_photo())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.t_photo);
        holder.t_name.setText(tvShow.getTv_name());
        holder.t_rate.setText(tvShow.getTv_rate());
        holder.t_description.setText(tvShow.getTv_description());
        holder.t_date.setText(tvShow.getTv_date());
        holder.t_ori_lang.setText(tvShow.getTv_ori_lang());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveObjectIntent = new Intent(holder.itemView.getContext(), TVDetailActivity.class);
                TVShow tvshows = new TVShow();
                tvshows.setId(films.get(position).getId());
                tvshows.setTv_name(films.get(position).getTv_name());
                tvshows.setTv_rate(films.get(position).getTv_rate());
                tvshows.setTv_description(films.get(position).getTv_description());
                tvshows.setTv_date(films.get(position).getTv_date());
                tvshows.setTv_ori_lang(films.get(position).getTv_ori_lang());
                moveObjectIntent.putExtra(TVDetailActivity.EXTRA_TVSHOW, tvShow);
                holder.itemView.getContext().startActivity(moveObjectIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i("getItemCount", String.valueOf(films.size()));
        return films.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView t_name, t_rate, t_description, t_date, t_ori_lang;
        ImageView t_photo;

        ListViewHolder(@NonNull View v) {
            super(v);
            t_name = v.findViewById(R.id.tvshow_name);
            t_rate = v.findViewById(R.id.tvshow_rate);
            t_description = v.findViewById(R.id.tvshow_description);
            t_date = v.findViewById(R.id.tvshow_date);
            t_ori_lang = v.findViewById(R.id.tvshow_original_lang);
            t_photo = v.findViewById(R.id.tvshow_photo);
        }
    }
}
