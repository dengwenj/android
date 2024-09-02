package vip.dengwj.service.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import vip.dengwj.service.actions.impl.BankBossActionImpl;
import vip.dengwj.service.actions.impl.BankWorkerActionImpl;
import vip.dengwj.service.actions.impl.NormalUserActionImpl;

public class BankService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String actionName = intent.getAction();
        Log.d("pumu", "actionName -> " + actionName);
        if (!TextUtils.isEmpty(actionName)) {
            // 根据意图动作返回接口实现
            if ("vip.dengwj.ACTION_NORMAL_USER".equals(actionName)) {
                return new NormalUserActionImpl();
            } else if ("vip.dengwj.ACTION_BANK_WORKER".equals(actionName)) {
                return new BankWorkerActionImpl();
            } else if ("vip.dengwj.ACTION_BANK_BOSS".equals(actionName)) {
                return new BankBossActionImpl();
            }
        }
        return null;
    }
}
