package com.dicoding.submission_5.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.submission_5.activity.TVDetailActivity;
import com.dicoding.submission_5.BuildConfig;
import com.dicoding.submission_5.item.TVShow;
import com.dicoding.submission_5.R;

import java.util.ArrayList;
import java.util.List;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.ListViewHolder>{
    private Activity activity;
    private Context context;
    private ArrayList<TVShow> list;
    private List<TVShow> tvshow = new ArrayList<>();

    public TVShowAdapter(Activity activity){
        this.activity = activity;
    }

    public ArrayList<TVShow> getTvShow(){
        return list;
    }

    public TVShowAdapter(Context context){
        this.context = context;
    }

    public void setTVShow(List<TVShow> data){
        this.tvshow.clear();
        this.tvshow.addAll(data);
        notifyDataSetChanged();
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
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tvshow_item, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        final TVShow tvShow = tvshow.get(position);
        Glide.with(holder.itemView.getContext())
                .load(tvshow.get(position).getTv_photo())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.t_photo);
        holder.t_name.setText(tvShow.getTv_name());
        holder.t_rate.setText(tvShow.getTv_rate());
        holder.t_description.setText(tvShow.getTv_description());
        holder.t_date.setText(tvShow.getTv_date());
        holder.t_ori_lang.setText(tvShow.getTv_ori_lang());

        context = holder.itemView.getContext();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveObjectIntent = new Intent(context, TVDetailActivity.class);

                TVShow tvshows = new TVShow();
                tvshows.setId(tvshow.get(position).getId());
                tvshows.setTv_name(tvshow.get(position).getTv_name());
                tvshows.setTv_rate(tvshow.get(position).getTv_rate());
                tvshows.setTv_description(tvshow.get(position).getTv_description());
                tvshows.setTv_date(tvshow.get(position).getTv_date());
                tvshows.setTv_ori_lang(tvshow.get(position).getTv_ori_lang());
                moveObjectIntent.putExtra(TVDetailActivity.EXTRA_TVSHOW, tvShow);
                context.startActivity(moveObjectIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvshow.size();
    }
}