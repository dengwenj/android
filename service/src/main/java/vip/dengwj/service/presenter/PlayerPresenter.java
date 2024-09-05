package vip.dengwj.service.presenter;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.util.Log;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import vip.dengwj.service.actions.interfaces.IPlayerControl;
import vip.dengwj.service.actions.interfaces.IPlayerViewControl;

public class PlayerPresenter extends Binder implements IPlayerControl {
    private IPlayerViewControl playerViewControl;

    // 最开始是暂停
    private int currentState = PLAY_STATE_STOP;
    private MediaPlayer mediaPlayer;
    private Timer timer;
    private TimerTask timerTask;

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
                startTimer();
            } catch (IOException e) {
                Log.d("pumu", "message" + e.getMessage());
                throw new RuntimeException(e);
            }
        } else if (currentState == PLAY_STATE_PAUSE) {
            // 当前状态是暂停就播放
            mediaPlayer.start();
            currentState = PLAY_STATE_PLAY;
            startTimer();
        } else if (currentState == PLAY_STATE_PLAY) {
            // 当前状态是播放就暂停
            mediaPlayer.pause();
            currentState = PLAY_STATE_PAUSE;
            stopTimer();
        }

        playerViewControl.onPlayerStateChange(currentState);
    }

    private void initPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    public void stopPlay() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            // 释放资源
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            currentState = PLAY_STATE_STOP;
            stopTimer();

            // 停止播放
            playerViewControl.onPlayerStateChange(PLAY_STATE_STOP);
        }
    }

    @Override
    public void seekTo(int seek) {
        if (mediaPlayer != null) {
            int tarSeek = (int) (seek * 1.0f / 100 * mediaPlayer.getDuration());
            mediaPlayer.seekTo(tarSeek);
        }
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

    /**
     * 开启一个 timerTask
     */
    private void startTimer() {
        Log.d("pumu", "startTimer。。。");
        if (timer == null) {
            timer = new Timer();
        }

        if (timerTask == null) {
            timerTask = new SeekTimeTask();
        }
        timer.schedule(timerTask, 0, 500);
    }

    private void stopTimer() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private class SeekTimeTask extends TimerTask {

        @Override
        public void run() {
            Log.d("pumu", "run...");
            // 获取当前的播放进度
            if (mediaPlayer != null && playerViewControl != null) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                int curPosition = (int) (currentPosition * 1.0f / mediaPlayer.getDuration() * 100);
                playerViewControl.onSeekChange(curPosition);
            }
        }
    }
}
