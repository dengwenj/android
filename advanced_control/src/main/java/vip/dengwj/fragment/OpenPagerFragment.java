package vip.dengwj.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import vip.dengwj.R;
import vip.dengwj.util.ToastUtil;

public class OpenPagerFragment extends Fragment implements View.OnClickListener {
    public static OpenPagerFragment newInstance(int[] data, int position) {
        OpenPagerFragment fragment = new OpenPagerFragment();
        Bundle args = new Bundle();
        args.putInt("img", data[position]);
        args.putInt("count", data.length);
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = getContext();
        View view = inflater.inflate(R.layout.fragment_open_pager, container, false);

        Bundle arguments = getArguments();
        if (arguments == null) {
            return view;
        }

        int img = arguments.getInt("img");
        int len = arguments.getInt("count");
        int position = arguments.getInt("position");

        // View view = LayoutInflater.from(context).inflate(R.layout.open_pager_item, null);
        // 图片
        ImageView imgV = view.findViewById(R.id.img);
        imgV.setImageResource(img);

        // 单选按钮
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        for (int i = 0; i < len; i++) {
            RadioButton radioButton = new RadioButton(context);
            radioButton.setPadding(10, 10, 10, 10);
            radioButton.setLayoutParams(new RadioGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            radioButton.setChecked(position == i);
            radioGroup.addView(radioButton);
        }

        // 最后一页 button
        if (position == len - 1) {
            Button button = view.findViewById(R.id.btn);
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        Context context = getContext();
        ToastUtil.show(context, "欢迎");
    }
}