package com.dicoding.submission_5.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.submission_5.BuildConfig;
import com.dicoding.submission_5.item.TVShow;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class TVShowModel extends ViewModel {
    private MutableLiveData<ArrayList<TVShow>> tvList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<TVShow>> tvListSearch = new MutableLiveData<>();

    public void cariTVShow(String nama){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TVShow> tvItemSearch = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/tv?api_key=" + BuildConfig.API_URL_KEY + "&language=en-US&query=" + nama;
        Log.i("cariTVShow", url);
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String rTVShow = new String(responseBody);
                    JSONObject objTVShow = new JSONObject(rTVShow);
                    JSONArray LTVShow = objTVShow.getJSONArray("results");
                    for(int i = 0; i < LTVShow.length(); i++ ){
                        Log.i("length TV", String.valueOf(LTVShow.length()));
                        JSONObject tv = LTVShow.getJSONObject(i);
                        TVShow ITVShow = new TVShow(tv);
                        Log.i("onSueccess",ITVShow.getTv_name());
                        tvItemSearch.add(ITVShow);
                    }
                    tvListSearch.postValue(tvItemSearch);
                } catch (Exception e) {
                    Log.i("onFailure","Gak dapet");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });

    }


    public void listTVShow(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TVShow> tvItem = new ArrayList<>();
        client.get(BuildConfig.API_URL_TV, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String rTVShow = new String(responseBody);
                    JSONObject objTVShow = new JSONObject(rTVShow);
                    JSONArray LTVShow = objTVShow.getJSONArray("results");
                    for(int i = 0; i < LTVShow.length(); i++ ){
                        JSONObject tv = LTVShow.getJSONObject(i);
                        TVShow ITVShow = new TVShow(tv);
                        tvItem.add(ITVShow);
                    }
                    tvList.postValue(tvItem);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }
    public LiveData<ArrayList<TVShow>> getTVShow(){
        return tvList;
    }

    public LiveData<ArrayList<TVShow>> getTVShowSearch(){
        return tvListSearch;
    }
}
