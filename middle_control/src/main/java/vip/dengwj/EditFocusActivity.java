package vip.dengwj;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditFocusActivity extends AppCompatActivity implements View.OnFocusChangeListener {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_focus);

        editText = findViewById(R.id.phone);

        EditText psd = findViewById(R.id.password);
        psd.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            String string = editText.getText().toString();
            if (string.isEmpty() || string.length() < 11) {
                Toast.makeText(this, "请输入11位的手机号", Toast.LENGTH_SHORT).show();
                editText.requestFocus();
            }
        }
    }
}