package vip.dengwj.network;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        findViewById(R.id.get_btn).setOnClickListener(this::handleGetRequest);
    }

    private void handleGetRequest(View view) {
        // 要有客户端，类似我们要有一个浏览器
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();

        // 创建请求内容
        Request request = new Request.Builder()
                .get()
                .url("http://10.0.2.2:9102/get/text")
                .build();

        // 用 client 去创建请求任务
        Call task = okHttpClient.newCall(request);

        // 异步请求
        task.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 请求失败的回调
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                // 请求成功的回调
                assert response.body() != null;
                Log.d("pumu", response.body().string());
            }
        });
    }
}