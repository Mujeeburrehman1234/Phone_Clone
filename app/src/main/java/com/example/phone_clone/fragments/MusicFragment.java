package com.example.phone_clone.fragments;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phone_clone.R;
import com.example.phone_clone.adapter.MusicAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.Track;

public class MusicFragment extends Fragment {
    RecyclerView mRecyclerView;
    ArrayList<String> musicNameList;
    MusicAdapter mMusicAdapter;
    List<Track> tracks;
    MediaPlayer mediaPlayer = new MediaPlayer();
    AsyncTaskRunner asyn;
    View mView;

    public MusicFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.music_fragment, container, false);
        mRecyclerView = mView.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        asyn = new AsyncTaskRunner();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            asyn.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            asyn.execute();
        return mView;

    }

    List<Track> getAllMusicTracks() {
        // List<Track> mTracks= ArrayList<Track>();
        ArrayList<Track> mTracks = new ArrayList<Track>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        //Uri uri = Uri.fromFile(Environment.getExternalStoragePublicDirectory("DIRECTORY_MUSIC"));
        String sortOrder = MediaStore.MediaColumns.DATE_MODIFIED + " DESC";
        Cursor cursor = getContext().getContentResolver().query(
                uri, // Uri
                null,
                null,
                null,
                sortOrder);
        if (cursor == null) {

            Toast.makeText(getContext(), "Something Went Wrong.", Toast.LENGTH_LONG);

        } else if (!cursor.moveToFirst()) {

            Toast.makeText(getContext(), "No Music Found on SD Card.", Toast.LENGTH_LONG);

        } else {

            int Title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int size = cursor.getColumnIndex(MediaStore.Audio.Media.SIZE);
            int length = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            int path = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            int dateint = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_MODIFIED);


            //Getting Song ID From Cursor.
            //int id = cursor.getColumnIndex(MediaStore.Audio.Media._ID);

            do {

                // You can also get the Song ID using cursor.getLong(id).
                //long SongID = cursor.getLong(id);

                String SongTitle = cursor.getString(Title);
                // String types=cursor.getString(type);
                // String songPath=uri+"/"+SongTitle+"."+types;
                String songPath = cursor.getString(path);
                String songSize = cursor.getString(size);
                String songLength = cursor.getString(length);
                Date date = new Date(Long.parseLong(cursor.getString(dateint)));
                songLength = setCorrectDuration(songLength);
                float sz = cursor.getInt(length);
                Track track = new Track();
                track.setTitle(SongTitle);
                track.setSize(getFileSize(songSize));
                track.setLength(songLength);
                track.setPath(songPath);
                track.setDate(date);
                mTracks.add(track);
            } while (cursor.moveToNext());
        }
        // Collections.sort(mTracks);
        return mTracks;
    }

    private String setCorrectDuration(String songs_duration) {
        // TODO Auto-generated method stub

        if (Integer.valueOf(songs_duration) != null) {
            int time = Integer.valueOf(songs_duration);

            int seconds = time / 1000;
            int minutes = seconds / 60;
            seconds = seconds % 60;

            if (seconds < 10) {
                songs_duration = String.valueOf(minutes) + ":0" + String.valueOf(seconds);
            } else {
                songs_duration = String.valueOf(minutes) + ":" + String.valueOf(seconds);
            }
            return songs_duration;
        }
        return null;
    }

    public String getFileSize(String size) {
        String modifiedFileSize = null;
        double fileSize = 0.0;
        if (size != null) {
            //fileSize = Double.longBitsToDouble();//in Bytes
            fileSize = Double.parseDouble(size);

            if (fileSize < 1024) {
                modifiedFileSize = String.valueOf(fileSize).concat("B");
            } else if (fileSize > 1024 && fileSize < (1024 * 1024)) {
                modifiedFileSize = String.valueOf(Math.round((fileSize / 1024 * 100.0)) / 100.0).concat("KB");
            } else {
                modifiedFileSize = String.valueOf(Math.round((fileSize / (1024 * 1204) * 100.0)) / 100.0).concat("MB");
            }
        } else {
            modifiedFileSize = "Unknown";
        }

        return modifiedFileSize;
    }

    private class AsyncTaskRunner extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                tracks = getAllMusicTracks();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                mRecyclerView.setAdapter(new MusicAdapter(tracks, getContext()));
                mRecyclerView.setVisibility(View.VISIBLE);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute() {
            mRecyclerView.setVisibility(View.GONE);
        }

    }
}