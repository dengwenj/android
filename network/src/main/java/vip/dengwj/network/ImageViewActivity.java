package vip.dengwj.network;

import static java.net.HttpURLConnection.HTTP_OK;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageViewActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        findViewById(R.id.btn).setOnClickListener(this);

        imageView = findViewById(R.id.image_view);
    }

    @Override
    public void onClick(View v) {
        // 子线程，只是在这里测试下，一般不这样写
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(10000);
                    connection.setRequestMethod("GET");
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HTTP_OK) {
                        InputStream inputStream = connection.getInputStream();
                        // 转成 Bitmap
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        // 更新 UI，要在主线程
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}