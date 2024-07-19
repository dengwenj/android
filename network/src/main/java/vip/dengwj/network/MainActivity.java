package vip.dengwj.network;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import vip.dengwj.network.adapter.RecyclerViewAdapter;
import vip.dengwj.network.domian.GetTextItem;

public class MainActivity extends AppCompatActivity {
    private List<GetTextItem.DataBean> list = new ArrayList<>();
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.test);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(list);
        recyclerView.setAdapter(recyclerViewAdapter);

        // 测试
        textView.setOnClickListener(v -> new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://10.0.2.2:9102/get/text");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(10000);
                    connection.setRequestMethod("GET");
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String data = bufferedReader.readLine();

                        Gson gson = new Gson();
                        GetTextItem getTextItem = gson.fromJson(data, GetTextItem.class);
                        updateUI(getTextItem.getData());
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start());
    }

    public void updateUI(List<GetTextItem.DataBean> list) {
        // 更新 UI 只能到主线程更新，所以这里要切换到 ui 线程即主线程
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerViewAdapter.updateData(list);
            }
        });
    }
}