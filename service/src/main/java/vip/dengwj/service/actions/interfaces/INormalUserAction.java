package vip.dengwj.service.actions.interfaces;

public interface INormalUserAction {
    // 存钱
    void saveMoney(float money);

    // 取钱
    float getMoney();

    // 贷款
    float loanMoney();
}
