package vip.dengwj.service;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.service.services.BankService;

public class BankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        // 显示意图绑定
        // Intent intent = new Intent(this, BankService.class);
        // bindService();
    }
}