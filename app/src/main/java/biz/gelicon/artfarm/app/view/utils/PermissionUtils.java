package biz.gelicon.artfarm.app.view.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by vva2@gelicon.biz on 30.01.2019.
 */
@SuppressWarnings("WeakerAccess")
public final class PermissionUtils {

    private PermissionUtils() {
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isGrantedPermission(@NonNull Context context, @NonNull String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermissionIfNeeded(@NonNull Activity activity, @NonNull String permission, int requestId) {
        if (!isGrantedPermission(activity, permission)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
                requestPermission(activity, permission, requestId);
            }
        }
    }

    public static void requestPermission(@NonNull Activity activity, @NonNull String permission, int requestId) {
        ActivityCompat.requestPermissions(activity, new String[] {permission}, requestId);
    }
}
