package vip.dengwj.custom_control.customView.loginPage;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
        getCodeTv = findViewById(R.id.get_code);
        confirmTV = findViewById(R.id.confirm);
        checkBox = findViewById(R.id.checkbox);
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
        val.delete(index - 1, index);

        btnEnabled(id, val.toString());
    }

    private void btnEnabled(int id, String val) {
        Log.d("pumu", val);
        // 手机号
        if (id == R.id.phone) {
            phone = val;
            getCodeTv.setEnabled(val.length() == 11);
            updateConfirm();
        } else if (id == R.id.password) {
            // 验证码
            code = val;
            updateConfirm();
        }
    }

    // 点击获取验证码
    private void handleGetCode(View v) {

    }

    // 点击同意
    private void handleCheckBox(CompoundButton buttonView, boolean isC) {
        isChecked = isC;
        updateConfirm();
    }

    // 点击登录
    private void handleOk(View v) {

    }

    private void updateConfirm() {
        confirmTV.setEnabled(phone.length() == 11 && code.length() == 6 && isChecked);
    }

    public interface OnLoginListener {
        void onGetCodeClick();

        void onCheckClick();

        void onConfirmClick();
    }
}
