package vip.dengwj.function;

import android.content.DialogInterface;

@FunctionalInterface
public interface DialogCallbackClose {
    void close(DialogInterface dialog, int which);
}
