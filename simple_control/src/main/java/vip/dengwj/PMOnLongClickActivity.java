package vip.dengwj;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PMOnLongClickActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pm_onlongclick);

        Button button = findViewById(R.id.btn);
        TextView textView = findViewById(R.id.tv);

        button.setOnLongClickListener((v) -> {
            // 时间冒泡，false 不冒，true 冒
            textView.setText("pm");
            return false;
        });
    }
}
