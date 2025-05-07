package org.lineageos.settings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.display.DisplayManager;
import android.util.Log;
import android.view.Display;
import android.view.Display.HdrCapabilities;
import androidx.preference.PreferenceManager;

import org.lineageos.settings.thermal.ThermalUtils;
import org.lineageos.settings.refreshrate.RefreshUtils;
import org.lineageos.settings.utils.FileUtils;

public class BootCompletedReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = false;
    private static final String TAG = "XiaomiParts";

    private static final String DC_DIMMING_ENABLE_KEY = "dc_dimming_enable";
    private static final String DC_DIMMING_NODE = "/sys/devices/platform/soc/soc:qcom,dsi-display-primary/dimlayer_exposure";

    private static final String HTSR_ENABLE_KEY = "htsr_enable";
    private static final String HTSR_FILE = "/sys/devices/virtual/touch/touch_dev/bump_sample_rate";

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) return;
        if (DEBUG) Log.d(TAG, "Received intent: " + intent.getAction());

        switch (intent.getAction()) {
            case Intent.ACTION_LOCKED_BOOT_COMPLETED:
                onLockedBootCompleted(context);
                break;
            case Intent.ACTION_BOOT_COMPLETED:
                onBootCompleted(context);
                break;
        }
    }

    private static void onLockedBootCompleted(Context context) {
        // For future locked boot services
    }

    private static void onBootCompleted(Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        ThermalUtils.startService(context);
        RefreshUtils.startService(context);

        boolean HTSREnabled = sharedPrefs.getBoolean(HTSR_ENABLE_KEY, false);
        FileUtils.writeLine(HTSR_FILE, HTSREnabled ? "1" : "0");

        boolean dcDimmingEnabled = sharedPrefs.getBoolean(DC_DIMMING_ENABLE_KEY, false);
        FileUtils.writeLine(DC_DIMMING_NODE, dcDimmingEnabled ? "1" : "0");

        DisplayManager displayManager = context.getSystemService(DisplayManager.class);
        if (displayManager != null) {
            displayManager.overrideHdrTypes(Display.DEFAULT_DISPLAY, new int[]{
                    HdrCapabilities.HDR_TYPE_DOLBY_VISION,
                    HdrCapabilities.HDR_TYPE_HDR10,
                    HdrCapabilities.HDR_TYPE_HLG,
                    HdrCapabilities.HDR_TYPE_HDR10_PLUS
            });
        } else if (DEBUG) {
            Log.w(TAG, "DisplayManager not available, HDR override skipped");
        }
    }
}
