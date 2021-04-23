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

import java.util.ArrayList;

public class MusicFragment extends Fragment {
    RecyclerView mRecyclerView;
    ArrayList<String> musicNameList;
   MusicAdapter mMusicAdapter;
    View mView;
    public MusicFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate ( R.layout.music_fragment,container,false );
        mRecyclerView = mView.findViewById ( R.id.recycler );
        mRecyclerView.setLayoutManager ( new LinearLayoutManager (getContext ()) );
        musicNameList = new ArrayList<> ();
        musicNameList.add ( "On My Way" );
        musicNameList.add ( "Lily" );
        musicNameList.add ( "Sing me to sleep" );
        musicNameList.add ( "Diamond Heart" );
        musicNameList.add ( "Alone" );
        musicNameList.add ( "Darkside" );
        musicNameList.add ( "Faded" );
        musicNameList.add ( "Faded" );
        musicNameList.add ( "Darkside" );

        mMusicAdapter = new MusicAdapter ( musicNameList ,getContext ());
        mRecyclerView.setAdapter ( mMusicAdapter );

        return  mView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
    }
}
