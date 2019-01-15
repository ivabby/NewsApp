package com.example.vabby.newsapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vabby.newsapp.Fragments.LatestFragment;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {
    Context mContext;
    public NewsAdapter(Context context, List<News> resource) {
        super(context, 0 , resource);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item ,parent, false);
        }

        //  Get the News object located at this position
        News currentNews = getItem(position);

        //  TextView in list_item.xml layout for news title
        TextView newsTitle = (TextView) listItemView.findViewById(R.id.news_title);

        //  Set the current news title
        newsTitle.setText(currentNews.getTitle());

        //  TextView in list_item.xml for news author
        TextView author = (TextView) listItemView.findViewById(R.id.news_author);

        //  Set the author for news
        author.setText(currentNews.getAuthor());

        //  ImageView in list_item.xml for news image
        ImageView image = (ImageView) listItemView.findViewById(R.id.news_image);

        String imageUrl = currentNews.getImageUrl();

        Picasso.with(mContext).load(imageUrl).memoryPolicy(MemoryPolicy.NO_CACHE).priority(Picasso.Priority.HIGH).into(image);

        return listItemView;
    }

//    private class ImageSyncTask extends AsyncTask<String,Void,Integer>{
//
//        @Override
//        protected Integer doInBackground(String... strings) {
//            if(strings.length < 1 || strings[0] == null){
//                return null;
//            }
//
//            Inte
//        }
//    }
}
