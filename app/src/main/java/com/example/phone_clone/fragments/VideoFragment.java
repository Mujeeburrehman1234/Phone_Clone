package com.example.phone_clone.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phone_clone.R;
import com.example.phone_clone.adapter.MusicAdapter;
import com.example.phone_clone.adapter.VideoAdapter;

import java.util.ArrayList;

public class VideoFragment extends Fragment {
    RecyclerView mRecyclerView;
    ArrayList<String> videosNameList;
   VideoAdapter mVideoAdapter;
    View mView;

    public VideoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView =   inflater.inflate ( R.layout.videos_fragment ,container,false);
        mRecyclerView = mView.findViewById ( R.id.recycler );
        mRecyclerView.setLayoutManager ( new LinearLayoutManager (getContext ()) );

        videosNameList = new ArrayList<> ();
        videosNameList.add ( "On My Way" );
        videosNameList.add ( "Lily" );
        videosNameList.add ( "Sing me to sleep" );
        videosNameList.add ( "Diamond Heart" );
        videosNameList.add ( "Alone" );
        videosNameList.add ( "Darkside" );
        videosNameList.add ( "Faded" );
        videosNameList.add ( "Faded" );
        videosNameList.add ( "Darkside" );

        mVideoAdapter = new VideoAdapter ( videosNameList ,getContext ());
        mRecyclerView.setAdapter ( mVideoAdapter );
        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
    }
}
