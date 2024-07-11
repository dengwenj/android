package vip.dengwj.custom_control;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.custom_control.customView.loginPage.LoginKeyBoard;
import vip.dengwj.custom_control.customView.loginPage.LoginPageView;

public class LoginPageActivity extends AppCompatActivity implements LoginPageView.OnLoginListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        LoginPageView loginPageView = findViewById(R.id.login_page_view);

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

    }
}