package vip.dengwj.custom_control;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.custom_control.customView.keypad.KeypadView;

public class KeypadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keypad);

        KeypadView keypadView = findViewById(R.id.key_pad);

        keypadView.setOnItemClickListener(new KeypadView.OnItemClickListener() {
            @Override
            public void onNumberClick(int num) {
                Log.d("pumu", "num ->" + num);
            }

            @Override
            public void onDeleteClick() {
                Log.d("pumu", "back,back");
            }
        });
    }
}