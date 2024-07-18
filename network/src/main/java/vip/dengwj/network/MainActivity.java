package vip.dengwj.network;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vip.dengwj.network.domian.GetTextItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.test);

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
                        Map<String, List<String>> headerFields = connection.getHeaderFields();
                        Set<Map.Entry<String, List<String>>> entries = headerFields.entrySet();
                        for (Map.Entry<String, List<String>> entry : entries) {
                            Log.d("pumu", "entry.getValue() " + entry.getValue());
                        }
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String data = bufferedReader.readLine();

                        Gson gson = new Gson();
                        GetTextItem getTextItem = gson.fromJson(data, GetTextItem.class);
                        Log.d("pumu", "getTextItem ->" + getTextItem.toString());
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start());
    }
}