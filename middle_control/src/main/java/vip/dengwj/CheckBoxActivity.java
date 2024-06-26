package vip.dengwj;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

public class CheckBoxActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private CheckBox my;
    private CheckBox d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        my = findViewById(R.id.myCheckBox);
        d = findViewById(R.id.defaultCheckBox);
        my.setOnCheckedChangeListener(this);
        d.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
           my.setText("我勾选了");
        } else {
            my.setText("我取消勾选了");
        }
    }
}