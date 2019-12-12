package com.dicoding.submission_5.helper;

import android.database.Cursor;

import com.dicoding.submission_5.database.DatabaseContract;
import com.dicoding.submission_5.item.Movie;

import java.util.ArrayList;

public class MappHelper {
    public static ArrayList<Movie> mapCursorToArrayList(Cursor cursor){
        ArrayList<Movie> list = new ArrayList<>();
        while(cursor.moveToNext()){
            int ID = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE));
            String ori_lang = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.LANGUAGE));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DATE));
            String rate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.RATE));
            String desc = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION));
            String photo = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.PHOTO));

            Movie movie = new Movie();
            movie.setId(ID);
            movie.setMv_name(title);
            movie.setMv_ori_lang(ori_lang);
            movie.setMv_date(date);
            movie.setMv_rate(rate);
            movie.setMv_description(desc);
            movie.setMv_photo(photo);
            list.add(movie);
        }
        return list;
    }
}
