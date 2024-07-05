package vip.dengwj.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vip.dengwj.R;
import vip.dengwj.entity.GoodsInfo;

public class ViewPagerFragment extends Fragment {
    public static ViewPagerFragment newInstance(GoodsInfo goodsInfo) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        // 打包起来
        Bundle args = new Bundle();
        args.putInt("img", goodsInfo.pic);
        args.putString("desc", goodsInfo.description);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            ImageView img = view.findViewById(R.id.img);
            img.setImageResource(arguments.getInt("img", R.drawable.huawei));
            TextView desc = view.findViewById(R.id.desc);
            desc.setText(arguments.getString("desc", ""));
        }

        return view;
    }
}