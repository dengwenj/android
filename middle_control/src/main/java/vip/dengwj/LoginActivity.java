package vip.dengwj;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private TextView passwordLabel;
    private TextView forgetPassword;
    private CheckBox rememberPassword;
    private Button login;
    private EditText edittextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RadioGroup radioGroup = findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener(this);
        findViewById(R.id.password_login);
        findViewById(R.id.verify_code_login);

        findViewById(R.id.edittext_phone);
        passwordLabel = findViewById(R.id.password_label);
        edittextPassword = findViewById(R.id.edittext_password);
        forgetPassword = findViewById(R.id.forget_password);
        rememberPassword = findViewById(R.id.remember_password);
        login =findViewById(R.id.login);
    }

    // tab 切换
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // 密码登录
        if (checkedId == R.id.password_login) {
            passwordLabel.setText(getString(R.string.login_password));
            edittextPassword.setHint(getString(R.string.input_password));
            forgetPassword.setText(getString(R.string.forget_password));
            rememberPassword.setVisibility(View.VISIBLE);
        } else if (checkedId == R.id.verify_code_login) {
            // 验证码登录
            passwordLabel.setText(getString(R.string.verifycode));
            edittextPassword.setHint(getString(R.string.input_verifycode));
            forgetPassword.setText(getString(R.string.get_verifycode));
            rememberPassword.setVisibility(View.GONE);
        }
    }
}