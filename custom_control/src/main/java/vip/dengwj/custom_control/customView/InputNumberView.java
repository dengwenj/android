package vip.dengwj.custom_control.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import vip.dengwj.custom_control.R;

public class InputNumberView extends RelativeLayout {
    private static final String PUMU = "InputNumberView";

    private int currentNumber = 0;
    private EditText editText;
    private OnNumberChangeListener onNumberChangeListener;
    private int mMax;
    private int mMin;
    private int mStep;
    private boolean mDisabled;
    private int mDefaultValue;
    private int mBtnBackground;
    private float mValueSize;

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
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InputNumberView);
        mMax = a.getInt(R.styleable.InputNumberView_max, 0);
        mMin = a.getInt(R.styleable.InputNumberView_min, 0);
        mStep = a.getInt(R.styleable.InputNumberView_step, 0);
        mDisabled = a.getBoolean(R.styleable.InputNumberView_disabled, false);
        mDefaultValue = a.getInt(R.styleable.InputNumberView_defaultValue, 0);
        mBtnBackground = a.getResourceId(R.styleable.InputNumberView_btnBackground, -1);
        mValueSize = a.getDimension(R.styleable.InputNumberView_valueSize, 0);
        Log.d(PUMU, "mMax:" + mMax);
        Log.d(PUMU, "mMin:" + mMin);
        Log.d(PUMU, "mStep:" + mStep);
        Log.d(PUMU, "mDisabled:" + mDisabled);
        Log.d(PUMU, "mDefaultValue:" + mDefaultValue);
        Log.d(PUMU, "mBtnBackground:" + mBtnBackground);
        Log.d(PUMU, "mValueSize:" + mValueSize);
        a.recycle();
    }

    private void initView(Context context) {
        // 以下三种方式一样，把 view 添加到当前容器里
        // LayoutInflater.from(context).inflate(R.layout.input_number_view, this);

        // LayoutInflater.from(context).inflate(R.layout.input_number_view, this, true);

        View view = LayoutInflater.from(context).inflate(R.layout.input_number_view, this, false);
        addView(view);
        // 减
        findViewById(R.id.dec).setOnClickListener(this::handleDec);
        // 值
        editText = findViewById(R.id.edit);
        updateNumber();
        // 加
        findViewById(R.id.add).setOnClickListener(this::handleAdd);
    }

    public int getNumber() {
        return currentNumber;
    }

    public void setNumber(int currentNumber) {
        this.currentNumber = currentNumber;
        editText.setText(String.valueOf(currentNumber));
    }

    public void updateNumber() {
        editText.setText(String.valueOf(currentNumber));

        if (onNumberChangeListener != null) {
            onNumberChangeListener.onChange(currentNumber);
        }
    }

    private void handleAdd(View view) {
        currentNumber++;
        updateNumber();
    }

    private void handleDec(View view) {
        currentNumber--;
        updateNumber();
    }

    // 外面调用的，里面的回调方法会在 val 改变时调用
    public void setOnNumberChangeListener(OnNumberChangeListener listener) {
        onNumberChangeListener = listener;
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
    }
}
