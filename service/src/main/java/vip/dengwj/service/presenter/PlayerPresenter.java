package vip.dengwj.service.presenter;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.util.Log;

import java.io.IOException;

import vip.dengwj.service.actions.interfaces.IPlayerControl;
import vip.dengwj.service.actions.interfaces.IPlayerViewControl;

public class PlayerPresenter extends Binder implements IPlayerControl {
    private IPlayerViewControl playerViewControl;

    // 最开始是暂停
    private int currentState = PLAY_STATE_STOP;
    private MediaPlayer mediaPlayer;

    @Override
    public void playOrPause() {
        // 播放暂停
        if (currentState == PLAY_STATE_STOP) {
            // 创建播放器
            initPlayer();
            // 设置数据源
            try {
                // mediaPlayer.setDataSource("/sdcard/song.mp3");
                mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getPath() + "/song.mp3");
                mediaPlayer.prepare();
                mediaPlayer.start();
                currentState = PLAY_STATE_PLAY;
            } catch (IOException e) {
                Log.d("pumu", "message" + e.getMessage());
                throw new RuntimeException(e);
            }
        } else if (currentState == PLAY_STATE_PAUSE) {
            // 当前状态是暂停就播放
            mediaPlayer.start();
            currentState = PLAY_STATE_PLAY;
        } else if (currentState == PLAY_STATE_PLAY) {
            // 当前状态是播放就暂停
            mediaPlayer.pause();
            currentState = PLAY_STATE_PAUSE;
        }

        playerViewControl.onPlayerStateChange(currentState);
    }

    private void initPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    public void stopPlay() {
        if (mediaPlayer != null) {
            // 释放资源
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            currentState = PLAY_STATE_STOP;

            // 停止播放
            playerViewControl.onPlayerStateChange(PLAY_STATE_STOP);
        }
    }

    @Override
    public void seekTo(int seek) {
        playerViewControl.onSeekChange(seek);
    }

    @Override
    public void registerViewController(IPlayerViewControl call) {
        playerViewControl = call;
    }

    @Override
    public void unRegisterViewController() {
        playerViewControl = null;
    }
}
