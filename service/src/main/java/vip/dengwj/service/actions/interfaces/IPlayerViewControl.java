package vip.dengwj.service.actions.interfaces;

public interface IPlayerViewControl {
    /**
     * 播放状态
     * @param state
     */
    void onPlayerStateChange(int state);

    /**
     * 进度条的改变
     */
    void onSeekChange(int seek);
}
