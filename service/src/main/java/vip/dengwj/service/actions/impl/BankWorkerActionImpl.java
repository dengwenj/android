package vip.dengwj.service.actions.impl;

import android.os.Binder;
import android.util.Log;

import vip.dengwj.service.actions.interfaces.IBankWorkAction;

public class BankWorkerActionImpl extends Binder implements IBankWorkAction {
    @Override
    public void checkUserCredit() {
        Log.d("pumu", "检查信用 -> 752");
    }

    @Override
    public void freezeUserAccount() {
        Log.d("pumu", "冻结用户账户");
    }

    @Override
    public void saveMoney(float money) {
        Log.d("pumu", "存钱 -> " + money);
    }

    @Override
    public float getMoney() {
        Log.d("pumu", "取钱 ->" + 10000.00);
        return 10000.00f;
    }

    @Override
    public float loanMoney() {
        Log.d("pumu", "贷款 -> " + 100000.00);
        return 100000.00f;
    }
}
