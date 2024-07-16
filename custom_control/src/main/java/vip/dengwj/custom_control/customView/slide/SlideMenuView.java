package vip.dengwj.custom_control.customView.slide;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import vip.dengwj.custom_control.R;

public class SlideMenuView extends ViewGroup {
    private int func;
    private TextView contentView;
    private View editView;
    private OnActionClickListener onActionClickListener;

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

        contentView  = (TextView) getChildAt(0);
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
        editView = linearLayout;
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
        ele.setTag(title);
        ele.setOnClickListener((view) -> {
            String tag = (String) view.getTag();
            if ("置顶".equals(tag)) {
                onActionClickListener.onTopClick(view);
            } else if ("已读".equals(tag)) {
                onActionClickListener.onReadClick(view);
            } else if ("删除".equals(tag)) {
                onActionClickListener.onDeleteClick(view);
            }
        });

        return ele;
    }

    // 测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 测量内容
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 高度三种情况，如果指定大小，那获取到它的大小，直接测量，如果是包裹内容，atmost, 如果是 match_parent,就给他大小
        LayoutParams layoutParams = contentView.getLayoutParams();
        int height = layoutParams.height;
        int contentHeightMeasureSpec;
        if (height == LayoutParams.MATCH_PARENT) {
            contentHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
        } else if (height == LayoutParams.WRAP_CONTENT) {
            contentHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.AT_MOST);
        } else {
            // 指定了大小
            contentHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        // 第一个孩子测量
        contentView.measure(widthMeasureSpec, contentHeightMeasureSpec);

        // 第二个孩子测量,宽度是一半，高度是一样的
        int actionWidth = widthSize / 2;
        int actionWidthMeasureSpec = MeasureSpec.makeMeasureSpec(actionWidth, MeasureSpec.EXACTLY);
        editView.measure(actionWidthMeasureSpec, contentHeightMeasureSpec);

        // 测量自己
        // actionWidthMeasureSpec + widthMeasureSpec
        setMeasuredDimension(actionWidth + widthSize, contentHeightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int contentLeft = -540;
        int contentTop = 0;
        int contentRight = contentLeft + contentView.getMeasuredWidth();
        int contentBottom = contentTop + contentView.getMeasuredHeight();
        contentView.layout(contentLeft, contentTop, contentRight, contentBottom);

        // action 布局
        int actionRight = contentRight + editView.getMeasuredWidth();
        int actionBottom = contentTop + editView.getMeasuredHeight();
        editView.layout(contentRight, contentTop, actionRight, actionBottom);
    }

    public void setOnActionClickListener(OnActionClickListener listener) {
        onActionClickListener = listener;
    }

    public interface OnActionClickListener {
        void onReadClick(View view);

        void onTopClick(View view);

        void onDeleteClick(View view);
    }
}
