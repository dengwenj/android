package vip.dengwj;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name;
    private EditText age;
    private EditText weight;
    private EditText height;
    private CheckBox checkBox;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        checkBox = findViewById(R.id.checkbox);
        findViewById(R.id.btn).setOnClickListener(this);

        // 应用程序的私有目录下面
        preferences = getSharedPreferences("test", Context.MODE_PRIVATE);

        reload();
    }

    private void reload() {
        name.setText(preferences.getString("name", ""));
        age.setText(String.valueOf(preferences.getInt("age", 0)));
        height.setText(String.valueOf(preferences.getFloat("height", 0f)));
        weight.setText(String.valueOf(preferences.getFloat("weight", 0f)));
        checkBox.setChecked(preferences.getBoolean("checkbox", false));
    }

    @Override
    public void onClick(View v) {
        String string = name.getText().toString();
        String string1 = age.getText().toString();
        String string2 = height.getText().toString();
        String string3 = weight.getText().toString();
        boolean checked = checkBox.isChecked();

        preferences.edit()
                .putString("name", string)
                .putInt("age", Integer.parseInt(string1))
                .putFloat("height", Float.parseFloat(string2))
                .putFloat("weight", Float.parseFloat(string3))
                .putBoolean("checkbox", checked)
                .apply();
    }
}