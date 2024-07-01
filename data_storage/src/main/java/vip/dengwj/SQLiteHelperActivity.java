package vip.dengwj;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.database.UserDBHelper;
import vip.dengwj.enity.User;
import vip.dengwj.util.ToastUtil;

public class SQLiteHelperActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name;
    private EditText age;
    private EditText height;
    private EditText weight;
    private CheckBox checkBox;
    private UserDBHelper userDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_helper);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        checkBox = findViewById(R.id.checkbox);
        findViewById(R.id.insert).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 获得数据库帮助器的实例
        userDBHelper = UserDBHelper.getInstance(this);
        // 打开数据库帮助器的读写连接
        userDBHelper.openReadLink();
        userDBHelper.openWriteLink();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 关闭数据库连接
        userDBHelper.closeLink();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.insert) {
            User user = new User(
                    name.getText().toString(),
                    Integer.parseInt(age.getText().toString()),
                    Long.parseLong(height.getText().toString()),
                    Float.parseFloat(weight.getText().toString()),
                    checkBox.isChecked()
            );
            long insert = userDBHelper.insert(user);

            if (insert > 0) {
                ToastUtil.show(this, "添加成功");
            }
        }
    }
}