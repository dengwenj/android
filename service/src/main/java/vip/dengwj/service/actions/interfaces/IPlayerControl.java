package vip.dengwj.service.actions.interfaces;

public interface IPlayerControl {
    /**
     * 播放或暂停音乐
     */
    void playOrPause();

    /**
     * 停止播放
     */
    void stopPlay();

    /**
     * 设置播放进度
     */
    void seekTo(int seek);

    /**
     * 把 UI 的控制接口设置给逻辑层
     */
    void registerViewController(IPlayerViewControl playerViewControl);

    /**
     * 取消接口通知的注册
     */
    void unRegisterViewController();
}
