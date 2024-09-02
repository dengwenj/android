package vip.dengwj.service.actions.interfaces;

public interface IBankWorkAction extends INormalUserAction{
    // 查询用户信用
    void checkUserCredit();

    // 冻结用户账号
    void freezeUserAccount();
}
