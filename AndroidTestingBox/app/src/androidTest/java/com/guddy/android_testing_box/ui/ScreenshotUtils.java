package com.guddy.android_testing_box.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.View;

import java.io.ByteArrayOutputStream;

public final class ScreenshotUtils {

    private ScreenshotUtils() {}

    public static byte[] takeScreenshot(@NonNull final Activity poActivity) {
        final View loView = poActivity.getWindow().getDecorView().getRootView();
        loView.setDrawingCacheEnabled(true);
        final Bitmap loBitmap = Bitmap.createBitmap(loView.getDrawingCache());
        loView.setDrawingCacheEnabled(false);
        final ByteArrayOutputStream loBaos = new ByteArrayOutputStream();
        loBitmap.compress(Bitmap.CompressFormat.PNG, 100, loBaos);
        return loBaos.toByteArray();
    }
}
