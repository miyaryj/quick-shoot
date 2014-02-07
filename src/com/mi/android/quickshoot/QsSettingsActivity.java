
package com.mi.android.quickshoot;

import android.app.Activity;
import android.os.Bundle;

public class QsSettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(android.R.id.content, new QsSettingsFragment()).commit();
        }
    }

}
