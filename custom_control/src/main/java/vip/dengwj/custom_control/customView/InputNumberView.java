package vip.dengwj.custom_control.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import vip.dengwj.custom_control.R;

public class InputNumberView extends RelativeLayout {
    public InputNumberView(Context context) {
        // 统一在一个构造方法中处理
        this(context, null);
    }

    public InputNumberView(Context context, AttributeSet attrs) {
        // 统一在一个构造方法中处理
        this(context, attrs, 0);
    }

    public InputNumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 以下三种方式一样，把 view 添加到当前容器里
        // LayoutInflater.from(context).inflate(R.layout.input_number_view, this);

        // LayoutInflater.from(context).inflate(R.layout.input_number_view, this, true);

        View view = LayoutInflater.from(context).inflate(R.layout.input_number_view, this, false);
        addView(view);
    }
}
