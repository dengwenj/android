package vip.dengwj.util;

import android.content.Context;

public class Utils {
    public static int dp2px(Context context, int dp) {
        // 获取当前手机的像素密度（1 dp 等于多少 px）
        float density = context.getResources().getDisplayMetrics().density;
        return (int) Math.ceil(density * dp);
    }
}
