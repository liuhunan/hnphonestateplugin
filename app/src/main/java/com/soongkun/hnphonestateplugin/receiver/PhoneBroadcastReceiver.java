package com.soongkun.hnphonestateplugin.receiver;

/**
 * Created by hunan on 17-6-10.
 */

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.soongkun.hnphonestateplugin.utils.LogUtils;

public class PhoneBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "HNReceiver";
    private static boolean mIncomingFlag = false;
    private static String mIncomingNumber = null;

    public static final String OUTGOING = "outgoing";
    public static final String RINGING = "ringing";
    public static final String OFFHOOK = "offhook";
    public static final String IDEL = "idel";

    @Override
    public void onReceive(Context context, Intent intent) {
        // 如果是拨打电话
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            mIncomingFlag = false;
            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            LogUtils.log(context,mIncomingFlag,OUTGOING,phoneNumber);
        } else {
            // 如果是来电
            TelephonyManager tManager = (TelephonyManager) context
                    .getSystemService(Service.TELEPHONY_SERVICE);
            switch (tManager.getCallState()) {
                case TelephonyManager.CALL_STATE_RINGING:
                    mIncomingFlag = true;
                    mIncomingNumber = intent.getStringExtra("incoming_number");
                    LogUtils.log(context,mIncomingFlag,RINGING,mIncomingNumber);
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    if(mIncomingFlag){
                        LogUtils.log(context,true,OFFHOOK,mIncomingNumber);
                    }
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    LogUtils.log(context,mIncomingFlag,IDEL,null);
                    break;
            }
        }
    }

}