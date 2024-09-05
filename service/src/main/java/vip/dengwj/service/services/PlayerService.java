package vip.dengwj.service.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import vip.dengwj.service.presenter.PlayerPresenter;

public class PlayerService extends Service {

    private PlayerPresenter playerControl;

    @Override
    public void onCreate() {
        super.onCreate();
        playerControl = new PlayerPresenter();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return playerControl;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        playerControl = null;
    }
}
