package com.example.phone_clone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.phone_clone.adapter.ViewPagerAdapter;
import com.example.phone_clone.fragments.AppsFragment;
import com.example.phone_clone.fragments.FilesFragment;
import com.example.phone_clone.fragments.HistoryFragment;
import com.example.phone_clone.fragments.MusicFragment;
import com.example.phone_clone.fragments.PhotoFragment;
import com.example.phone_clone.fragments.VideoFragment;
import com.google.android.material.tabs.TabLayout;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends AppCompatActivity {
    TabLayout mTabLayout;
    ViewPager mViewPager;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        mToolbar = findViewById ( R.id.toolbar );
        setSupportActionBar ( mToolbar );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( false );
        getSupportActionBar ().setTitle ( null );
        mViewPager = findViewById ( R.id.view_pager1 );
        setupViewPager(mViewPager);
        mTabLayout = findViewById ( R.id.tabs );
        mTabLayout.setupWithViewPager ( mViewPager );
    }
    private  void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter ( getSupportFragmentManager (),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.AddFragment (new HistoryFragment (),"History" );
        viewPagerAdapter.AddFragment (new AppsFragment () ,"App");
        viewPagerAdapter.AddFragment (new PhotoFragment () ,"Photo");
        viewPagerAdapter.AddFragment (new MusicFragment () ,"Music");
         viewPagerAdapter.AddFragment (new VideoFragment (),"Video");
        viewPagerAdapter.AddFragment (new FilesFragment (),"File");
        viewPager.setAdapter ( viewPagerAdapter );

    }


}