package com.example.phone_clone.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.phone_clone.R;

import java.util.ArrayList;
import java.util.List;

import Model.Image;

public class PhotoFragment extends AllPhotoFragment {

    public PhotoFragment() {


    }
LinearLayoutCompat layoutCompat1,layoutCompat2;
    private List<Image> imagesList=new ArrayList<>();
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    TextView txtimagecount,txtfilecount;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_fragment, container, false);
        layoutCompat1=view.findViewById(R.id.layout_allphoto);
        layoutCompat2=view.findViewById(R.id.layout_folderphoto);
        txtimagecount=view.findViewById(R.id.photo_list_Count);
        txtfilecount=view.findViewById(R.id.photo_file_list_Count);

        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            imagesList=getAllShownImagesPath();
        }
        txtimagecount.setText(""+imagesList.size());
        AllPhotoFragment galleryFragment=new AllPhotoFragment(getContext());
        FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_view,galleryFragment);
        transaction.commit();
        layoutCompat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllPhotoFragment galleryFragment=new AllPhotoFragment(getContext());
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_view,galleryFragment);
                transaction.commit();
            }
        });
        layoutCompat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoFolderFragment galleryFragment=new PhotoFolderFragment();
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_view,galleryFragment);
                transaction.commit();
            }
        });
        return view;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                imagesList=getAllShownImagesPath();
            } else {
                // Permission Denied
                Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
