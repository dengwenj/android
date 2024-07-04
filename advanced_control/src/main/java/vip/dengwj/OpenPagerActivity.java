package vip.dengwj;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import vip.dengwj.adapter.OpenPagerAdapter;
import vip.dengwj.util.ToastUtil;

public class OpenPagerActivity extends AppCompatActivity implements OpenPagerAdapter.BtnCallback {
    // 声明引导页面的图片数组
    private int[] imgs = {
      R.drawable.guide_bg1, R.drawable.guide_bg2,
      R.drawable.guide_bg3, R.drawable.guide_bg4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_pager);

        ViewPager viewPager = findViewById(R.id.viewPager);
        OpenPagerAdapter openPagerAdapter = new OpenPagerAdapter(this, imgs, this);
        viewPager.setAdapter(openPagerAdapter);
    }

    @Override
    public void onClick(View view) {
        ToastUtil.show(this, "欢迎进入主界面");
    }
}