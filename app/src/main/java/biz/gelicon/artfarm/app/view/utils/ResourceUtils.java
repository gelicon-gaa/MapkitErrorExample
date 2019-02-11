package biz.gelicon.artfarm.app.view.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.AnyRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleableRes;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

/**
 * Created by vva2@gelicon.biz on 23.01.2019.
 */
public final class ResourceUtils {

    @Nullable
    public static Drawable getVectorDrawableStyleable(TypedArray array, @StyleableRes int resId, @Nullable Resources.Theme theme) {
        Drawable drawable = null;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            int id = array.getResourceId(resId, 0);
            VectorDrawableCompat drawableCompat = VectorDrawableCompat.create(array.getResources(), id, theme);
            if (drawableCompat != null) {
                drawable = drawableCompat.mutate();
            }
        } else {
            drawable = array.getDrawable(resId);
        }
        return drawable;
    }

    @Nullable
    public static Drawable getVectorDrawableStyleable(TypedArray array, @StyleableRes int resId, int defValue, @Nullable Resources.Theme theme) {
        Drawable drawable = null;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            int id = array.getResourceId(resId, defValue);
            VectorDrawableCompat drawableCompat = VectorDrawableCompat.create(array.getResources(), id, theme);
            if (drawableCompat != null) {
                drawable = drawableCompat.mutate();
            }
        } else {
            drawable = array.getDrawable(resId);
        }
        return drawable;
    }

    @AnyRes
    public static int getResourceId(TypedArray array, @StyleableRes int resId, @AnyRes int defValue) {
        return array.getResourceId(resId, defValue);
    }

    @Nullable
    public static Drawable getVectorDrawable(@NonNull Context context, @DrawableRes int resId) {
        return ResourcesCompat.getDrawable(context.getResources(), resId, null);
    }

    @NonNull
    public static String getString(@NonNull Context context, @StringRes int resId) {
        return context.getString(resId);
    }

    public static int getColor(@NonNull Context context, @ColorRes int resId) {
        return ContextCompat.getColor(context, resId);
    }
}
