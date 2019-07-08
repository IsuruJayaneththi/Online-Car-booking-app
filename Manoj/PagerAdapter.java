package com.example.issa.pdm_project_2018.Manoj;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    int mNooftabs;

    public PagerAdapter(FragmentManager fm, int mNooftabs) {
        super(fm);
        this.mNooftabs = mNooftabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                tab1 t1=new tab1();
                return t1;
            case 1:
                Tab3 tab3=new Tab3();
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNooftabs;
    }
}
