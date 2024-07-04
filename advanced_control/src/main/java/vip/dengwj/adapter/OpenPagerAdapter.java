package vip.dengwj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import vip.dengwj.R;

public class OpenPagerAdapter extends PagerAdapter implements View.OnClickListener {
    private Context context;

    private BtnCallback btnCallback;

    private final List<View> viewList = new ArrayList<>();

    public OpenPagerAdapter(Context context, int[] imgs, BtnCallback btnCallback) {
        this.context = context;
        this.btnCallback = btnCallback;

        for (int w = 0; w < imgs.length;  w++) {
            View view = LayoutInflater.from(context).inflate(R.layout.open_pager_item, null);
            // 图片
            ImageView imgV = view.findViewById(R.id.img);
            imgV.setImageResource(imgs[w]);

            // 单选按钮
            RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
            for (int i = 0; i < imgs.length; i++) {
                RadioButton radioButton = new RadioButton(context);
                radioButton.setPadding(10, 10, 10, 10);
                radioButton.setLayoutParams(new RadioGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                radioButton.setChecked(w == i);
                radioGroup.addView(radioButton);
            }

            // 最后一页 button
            if (w == imgs.length - 1) {
                Button button = view.findViewById(R.id.btn);
                button.setVisibility(View.VISIBLE);
                button.setOnClickListener(this);
            }
            viewList.add(view);
        }
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = viewList.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public void onClick(View v) {
        if (btnCallback == null) return;

        btnCallback.onClick(v);
    }

    public interface BtnCallback {
        void onClick(View view);
    }
}
