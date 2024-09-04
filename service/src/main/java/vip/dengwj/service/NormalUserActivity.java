package vip.dengwj.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.service.actions.interfaces.INormalUserAction;

public class NormalUserActivity extends AppCompatActivity {
    private MyServiceConnection myServiceConnection;
    // private static INormalUserAction normalUserAction;
    private static NormalUserAction normalUserAction;
    private boolean isBindService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user);

        doBindService();
    }

    // 绑定服务
    private void doBindService() {
        Intent intent = new Intent();
        intent.setAction("vip.dengwj.ACTION_NORMAL_USER");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage("vip.dengwj.service");
        myServiceConnection = new MyServiceConnection();
        isBindService = bindService(intent, myServiceConnection, BIND_AUTO_CREATE);
    }

    private static class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // normalUserAction = (INormalUserAction) service;
            normalUserAction = NormalUserAction.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    // 存钱
    public void handleSaveMoneyClick(View view) throws RemoteException {
        normalUserAction.saveMoney(100000);
    }

    // 取钱
    public void handleGetMoneyClick(View view) throws RemoteException {
        normalUserAction.getMoney();
    }

    // 贷款
    public void handleLoanMoneyClick(View view) {
        // normalUserAction.loanMoney();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (isBindService && myServiceConnection != null) {
            unbindService(myServiceConnection);
            myServiceConnection = null;
            isBindService = false;
        }
    }
}