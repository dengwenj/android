package vip.dengwj.custom_control.customView.slide;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import vip.dengwj.custom_control.R;

public class SlideMenuView extends ViewGroup {
    private int func;

    public SlideMenuView(Context context) {
        this(context, null);
    }

    public SlideMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlideMenuView);
        func = a.getInt(R.styleable.SlideMenuView_function, 0x30);

        a.recycle();
    }

    // 完成加载
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        Context context = getContext();

        int childCount = getChildCount();
        // 只能有一个子 view
        if (childCount > 1) {
            throw new IllegalArgumentException("只能写一个孩子");
        }

        // 根据属性，继续添加子 view
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        linearLayout.addView(createTextView("置顶", "#afafaf"));
        linearLayout.addView(createTextView("已读", "#6e52bb"));
        linearLayout.addView(createTextView("删除", "#ec694a"));

        addView(linearLayout);
        Log.d("pumu", "getChildCount -> " + getChildCount());
    }

    private TextView createTextView(String title, String colorString) {
        TextView ele = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        layoutParams.weight = 1;
        ele.setLayoutParams(layoutParams);
        ele.setGravity(Gravity.CENTER);
        ele.setText(title);
        ele.setTextColor(getResources().getColor(R.color.white));
        // colorString 十六进制
        ele.setBackgroundColor(Color.parseColor(colorString));

        return ele;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
