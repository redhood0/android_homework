package com.homework.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaTimestamp;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.homework.R;

import java.io.IOException;

import androidx.annotation.Nullable;

public class MusicService extends Service implements MediaPlayer.OnCompletionListener {
    public MediaPlayer mediaPlayerl;

    private final IBinder binder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        mediaPlayerl = MediaPlayer.create(this, R.raw.song1);
        mediaPlayerl.setOnCompletionListener(this);
        MediaTimestamp mediaTimestamp = mediaPlayerl.getTimestamp();
        Log.d("MUSIC", "MusicService:onCreate: " + mediaPlayerl.getDuration() + "-"
                + mediaTimestamp.getAnchorSytemNanoTime());
        super.onCreate();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mediaPlayerl.release();
    }

    /**
     * for startService
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int status = intent.getIntExtra("status", 3);
        switch (status) {
            case 0:
                mediaPlayerl.start();
                break;
            case 1:
                mediaPlayerl.pause();
                break;
            case 2:
                mediaPlayerl.stop();
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if (mediaPlayerl != null) {
            mediaPlayerl.release();
        }
        super.onDestroy();
    }

    // IBinder是远程对象的基本接口，是为高性能而设计的轻量级远程调用机制的核心部分。但它不仅用于远程
    // 调用，也用于进程内调用。这个接口定义了与远程对象交互的协议。
    // 不要直接实现这个接口，而应该从Binder派生。
    // Binder类已实现了IBinder接口
    public class MyBinder extends Binder implements MusicInterface {
        /**
         * 获取Service的方法 * @return 返回PlayerService
         */
        public MusicService getService() {
            return MusicService.this;
        }

        @Override
        public void play() {
            mediaPlayerl.start();
        }

        @Override
        public void pause() {
            mediaPlayerl.pause();
        }

        @Override
        public void stop() {
            if (mediaPlayerl != null) {
                mediaPlayerl.stop();
                try {
                    mediaPlayerl.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
