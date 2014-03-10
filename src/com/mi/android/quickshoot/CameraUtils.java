
package com.mi.android.quickshoot;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.view.KeyEvent;

public class CameraUtils {
    private static final String PACKAGE_CAMERA = "com.sonyericsson.android.camera";

    private static final String CLASS_CAMERA_ACTIVITY = PACKAGE_CAMERA + ".CameraActivity";

    public static Intent getLaunchIntent(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName(PACKAGE_CAMERA, CLASS_CAMERA_ACTIVITY);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        return intent;
    }

    public static Intent getQuickLaunchIntent(Context context) {
        Intent intent = new Intent(Intent.ACTION_CAMERA_BUTTON, null);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_CAMERA);
        intent.putExtra(Intent.EXTRA_KEY_EVENT, keyEvent);
        intent.putExtra(Intent.EXTRA_SUBJECT, "start");
        return intent;
    }

    public static Toach getToach() {
        return new Toach();
    }

    public static class Toach {
        private Camera mCamera;

        Toach() {
        }

        void start() {
            try {
                mCamera = Camera.open();
            } catch (RuntimeException e) {
                Logger.e("Failed to open Camera", e);
            }

            Camera.Parameters params = mCamera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(params);
            mCamera.startPreview();
        }

        void stop() {
            if (mCamera != null) {
                mCamera.release();
            }
        }
    }

}
