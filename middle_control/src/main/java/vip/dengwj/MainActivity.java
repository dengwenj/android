package vip.dengwj;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.tv_view);
        findViewById(R.id.btn_rectangle).setOnClickListener(this);
        findViewById(R.id.btn_oval).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_rectangle) {
            view.setBackgroundResource(R.drawable.shape_rectangle);
        } else if (v.getId() == R.id.btn_oval) {
            view.setBackgroundResource(R.drawable.shape_oval);
        }
    }
}