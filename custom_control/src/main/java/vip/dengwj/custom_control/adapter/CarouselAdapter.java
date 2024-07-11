package vip.dengwj.custom_control.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import vip.dengwj.custom_control.R;

public class CarouselAdapter extends PagerAdapter {
    private final List<Integer> list = new ArrayList<>();
    private OnImgClickListener onImgClickListener;


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    // 实例化指定位置的页面，并将其添加到容器中
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Integer id = list.get(position);
        Context context = container.getContext();

        View item = LayoutInflater.from(context).inflate(R.layout.carousel_item, container, false);
        ImageView img = item.findViewById(R.id.img);
        img.setImageResource(id);

        // 点击图片
        item.findViewById(R.id.img).setOnClickListener(v -> {
            if (onImgClickListener == null) return;
            onImgClickListener.onImgClick(v, list.get(position));
        });

        LinearLayout linearLayout = item.findViewById(R.id.dian);
        for (int i = 0; i < list.size(); i++) {
            TextView v = getTextView(position, context, i);
            linearLayout.addView(v);
        }

        container.addView(item);
        return item;
    }

    private static @NonNull TextView getTextView(int position, Context context, int i) {
        TextView v = new TextView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
        layoutParams.setMargins(16, 0, 0, 0);
        v.setLayoutParams(layoutParams);

        if (i == position) {
            Drawable drawable = AppCompatResources.getDrawable(context, R.drawable.shape_red_round);
            // 确认Drawable是可以修改的
            if (drawable instanceof GradientDrawable) {
                GradientDrawable gradientDrawable = (GradientDrawable) drawable;
                // 使用Color.parseColor()方法将十六进制颜色代码转换为int类型的颜色值
                // TODO 抽取属性
                int i1 = Color.parseColor("#5e85e8");
                // 更改默认颜色
                gradientDrawable.setColor(i1);
            }
            v.setBackground(drawable);
            // v.setBackgroundResource(R.drawable.shape_red_round);
        } else {
            v.setBackgroundResource(R.drawable.shape_white_round);
        }
        return v;
    }

    // 销毁时触发
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void updateData(List<Integer> list) {
        // 引用不能断
        this.list.clear();
        this.list.addAll(list);
    }

    public <T> void setOnImgClick(OnImgClickListener<T> listener) {
        onImgClickListener = listener;
    }

    public interface OnImgClickListener<T> {
        void onImgClick(View view, T item);
    }
}
