package vip.dengwj.custom_control.customView.loginPage;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import vip.dengwj.custom_control.R;

public class LoginKeyBoard extends LinearLayout {
    public LoginKeyBoard(Context context) {
        this(context, null);
    }

    public LoginKeyBoard(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoginKeyBoard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.num_key_pad, this);

        view.findViewById(R.id.number_0).setOnClickListener((v) -> {
            Log.d("pumu", "点击");
        });
    }
}
