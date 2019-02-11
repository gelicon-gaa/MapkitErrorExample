package biz.gelicon.artfarm.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import org.androidannotations.annotations.EReceiver;
import org.androidannotations.annotations.ReceiverAction;

import androidx.annotation.Nullable;
import biz.gelicon.artfarm.app.NetworkUtils;

/**
 * Created by vva2@gelicon.biz on 10.01.2019.
 */
@EReceiver
public class NetworkConnection extends BroadcastReceiver {

    @Nullable
    private static Observer observer;

    public interface Observer {

        void onNetworkAvailable();
    }

    public static void setObserver(@Nullable Observer observer) {
        if (observer != null && observer.equals(NetworkConnection.observer)) {
            return;
        }
        NetworkConnection.observer = observer;
    }

    public static void removeObserver() {
        NetworkConnection.observer = null;
    }

    @ReceiverAction(actions = {ConnectivityManager.CONNECTIVITY_ACTION})
    void switchNetworkState(Context context) {
        if (NetworkUtils.isConnected(context)) {
            if (observer != null) {
                observer.onNetworkAvailable();
            }
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
    }
}
