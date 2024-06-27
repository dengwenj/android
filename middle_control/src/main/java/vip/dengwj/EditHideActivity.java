package vip.dengwj;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.Config.HideTextWatcher;
import vip.dengwj.util.HideInput;

public class EditHideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hide);

        EditText username = findViewById(R.id.phone);
        EditText psd = findViewById(R.id.password);
        username.addTextChangedListener(
                new HideTextWatcher(username, 11, EditHideActivity.this)
        );
        psd.addTextChangedListener(
                new HideTextWatcher(psd, 6, EditHideActivity.this)
        );
    }

    // public class HideTextWatcher implements TextWatcher {
    //     private EditText mView;
    //     private int maxLen;
    //
    //     public HideTextWatcher(EditText v, int maxLen) {
    //         this.mView = v;
    //         this.maxLen = maxLen;
    //     }
    //
    //     // 在文本改变之前触发
    //     @Override
    //     public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    //
    //     }
    //
    //     // 在文本改变过程中触发
    //     @Override
    //     public void onTextChanged(CharSequence s, int start, int before, int count) {
    //
    //     }
    //
    //     // 在文本改变之后触发
    //     @Override
    //     public void afterTextChanged(Editable s) {
    //         int len = s.toString().length();
    //
    //         // 输入的数字对上了，隐藏键盘
    //         if (len == maxLen) {
    //             HideInput.hideOneInputMethod(EditHideActivity.this, mView);
    //         }
    //     }
    // }
}