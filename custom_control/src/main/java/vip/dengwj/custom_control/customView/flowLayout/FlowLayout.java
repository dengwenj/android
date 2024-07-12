package vip.dengwj.custom_control.customView.flowLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
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

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

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
