package vip.dengwj;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalTime;

public class PMButtonActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pm_button);

        textView = findViewById(R.id.tv);
    }

    @SuppressLint("SetTextI18n")
    public void handleClick(View view) {
        LocalTime now = LocalTime.now();
        textView.setText(now.toString() + ((Button) view).getText());
    }
}
