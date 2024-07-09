package vip.dengwj.custom_control.customView.loginPage;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import vip.dengwj.custom_control.R;

public class LoginKeyBoard extends LinearLayout implements View.OnClickListener {
    private int numKey;

    private OnKeyPressListener onKeyPressListener;

    public LoginKeyBoard(Context context) {
        this(context, null);
    }

    public LoginKeyBoard(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoginKeyBoard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.num_key_pad, this);

        findViewById(R.id.number_1).setOnClickListener(this);
        findViewById(R.id.number_2).setOnClickListener(this);
        findViewById(R.id.number_3).setOnClickListener(this);
        findViewById(R.id.number_4).setOnClickListener(this);
        findViewById(R.id.number_5).setOnClickListener(this);
        findViewById(R.id.number_6).setOnClickListener(this);
        findViewById(R.id.number_7).setOnClickListener(this);
        findViewById(R.id.number_8).setOnClickListener(this);
        findViewById(R.id.number_9).setOnClickListener(this);
        findViewById(R.id.number_0).setOnClickListener(this);
        findViewById(R.id.number_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        // 删除键
        if (id == R.id.number_back) {
            if (onKeyPressListener == null) return;
            onKeyPressListener.onBackPress();
        } else {
            // 数字键
            numKey = Integer.parseInt(((TextView) v).getText().toString());

            if (onKeyPressListener != null) {
                onKeyPressListener.onNumberPress(numKey);
            }
        }
    }

    public void setOnKeyPressListener(OnKeyPressListener listener) {
        onKeyPressListener = listener;
    }

    public interface OnKeyPressListener {
        void onNumberPress(int number);

        void onBackPress();
    }
}
