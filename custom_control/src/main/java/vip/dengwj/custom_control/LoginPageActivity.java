package vip.dengwj.custom_control;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.custom_control.customView.loginPage.LoginKeyBoard;
import vip.dengwj.custom_control.customView.loginPage.LoginPageView;

public class LoginPageActivity extends AppCompatActivity implements LoginPageView.OnLoginListener {
    private LoginPageView loginPageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loginPageView = findViewById(R.id.login_page_view);

        loginPageView.setOnLoginListener(this);
    }

    @Override
    public void onGetCodeClick(String phoneNum) {
        Log.d("pumu", "phoneNum -> " + phoneNum);
    }

    @Override
    public void onCheckClick() {

    }

    @Override
    public void onConfirmClick(String phoneNum, String code) {
        App.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginPageActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                loginPageView.onVerifyCodeError();
            }
        }, 2000);
    }
}