package vip.dengwj.custom_control.customView.loginPage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import vip.dengwj.custom_control.App;
import vip.dengwj.custom_control.R;

public class LoginPageView extends FrameLayout implements LoginKeyBoard.OnKeyPressListener {
    private OnLoginListener onLoginListener;
    private LoginKeyBoard loginKeyBoard;
    private EditText phoneEdT;
    private EditText codeEdt;
    private TextView getCodeTv;
    private TextView confirmTV;
    private CheckBox checkBox;
    private String phone = "";
    private String code = "";
    private boolean isChecked;
    private Handler handler;
    private int totalTime = 60;
    private int dTime = 1;
    private int interval = totalTime;

    public LoginPageView(@NonNull Context context) {
        this(context, null);
    }

    public LoginPageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoginPageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
        initEvent();
    }

    private void initEvent() {
        loginKeyBoard.setOnKeyPressListener(this);
        getCodeTv.setOnClickListener(this::handleGetCode);
        confirmTV.setOnClickListener(this::handleOk);
        checkBox.setOnCheckedChangeListener(this::handleCheckBox);
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.login_page_view, this);

        // 图标过大自己设置
        editIconSize(R.id.top_title, R.mipmap.user);
        editIconSize(R.id.phone, R.mipmap.phone);
        editIconSize(R.id.password, R.mipmap.password);
        loginKeyBoard  = findViewById(R.id.login_key_board);
        phoneEdT = findViewById(R.id.phone);
        codeEdt = findViewById(R.id.password);
        // 禁止显示键盘
        phoneEdT.setShowSoftInputOnFocus(false);
        codeEdt.setShowSoftInputOnFocus(false);
        // 聚焦焦点
        phoneEdT.requestFocus();
        getCodeTv = findViewById(R.id.get_code);
        confirmTV = findViewById(R.id.confirm);
        checkBox = findViewById(R.id.checkbox);
    }

    // 使用 handler 倒计时
    private void startCountDown() {
        handler = App.getHandler();
        // lambda 里面的 this 指向的是外层的 this
        handler.post(new Runnable() {
            @Override
            public void run() {
                interval -= dTime;
                // 大于 0 就一直跑
                if (interval > 0) {
                    // delayMillis 是毫秒，这个方法时每隔 1 秒钟执行
                    handler.postDelayed(this, dTime * 1000L);
                    getCodeTv.setText(String.valueOf(interval + " 秒"));
                    getCodeTv.setEnabled(false);
                } else {
                    interval = totalTime;
                    getCodeTv.setText("获取验证码");
                    getCodeTv.setEnabled(true);
                }
            }
        });
    }

    // 使用 CountDownTimer 倒计时
    private void beginCountDownTimer() {
        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                getCodeTv.setText(String.valueOf((millisUntilFinished / 1000) + " 秒"));
                getCodeTv.setEnabled(false);
            }

            @Override
            public void onFinish() {
                // 倒计时结束
                getCodeTv.setText("获取验证码");
                getCodeTv.setEnabled(true);
            }
        }.start();
    }

    private void editIconSize(int idRes, int drawableId) {
        TextView textView = findViewById(idRes);
        Drawable icon = getResources().getDrawable(drawableId);
        icon.setBounds(0, 0, 40, 40);
        textView.setCompoundDrawables(icon, null, null, null);
    }

    /**
     * 获取当前有焦点输入框
     */
    private EditText getFocusEdt() {
        View focus = findFocus();
        if (focus instanceof EditText) {
            return (EditText) focus;
        }
        return null;
    }

    public void setOnLoginListener(OnLoginListener listener) {
        onLoginListener = listener;
    }

    // 点击数字
    @Override
    public void onNumberPress(int number) {
        EditText focusEdt = getFocusEdt();
        if (focusEdt == null) return;

        int id = focusEdt.getId();
        Editable text = focusEdt.getText();
        // focusEdt.setText(text.toString() + number);
        int index = focusEdt.getSelectionEnd();
        text.insert(index, number + "");

        btnEnabled(id, text.toString());
    }

    // 点击返回
    @Override
    public void onBackPress() {
        EditText focusEdt = getFocusEdt();
        if (focusEdt == null) return;

        int id = focusEdt.getId();
        Editable val = focusEdt.getText();
        int index = focusEdt.getSelectionEnd();
        if (index != 0) {
            val.delete(index - 1, index);
        }

        btnEnabled(id, val.toString());
    }

    private void btnEnabled(int id, String val) {
        // 手机号
        if (id == R.id.phone) {
            phone = val;
            getCodeTv.setEnabled(val.length() == 11);
        } else if (id == R.id.password) {
            // 验证码
            code = val;
        }
        updateConfirm();
    }

    // 点击获取验证码
    private void handleGetCode(View v) {
        if (onLoginListener == null) return;

        onLoginListener.onGetCodeClick(phone);
        // startCountDown();
        beginCountDownTimer();
    }

    // 点击同意
    private void handleCheckBox(CompoundButton buttonView, boolean isC) {
        isChecked = isC;
        updateConfirm();
        if (onLoginListener == null) return;
        onLoginListener.onCheckClick();
    }

    // 点击登录
    private void handleOk(View v) {
        if (onLoginListener == null) {
            return;
        }

        onLoginListener.onConfirmClick(phone, code);
    }

    private void updateConfirm() {
        confirmTV.setEnabled(phone.length() == 11 && code.length() == 6 && isChecked);
    }

    public interface OnLoginListener {
        void onGetCodeClick(String phoneNum);

        void onCheckClick();

        void onConfirmClick(String phoneNum, String code);
    }
}
