package vip.dengwj.network;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import vip.dengwj.network.domian.CommentItem;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        findViewById(R.id.btn).setOnClickListener(this::handleClick);
    }

    private void handleClick(View view) {
        new Thread(new Runnable() {
            // test
            OutputStream outputStream = null;
            InputStream inputStream = null;

            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://10.0.2.2:9102/post/comment");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(10000);
                    connection.setRequestProperty("Content-type", "application/json;charset=UTF-8");
                    CommentItem commentItem = new CommentItem("1233211234567", "你写的文章很好！");
                    Gson gson = new Gson();
                    String jsonStr = gson.toJson(commentItem);

                    // 连接
                    connection.connect();
                    // 把数据给服务
                    outputStream = connection.getOutputStream();
                    byte[] bytes = jsonStr.getBytes("UTF-8");
                    outputStream.write(bytes);
                    outputStream.flush();
                    Log.d("pumu", "outputStream -->" + outputStream);
                    // 获取结果
                    int responseCode = connection.getResponseCode();
                    Log.d("pumu", "responseCode -->" + responseCode);
                    if (responseCode == 200) {
                        inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        Log.d("pumu", "结果 -> " + bufferedReader.readLine());
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        outputStream.close();
                        inputStream.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}