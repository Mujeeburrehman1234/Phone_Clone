package com.example.phone_clone.fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phone_clone.R;
import com.example.phone_clone.adapter.VideoAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.phone_clone.Model.Video;

public class VideoFragment extends Fragment {
    RecyclerView mRecyclerView;
    List<Video> mVideoList=new ArrayList<Video>();
    View mView;
    AsyncTaskRunner asyn;

    public VideoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.videos_fragment, container, false);
        mRecyclerView = mView.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        asyn = new AsyncTaskRunner();
        asyn.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        return mView;
    }

    private List<Video> getAllShownVideoPath(Context activity) {
        Uri uri;
        Cursor cursor;
        List<Video> vidList=new ArrayList<>();
        int column_index_data;
        String absolutePathOfVideo = null;
        // Environment.getExternalStorageDirectory() + File.separator + "folder_name/";
        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String sortOrder=MediaStore.MediaColumns.DATE_MODIFIED+" DESC";
        cursor = activity.getContentResolver().query(uri, null, null,
                null, sortOrder);
        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        while (cursor.moveToNext()) {
            absolutePathOfVideo = cursor.getString(column_index_data);
            String title=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.TITLE));
            Date date= new Date(Long.parseLong(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_MODIFIED))));
            Video vid=new Video();
            vid.setTitle(title);
            vid.setDateModified(date);
            vid.setPath(absolutePathOfVideo);
            vidList.add(vid);
        }
        return vidList;
    }
    private class AsyncTaskRunner extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                mVideoList = getAllShownVideoPath(getContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {

            try {
                mRecyclerView.setAdapter(new VideoAdapter(getContext(),mVideoList));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        protected void onPreExecute() {

        }
    }
}
