package vip.dengwj.custom_control.customView.keypad;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vip.dengwj.custom_control.R;
import vip.dengwj.custom_control.util.SizeUtils;

public class KeypadView extends ViewGroup {
    private static final String TAG = "pumu";
    // 行
    private static final int row = 4;
    // 列
    private static final int column = 3;

    private int numberColor;
    private float numberSize;
    private int itemPressColor;
    private int itemNormalColor;
    private int itemMargin = SizeUtils.dip2px(4);
    private OnItemClickListener onItemClickListener;

    public KeypadView(Context context) {
        this(context, null);
    }

    public KeypadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeypadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(context, attrs);
        setupItem();
    }

    private void setupItem() {
        removeAllViews();

        for (int i = 0; i < 11; i++) {
            TextView textView = new TextView(getContext());
            // 内容
            textView.setText(i == 10 ? "删除" : String.valueOf(i));
            // 大小
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, numberSize);
            // 居中
            textView.setGravity(Gravity.CENTER);
            // 字体
            textView.setTextColor(numberColor);
            // 设置背景
            textView.setBackground(setItemBg());
            // 标签
            textView.setTag(i == 10);

            textView.setOnClickListener((view) -> {
                Boolean isDelete = (Boolean) view.getTag();
                if (isDelete) {
                    onItemClickListener.onDeleteClick();
                } else {
                    onItemClickListener.onNumberClick(Integer.parseInt(((TextView) view).getText().toString()));
                }
            });

            addView(textView);
        }
    }

    private Drawable setItemBg() {
        // shape
        GradientDrawable press = new GradientDrawable();
        press.setColor(itemPressColor);
        press.setCornerRadius(SizeUtils.dip2px(8));

        GradientDrawable normal = new GradientDrawable();
        normal.setColor(itemNormalColor);
        normal.setCornerRadius(SizeUtils.dip2px(8));

        // selector
        StateListDrawable listDrawable = new StateListDrawable();
        listDrawable.addState(new int[]{android.R.attr.state_pressed}, press);
        listDrawable.addState(new int[]{}, normal);

        return listDrawable;
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        // 获取属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.KeypadView);
        numberColor = a.getColor(R.styleable.KeypadView_numberColor, getResources().getColor(R.color.white));
        numberSize = a.getDimensionPixelSize(R.styleable.KeypadView_numberSize, 16);
        itemPressColor = a.getColor(R.styleable.KeypadView_itemPressColor, getResources().getColor(R.color.itemPressColor));
        itemNormalColor = a.getColor(R.styleable.KeypadView_itemNormalColor, getResources().getColor(R.color.numberColor));
        itemMargin = a.getDimensionPixelSize(R.styleable.KeypadView_itemMargin, itemMargin);

        a.recycle();
    }

    // 测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int paddingLR = getPaddingLeft() + getPaddingRight();
        int paddingTB = getPaddingTop() + getPaddingBottom();

        // px
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 每行 3 个
        int itemWidth = (widthSize - itemMargin * (column + 1) - paddingLR) / column;
        // 每列四个
        int itemHeight = (heightSize - itemMargin * (row + 1) - paddingTB) / row;
        // EXACTLY exactly 固定
        int itemWidthSpec = MeasureSpec.makeMeasureSpec(itemWidth, MeasureSpec.EXACTLY);
        int itemHeightSpec = MeasureSpec.makeMeasureSpec(itemHeight, MeasureSpec.EXACTLY);
        // 最后一个
        int deleteWidthSpec = MeasureSpec.makeMeasureSpec(itemWidth * 2 + itemMargin, MeasureSpec.EXACTLY);

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Boolean isDelete = (Boolean) child.getTag();
            // 测量孩子
            child.measure(isDelete ? deleteWidthSpec : itemWidthSpec, itemHeightSpec);
        }

        // 测量自己
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    // 布局
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();

        int left = itemMargin + paddingLeft;
        int top;
        int right;
        int bottom;
        for (int i = 0; i < childCount; i++) {
            View item = getChildAt(i);
            // 第几行，第几列
            int rowIdx = i / column;
            int columnIdx = i % column;
            // 说明换行了
            if (columnIdx == 0) {
                left = itemMargin + paddingLeft;
            }
            top = rowIdx * item.getMeasuredHeight() + ((rowIdx + 1) * itemMargin) + paddingTop;
            right = left + item.getMeasuredWidth();
            bottom = top + item.getMeasuredHeight();
            item.layout(left, top, right, bottom);
            left += item.getMeasuredWidth() + itemMargin;
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onNumberClick(int num);

        void onDeleteClick();
    }
}
