package com.dicoding.submission_5.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_FAV_MOVIE = "fav_movie";
    public static String TABLE_FAV_TVSHOW = "fav_tvshow";
    public static final String AUTHORITY = "com.dicoding.submission_5";
    private static final String SCHEME = "content";
    public static final class NoteColumns implements BaseColumns{
        public static String TITLE = "title";
        public static String DATE = "date";
        public static String RATE = "rate";
        public static String DESCRIPTION = "description";
        public static String PHOTO = "photo";
        public static String LANGUAGE = "language";

        public static final Uri CONTENT_URI_MOVIE= new Uri.Builder()
                .scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_FAV_MOVIE)
                .build();
    }
}
