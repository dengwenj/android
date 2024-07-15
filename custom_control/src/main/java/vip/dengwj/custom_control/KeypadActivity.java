package vip.dengwj.custom_control;

import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.custom_control.util.SizeUtils;

public class KeypadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keypad);

        TextView textView = findViewById(R.id.test);

        GradientDrawable press = new GradientDrawable();
        press.setColor(getResources().getColor(R.color.itemPressColor));
        press.setCornerRadius(SizeUtils.dip2px(8));

        GradientDrawable normal = new GradientDrawable();
        normal.setColor(getResources().getColor(R.color.numberColor));
        normal.setCornerRadius(SizeUtils.dip2px(8));

        StateListDrawable listDrawable = new StateListDrawable();
        listDrawable.addState(new int[]{android.R.attr.state_pressed}, press);
        listDrawable.addState(new int[]{}, normal);

        textView.setBackground(listDrawable);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}