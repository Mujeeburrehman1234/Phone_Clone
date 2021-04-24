package com.example.phone_clone.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.phone_clone.R;
import com.example.phone_clone.adapter.AllimageAdapter;

import java.util.ArrayList;
import java.util.List;

import com.example.phone_clone.Model.Image;


public class AllPhotoFragment extends Fragment {
    private List<Image> imagesList = new ArrayList<>();
    RecyclerView mRecyclerView;
    Context context;

    public AllPhotoFragment(Context context) {
        // Required empty public constructor
        this.context = context;
    }

    public AllPhotoFragment() {

    }

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_photo, container, false);
        mRecyclerView = view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            imagesList = getAllShownImagesPath();
        }


        mRecyclerView.setAdapter(new AllimageAdapter(getActivity(), imagesList));
        return view;
    }

    public List<Image> getAllShownImagesPath() {
        Uri uri;
        Cursor cursor;
        List<Image> mList = new ArrayList<Image>();
        int column_index_data, column_index_folder_name;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.MediaColumns.DATA/*,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME*/, MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.TITLE};
        String sortOrder = MediaStore.MediaColumns.DATE_MODIFIED + " DESC";
        cursor = getContext().getContentResolver().query(
                uri, // Uri
                null,
                null,
                null,
                sortOrder);
        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            int title = cursor.getColumnIndex(MediaStore.Images.Media.TITLE);
            //  int dt=cursor.getColumnIndex(MediaStore.Images.Media.DATE_MODIFIED);
            //            //absolutePathOfImage = cursor.getString(column_index_data);
            Image img = new Image();
            img.setPath(cursor.getString(column_index_data));
            img.setTitle(cursor.getString(title));
            //   img.setLastModified( new Date(Long.parseLong(cursor.getString(dt))));
            mList.add(img);
            //listOfAllImages.add(absolutePathOfImage);
        }
        return mList;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                imagesList = getAllShownImagesPath();
            } else {
                // Permission Denied
                Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}