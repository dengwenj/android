package vip.dengwj.custom_control;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.custom_control.customView.InputNumberView;

public class MainActivity extends AppCompatActivity implements InputNumberView.OnNumberChangeListener {

    private static final String PUMU = "MainActivity";

    private InputNumberView inputNumberView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumberView = findViewById(R.id.input_number_view);
        inputNumberView.setOnNumberChangeListener(this);
    }

    @Override
    public void onChange(int number) {
        Log.d(PUMU, "number:" + number);
    }
}