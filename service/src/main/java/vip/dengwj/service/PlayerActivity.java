package vip.dengwj.service;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private Button playerPauseBtn;
    private Button closeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        initView();
        initEvent();
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
                Log.d("pumu", "停止拖动...");
            }
        });

        playerPauseBtn.setOnClickListener(v -> {
            Log.d("pumu", "playerPauseBtn。。。");
        });

        closeBtn.setOnClickListener(v -> {
            Log.d("pumu", "closeBtn...");
        });
    }

    private void initView() {
        seekBar = findViewById(R.id.seek_bar);
        playerPauseBtn = findViewById(R.id.play_or_pause);
        closeBtn = findViewById(R.id.close);
    }
}