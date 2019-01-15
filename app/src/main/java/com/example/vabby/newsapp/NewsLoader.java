package com.example.vabby.newsapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;

    public NewsLoader(Context context , String url){
        super(context);
        mUrl = url;
    }

    public void onStartLoading(){
        Log.i("On start","Loader is starting");
        forceLoad();
    }

    @Nullable
    @Override
    public List<News> loadInBackground() {
        Log.i("Load in background","Loader is in background");

        if(mUrl == null){
            return null;
        }

        List<News> news = ExtractNews.fetchEarthquakeData(mUrl);
        return news;
    }
}
