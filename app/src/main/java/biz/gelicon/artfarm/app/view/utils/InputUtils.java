package biz.gelicon.artfarm.app.view.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;

/**
 * Created by vva2@gelicon.biz on 31.01.2019.
 * <p>
 * Утилита управления клавиатурой пользователя
 */
public final class InputUtils {

    private InputUtils() {
    }

    /**
     * Показывает клавиатуру
     */
    public static void showKeyboard(@NonNull View view) {
        Context context = view.getContext();
        if (context != null && view.requestFocus()) {
            view.postDelayed(()->{
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(view, 0);
            }, 50);
        }
    }

    /**
     * .
     * Скрывает клавиатуру
     */
    public static void hideKeyboard(@NonNull View view) {
        Context context = view.getContext();
        if (context != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
