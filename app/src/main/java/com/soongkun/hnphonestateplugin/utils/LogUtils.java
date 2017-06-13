package com.soongkun.hnphonestateplugin.utils;

import android.content.Context;
import android.util.Log;

/**
 * Created by hunan on 17-6-10.
 */

public class LogUtils {

    private static final String TAG ="LHNan" ;

    public static void log(Context context, boolean incomingFlag, String state, String phoneNumber) {
        Log.d(TAG,"incomingFlag=>" + incomingFlag + ";state=>" + state + ";phoneNumber=>" + phoneNumber);
    }
}
