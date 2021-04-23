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
import com.example.phone_clone.adapter.ReceivesHistoryAdapter;

import java.util.ArrayList;

public class ReceivesHistoryFragment extends Fragment {

    RecyclerView mRecyclerView;
    ArrayList<String> ReceivesList;
    ReceivesHistoryAdapter mReceivesHistoryAdapter;

    public ReceivesHistoryFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.receives_history_fragment ,container,false);
        mRecyclerView = view.findViewById ( R.id.recycler );
        mRecyclerView.setLayoutManager ( new LinearLayoutManager (getContext ()) );
        ReceivesList= new ArrayList<> ();
        ReceivesList.add ( "On My Way" );
        ReceivesList.add ( "Lily" );
        ReceivesList.add ( "Sing me to sleep" );
        ReceivesList.add ( "Diamond Heart" );
        ReceivesList.add ( "Alone" );
        ReceivesList.add ( "Darkside" );
        ReceivesList.add ( "Faded" );
        ReceivesList.add ( "Faded" );
        ReceivesList.add ( "Darkside" );

        mReceivesHistoryAdapter = new ReceivesHistoryAdapter (ReceivesList ,getContext ());

        mRecyclerView.setAdapter ( mReceivesHistoryAdapter );
         return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
    }
}
