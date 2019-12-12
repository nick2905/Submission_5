package com.dicoding.submission_5.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dicoding.submission_5.item.Movie;
import com.dicoding.submission_5.item.TVShow;

import java.util.ArrayList;

import static android.os.Build.ID;
import static android.provider.BaseColumns._ID;
import static com.dicoding.submission_5.database.DatabaseContract.NoteColumns.DESCRIPTION;
import static com.dicoding.submission_5.database.DatabaseContract.NoteColumns.LANGUAGE;
import static com.dicoding.submission_5.database.DatabaseContract.NoteColumns.PHOTO;
import static com.dicoding.submission_5.database.DatabaseContract.NoteColumns.RATE;
import static com.dicoding.submission_5.database.DatabaseContract.NoteColumns.TITLE;
import static com.dicoding.submission_5.database.DatabaseContract.NoteColumns.DATE;
import static com.dicoding.submission_5.database.DatabaseContract.TABLE_FAV_MOVIE;
import static com.dicoding.submission_5.database.DatabaseContract.TABLE_FAV_TVSHOW;


public class FavHelper {
    private static final String DATABASE_TABLE_1 = TABLE_FAV_MOVIE;
    private static final String DATABASE_TABLE_2 = TABLE_FAV_TVSHOW;
    private static DBHelper dbHelper;
    private static FavHelper INSTANCE;
    private static SQLiteDatabase db;

    public FavHelper(Context context) {
        dbHelper = new DBHelper(context);
    }

    public static FavHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new FavHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
        if(db.isOpen())
            db.close();
    }

    public ArrayList<Movie> getAllFav(){
        ArrayList<Movie> arrayList = new ArrayList<>();
        Cursor cursor = db.query(DATABASE_TABLE_1,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();

        Movie movie;
        if(cursor.getCount() > 0){
            do {
                movie = new Movie();
                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                movie.setMv_name(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movie.setMv_date(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                movie.setMv_rate(cursor.getString(cursor.getColumnIndexOrThrow(RATE)));
                movie.setMv_description(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                movie.setMv_photo(cursor.getString(cursor.getColumnIndexOrThrow(PHOTO)));
                movie.setMv_ori_lang(cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGE)));
                arrayList.add(movie);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public boolean isExist(Movie movie){
        db = dbHelper.getReadableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_FAV_MOVIE + " WHERE " + _ID + "=" + movie.getId();
        Cursor cursor = db.rawQuery(QUERY,null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public long insertFav(Movie movie){
        ContentValues args = new ContentValues();
        args.put(_ID, movie.getId());
        args.put(TITLE, movie.getMv_name());
        args.put(DATE, movie.getMv_date());
        args.put(RATE, movie.getMv_rate());
        args.put(DESCRIPTION, movie.getMv_description());
        args.put(PHOTO, movie.getMv_photo());
        args.put(LANGUAGE, movie.getMv_ori_lang());
        return db.insert(DATABASE_TABLE_1, null, args);
    }

    public int deleteFav(int id){
        return db.delete(TABLE_FAV_MOVIE, _ID + " = '" + id + "'", null);
    }

    public Cursor queryByIdProvider(String id){
        return db.query(DATABASE_TABLE_1,
                null,
                _ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null);
    }

    public Cursor queryProvider(){
        return db.query(DATABASE_TABLE_1,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC");
    }

    public ArrayList<TVShow> getAllFavTv(){
        ArrayList<TVShow> arrayList = new ArrayList<>();
        Cursor cursor = db.query(DATABASE_TABLE_2,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        TVShow tvshow;
        if(cursor.getCount() > 0){
            do {
                tvshow = new TVShow();
                tvshow.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                tvshow.setTv_name(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                tvshow.setTv_date(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                tvshow.setTv_rate(cursor.getString(cursor.getColumnIndexOrThrow(RATE)));
                tvshow.setTv_description(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                tvshow.setTv_photo(cursor.getString(cursor.getColumnIndexOrThrow(PHOTO)));
                tvshow.setTv_ori_lang(cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGE)));
                arrayList.add(tvshow);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public boolean isExistTv(TVShow tvshow){
        db = dbHelper.getReadableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_FAV_TVSHOW + " WHERE " + _ID + "=" + tvshow.getId();

        Cursor cursor = db.rawQuery(QUERY,null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public long insertFavTv(TVShow tvshow){
        ContentValues args = new ContentValues();
        args.put(_ID, tvshow.getId());
        args.put(TITLE, tvshow.getTv_name());
        args.put(DATE, tvshow.getTv_date());
        args.put(RATE, tvshow.getTv_rate());
        args.put(DESCRIPTION, tvshow.getTv_description());
        args.put(PHOTO, tvshow.getTv_photo());
        args.put(LANGUAGE, tvshow.getTv_ori_lang());
        return db.insert(DATABASE_TABLE_2, null, args);
    }

    public int deleteFavTv(int id){
        return db.delete(TABLE_FAV_TVSHOW, _ID + " = '" + id + "'", null);
    }
}
