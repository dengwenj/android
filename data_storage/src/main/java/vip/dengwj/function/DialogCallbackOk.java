package vip.dengwj.function;

import android.content.DialogInterface;

@FunctionalInterface
public interface DialogCallbackOk {
    void ok(DialogInterface dialog, int which);
}
