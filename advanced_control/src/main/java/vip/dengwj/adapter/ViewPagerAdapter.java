package vip.dengwj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import vip.dengwj.entity.GoodsInfo;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<GoodsInfo> goodsInfoList;

    // 声明一个图像视图列表
    private final List<ImageView> viewList = new ArrayList<>();

    public ViewPagerAdapter(Context context, List<GoodsInfo> goodsInfoList) {
        this.context = context;
        this.goodsInfoList = goodsInfoList;

        for (GoodsInfo goodsInfo : goodsInfoList) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            imageView.setImageResource(goodsInfo.pic);
            viewList.add(imageView);
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

    // 实例化指定位置的页面，并将其添加到容器中
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // 添加一个 view 到 container 中，而后返回一个跟这个 view 可以关联起来的对象
        // 这个对象能够是 view 本身，也能够是其余对象
        // 关键是在 isViewFromObject 可以将 view 和这个 Object 关联起来
        ImageView item = viewList.get(position);
        container.addView(item);
        return item;
    }

    // 销毁时触发
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewList.get(position));
    }
}
