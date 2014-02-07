
package com.mi.android.quickshoot;

import android.hardware.Camera;

public class CameraTorch {
    private Camera mCamera;

    CameraTorch() {
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
