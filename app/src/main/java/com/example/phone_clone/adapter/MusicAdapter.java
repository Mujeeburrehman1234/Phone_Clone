package com.example.phone_clone.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phone_clone.R;

import java.io.IOException;
import java.util.List;

import com.example.phone_clone.Model.Track;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>{
    List<Track> musicList;
    Context mContext;
    private static final String TAG = "MusicAdapter";
    MediaPlayer mediaPlayer = new MediaPlayer();
    
    public MusicAdapter(List<Track> musicList, Context context) {
        this.musicList = musicList;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).
                inflate ( R.layout.music_layout,parent,false);
        ViewHolder viewholder = new ViewHolder ( view );
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final int pos=position;
        final String text = musicList.get(position).getTitle();
        final String size = musicList.get(position).getSize();
        final String length = musicList.get(position).getLength();
        //Uri musicUri = Uri.parse ( musicList.get ( position ).getPath () );
        holder.title.setText(text);
        holder.size.setText(size);
        holder.length.setText(length);
        //playing music
        holder.title.setOnClickListener ( new View.OnClickListener () {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                mediaPlayer.stop ();
                mediaPlayer = new MediaPlayer ();
                final String path = musicList.get ( position ).getPath ();
                String title = Uri.parse ( path ).getLastPathSegment ();
                final Dialog dialog = new Dialog ( mContext );
                dialog.setContentView ( R.layout.audio_dailog_layout );
                dialog.setTitle ( title );
                final ImageView buttonPlayPause = (ImageView) dialog.findViewById ( R.id.play_button );
                buttonPlayPause.setImageResource ( R.drawable.ic_baseline_play_circle_outline_24 );
                seekBarProgress = (SeekBar) dialog.findViewById ( R.id.seek_bar );
                seekBarProgress.setProgress ( 0 );
                seekBarProgress.setMax ( 100 );
                seekBarProgress.setOnSeekBarChangeListener ( new SeekBar.OnSeekBarChangeListener () {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            float factor = (float) progress / 100;
                            float mills = factor * mediaFileLengthInMilliseconds;
                            mediaPlayer.seekTo ( (int) mills );
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                } );

                TextView titleText = dialog.findViewById ( R.id.track_name );
                titleText.setText ( title );
                dialog.setOnDismissListener ( new DialogInterface.OnDismissListener () {

                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mediaPlayer.stop ();
                    }
                } );
                buttonPlayPause.setOnClickListener ( new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        try {
                            if (mediaPlayer.isPlaying ()) {
                                mediaPlayer.pause ();
                            } else {
                                mediaPlayer.start ();
                            }
                            try {
                                mediaPlayer.setDataSource ( path );
                                mediaPlayer.prepare ();
                                mediaPlayer.start ();
                            } catch (Exception e) {
                                e.printStackTrace ();
                            }
                            primarySeekBarProgressUpdater ();

                        } catch (Exception e) {
                            e.printStackTrace ();
                        }
                        mediaFileLengthInMilliseconds = mediaPlayer.getDuration ();
                        if (mediaPlayer.isPlaying ()) {
                            buttonPlayPause.setImageResource ( R.drawable.ic_baseline_pause_circle_outline_24 );
                        } else {
                            buttonPlayPause.setImageResource ( R.drawable.ic_baseline_play_circle_outline_24 );

                        }


                    }
                } );
                dialog.show ();
            }

        } );
    }
    private final Handler handler = new Handler();
    SeekBar seekBarProgress;
    private int mediaFileLengthInMilliseconds;
    private void primarySeekBarProgressUpdater() {
        seekBarProgress.setProgress((int)(((float)
                mediaPlayer.getCurrentPosition()/mediaFileLengthInMilliseconds)*100)); // This math construction give a percentage of "was playing"/"song length"
        if (mediaPlayer.isPlaying()) {
            Runnable notification = new Runnable() {
                public void run() {
                    primarySeekBarProgressUpdater();
                }
            };
            handler.postDelayed(notification,1000);
        }
    }

    @Override
    public int getItemCount() {
        return musicList.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

       public CheckBox musicCheckbox;
        public TextView title;
        public  TextView size;
        public TextView length;
        public ImageView musicIcon;
        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
          musicIcon = itemView.findViewById ( R.id.music_thumb );
          title = itemView.findViewById ( R.id.music_title );
          size = itemView.findViewById ( R.id.music_size );
          length=itemView.findViewById(R.id.music_lenght);

        }
    }
}
