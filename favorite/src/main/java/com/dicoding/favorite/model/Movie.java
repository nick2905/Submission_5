package com.dicoding.favorite.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import static com.dicoding.favorite.database.DatabaseContract.NoteColumns._ID;
import static com.dicoding.favorite.database.DatabaseContract.NoteColumns.TITLE;
import static com.dicoding.favorite.database.DatabaseContract.NoteColumns.DESCRIPTION;
import static com.dicoding.favorite.database.DatabaseContract.NoteColumns.DATE;
import static com.dicoding.favorite.database.DatabaseContract.NoteColumns.LANGUAGE;
import static com.dicoding.favorite.database.DatabaseContract.NoteColumns.PHOTO;
import static com.dicoding.favorite.database.DatabaseContract.NoteColumns.RATE;
import static com.dicoding.favorite.database.DatabaseContract.getColumnInt;
import static com.dicoding.favorite.database.DatabaseContract.getColumnString;

public class Movie implements Parcelable {

    private String mv_name, mv_rate, mv_description, mv_date, mv_ori_lang, mv_photo;
    private int mv_id;


    private Movie(Parcel in) {
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

    public Movie(Cursor cursor){
        this.mv_id = getColumnInt(cursor,_ID);
        this.mv_name = getColumnString(cursor, TITLE);
        this.mv_ori_lang = getColumnString(cursor, LANGUAGE);
        this.mv_date = getColumnString(cursor, DATE);
        this.mv_rate = getColumnString(cursor, RATE);
        this.mv_description = getColumnString(cursor, DESCRIPTION);
        this.mv_photo = getColumnString(cursor, PHOTO);
    }
}
