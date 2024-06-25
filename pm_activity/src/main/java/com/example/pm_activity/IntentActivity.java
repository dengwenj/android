package com.example.pm_activity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class IntentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        findViewById(R.id.dial).setOnClickListener(this);
        findViewById(R.id.sms).setOnClickListener(this);
        findViewById(R.id.my).setOnClickListener(this);

        Intent intent = new Intent();
        // intent.setComponent(new ComponentName("", ""));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent();
        if (id == R.id.dial) {
            intent.setAction(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + "123456");
            intent.setData(uri);
            startActivity(intent);
        } else if (id == R.id.sms) {
            intent.setAction(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:" + "123456"));
            startActivity(intent);
        } else if (id ==R.id.my) {
            intent.setAction("android.intent.action.PUMU");
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            startActivity(intent);
        }
    }
}