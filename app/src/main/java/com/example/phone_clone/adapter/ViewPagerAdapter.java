package com.example.phone_clone.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "ViewPagerAdapter";
    private List<Fragment> mFragmentList = new ArrayList<> ();
    private  List<String> mFragmentTitle = new ArrayList<> ();

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super ( fm, behavior );
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get ( position );
    }

    @Override
    public int getCount() {
        return mFragmentList.size ();
    }
    public void AddFragment(Fragment fragment ,String title) {
        mFragmentList.add ( fragment );
        mFragmentTitle.add ( title );

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitle.get ( position );
    }


}
