package vip.dengwj.broadcastdemo2;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private OtherReceiver otherReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("pumu", "进了吗");

        otherReceiver = new OtherReceiver();
        IntentFilter filter = new IntentFilter("vip.dengwj.broadcastDemo1.TEST");
        registerReceiver(otherReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("pumu", "onStop");
        // unregisterReceiver(otherReceiver);
    }
}