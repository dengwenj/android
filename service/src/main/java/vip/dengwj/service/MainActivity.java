package vip.dengwj.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.service.services.FirstService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleStartService(View view) {
        Intent intent = new Intent();
        intent.setClass(this, FirstService.class);
        startService(intent);
    }

    public void handleStopService(View view) {
        Intent intent = new Intent();
        intent.setClass(this, FirstService.class);
        stopService(intent);
    }
}