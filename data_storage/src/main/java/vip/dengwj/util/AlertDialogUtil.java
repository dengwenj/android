package vip.dengwj.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import vip.dengwj.function.DialogCallbackClose;
import vip.dengwj.function.DialogCallbackOk;

public class AlertDialogUtil {
    public static void show(
            Context context,
            String title,
            String msg,
            String okText,
            String closeText,
            DialogCallbackOk callback,
            DialogCallbackClose callbackClose
    ) {
        if (callback == null) {
            callback = AlertDialogUtil::ok;
        }

        if (callbackClose == null) {
            callbackClose = AlertDialogUtil::close;
        }
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(okText, callback::ok)
                .setNegativeButton(closeText, callbackClose::close)
                .show();
    }

    private static void ok(DialogInterface dialogInterface, int i) {

    }

    private static void close(DialogInterface dialog, int which) {

    }
}
