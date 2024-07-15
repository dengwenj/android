package vip.dengwj.custom_control;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.custom_control.util.SizeUtils;

public class SlideMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_menu);

        LinearLayout linearLayout = new LinearLayout(getApplicationContext());
        linearLayout.addView(createTextView("置顶", "#afafaf"));
        linearLayout.addView(createTextView("已读", "#6e52bb"));
        linearLayout.addView(createTextView("删除", "#ec694a"));

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                SizeUtils.dip2px(30)
        );
        addContentView(linearLayout, layoutParams);
    }

    private TextView createTextView(String title, String colorString) {
        TextView ele = new TextView(getApplicationContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        layoutParams.weight = 1;
        ele.setLayoutParams(layoutParams);
        ele.setGravity(Gravity.CENTER);
        ele.setText(title);
        int eleBgColor = Color.parseColor(colorString);
        ele.setBackgroundColor(eleBgColor);
        ele.setTextColor(getResources().getColor(R.color.white));

        return ele;
    }
}