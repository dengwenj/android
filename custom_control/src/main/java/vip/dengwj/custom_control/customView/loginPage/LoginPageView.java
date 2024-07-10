package vip.dengwj.custom_control.customView.loginPage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import vip.dengwj.custom_control.R;

public class LoginPageView extends FrameLayout {
    public LoginPageView(@NonNull Context context) {
        this(context, null);
    }

    public LoginPageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoginPageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.login_page_view, this);

        // 图标过大自己设置
        editIconSize(R.id.top_title, R.mipmap.user);
        editIconSize(R.id.phone, R.mipmap.phone);
        editIconSize(R.id.password, R.mipmap.password);
    }

    private void editIconSize(int idRes, int drawableId) {
        TextView textView = findViewById(idRes);
        Drawable icon = getResources().getDrawable(drawableId);
        icon.setBounds(0, 0, 40, 40);
        textView.setCompoundDrawables(icon, null, null, null);
    }
}
