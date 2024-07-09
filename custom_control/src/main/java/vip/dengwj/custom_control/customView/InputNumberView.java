package vip.dengwj.custom_control.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import vip.dengwj.custom_control.R;

public class InputNumberView extends RelativeLayout {
    private int currentNumber = 0;

    private EditText editText;

    private OnNumberChangeListener onNumberChangeListener;

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

        // 初始化视图
        initView(context);
    }

    private void initView(Context context) {
        // 以下三种方式一样，把 view 添加到当前容器里
        // LayoutInflater.from(context).inflate(R.layout.input_number_view, this);

        // LayoutInflater.from(context).inflate(R.layout.input_number_view, this, true);

        View view = LayoutInflater.from(context).inflate(R.layout.input_number_view, this, false);
        addView(view);
        // 减
        findViewById(R.id.dec).setOnClickListener(this::handleDec);
        // 值
        editText = findViewById(R.id.edit);
        updateNumber();
        // 加
        findViewById(R.id.add).setOnClickListener(this::handleAdd);
    }

    public int getNumber() {
        return currentNumber;
    }

    public void setNumber(int currentNumber) {
        this.currentNumber = currentNumber;
        editText.setText(String.valueOf(currentNumber));
    }

    public void updateNumber() {
        editText.setText(String.valueOf(currentNumber));

        if (onNumberChangeListener != null) {
            onNumberChangeListener.onChange(currentNumber);
        }
    }

    private void handleAdd(View view) {
        currentNumber++;
        updateNumber();
    }

    private void handleDec(View view) {
        currentNumber--;
        updateNumber();
    }

    // 外面调用的，里面的回调方法会在 val 改变时调用
    public void setOnNumberChangeListener(OnNumberChangeListener listener) {
        onNumberChangeListener = listener;
    }

    //  暴露接收给外面使用
    public interface OnNumberChangeListener {
        void onChange(int number);
    }
}
