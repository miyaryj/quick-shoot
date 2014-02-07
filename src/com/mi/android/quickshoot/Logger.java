/*
 * Copyright (c) 2013 Sony Mobile Communications AB.
 * All rights, including trade secret rights, reserved.
 */

package com.mi.android.quickshoot;

import android.util.Log;

/**
 * The logger class.
 */
public final class Logger {
    public static final boolean DEBUG = false;

    private static final String APP_TAG = "Podcast";

    // The stack position at which logging method of Logger class is called
    private static final int STACK_INDEX = 5;

    private static final String COLON = ":";

    private static final String DOT_SPACE = ". ";

    private Logger() {
    }

    private static StackTraceElement getCurrentStackElementAt(int idx) {
        return Thread.currentThread().getStackTrace()[idx];
    }

    private static String makeLogString(String msg) {
        StackTraceElement elem = getCurrentStackElementAt(STACK_INDEX);

        StringBuilder sb = new StringBuilder();
        sb.append(elem.getFileName());
        sb.append(COLON);
        sb.append(elem.getLineNumber());
        sb.append(DOT_SPACE);
        sb.append(msg);
        return sb.toString();
    }

    public static void i(String msg) {
        Log.i(APP_TAG, makeLogString(msg));
    }

    public static void d(String msg) {
        if (DEBUG) {
            Log.d(APP_TAG, makeLogString(msg));
        }
    }

    public static void v(String msg) {
        if (DEBUG) {
            Log.v(APP_TAG, makeLogString(msg));
        }
    }

    public static void w(String msg) {
        Log.w(APP_TAG, makeLogString(msg));
    }

    public static void w(String msg, Throwable e) {
        if (DEBUG) {
            Log.w(APP_TAG, makeLogString(msg), e);
        } else {
            Log.w(APP_TAG, makeLogString(msg));
            Log.w(APP_TAG, e.toString());
        }
    }

    public static void e(String msg) {
        Log.e(APP_TAG, makeLogString(msg));
    }

    public static void e(String msg, Throwable e) {
        if (DEBUG) {
            Log.e(APP_TAG, makeLogString(msg), e);
        } else {
            Log.e(APP_TAG, makeLogString(msg));
            Log.e(APP_TAG, e.toString());
        }
    }
}
