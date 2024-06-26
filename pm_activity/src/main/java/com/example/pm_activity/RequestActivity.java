package com.example.pm_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class RequestActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String tvRequest = "你最近怎么样";

    private ActivityResultLauncher<Intent> activityResultLauncher;

    private TextView response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        response = findViewById(R.id.tv_response);
        TextView textView = findViewById(R.id.tv_request);
        textView.setText(tvRequest);

        findViewById(R.id.btn_request).setOnClickListener(this);

        // 向上一个 Activity 返回数据时这样用
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                // 下一个 activity 返回结果了会回调这个方法
                this::onActivityResult
        );

        // 利用资源文件配置字符串
        String pm = getString(R.string.pumu);
        Log.i("pm", pm);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ResponseActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("requestContent", tvRequest);
        intent.putExtras(bundle);

        activityResultLauncher.launch(intent);
    }

    private void onActivityResult(ActivityResult result) {
        Intent data = result.getData();
        if (data == null || result.getResultCode() != Activity.RESULT_OK) {
            return;
        }

        Bundle extras = data.getExtras();
        if (extras == null) {
            return;
        }

        String tvResponse = extras.getString("responseContent");
        response.setText(tvResponse);
    }
}