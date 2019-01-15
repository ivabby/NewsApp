package com.example.vabby.newsapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vabby.newsapp.Fragments.BuisnessFragment;
import com.example.vabby.newsapp.Fragments.EntertainmentFragment;
import com.example.vabby.newsapp.Fragments.HealthFragment;
import com.example.vabby.newsapp.Fragments.LatestFragment;
import com.example.vabby.newsapp.Fragments.ScienceFragment;
import com.example.vabby.newsapp.Fragments.SportsFragment;
import com.example.vabby.newsapp.Fragments.TechnologyFragment;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public SimpleFragmentPagerAdapter( Context context , FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new LatestFragment();
        else if(position == 1)
            return new BuisnessFragment();
        else if(position == 2)
            return new TechnologyFragment();
        else if(position == 3)
            return new EntertainmentFragment();
        else if(position == 4)
            return  new SportsFragment();
        else if(position == 5)
            return new ScienceFragment();
        else
            return new HealthFragment();
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return mContext.getString(R.string.latest);
        else if(position == 1)
            return mContext.getString(R.string.buisness);
        else if(position == 2)
            return mContext.getString(R.string.technology);
        else if(position == 3)
            return mContext.getString(R.string.entertainment);
        else if(position == 4)
            return mContext.getString(R.string.sports);
        else if(position == 5)
            return mContext.getString(R.string.science);
        else
            return mContext.getString(R.string.health);
    }
}
