package vip.dengwj;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PMOnClickActivity extends AppCompatActivity implements View.OnClickListener {
    private static int count;

    private TextView textView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pm_onclick);
        TextView textView = findViewById(R.id.tv1);
        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new MyOnClickListener(textView));

        Button btn2 = findViewById(R.id.btn2);
        textView2 = findViewById(R.id.tv2);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn2) {
            textView2.setText("我已经点了第二行");
        }
    }

    static class MyOnClickListener implements View.OnClickListener {
        private final TextView textView;

        public MyOnClickListener(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            count++;
            textView.setText(textView.getText() + "" + count);
        }
    }
}
