package vip.dengwj.broadcastDemo1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener((view) -> {
            Log.d("pumu", "demo1");
            // 发送广播
            Intent intent = new Intent("vip.dengwj.broadcastDemo1.TEST");
            sendBroadcast(intent);
            Toast.makeText(this, "发送广播", Toast.LENGTH_SHORT).show();
        });
    }
}
