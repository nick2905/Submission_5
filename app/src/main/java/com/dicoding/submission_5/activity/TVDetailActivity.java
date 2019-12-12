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
import com.dicoding.submission_5.item.TVShow;
import com.dicoding.submission_5.R;

public class TVDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TVSHOW = "extra_tvshow";
    private TextView name,rate,description,date,ori_lang;
    private ImageView photo;
    private FavHelper favHelper;
    private TVShow tvshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvdetail);

        tvshow = getIntent().getParcelableExtra(EXTRA_TVSHOW);

        String tvName = tvshow.getTv_name();
        String tvRate = tvshow.getTv_rate();
        String tvDescription = tvshow.getTv_description();
        String tvDate = tvshow.getTv_date();
        String tvOriLang = tvshow.getTv_ori_lang();

        name = findViewById(R.id.tvshow_name);
        rate = findViewById(R.id.tvshow_rate);
        description = findViewById(R.id.tvshow_description);
        date = findViewById(R.id.tvshow_date);
        ori_lang = findViewById(R.id.tvshow_original_lang);
        photo = findViewById(R.id.tvshow_photo);

        name.setText(tvName);
        rate.setText(tvRate);
        description.setText(tvDescription);
        date.setText(tvDate);
        ori_lang.setText(tvOriLang);
        String gambarUrl = BuildConfig.API_URL_PHOTO + tvshow.getTv_photo();
        Glide.with(TVDetailActivity.this)
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
        if (favHelper.isExistTv(tvshow)){
            getMenuInflater().inflate(R.menu.menu_add_unselect_fav, menu);
        }else{
            getMenuInflater().inflate(R.menu.menu_add_select_fav, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_favorite){
            if(!favHelper.isExistTv(tvshow)){
                long result = favHelper.insertFavTv(tvshow);
                if(result > 0){
                    item.setIcon(R.drawable.ic_fav_sellect);
                    Toast.makeText(TVDetailActivity.this, getString(R.string.success_add),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(TVDetailActivity.this, getString(R.string.failed_add), Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(TVDetailActivity.this,getString(R.string.done_add),Toast.LENGTH_SHORT).show();
            }
        } else{
            if(item.getItemId() == R.id.action_unsellect_favorite){
                int result = favHelper.deleteFavTv(tvshow.getId());
                if(result > 0){
                    item.setIcon(R.drawable.ic_fav_unsellect);
                    Toast.makeText(TVDetailActivity.this,getString(R.string.delete_success),Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(TVDetailActivity.this,getString(R.string.remove_failed), Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
