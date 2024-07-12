package vip.dengwj.custom_control.util;

import vip.dengwj.custom_control.App;

public class SizeUtils {
    public static int dip2px(float dpVal) {
        float scale = App.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (dpVal * scale + 0.5f);
    }
}
