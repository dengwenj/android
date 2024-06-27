package vip.dengwj;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        findViewById(R.id.btn).setOnClickListener(this);
        textView = findViewById(R.id.tv);
    }

    @Override
    public void onClick(View v) {
        // 创建提醒对话框的建造器
        AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
        // 设置对话框的标题文本
        builder.setTitle("标题");
        // 设置对话框的内容文本
        builder.setMessage("内容");
        // 设置对话框的肯定按钮文本及其点击监听器
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // dialog.cancel();
                textView.setText("确定了");
            }
        });
        // 设置对话框的否定按钮文本及其点击监听器
        builder.setNegativeButton("取消", (dialog, which) -> {
            // dialog.cancel();
            textView.setText("取消了");
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}