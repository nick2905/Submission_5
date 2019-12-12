package com.dicoding.submission_5.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dicoding.submission_5.BuildConfig;
import com.dicoding.submission_5.helper.FavHelper;
import com.dicoding.submission_5.item.Movie;
import com.dicoding.submission_5.R;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "Movie";
    private Movie movie;
    private FavHelper favHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        String mvName = movie.getMv_name();
        String mvRate = movie.getMv_rate();
        String mvDescription = movie.getMv_description();
        String mvDate = movie.getMv_date();
        String mvOriLang = movie.getMv_ori_lang();

        TextView name = findViewById(R.id.txt_name);
        TextView rate = findViewById(R.id.txt_rate);
        TextView description = findViewById(R.id.txt_description);
        TextView date = findViewById(R.id.txt_date);
        TextView ori_lang = findViewById(R.id.txt_original_lang);
        ImageView photo = findViewById(R.id.img_photo);

        name.setText(mvName);
        rate.setText(mvRate);
        description.setText(mvDescription);
        date.setText(mvDate);
        ori_lang.setText(mvOriLang);
        String gambarUrl = BuildConfig.API_URL_PHOTO + movie.getMv_photo();
        Glide.with(MovieDetailActivity.this)
                .load(gambarUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(photo);

        favHelper = FavHelper.getInstance(getApplicationContext());
        favHelper.open();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (favHelper.isExist(movie)){
            getMenuInflater().inflate(R.menu.menu_add_unselect_fav, menu);
        }else{
            getMenuInflater().inflate(R.menu.menu_add_select_fav, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_favorite){
            if(!favHelper.isExist(movie)){
                long result = favHelper.insertFav(movie);
                if(result > 0){
                    item.setIcon(R.drawable.ic_fav_sellect);
                    Toast.makeText(MovieDetailActivity.this, getString(R.string.success_add),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MovieDetailActivity.this, getString(R.string.failed_add), Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(MovieDetailActivity.this,getString(R.string.done_add),Toast.LENGTH_SHORT).show();
            }
        } else{
            if(item.getItemId() == R.id.action_unsellect_favorite){
                int result = favHelper.deleteFav(movie.getId());
                if(result > 0){
                    item.setIcon(R.drawable.ic_fav_unsellect);
                    Toast.makeText(MovieDetailActivity.this,getString(R.string.delete_success),Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(MovieDetailActivity.this,getString(R.string.remove_failed), Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
