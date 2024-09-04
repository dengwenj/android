package vip.dengwj.service.actions.impl;

import android.os.RemoteException;
import android.util.Log;

import vip.dengwj.service.NormalUserAction;

public class NormalUserAIDLActionImpl extends NormalUserAction.Stub {
    @Override
    public void saveMoney(float money) {
        Log.d("pumu", "存钱ww");
    }

    @Override
    public float getMoney() {
        Log.d("pumu", "取钱ww");
        return 0;
    }
}
