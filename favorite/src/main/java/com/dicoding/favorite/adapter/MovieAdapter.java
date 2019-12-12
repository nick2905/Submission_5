package com.dicoding.favorite.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.favorite.R;
import com.dicoding.favorite.model.Movie;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ListViewHolder>{

    private Context context;
    private Cursor cursor;

    public MovieAdapter(Context context){
        this.context = context;
    }

    public void setMovie(Cursor cursor){
        this.cursor = cursor;
    }

    private Movie getItem(int i){
        if(!cursor.moveToPosition(i)){
            throw new IllegalStateException("Position Not Correct!");
        }
        return new Movie(cursor);
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
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        final Movie movie = getItem(position);
        Glide.with(context)
                .load(movie.getMv_photo())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.m_photo);
        holder.m_name.setText(movie.getMv_name());
        holder.m_rate.setText(movie.getMv_rate());
        holder.m_description.setText(movie.getMv_description());
        holder.m_date.setText(movie.getMv_date());
        holder.m_ori_lang.setText(movie.getMv_ori_lang());
    }

    @Override
    public int getItemCount() {
        if (cursor == null) return 0;
        return cursor.getCount();
    }
}
