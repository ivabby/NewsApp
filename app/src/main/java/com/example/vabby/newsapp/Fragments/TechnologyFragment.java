package com.example.vabby.newsapp.Fragments;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vabby.newsapp.ExtractNews;
import com.example.vabby.newsapp.News;
import com.example.vabby.newsapp.NewsAdapter;
import com.example.vabby.newsapp.NewsLoader;
import com.example.vabby.newsapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechnologyFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>> {

    View rootView = null;

    private static final String LATEST_NEWS = "https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=e4d63d0560244d9baa4630e199bc063f";

    private static final int TECHNOLOGY_LOADER_ID = 3;

    private NewsAdapter newsAdapter;

    private TextView mEmptyTextView;
    private Context mContext;
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.news_list, container, false);

            mEmptyTextView = (TextView) rootView.findViewById(R.id.empty_view);
            listView = (ListView) rootView.findViewById(R.id.list);
            mContext = getActivity();
        //  Creating adapter that takes empty list of news
        newsAdapter = new NewsAdapter(mContext , new ArrayList<News>());

        //  Set the adapter on list
        listView.setAdapter(newsAdapter);


        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected news.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = newsAdapter.getItem(position);

                Uri news = Uri.parse(currentNews.getUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW , news);

                startActivity(websiteIntent);
            }
        });


        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            if(newsAdapter.isEmpty() || newsAdapter == null)
            {
                ProgressBar progressBar = getActivity().findViewById(R.id.progress_bar);
                progressBar.setVisibility(View.VISIBLE);
            }
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(TECHNOLOGY_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = rootView.findViewById(R.id.progress_bar);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyTextView.setText(R.string.no_internet_connection);
        }
        return rootView;
    }


    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, @Nullable Bundle bundle) {

        return new NewsLoader(mContext , LATEST_NEWS);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> news) {
        Log.i("Load finished","Loader is finished");

        ProgressBar progressBar = (ProgressBar) getActivity().findViewById(R.id.progress_bar);

        progressBar.setVisibility(View.GONE);
        //if(news!=null && !news.isEmpty())
        newsAdapter.addAll(news);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        Log.i("Load reset","Loader is reset");

        newsAdapter.clear();
    }
}
