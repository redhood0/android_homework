package com.homework.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.homework.R;
import com.homework.service.MusicInterface;
import com.homework.service.MusicService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MusicPlayActivity extends AppCompatActivity {
    public static final String MUSIC_SERVICE = "com.homework.service.MusicService";
    public static final int MUSIC_MESSAGE = 47;
    private TextView tv_title;
    private TextView tv_alltime;
    private ImageView iv_start;
    private ImageView iv_pause;
    private ImageView iv_stop;
    private ImageView iv_blackdisk;
    private SeekBar sb_progress;
    private ObjectAnimator mCircleAnimator;
    private ServiceConnection serviceConnection;
    private MusicInterface mi;
    private MusicService musicService;
    private boolean isPause = false;
    private boolean isStart = false;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        setStatusBarFullTransparent();
        setFitSystemWindow(false);

        init();
        initDatas();
    }


    private void init() {
        tv_title = findViewById(R.id.tv_title);
        iv_start = findViewById(R.id.iv_start);
        iv_pause = findViewById(R.id.iv_pause);
        iv_stop = findViewById(R.id.iv_stop);
        tv_alltime = findViewById(R.id.tv_alltime);
        iv_blackdisk = findViewById(R.id.iv_blackdisk);
        sb_progress = findViewById(R.id.sb_progress);


    }

    class MusicServiceConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 建立连接
            // 获取服务的操作对象
            MusicService.MyBinder binder = (MusicService.MyBinder) service;
            musicService = binder.getService();// 获取到的Service即MyService
            mi = (MusicInterface) service;

            int mp3time = musicService.mediaPlayerl.getDuration();

            SimpleDateFormat sd = new SimpleDateFormat("mm:ss");
            String x = sd.format(new Date(mp3time));
            tv_alltime.setText(x);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
        }
    }


    private void initDatas() {
        String title = getIntent().getStringExtra("title");
        tv_title.setText(title);
        Intent intent = new Intent(this, MusicService.class);
        intent.setAction(MUSIC_SERVICE);

        serviceConnection = new MusicServiceConn();
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);



        iv_start.setOnClickListener(n -> {

            Log.d("MUSIC", mi.toString());
            mi.play();
            startUpdateSeekBarProgress();
            if (isPause) {
                resumeAnim2();
            } else if (!isStart) {
                startAnim2();
            }
        });

        iv_pause.setOnClickListener(n -> {
            Log.d("MSG", ">>>>>: " + musicService.mediaPlayerl.getCurrentPosition());
            mi.pause();
            if (isStart) {
                pauseAnim2();
                stopUpdateSeekBarProgree();
            }
        });

        iv_stop.setOnClickListener(n -> {
            mi.stop();
            stopUpdateSeekBarProgree();
            sb_progress.setProgress(0,true);
            stopAnim2();
        });
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }

    /**
     * 全透状态栏
     */
    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 半透明状态栏
     */
    protected void setHalfTransparent() {

        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 如果需要内容紧贴着StatusBar
     * 应该在对应的xml布局文件中，设置根布局fitsSystemWindows=true。
     */
    private View contentViewGroup;

    protected void setFitSystemWindow(boolean fitSystemWindow) {
        if (contentViewGroup == null) {
            contentViewGroup = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        }
        contentViewGroup.setFitsSystemWindows(fitSystemWindow);
    }

    /**
     * anim
     */
    private void startAnim() {
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);

        if (rotate != null) {
            iv_blackdisk.startAnimation(rotate);
        } else {
            iv_blackdisk.setAnimation(rotate);
            iv_blackdisk.startAnimation(rotate);
        }

    }

    private void stopAnim() {
        iv_blackdisk.clearAnimation();

    }

    private void startAnim2() {
        mCircleAnimator = ObjectAnimator.ofFloat(iv_blackdisk, "rotation", 0.0f, 359.0f);
        mCircleAnimator.setDuration(60000);
        mCircleAnimator.setInterpolator(new LinearInterpolator());
        mCircleAnimator.setRepeatCount(-1);
        mCircleAnimator.setRepeatMode(ObjectAnimator.RESTART);
        mCircleAnimator.start();
        isStart = true;
    }

    private void pauseAnim2() {
        isPause = true;
        mCircleAnimator.pause();
    }

    private void resumeAnim2() {
        mCircleAnimator.resume();
        isPause = false;
        isStart = true;
    }

    private void stopAnim2() {
        if (mCircleAnimator != null) {
            mCircleAnimator.end();
            isStart = false;
            isPause = false;
        }
    }
    private Handler mMusicHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            sb_progress.setProgress(sb_progress.getProgress() + 1);
            //mTvMusicDuration.setText(duration2Time(sb_progress.getProgress()));
            startUpdateSeekBarProgress();
        }
    };

    private void startUpdateSeekBarProgress() {
        /*避免重复发送Message*/
        stopUpdateSeekBarProgree();
        mMusicHandler.sendEmptyMessageDelayed(MUSIC_MESSAGE,1000);
    }

    private void stopUpdateSeekBarProgree() {
        mMusicHandler.removeMessages(MUSIC_MESSAGE);
    }

}
