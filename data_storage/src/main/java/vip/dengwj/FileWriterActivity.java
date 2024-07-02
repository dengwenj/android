package vip.dengwj;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import vip.dengwj.util.FileUtil;
import vip.dengwj.util.ToastUtil;

public class FileWriterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name;
    private EditText age;
    private EditText height;
    private EditText weight;
    private CheckBox checkBox;
    // 只有进程被杀死了，这内存才会释放
    private static String path;
    private TextView textView;

    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("拉了");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_writer);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        checkBox = findViewById(R.id.checkbox);
        findViewById(R.id.save).setOnClickListener(this);
        findViewById(R.id.read).setOnClickListener(this);
        textView = findViewById(R.id.tv);

        findViewById(R.id.global).setOnClickListener(this);
        app = MyApplication.getInstance();
        reload();
    }

    private void reload() {
        String n = app.globalInfo.get("name");
        if (n == null) {
            return;
        }

        String a = app.globalInfo.get("age");
        String h = app.globalInfo.get("height");
        String w = app.globalInfo.get("weight");
        String c = app.globalInfo.get("checked");
        name.setText(n);
        age.setText(a);
        height.setText(h);
        weight.setText(w);
        checkBox.setChecked("是".equals(c));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        String string = name.getText().toString();
        String string1 = age.getText().toString();
        String string2 = height.getText().toString();
        String string3 = weight.getText().toString();
        boolean checked = checkBox.isChecked();

        StringBuilder sb = new StringBuilder();
        sb.append("姓名=" + string)
                .append("\n年龄=" + string1)
                .append("\n身高=" + string2)
                .append("\n体重=" + string3)
                .append("\n已婚=" + checked);

        String fileName = System.currentTimeMillis() + ".txt";
        // 外部存储的私有空间
        String directory = Objects.requireNonNull(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)).toString();
        // 外部存储的公共空间
        directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        // 内部存储私有空间
        directory = getFilesDir().toString();
        if (id == R.id.save) {
            path = directory + File.separatorChar + fileName;
            Log.d("pumu", path);
            try {
                FileUtil.saveText(path, sb.toString());
                ToastUtil.show(this, "保存成功");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (id == R.id.read) {
            try {
                String s = FileUtil.openText(path);
                textView.setText(s);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (id == R.id.global) {
            app.globalInfo.put("name", string);
            app.globalInfo.put("age", string1);
            app.globalInfo.put("height", string2);
            app.globalInfo.put("weight", string3);
            app.globalInfo.put("checked", checked ? "是" : "否");
        }
    }
}