package vip.dengwj.service;

import static vip.dengwj.service.actions.interfaces.IPlayerControl.PLAY_STATE_PAUSE;
import static vip.dengwj.service.actions.interfaces.IPlayerControl.PLAY_STATE_PLAY;
import static vip.dengwj.service.actions.interfaces.IPlayerControl.PLAY_STATE_STOP;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import vip.dengwj.service.actions.interfaces.IPlayerControl;
import vip.dengwj.service.actions.interfaces.IPlayerViewControl;
import vip.dengwj.service.services.PlayerService;

public class PlayerActivity extends AppCompatActivity implements IPlayerViewControl {
    private static IPlayerControl playerControl;
    private SeekBar seekBar;
    private Button playerPauseBtn;
    private Button closeBtn;
    private PlayerConnection playerConnection;
    private boolean isUserTouchProgressBar = false;

    private void openPermissions() {
        String readRequest = android.Manifest.permission.READ_EXTERNAL_STORAGE;
        boolean readFlag = ActivityCompat.checkSelfPermission(this, readRequest) != PackageManager.PERMISSION_GRANTED;

        if (readFlag) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, readRequest)) {
                // 引导用户打开读取联系人权限
            }
            ActivityCompat.requestPermissions(this, new String[]{readRequest}, 100);

        } else {
            // getContacts()
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // 打开权限
        openPermissions();

        initView();
        initEvent();
        // 开启服务
        initService();
        // 绑定服务
        initBindService();
    }

    private void initBindService() {
        Intent intent = new Intent(this, PlayerService.class);
        playerConnection = new PlayerConnection();
        bindService(intent, playerConnection, BIND_AUTO_CREATE);
    }

    private class PlayerConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("pumu", "onServiceConnected。。。");
            playerControl = (IPlayerControl) service;
            playerControl.registerViewController(PlayerActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    private void initService() {
        startService(new Intent(this, PlayerService.class));
    }

    private void initEvent() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 进度条发生改变
                Log.d("pumu", "进度条发生改变..." + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 手已经触摸上去拖动
                isUserTouchProgressBar = true;
                Log.d("pumu", "手已经触摸上去拖动...");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isUserTouchProgressBar = false;
                // 停止拖动
                Log.d("pumu", "停止拖动..." + seekBar.getProgress());
                if (playerControl != null) {
                    playerControl.seekTo(seekBar.getProgress());
                }
            }
        });

        // 播放或者暂停
        playerPauseBtn.setOnClickListener(v -> {
            Log.d("pumu", "playerPauseBtn。。。");
            if (playerControl != null) {
                playerControl.playOrPause();
            }
        });

        // 关闭
        closeBtn.setOnClickListener(v -> {
            Log.d("pumu", "closeBtn...");
            if (playerControl != null) {
                playerControl.stopPlay();
            }
        });
    }

    private void initView() {
        seekBar = findViewById(R.id.seek_bar);
        playerPauseBtn = findViewById(R.id.play_or_pause);
        closeBtn = findViewById(R.id.close);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (playerConnection != null) {
            playerControl.unRegisterViewController();
            // 解绑服务
            unbindService(playerConnection);
        }
    }

    @Override
    public void onPlayerStateChange(int state) {
        // 播放
        if (state == PLAY_STATE_PLAY) {
            playerPauseBtn.setText("暂停");
        } else if (state == PLAY_STATE_PAUSE) {
            playerPauseBtn.setText("播放");
        } else if (state == PLAY_STATE_STOP) {
            // 停止
            playerPauseBtn.setText("播放");
        }
    }

    @Override
    public void onSeekChange(int seek) {
        // android 里，有两个控件是可以用子线程去更新
        // progressBar 和 surfaceView
        // if (!isUserTouchProgressBar) {
        //     seekBar.setProgress(seek);
        // }

        runOnUiThread(() -> {
            if (!isUserTouchProgressBar) {
                seekBar.setProgress(seek);
            }
        });
    }
}