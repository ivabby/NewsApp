package com.example.vabby.newsapp;

public class News {
    //  String to contain news title
    public String mNewsTitle;

    //  String to contain news page
    public String mNewsPage;

    //  String to contain url
    public String mUrl ;

    //  String to contain imageUrl
    public String mImageUrl;

    //  String to contain author
    public String mAuthor;

    public News(String newsTitle , String url , String imageUrl , String author){
        mNewsTitle = newsTitle;
        mUrl = url;
        mImageUrl = imageUrl;
        mAuthor = author;
    }

    public String getTitle(){
        return mNewsTitle;
    }

    public String getUrl(){ return mUrl;}

    public String getAuthor(){ return mAuthor;}

    public String getImageUrl(){ return mImageUrl;}
}
