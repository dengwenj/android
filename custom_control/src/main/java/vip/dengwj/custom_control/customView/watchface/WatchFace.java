package vip.dengwj.custom_control.customView.watchface;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import vip.dengwj.custom_control.R;

public class WatchFace extends View {
    private int secondColor;
    private int minColor;
    private int hourColor;
    private int scaleColor;
    private int faceBackground;
    private boolean scaleShow;

    public WatchFace(Context context) {
        this(context, null);
    }

    public WatchFace(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WatchFace(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WatchFace);
        secondColor = a.getColor(R.styleable.WatchFace_secondColor, getResources().getColor(R.color.secondDefaultColor));
        minColor = a.getColor(R.styleable.WatchFace_minColor, getResources().getColor(R.color.minDefaultColor));
        hourColor = a.getColor(R.styleable.WatchFace_hourColor, getResources().getColor(R.color.hourDefaultColor));
        scaleColor = a.getColor(R.styleable.WatchFace_scaleColor, getResources().getColor(R.color.scaleDefaultColor));
        faceBackground = a.getResourceId(R.styleable.WatchFace_faceBackground, -1);
        scaleShow = a.getBoolean(R.styleable.WatchFace_scaleShow, true);
        Log.d("pumu", "secondColor" + secondColor);
        a.recycle();
    }
}
