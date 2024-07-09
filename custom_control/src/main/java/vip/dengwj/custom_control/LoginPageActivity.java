package vip.dengwj.custom_control;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.custom_control.customView.loginPage.LoginKeyBoard;

public class LoginPageActivity extends AppCompatActivity
        implements LoginKeyBoard.OnKeyPressListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        LoginKeyBoard loginKeyBoard = findViewById(R.id.login_key_board);
        loginKeyBoard.setOnKeyPressListener(this);
    }

    @Override
    public void onNumberPress(int number) {
        Log.d("pumu", "number:" + number);
    }

    @Override
    public void onBackPress() {
        Log.d("pumu", "back");
    }
}