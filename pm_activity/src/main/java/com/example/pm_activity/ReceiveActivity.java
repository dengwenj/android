package com.example.pm_activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReceiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        TextView textView = findViewById(R.id.receive_tv);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        String string = extras.getString("name");
        String string1 = extras.getString("age");
        String string2 = extras.getString("tv");

        String format = String.format(string + "\n" + string1 + "\n" + string2);
        textView.setText(format);
    }
}