package vip.dengwj;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PMImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pm_image);
        ImageView imageView = findViewById(R.id.image_view);
        imageView.setImageResource(R.drawable.pmimage);
        // imageView.setScaleType();
    }
}
