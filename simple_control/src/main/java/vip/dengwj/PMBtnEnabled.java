package vip.dengwj;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PMBtnEnabled extends AppCompatActivity implements View.OnClickListener {
    private Button btn;

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pm_btnenable);
        Button enabled = findViewById(R.id.enabled);
        Button disabled = findViewById(R.id.disabled);
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.tv);

        enabled.setOnClickListener(this);
        disabled.setOnClickListener(this);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.enabled) {
            btn.setEnabled(true);
        } else if (id == R.id.disabled) {
            btn.setEnabled(false);
        } else if (id == R.id.btn) {
            textView.setText("pm and ww");
        }
    }
}
