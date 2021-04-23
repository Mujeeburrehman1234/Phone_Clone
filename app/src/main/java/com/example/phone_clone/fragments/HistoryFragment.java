package com.example.phone_clone.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.phone_clone.R;

public class HistoryFragment extends Fragment {
    LinearLayoutCompat receiveHistory,sentHistory;
    public HistoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate ( R.layout.history_fragment ,container,false);
      receiveHistory = view.findViewById ( R.id.layout_receive );
        sentHistory = (LinearLayoutCompat) view.findViewById ( R.id.layout_sent );
        Fragment fragment = new ReceivesHistoryFragment ();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.history_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();



        sentHistory.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Fragment fragment = new SentHistoryFragment ();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.history_container, fragment);
               fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        } );
        receiveHistory.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ReceivesHistoryFragment ();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.history_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        } );

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
    }
}
