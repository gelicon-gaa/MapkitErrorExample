package biz.gelicon.artfarm.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by vva2@gelicon.biz on 10.01.2019.
 */
public class NetworkUtils {

    /**
     * Проверяет подключение к интернету
     *
     * @param context Контекст
     * @return Возваращет true, если устройство подключено к интернету.
     */
    public static boolean isConnected(@NonNull Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        @Nullable
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isConnected() {
        Context context = App.getContext();
        return context != null && isConnected(context);
    }
}
