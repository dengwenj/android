package vip.dengwj.network;

import static java.net.HttpURLConnection.HTTP_OK;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import vip.dengwj.network.domian.CommentItem;

public class OkhttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        findViewById(R.id.get_btn).setOnClickListener(this::handleGetRequest);

        findViewById(R.id.post_btn).setOnClickListener(this::handlePostRequest);

        findViewById(R.id.post_file_btn).setOnClickListener(this::handlePostFileRequest);
    }

    // 上传文件
    private void handlePostFileRequest(View view) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();

        File file = new File("/data/data/vip.dengwj.network/files/yuyuyuyu.png");
        MediaType mediaType = MediaType.parse("image/jpg");
        RequestBody fileBody = RequestBody.create(file, mediaType);
        RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("file", file.getName(), fileBody)
                .build();

        Request request = new Request.Builder()
                .url("http://10.0.2.2:9102/file/upload")
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("pumu", "失败了");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d("pumu", "res" + response.code());
                if (response.code() == HTTP_OK) {
                    assert response.body() != null;
                    Log.d("pumu", response.body().string());
                }
            }
        });
    }

    // post 请求
    private void handlePostRequest(View view) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.MILLISECONDS)
                .build();


        CommentItem commentItem = new CommentItem("123321", "杰克 and 肉丝");
        Gson gson = new Gson();
        String str = gson.toJson(commentItem);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(str, mediaType);
        Request request = new Request.Builder()
                .post(requestBody)
                .url("http://10.0.2.2:9102/post/comment")
                .build();

        Call task = okHttpClient.newCall(request);
        task.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("pumu", "失败了");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.code() == HTTP_OK) {
                    assert response.body() != null;
                    String data = response.body().string();
                    Log.d("pumu", "data -> " + data);
                }
            }
        });
    }

    // get 请求
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