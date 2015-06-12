/**
 * Copyright (C) 2014 Samsung Electronics Co., Ltd. All rights reserved.
 *
 * Mobile Communication Division,
 * IT & Mobile Communications, Samsung Electronics Co., Ltd.
 *
 * This software and its documentation are confidential and proprietary
 * information of Samsung Electronics Co., Ltd.  No part of the software and
 * documents may be copied, reproduced, transmitted, translated, or reduced to
 * any electronic medium or machine-readable form without the prior written
 * consent of Samsung Electronics.
 *
 * Samsung Electronics makes no representations with respect to the contents,
 * and assumes no responsibility for any errors that might appear in the
 * software and documents. This publication and the contents hereof are subject
 * to change without notice.
 */

package com.samsung.android.app.plugintracker;

import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.shealth.Shealth;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class PluginTrackerProvider extends Application {

    private Context mContext;
    private static final String LOG_TAG = "PluginTracker";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Shealth shealth = new Shealth();
        try {
            shealth.initialize(mContext);
        } catch (SsdkUnsupportedException e) {
            int eType = e.getType();
            Log.d(LOG_TAG, "Samsung Digital Health Initialization failed. Error type : " + eType);
            if (eType == SsdkUnsupportedException.VENDOR_NOT_SUPPORTED) {
                // It is thrown if the device does not support the S Health SDK.
            } else if (eType == SsdkUnsupportedException.LIBRARY_NOT_INSTALLED) {
                // It is thrown if the library of the SDK is not found.
            } 
        } catch (Exception e1) {
            Log.d(LOG_TAG, "Samsung Digital Health Initialization failed.");
        }
    }
}
