package vip.dengwj.custom_control.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import vip.dengwj.custom_control.R;

public class InputNumberView extends RelativeLayout {
    private static final String PUMU = "InputNumberView";

    private int currentNumber = 0;
    private EditText editText;
    private TextView addBtn;
    private TextView decBtn;
    private OnNumberChangeListener onNumberChangeListener;
    private int mMax;
    private boolean hasMMax;
    private int mMin;
    private boolean hasMMin;
    private int mStep;
    private boolean mDisabled;
    private int mDefaultValue;
    private int mBtnBackground;
    private float mValueSize;
    private boolean hasMValueSize;
    private boolean isInit = true;

    public InputNumberView(Context context) {
        // 统一在一个构造方法中处理
        this(context, null);
    }

    public InputNumberView(Context context, AttributeSet attrs) {
        // 统一在一个构造方法中处理
        this(context, attrs, 0);
    }

    public InputNumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 获取属性
        initAttrs(context, attrs);
        // 初始化视图
        initView(context);

        initAttrsToView();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InputNumberView);
        mMax = a.getInt(R.styleable.InputNumberView_max, 0);
        mMin = a.getInt(R.styleable.InputNumberView_min, 0);
        mStep = a.getInt(R.styleable.InputNumberView_step, 1);
        mDisabled = a.getBoolean(R.styleable.InputNumberView_disabled, false);
        mDefaultValue = a.getInt(R.styleable.InputNumberView_defaultValue, 0);
        mBtnBackground = a.getResourceId(R.styleable.InputNumberView_btnBackground, -1);
        mValueSize = a.getDimension(R.styleable.InputNumberView_valueSize, 0);

        hasMMax = a.hasValue(R.styleable.InputNumberView_max);
        hasMMin = a.hasValue(R.styleable.InputNumberView_min);
        hasMValueSize = a.hasValue(R.styleable.InputNumberView_valueSize);

        a.recycle();
    }

    private void initView(Context context) {
        // 以下三种方式一样，把 view 添加到当前容器里
        // LayoutInflater.from(context).inflate(R.layout.input_number_view, this);
        // LayoutInflater.from(context).inflate(R.layout.input_number_view, this, true);
        View view = LayoutInflater.from(context).inflate(R.layout.input_number_view, this, false);
        addView(view);

        // 减
        decBtn = findViewById(R.id.dec);
        decBtn.setOnClickListener(this::handleDec);
        // 值
        editText = findViewById(R.id.edit);
        // 加
        addBtn = findViewById(R.id.add);
        addBtn.setOnClickListener(this::handleAdd);
    }

    private void initAttrsToView() {
        decBtn.setEnabled(!mDisabled);
        addBtn.setEnabled(!mDisabled);
        currentNumber = mDefaultValue;
        updateNumber();
        // 背景
        if (mBtnBackground != -1) {
            decBtn.setBackgroundResource(mBtnBackground);
            addBtn.setBackgroundResource(mBtnBackground);
        }

        if (hasMMin && mMin == mDefaultValue) {
            decBtn.setEnabled(false);
        }

        if (hasMMax && mMax == mDefaultValue) {
            addBtn.setEnabled(false);
        }

        if (hasMValueSize) {
            editText.setTextSize(mValueSize);
        }
    }

    public void updateNumber() {
        editText.setText(String.valueOf(currentNumber));

        // 第一次
        if (isInit) {
            isInit = false;
            return;
        }

        if (onNumberChangeListener != null) {
            onNumberChangeListener.onChange(currentNumber);
        }
    }

    // 加
    private void handleAdd(View view) {
        currentNumber += mStep;
        decBtn.setEnabled(true);

        // 到达最大值
        if (hasMMax && currentNumber >= mMax) {
            bestValue(true, mMax);
            return;
        }
        updateNumber();
    }

    // 减
    private void handleDec(View view) {
        currentNumber -= mStep;
        addBtn.setEnabled(true);

        // 到达最小值
        if (hasMMin && currentNumber <= mMin) {
            bestValue(false, mMin);
            return;
        }
        updateNumber();
    }

    private void bestValue(boolean isMax, int val) {
        currentNumber = val;
        if (isMax) {
            addBtn.setEnabled(false);
            onNumberChangeListener.onNumberMax(currentNumber);
        } else {
            decBtn.setEnabled(false);
            onNumberChangeListener.onNumberMin(currentNumber);
        }
        editText.setText(String.valueOf(currentNumber));
    }

    // 外面调用的，里面的回调方法会在 val 改变时调用
    public void setOnNumberChangeListener(OnNumberChangeListener listener) {
        onNumberChangeListener = listener;
    }

    public int getNumber() {
        return currentNumber;
    }

    public void setNumber(int currentNumber) {
        this.currentNumber = currentNumber;
        editText.setText(String.valueOf(currentNumber));
    }

    public int getMMax() {
        return mMax;
    }

    public void setMMax(int mMax) {
        this.mMax = mMax;
    }

    public int getMMin() {
        return mMin;
    }

    public void setMMin(int mMin) {
        this.mMin = mMin;
    }

    public int getMStep() {
        return mStep;
    }

    public void setMStep(int mStep) {
        this.mStep = mStep;
    }

    public boolean getMDisabled() {
        return mDisabled;
    }

    public void setMDisabled(boolean mDisabled) {
        this.mDisabled = mDisabled;
    }

    public int getMDefaultValue() {
        return mDefaultValue;
    }

    public void setMDefaultValue(int mDefaultValue) {
        this.mDefaultValue = mDefaultValue;
    }

    public int getMBtnBackground() {
        return mBtnBackground;
    }

    public void setMBtnBackground(int mBtnBackground) {
        this.mBtnBackground = mBtnBackground;
    }

    public float getMValueSize() {
        return mValueSize;
    }

    public void setMValueSize(float mValueSize) {
        this.mValueSize = mValueSize;
    }

    //  暴露接收给外面使用
    public interface OnNumberChangeListener {
        void onChange(int number);

        void onNumberMax(int number);

        void onNumberMin(int number);
    }

    public static int dp2px(Context context, int dp) {
        // 获取当前手机的像素密度（1 dp 等于多少 px）
        float density = context.getResources().getDisplayMetrics().density;
        return (int) Math.ceil(density * dp);
    }
}