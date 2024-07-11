package vip.dengwj.custom_control.customView.carousel;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import vip.dengwj.custom_control.App;
import vip.dengwj.custom_control.R;
import vip.dengwj.custom_control.adapter.CarouselAdapter;

public class Carousel extends LinearLayout {
    private CarouselAdapter carouselAdapter;
    private ViewPager viewPager;
    private List<Integer> list;
    private int cs = 0;

    private static final Handler handler = App.getHandler();

    public Carousel(Context context) {
        this(context, null);
    }

    public Carousel(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Carousel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.carousel, this, true);
        viewPager = findViewById(R.id.view_pager);
        carouselAdapter = new CarouselAdapter();
        viewPager.setAdapter(carouselAdapter);
    }

    public void setData(List<Integer> list) {
        this.list = list;
        carouselAdapter.updateData(list);
        // 更新 viewPage ui
        carouselAdapter.notifyDataSetChanged();

        handler.post(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(cs);
                // TODO 抽出属性
                handler.postDelayed(this, 3000);
                if (cs > Carousel.this.list.size() - 2) {
                    cs = 0;
                } else {
                    cs++;
                }
            }
        });
    }

    public <T> void setOnImgClick(CarouselAdapter.OnImgClickListener<T> listener) {
        carouselAdapter.setOnImgClick(listener);
    }
}
