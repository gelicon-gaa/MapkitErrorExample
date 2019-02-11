package biz.gelicon.artfarm.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.androidannotations.annotations.EApplication;

import androidx.annotation.Nullable;

/**
 * Created by vva2@gelicon.biz on 28.11.2018.
 */
@SuppressLint("Registered")
@EApplication
public class App extends Application {

    @Nullable
    private static Application application;

    @Nullable
    public static Context getContext() {
        Application application = App.application;
        if (application == null) {
            return null;
        } else {
            return application.getApplicationContext();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        App.application = this;
        Fresco.initialize(this);
    }
}
