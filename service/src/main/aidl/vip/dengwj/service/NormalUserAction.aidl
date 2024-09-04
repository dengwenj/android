package vip.dengwj.service;

interface NormalUserAction {
    // 存钱
    void saveMoney(in float money);
    // 取钱
    float getMoney();
}