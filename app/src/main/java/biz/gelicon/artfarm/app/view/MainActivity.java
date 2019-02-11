package biz.gelicon.artfarm.app.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import org.androidannotations.annotations.EActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import biz.gelicon.artfarm.app.R;
import biz.gelicon.artfarm.app.receiver.NetworkConnection_;

/**
 * Created by vva2@gelicon.biz on 28.11.2018.
 * <p>
 * Главное окно приложения
 */
@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {


    private static final String CURRENT_FRAGMENT_TAG = "R.layout.activity_main.main_activity_root_widget";

    private NetworkConnection_ networkReceiver = new NetworkConnection_();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerReceiver(networkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        FragmentManager fm = getSupportFragmentManager();
        Fragment currentView = fm.findFragmentByTag(CURRENT_FRAGMENT_TAG);

        currentView = MainFragment_.builder().build();

        fm.beginTransaction().add(R.id.main_activity_root_widget, currentView, CURRENT_FRAGMENT_TAG).commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment currentView = fm.findFragmentByTag(CURRENT_FRAGMENT_TAG);
        if (currentView != null) {
            currentView.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void replaceWithMain() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.main_activity_root_widget, MainFragment_.builder().build(), CURRENT_FRAGMENT_TAG).commit();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(networkReceiver);
        super.onDestroy();
    }
}
