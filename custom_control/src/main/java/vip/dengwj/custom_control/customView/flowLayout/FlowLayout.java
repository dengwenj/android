package vip.dengwj.custom_control.customView.flowLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vip.dengwj.custom_control.R;

public class FlowLayout extends ViewGroup {
    private int maxLine;
    private float horizontalMargin;
    private float verticalMargin;
    private int textMaxLength;
    private int textColor;
    private int borderColor;
    private float borderRadius;
    private List<String> list = new ArrayList<>();
    private List<List<View>> lineList = new ArrayList<>();

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        maxLine = a.getInt(R.styleable.FlowLayout_maxLine, 3);
        horizontalMargin = a.getDimension(R.styleable.FlowLayout_itemHorizontalMargin, 6);
        verticalMargin = a.getDimension(R.styleable.FlowLayout_itemVerticalMargin, 6);
        textMaxLength = a.getInt(R.styleable.FlowLayout_textMaxLength, 20);
        textColor = a.getColor(R.styleable.FlowLayout_textColor, Color.parseColor("#5e85e8"));
        borderColor = a.getColor(R.styleable.FlowLayout_borderColor, Color.parseColor("#333333"));
        borderRadius = a.getDimension(R.styleable.FlowLayout_borderRadius, 6);

        a.recycle();
    }

    /**
     * 测量
     * 主要就是一行可以放多少个根据内容定的，如果一行的全部宽度大于了父容器的宽度就换行
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int parentWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeightSize = MeasureSpec.getSize(heightMeasureSpec);

        int childCount = getChildCount();
        if (childCount == 0) return;

        // 每次进来清空
        lineList.clear();

        int childWidthSpec = MeasureSpec.makeMeasureSpec(parentWidthSize, MeasureSpec.AT_MOST);
        int childHeightSpec = MeasureSpec.makeMeasureSpec(parentHeightSize, MeasureSpec.AT_MOST);
        // 放入行
        List<View> line = new ArrayList<>();
        boolean isNewLine = false;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != VISIBLE) {
                continue;
            }

            // 测量孩子
            measureChild(child, childWidthSpec, childHeightSpec);

            if (line.isEmpty()) {
                line.add(child);
            } else {
                // 判断这行的宽度是否大于父容器的宽度,如果大于就换行，小于等于就添加到该行
                // view 是子元素
                int totalWidth = 0;
                for (View view : line) {
                    int childWidth = view.getMeasuredWidth();
                    totalWidth += childWidth;
                }
                // 加完之后再加上当前这个
                totalWidth += child.getMeasuredWidth();
                // 换行
                if (totalWidth > parentWidthSize) {
                    lineList.add(line);
                    line = new ArrayList<>();
                    // 下一行的第一个
                    line.add(child);
                    isNewLine = true;
                } else {
                    // 添加到该行
                    line.add(child);
                    isNewLine = false;
                }
            }
        }
        // 最后一行不满没有换行，要添加进去
        if (!isNewLine) {
            lineList.add(line);
        }
        // 测量父容器
        setMeasuredDimension(parentWidthSize, 500);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View childAt = getChildAt(0);
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = childAt.getMeasuredHeight();
        for (List<View> views : lineList) {
            for (View view : views) {
                int width = view.getMeasuredWidth();
                right += width;
                view.layout(left, top, right, bottom);
                left = right;
            }
            left = 0;
            right = 0;
            bottom += childAt.getMeasuredHeight();
            top += childAt.getMeasuredHeight();
        }
    }

    public void setData(List<String> data) {
        this.list.clear();
        this.list.addAll(data);

        setUpChildren();
    }

    private void setUpChildren() {
        // 先清除以前数据
        removeAllViews();

        for (String val : list) {
            TextView textView = new TextView(getContext());
            textView.setText(val);
            addView(textView);
        }
    }

    public int getMaxLine() {
        return maxLine;
    }

    public float getHorizontalMargin() {
        return horizontalMargin;
    }

    public int getTextMaxLength() {
        return textMaxLength;
    }

    public float getVerticalMargin() {
        return verticalMargin;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public float getBorderRadius() {
        return borderRadius;
    }

    public void setMaxLine(int maxLine) {
        this.maxLine = maxLine;
    }

    public void setVerticalMargin(float verticalMargin) {
        this.verticalMargin = verticalMargin;
    }

    public void setHorizontalMargin(float horizontalMargin) {
        this.horizontalMargin = horizontalMargin;
    }

    public void setTextMaxLength(int textMaxLength) {
        this.textMaxLength = textMaxLength;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public void setBorderRadius(float borderRadius) {
        this.borderRadius = borderRadius;
    }
}
