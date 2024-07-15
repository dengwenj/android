package vip.dengwj.custom_control.customView.keypad;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

import vip.dengwj.custom_control.R;

public class KeypadView extends ViewGroup {
    private int numberColor;
    private float numberSize;
    private int itemPressColor;
    private int itemNormalColor;

    public KeypadView(Context context) {
        this(context, null);
    }

    public KeypadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeypadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 获取属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.KeypadView);
        numberColor = a.getColor(R.styleable.KeypadView_numberColor, getResources().getColor(R.color.numberColor));
        numberSize = a.getDimension(R.styleable.KeypadView_numberSize, 16f);
        itemPressColor = a.getColor(R.styleable.KeypadView_itemPressColor, getResources().getColor(R.color.itemPressColor));
        itemNormalColor = a.getColor(R.styleable.KeypadView_itemNormalColor, getResources().getColor(R.color.numberColor));

        a.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
