package com.soongkun.hnphonestateplugin.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by hunan on 17-6-10.
 */

public class PhoneStateService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        CreateInform();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //创建通知
    public void CreateInform() {

    }
}
