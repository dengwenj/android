package vip.dengwj;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import vip.dengwj.Config.HideTextWatcher;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editNewPassword;
    private EditText editTwoNewPassword;
    private EditText yzm;
    private String verifyCode;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        editNewPassword = findViewById(R.id.edit_new_password);
        editTwoNewPassword = findViewById(R.id.edit_two_new_password);
        yzm = findViewById(R.id.yzm);
        findViewById(R.id.get_yzm).setOnClickListener(this);
        findViewById(R.id.btn).setOnClickListener(this);

        // 键盘自动隐藏
        editNewPassword.addTextChangedListener(new HideTextWatcher(editNewPassword, 6, ForgetPasswordActivity.this));
        editTwoNewPassword.addTextChangedListener(new HideTextWatcher(editTwoNewPassword, 6, ForgetPasswordActivity.this));

        // 获取前页面传递的数据
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        phone = extras.getString("phone");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        // 点击确定
        if (id == R.id.btn) {
            handleOk();
        } else if (id == R.id.get_yzm) {
            // 获取验证码
            getVerifyCode();
        }
    }

    /**
     * 获取验证码
     */
    private void getVerifyCode() {
        // 随机 6 位
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int r = random.nextInt(10);
            sb.append(r);
        }
        verifyCode = sb.toString();

        new AlertDialog.Builder(this)
                .setTitle("验证码")
                .setMessage("手机号为：" + phone + "用户的验证码是" + verifyCode)
                .setPositiveButton("记住了", null)
                .show();
    }

    /**
     * 点击确定
     */
    private void handleOk() {
        String one = editNewPassword.getText().toString();
        String two = editTwoNewPassword.getText().toString();
        String code = yzm.getText().toString();

        if (one.isEmpty() || one.length() < 6) {
            toastMsg("请输入6位新密码");
            return;
        }

        // 密码不一致
        if (!one.equals(two)) {
            toastMsg("两次输入的密码不一致");
            return;
        }

        // 验证码判断
        if (code.isEmpty() || code.length() < 6) {
            toastMsg("请输入6位的验证码");
            return;
        }

        if (!verifyCode.equals(code)) {
            toastMsg("验证码错误");
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("newPassword", one);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void toastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}