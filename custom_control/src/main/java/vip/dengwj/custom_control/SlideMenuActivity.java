package vip.dengwj.custom_control;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.custom_control.customView.slide.SlideMenuView;

public class SlideMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_menu);

        SlideMenuView slideMenuView = findViewById(R.id.slide_menu);

        slideMenuView.setOnActionClickListener(new SlideMenuView.OnActionClickListener() {
            @Override
            public void onReadClick(View view) {
                Log.d("pumu", "onReadClick ->" + view.getTag());
            }

            @Override
            public void onTopClick(View view) {
                Log.d("pumu", "onTopClick ->" + view.getTag());

            }

            @Override
            public void onDeleteClick(View view) {
                Log.d("pumu", "onDeleteClick ->" + view.getTag());

            }
        });

        slideMenuView.setOnOpenOrCloseListener(isOpen -> {
            if (isOpen) {
                Log.d("pumu", "打开" + isOpen);
            } else {
                Log.d("pumu", "关闭" + isOpen);
            }
            return null;
        });
    }
}