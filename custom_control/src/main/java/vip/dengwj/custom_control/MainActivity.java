package vip.dengwj.custom_control;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.custom_control.customView.inputNumberView.InputNumberView;

public class MainActivity extends AppCompatActivity implements InputNumberView.OnNumberChangeListener {

    private static final String PUMU = "MainActivity";

    private InputNumberView inputNumberView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumberView = findViewById(R.id.input_number_view);
        inputNumberView.setOnNumberChangeListener(this);
        inputNumberView.setMDisabled(true);
    }

    @Override
    public void onChange(int number) {
        Log.d(PUMU, "number:" + number);
    }

    @Override
    public void onNumberMax(int number) {
        Log.d(PUMU, "最大值" + number);
    }

    @Override
    public void onNumberMin(int number) {
        Log.d(PUMU, "最小值" + number);
    }
}