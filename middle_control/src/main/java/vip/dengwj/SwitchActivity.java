package vip.dengwj;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SwitchActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private TextView textView;

    private TextView swTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        textView = findViewById(R.id.tv);
        swTv = findViewById(R.id.swios);

        SwitchCompat sw = findViewById(R.id.sw);
        sw.setOnCheckedChangeListener(this);

        CheckBox checkBox = findViewById(R.id.ckios);
        checkBox.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        // 原生的
        if (id == R.id.sw) {
            if (isChecked) {
                textView.setText("开");
            } else {
                textView.setText("关");
            }
        } else if (id == R.id.ckios) {
            // 仿 ios 的
            if (isChecked) {
                swTv.setText("开");
            } else {
                swTv.setText("关");
            }
        }

    }
}