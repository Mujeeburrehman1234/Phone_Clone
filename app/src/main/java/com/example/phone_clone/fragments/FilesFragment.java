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
import com.example.phone_clone.adapter.FilesAdapter;
import com.example.phone_clone.adapter.MusicAdapter;
import com.example.phone_clone.adapter.VideoAdapter;

import java.util.ArrayList;

public class FilesFragment extends Fragment {
    RecyclerView mRecyclerView;
    ArrayList<String> filesNameList;
   FilesAdapter mFilesAdapter;
    View mView;
    public FilesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate ( R.layout.file_fragment,container,false );
        mRecyclerView = mView.findViewById ( R.id.recycler );
        mRecyclerView.setLayoutManager ( new LinearLayoutManager (getContext ()) );
        filesNameList = new ArrayList<> ();
        filesNameList.add ( "On My Way" );
        filesNameList.add ( "Lily" );
        filesNameList.add ( "Sing me to sleep" );
        filesNameList.add ( "Diamond Heart" );
        filesNameList.add ( "Alone" );
        filesNameList.add ( "Darkside" );
        filesNameList.add ( "Faded" );
        filesNameList.add ( "Faded" );
        filesNameList.add ( "Darkside" );
        mFilesAdapter = new FilesAdapter (  filesNameList,getContext ());
        mRecyclerView.setAdapter ( mFilesAdapter  );
       return  mView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
    }
}
