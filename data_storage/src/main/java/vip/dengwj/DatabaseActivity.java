package vip.dengwj;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DatabaseActivity extends AppCompatActivity implements View.OnClickListener {
    private String mDatabaseName;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        textView = findViewById(R.id.tv);

        findViewById(R.id.create_database).setOnClickListener(this);
        findViewById(R.id.delete_database).setOnClickListener(this);

        // 生成一个测试数据库的完整路径
        mDatabaseName = getFilesDir() + "/test.db";
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.create_database) {
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(mDatabaseName, Activity.MODE_PRIVATE, null);
            textView.setText(String.format("数据库为：" + sqLiteDatabase.getPath()));
        } else {
            boolean b = deleteDatabase(mDatabaseName);
            textView.setText(String.format("数据库：" + mDatabaseName + (b ? "删除成功" : "删除失败")));
        }
    }
}