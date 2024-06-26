package com.example.pm_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResponseActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String tvResponse = "我最近很好，你呢？";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);

        TextView textView = findViewById(R.id.tv_request);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        String reqC = extras.getString("requestContent");
        textView.setText(reqC);

        TextView tv = findViewById(R.id.tv_response);
        tv.setText(tvResponse);

        findViewById(R.id.btn_response).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        Bundle bundle = new Bundle();
        bundle.putString("responseContent", tvResponse);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        // 关闭 activity 活动
        finish();
    }
}