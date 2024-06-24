package vip.dengwj;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.util.Utils;

public class PMWidthHeight extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pm_width_height);

        TextView code = findViewById(R.id.code);
        // 获取该控件的布局参数
        ViewGroup.LayoutParams layoutParams = code.getLayoutParams();
        // 修改布局参数中的宽度数值，注意默认 px 单位，需要把 dp 数值转成 px 数值
        layoutParams.width = Utils.dp2px(this, 300);
        code.setLayoutParams(layoutParams);
    }
}
