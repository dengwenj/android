package vip.dengwj;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

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
        findViewById(R.id.del).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);
        findViewById(R.id.query).setOnClickListener(this);
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
        // 增
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
        } else if (id == R.id.del) {
            // 删除
            long l = userDBHelper.deleteByName(name.getText().toString());
            if (l > 0) {
                ToastUtil.show(this, "删除成功");
            }
        } else if (id == R.id.update) {
            User user = new User(
                    name.getText().toString(),
                    Integer.parseInt(age.getText().toString()),
                    Long.parseLong(height.getText().toString()),
                    Float.parseFloat(weight.getText().toString()),
                    checkBox.isChecked()
            );
            long update = userDBHelper.update(user);
            if (update > 0) {
                ToastUtil.show(this, "更新成功");
            }
        } else {
            // 查询
            List<User> users = userDBHelper.queryAll();
            for (User user : users) {
                Log.d("user", String.valueOf(user));
            }
            List<User> users1 = userDBHelper.queryByName(name.getText().toString());
            for (User user : users1) {
                Log.d("user", String.valueOf(user));
            }
        }
    }
}