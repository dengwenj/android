package vip.dengwj;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PMColorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pm_color);

        TextView java_set = findViewById(R.id.java_set);
        // ff 代表显示，00 代表透明
        // java 代码里透明度默认是透明
        java_set.setTextColor(Color.GREEN);
        java_set.setTextColor(0xff63519f);

        TextView java_set2 = findViewById(R.id.java_set2);
        java_set2.setTextColor(Color.GREEN);

        TextView bgc = findViewById(R.id.bgc);
        bgc.setBackgroundColor(Color.BLUE);

        TextView bgc2 = findViewById(R.id.bgc2);
        bgc2.setBackgroundResource(R.color.pm_bgc);
    }
}
