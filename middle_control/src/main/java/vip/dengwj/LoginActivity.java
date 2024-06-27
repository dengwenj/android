package vip.dengwj;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import vip.dengwj.Config.HideTextWatcher;

public class LoginActivity extends AppCompatActivity
        implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private static String password = "111111";

    private TextView passwordLabel;
    private TextView forgetPassword;
    private CheckBox rememberPassword;
    private EditText edittextPassword;
    private EditText edittextPhone;
    private RadioButton passwordLogin;
    private RadioButton verifyCodeLogin;
    private String verifyCode;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RadioGroup radioGroup = findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener(this);
        passwordLogin = findViewById(R.id.password_login);
        verifyCodeLogin = findViewById(R.id.verify_code_login);

        findViewById(R.id.edittext_phone);
        passwordLabel = findViewById(R.id.password_label);
        edittextPhone = findViewById(R.id.edittext_phone);
        edittextPassword = findViewById(R.id.edittext_password);
        forgetPassword = findViewById(R.id.forget_password);
        rememberPassword = findViewById(R.id.remember_password);
        findViewById(R.id.login).setOnClickListener(this);
        forgetPassword.setOnClickListener(this);

        // 键盘自动隐藏
        edittextPhone.addTextChangedListener(new HideTextWatcher(edittextPhone, 11, LoginActivity.this));
        edittextPassword.addTextChangedListener(new HideTextWatcher(edittextPassword, 6, LoginActivity.this));

        // 下一个页面返回上一个页面传递数据
        intentActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::onActivityResult
        );
    }

    /**
     * 忘记密码页切换回来的回调函数
     */
    private void onActivityResult(ActivityResult result) {
        Intent data = result.getData();
        if (data == null || result.getResultCode() != Activity.RESULT_OK) {
            return;
        }
        Bundle extras = data.getExtras();
        assert extras != null;
        password = extras.getString("newPassword");
    }

    // tab 切换
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // 密码登录
        if (checkedId == R.id.password_login) {
            passwordLabel.setText(getString(R.string.login_password));
            edittextPassword.setHint(getString(R.string.input_password));
            edittextPassword.setInputType(0x00000012);
            edittextPassword.setText(null);
            forgetPassword.setText(getString(R.string.forget_password));
            rememberPassword.setVisibility(View.VISIBLE);
        } else if (checkedId == R.id.verify_code_login) {
            // 验证码登录
            passwordLabel.setText(getString(R.string.verifycode));
            edittextPassword.setHint(getString(R.string.input_verifycode));
            edittextPassword.setInputType(InputType.TYPE_CLASS_NUMBER);
            edittextPassword.setText(null);
            forgetPassword.setText(getString(R.string.get_verifycode));
            rememberPassword.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        // 点击登录
        if (id == R.id.login) {
            handleLogin();
        } else if (id == R.id.forget_password) {
            // 忘记密码
            if (passwordLogin.isChecked()) {
                forgetPasswordMethod();
            } else {
                // 获取验证码
                getVerifyCode();
            }
        }
    }

    /**
     * 忘记密码
     */
    private void forgetPasswordMethod() {
        String phone = edittextPhone.getText().toString();
        if (phone.isEmpty() || phone.length() < 11) {
            toastMsg("请输入11位手机号");
            return;
        }

        // 跳转到忘记密码页
        Intent intent = new Intent(this, ForgetPasswordActivity.class);
        intent.putExtra("phone", phone);
        intentActivityResultLauncher.launch(intent);
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
                .setMessage("手机号为：" + edittextPhone.getText().toString() + "用户的验证码是" + verifyCode)
                .setPositiveButton("记住了", null)
                .show();
    }

    /**
     * 点击登录逻辑
     */
    private void handleLogin() {
        if (!rules()) {
            return;
        }

        // 说明验证通过
        String phone = edittextPhone.getText().toString();
        String psd = edittextPassword.getText().toString();
        // 说明是密码登录
        if (passwordLogin.isChecked()) {
            // 密码错误
            if (!password.equals(psd)) {
                toastMsg("密码错误");
                return;
            }
        } else {
            // 说明是验证码登录
            if (!psd.equals(verifyCode)) {
                toastMsg("验证码错误");
                return;
            }
        }

        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("手机号为:" + edittextPhone.getText().toString() + "的用户登录成功")
                .setPositiveButton("退出App", (dialog, which) -> finish())
                .setNegativeButton("我再看看", null)
                .show();
    }

    /**
     * 手机号密码验证码验证
     */
    private boolean rules() {
        if (edittextPhone.getText().toString().length() < 11) {
            toastMsg("请输入11位手机号");
            return false;
        }

        if (edittextPassword.getText().toString().length() < 6) {
            String str = passwordLogin.isChecked() ? "密码" : "验证码";
            toastMsg("请输入6位" + str);
            return false;
        }

        return true;
    }

    private void toastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}