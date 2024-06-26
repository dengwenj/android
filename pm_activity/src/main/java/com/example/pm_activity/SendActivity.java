package com.example.pm_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SendActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        textView = findViewById(R.id.send_tv);

        findViewById(R.id.btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ReceiveActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("name", "朴睦");
        bundle.putString("age", "24");
        bundle.putString("tv", textView.getText().toString());
        intent.putExtras(bundle);

        startActivity(intent);
    }
}