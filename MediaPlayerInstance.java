package com.creeps.ex_ide.core;

import android.content.Context;
import android.icu.text.DateFormat;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;

/**
 * Created by rohan on 10/3/18.
 * A singleton wrapper around the mediaPlayer class
 * Requires the following permissions
 *  uses-permission android:name="android.permission.INTERNET"
 *
 */

public class MediaPlayerInstance {
    private static MediaPlayerInstance mMediaPlayerReference;
    private Context mContext;
    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private boolean prepared;
    private boolean hasStartedStopped;//true when stopped
    private MediaPlayerInstance(Context context){
        this.mContext=context;
        this.mediaPlayer=new MediaPlayer();
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.N)
            this.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        else
            this.mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).
            setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build());


    }


    public static MediaPlayerInstance getMediaPlayerInstance(Context ctx){
        return mMediaPlayerReference= mMediaPlayerReference==null?new MediaPlayerInstance(ctx):mMediaPlayerReference;
    }


    public void playURL(String url){
        new PlayerAsyncTask(null).execute(url);
    }
    public void changeState(){
        this.playPause=!this.playPause;
    }



    private class PlayerAsyncTask extends AsyncTask<String,Void,Boolean>{

        private final static String TAG="PlayerAsyncTask";
        private ProgressBar mPgb;
        public PlayerAsyncTask(ProgressBar pg){
            this.mPgb=pg;
            Log.d(TAG,"creatd");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(this.mPgb!=null){
                /* todo show buffering*/
            }
        }



        @Override
        protected Boolean doInBackground(String... params) {
            Log.d(TAG,"in doinBack");
            try {
                mediaPlayer.setDataSource(params[0]);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        playPause=false;
                        hasStartedStopped=true;
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }

                });
                mediaPlayer.prepare();
                prepared=true;
            }catch (IOException ioe){
                prepared=false;
                ioe.printStackTrace();
            }catch(Exception e){
                prepared=false;
                e.printStackTrace();
            }

            return prepared;
        }


        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(this.mPgb!=null && this.mPgb.isShown())
                this.mPgb.setVisibility(View.GONE);

            mediaPlayer.start();
            hasStartedStopped=false;
            Log.d(TAG,"in postEx player started");

        }
    }


}
