package vip.dengwj.service.actions.interfaces;

public interface IPlayerControl {
    /**
     * 播放音乐
     */
    void play();

    /**
     * 暂停播放
     */
    void pause();

    /**
     * 继续播放
     */
    void resume();

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
