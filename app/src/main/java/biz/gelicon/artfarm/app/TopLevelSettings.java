package biz.gelicon.artfarm.app;

import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by vva2@gelicon.biz on 11.02.2019.
 */
@SharedPref(value = SharedPref.Scope.UNIQUE, name = "artfarm-top")
public interface TopLevelSettings {

    @DefaultBoolean(value = true)
    boolean firstLaunch();

    @DefaultString("http://10.15.4.102:8080/artfarm-mobile-api/")
    String url();
}
