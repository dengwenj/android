package vip.dengwj.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import vip.dengwj.fragment.BillFragment;

public class BillListReceiver extends BroadcastReceiver {
    public static final String BILLLISTDATA = "billListData";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }

        String action = intent.getAction();
        // 获取广播传递的数据
        int year = intent.getIntExtra("year", 0);
        int month = intent.getIntExtra("month", 0);
        if (action.equals(BILLLISTDATA)) {
            // 回调
            new BillFragment().updateBillList(year, month);
        }
    }
}
