package vip.dengwj.service.presenter;

import android.os.Binder;
import android.util.Log;

import vip.dengwj.service.actions.interfaces.IPlayerControl;
import vip.dengwj.service.actions.interfaces.IPlayerViewControl;

public class PlayerPresenter extends Binder implements IPlayerControl {
    private IPlayerViewControl playerViewControl;

    // 最开始是暂停
    private int playOrPause = PLAY_STATE_PAUSE;

    @Override
    public void playOrPause() {
        // 播放暂停
        if (playOrPause == PLAY_STATE_PLAY) {
            playOrPause = PLAY_STATE_PAUSE;
        } else if (playOrPause == PLAY_STATE_PAUSE) {
            playOrPause = PLAY_STATE_PLAY;
        }
        playerViewControl.onPlayerStateChange(playOrPause);
    }

    @Override
    public void stopPlay() {
        // 停止播放
        playerViewControl.onPlayerStateChange(PLAY_STATE_STOP);
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
