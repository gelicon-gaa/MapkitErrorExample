package biz.gelicon.artfarm.app.view.utils;

import android.content.Context;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import biz.gelicon.artfarm.app.R;
import biz.gelicon.artfarm.app.view.dialog.Dialog;
import biz.gelicon.artfarm.app.view.dialog.FullScreenDialog;

/**
 * Created by vva2@gelicon.biz on 25.12.2018.
 * <p>
 * Класс утилит для управления жизненным циклом {@link Fragment}.
 * <p>
 * Инкапсулирует основные действия с целью общего рефакторинга. (в случае ошибочной реализации создателя)
 */
public class FragmentUtils {

    /**
     * Отображает полноэкранный диалог
     *
     * @param manager Менеджер фрагментов
     * @param nested  Вложенный фрагмент полноэкранного диалога
     * @param title   Заголовок диалога
     */
    public static void showAsDialog(@NonNull FragmentManager manager, @NonNull Fragment nested, @Nullable String title) {
        //noinspection ConstantConditions
        if (nested != null && manager != null) {
            FragmentTransaction transaction = manager.beginTransaction().addToBackStack(null);
            FullScreenDialog.newInstance()
                    .setNested(new FullScreenDialog.NestedInfo(nested, title))
                    .show(transaction, null);
        }
    }

    /**
     * Отображает полноэкранный диалог с заголовком по умолчанию
     *
     * @param manager Менеджер фрагментов
     * @param nested  Вложенный фрагмент полноэкранного диалога
     */
    public static void showAsDialog(@NonNull FragmentManager manager, @NonNull Fragment nested) {
        //noinspection ConstantConditions
        if (nested != null && manager != null) {
            Context context = nested.getContext();
            String title = context != null ? context.getString(R.string.app_name) : "";
            showAsDialog(manager, nested, title);
        }
    }

    /**
     * Отображает диалог
     *
     * @param manager Менеджер фрагментов
     * @param dialog  Диалог
     * @param tag     Тэг идентификации
     */
    public static void showAsDialog(@NonNull FragmentManager manager, @NonNull Dialog dialog, @NonNull String tag) {
        //noinspection ConstantConditions
        if (dialog != null && manager != null) {
            Fragment current = manager.findFragmentByTag(tag);
            FragmentTransaction transaction = manager.beginTransaction().addToBackStack(null);
            dialog.show(transaction, tag);
        }
    }

    /**
     * Отображает вложенное представление в текущем
     *
     * @param manager         Менеджер фрагментов
     * @param containerViewId Уникальный идентификатор ресурса
     * @param fragment        Вложенное представление
     * @param tag             Тэг идентификации
     * @see FragmentManager
     */
    public static void showAsCurrent(@NonNull FragmentManager manager, @IdRes int containerViewId, @NonNull Fragment fragment, @NonNull String tag) {
        Fragment current = manager.findFragmentByTag(tag);
        FragmentTransaction transaction = manager.beginTransaction();
        if (current == null) {
            transaction.add(containerViewId, fragment, tag);
        } else {
            transaction.replace(containerViewId, fragment, tag);
        }
        transaction.commit();
    }
}
