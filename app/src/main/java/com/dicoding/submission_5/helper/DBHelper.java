package com.dicoding.submission_5.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dicoding.submission_5.database.DatabaseContract;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_MOV = String.format("CREATE TABLE %s" +
            " (%s INTEGER PRIMARY KEY," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s REAL NOT NULL)",
            DatabaseContract.TABLE_FAV_MOVIE,
            DatabaseContract.NoteColumns._ID,
            DatabaseContract.NoteColumns.TITLE,
            DatabaseContract.NoteColumns.DATE,
            DatabaseContract.NoteColumns.RATE,
            DatabaseContract.NoteColumns.DESCRIPTION,
            DatabaseContract.NoteColumns.PHOTO,
            DatabaseContract.NoteColumns.LANGUAGE);

    private static final String CREATE_TABLE_TV = String.format("CREATE TABLE %s" +
                    " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s REAL NOT NULL)",
            DatabaseContract.TABLE_FAV_TVSHOW,
            DatabaseContract.NoteColumns._ID,
            DatabaseContract.NoteColumns.TITLE,
            DatabaseContract.NoteColumns.DATE,
            DatabaseContract.NoteColumns.RATE,
            DatabaseContract.NoteColumns.DESCRIPTION,
            DatabaseContract.NoteColumns.PHOTO,
            DatabaseContract.NoteColumns.LANGUAGE);

    private static String DATABASE_NAME = "favorite";

    DBHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_MOV);
        sqLiteDatabase.execSQL(CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAV_MOVIE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAV_TVSHOW);
        onCreate(sqLiteDatabase);
    }
}
