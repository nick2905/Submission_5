package com.dicoding.submission_5.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.submission_5.BuildConfig;
import com.dicoding.submission_5.item.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MovieModel extends ViewModel {
    private MutableLiveData<ArrayList<Movie>> mvList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movie>> mvListSearch = new MutableLiveData<>();

    public void cariMovie(String nama){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> mvItemSearch = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key=" + BuildConfig.API_URL_KEY + "&language=en-US&query=" + nama;
        Log.i("cariMovie",url);
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String rMovie = new String(responseBody);
                    JSONObject objMov = new JSONObject(rMovie);
                    JSONArray LMovie = objMov.getJSONArray("results");
                    for(int i = 0; i < LMovie.length(); i++){
                        Log.i("length Movie", String.valueOf(LMovie.length()));
                        JSONObject mv = LMovie.getJSONObject(i);
                        Movie IMovie = new Movie(mv);
                        //IMovie.setId(mv.getInt("id"));
                        Log.i("onSuccess", IMovie.getMv_name());
                        mvItemSearch.add(IMovie);
                    }
                    mvListSearch.postValue(mvItemSearch);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }



    public void listMovie(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> mvItem = new ArrayList<>();
        client.get(BuildConfig.API_URL_MOVIE, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String rMovie = new String(responseBody);
                    JSONObject objMov = new JSONObject(rMovie);
                    JSONArray LMovie = objMov.getJSONArray("results");
                    for(int i = 0; i < LMovie.length(); i++){
                        JSONObject mv = LMovie.getJSONObject(i);
                        Movie IMovie = new Movie(mv);
                        mvItem.add(IMovie);
                    }
                    mvList.postValue(mvItem);
                } catch (Exception e) {
                    Log.i("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<Movie>> getMovie(){
            return mvList;
    }

    public LiveData<ArrayList<Movie>> getMovieSearch() {
        return mvListSearch;
    }
}
