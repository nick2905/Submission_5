package com.dicoding.submission_5.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.dicoding.submission_5.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie implements Parcelable {

    private String mv_name, mv_rate, mv_description, mv_date, mv_ori_lang, mv_photo;
    private int mv_id;


    protected Movie(Parcel in) {
        this.mv_id = in.readInt();
        this.mv_name = in.readString();
        this.mv_rate = in.readString();
        this.mv_description = in.readString();
        this.mv_date = in.readString();
        this.mv_ori_lang = in.readString();
        this.mv_photo = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie() {

    }

    public void setMv_name(String mv_name) {
        this.mv_name = mv_name;
    }

    public void setMv_rate(String mv_rate) {
        this.mv_rate = mv_rate;
    }

    public void setMv_description(String mv_description) {
        this.mv_description = mv_description;
    }

    public void setMv_date(String mv_date) {
        this.mv_date = mv_date;
    }

    public void setMv_ori_lang(String mv_ori_lang) {
        this.mv_ori_lang = mv_ori_lang;
    }

    public void setMv_photo(String mv_photo) {
        this.mv_photo = mv_photo;
    }

    public void setId(int id) {
        this.mv_id = id;
    }

    public String getMv_name() {
        return mv_name;
    }

    public String getMv_rate() {
        return mv_rate;
    }

    public String getMv_description() {
        return mv_description;
    }

    public String getMv_date() {
        return mv_date;
    }

    public String getMv_ori_lang() {
        return mv_ori_lang;
    }

    public String getMv_photo() {
        return mv_photo;
    }

    public int getId() {
        return mv_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mv_id);
        parcel.writeString(this.mv_name);
        parcel.writeString(this.mv_rate);
        parcel.writeString(this.mv_description);
        parcel.writeString(this.mv_date);
        parcel.writeString(this.mv_ori_lang);
        parcel.writeString(this.mv_photo);
    }

    public Movie(JSONObject object){
        try {
            int id = object.getInt("id");
            String name = object.getString("title");
            String ori_lang = object.getString("original_language");
            String date = object.getString("release_date");
            String rate = object.getString("vote_average");
            String description = object.getString("overview");
            String photo = BuildConfig.API_URL_PHOTO + object.getString("poster_path");

            this.mv_id = id;
            this.mv_name = name;
            this.mv_ori_lang = ori_lang;
            this.mv_date = date;
            this.mv_rate = rate;
            this.mv_description = description;
            this.mv_photo = photo;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
