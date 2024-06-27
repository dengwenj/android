package vip.dengwj.Config;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import vip.dengwj.util.HideInput;

public class HideTextWatcher implements TextWatcher {
    private final EditText mView;
    private final int maxLen;
    private final Activity obj;

    public HideTextWatcher(EditText v, int maxLen, Activity obj) {
        this.mView = v;
        this.maxLen = maxLen;
        this.obj = obj;
    }

    // 在文本改变之前触发
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    // 在文本改变过程中触发
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    // 在文本改变之后触发
    @Override
    public void afterTextChanged(Editable s) {
        int len = s.toString().length();

        // 输入的数字对上了，隐藏键盘
        if (len == maxLen) {
            HideInput.hideOneInputMethod(obj, mView);
        }
    }
}
