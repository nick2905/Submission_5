package com.dicoding.submission_5.item;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.dicoding.submission_5.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class TVShow implements Parcelable {
    private String tv_name, tv_rate, tv_description, tv_date, tv_ori_lang, tv_photo;
    private int id;

    public TVShow() {

    }

    public void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }

    public void setTv_rate(String tv_rate) {
        this.tv_rate = tv_rate;
    }

    public void setTv_description(String tv_description) {
        this.tv_description = tv_description;
    }

    public void setTv_date(String tv_date) {
        this.tv_date = tv_date;
    }

    public void setTv_ori_lang(String tv_ori_lang) {
        this.tv_ori_lang = tv_ori_lang;
    }

    public void setTv_photo(String tv_photo) {
        this.tv_photo = tv_photo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTv_name() {
        return tv_name;
    }

    public String getTv_rate() {
        return tv_rate;
    }

    public String getTv_description() {
        return tv_description;
    }

    public String getTv_date() {
        return tv_date;
    }

    public String getTv_ori_lang() {
        return tv_ori_lang;
    }

    public String getTv_photo() {
        return tv_photo;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.tv_name);
        dest.writeString(this.tv_rate);
        dest.writeString(this.tv_description);
        dest.writeString(this.tv_date);
        dest.writeString(this.tv_ori_lang);
        dest.writeString(this.tv_photo);
    }

    public TVShow(JSONObject object) {
        try {
            int ids = object.getInt("id");
            String name = object.getString("name");
            Log.i("TVShowItem",name);
            String rate = object.getString("vote_average");
            String description = object.getString("overview");
            String date = object.getString("popularity");
            String ori_lang = object.getString("original_language");
            String photo = BuildConfig.API_URL_PHOTO + object.getString("poster_path");

            this.id = ids;
            this.tv_name = name;
            this.tv_rate = rate;
            this.tv_description = description;
            this.tv_date = date;
            this.tv_ori_lang = ori_lang;
            this.tv_photo = photo;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TVShow(Parcel in) {
        this.id = in.readInt();
        this.tv_name = in.readString();
        this.tv_rate = in.readString();
        this.tv_description = in.readString();
        this.tv_date = in.readString();
        this.tv_ori_lang = in.readString();
        this.tv_photo = in.readString();
    }

    public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel source) {
            return new TVShow(source);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };
}
