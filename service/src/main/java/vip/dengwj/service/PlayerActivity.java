package vip.dengwj.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.service.actions.interfaces.IPlayerControl;
import vip.dengwj.service.services.PlayerService;

public class PlayerActivity extends AppCompatActivity {
    private static IPlayerControl playerControl;
    private SeekBar seekBar;
    private Button playerPauseBtn;
    private Button closeBtn;
    private PlayerConnection playerConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

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

    private static class PlayerConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            playerControl = (IPlayerControl) service;
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
                Log.d("pumu", "手已经触摸上去拖动...");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
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
            // 解绑服务
            unbindService(playerConnection);
        }
    }
}