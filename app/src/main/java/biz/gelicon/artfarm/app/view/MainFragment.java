package biz.gelicon.artfarm.app.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import biz.gelicon.artfarm.app.R;
import biz.gelicon.artfarm.app.view.drugstoresregion.ViewDrugstoresRegionFragment_;
import biz.gelicon.artfarm.app.view.utils.FragmentUtils;

/**
 * Created by vva2@gelicon.biz on 12.12.2018.
 * <p>
 * Базовый фрагмент приложения
 */
@SuppressWarnings("WeakerAccess")
@EFragment(R.layout.view_main_fragment)
public class MainFragment extends Fragment {

    @InstanceState
    @FragmentArg
    int selectedNavItem = 0;

    private BottomNavigationView view;

    public static final String CURRENT_FRAGMENT_TAG = "R.layout.view_main_fragment.main_content";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getChildFragmentManager();
        Fragment currentView = fm.findFragmentByTag(CURRENT_FRAGMENT_TAG);
        if (currentView == null) {
            currentView = BottomNavHelper.getFragmentByPosition(selectedNavItem);
            fm.beginTransaction().add(R.id.main_content, currentView, CURRENT_FRAGMENT_TAG).commit();
        }
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navigationListener = menu -> {
        int position = BottomNavHelper.getPositionById(menu.getItemId());
        Fragment currentView = BottomNavHelper.getFragmentByPosition(position);

        FragmentUtils.showAsCurrent(getChildFragmentManager(), R.id.main_content, currentView, CURRENT_FRAGMENT_TAG);
        return true;
    };

    @ViewById(R.id.navigation)
    void setupNavigation(BottomNavigationView view) {
        this.view = view;
        // Отключение увеличения размера текста при выделении. В Api не реализовано
        for (int i = 0; i < view.getChildCount(); i++) {
            View child = view.getChildAt(i);
            if (child instanceof BottomNavigationMenuView) {
                View activeLabel = child.findViewById(R.id.largeLabel);
                if (activeLabel instanceof TextView) {
                    activeLabel.setPadding(0, 0, 0, 0);
                }
            }
        }
        view.setOnNavigationItemSelectedListener(navigationListener);
    }

    void simulateSwitching(int primaryPosition, int secondaryPosition) {
        view.setOnNavigationItemSelectedListener(null);
        view.setSelectedItemId(BottomNavHelper.getItemId(primaryPosition));
        FragmentUtils.showAsCurrent(getChildFragmentManager(), R.id.main_content, BottomNavHelper.getFragmentByPosition(primaryPosition, secondaryPosition), CURRENT_FRAGMENT_TAG);
        view.setOnNavigationItemSelectedListener(navigationListener);
    }

    static class BottomNavHelper {

        @IdRes
        static int getItemId(int primaryPosition) {
            switch (primaryPosition) {
                case 1:
                    return R.id.navigation_catalog;
                case 2:
                    return R.id.navigation_orders;
                case 3:
                    return R.id.navigation_discounts;
                case 4:
                    return R.id.navigation_profile;

                default:
                    return R.id.navigation_home;
            }
        }

        static int getPositionById(@IdRes int id) {
            switch (id) {
                case R.id.navigation_catalog:
                    return 1;
                case R.id.navigation_orders:
                    return 2;
                case R.id.navigation_discounts:
                    return 3;
                case R.id.navigation_profile:
                    return 4;

                default:
                    return 0;
            }
        }

        static Fragment getFragmentByPosition(int position) {
            switch (getItemId(position)) {
                case R.id.navigation_catalog:
                    return ViewDrugstoresRegionFragment_.builder().build();
                case R.id.navigation_orders:
                    return ViewDrugstoresRegionFragment_.builder().build();
                case R.id.navigation_discounts:
                    return ViewDrugstoresRegionFragment_.builder().build();
                case R.id.navigation_profile:
                    return ViewDrugstoresRegionFragment_.builder().build();
                default:
                    return ViewDrugstoresRegionFragment_.builder().build();
            }
        }

        static Fragment getFragmentByPosition(int position, int secondaryPosition) {
            switch (getItemId(position)) {
                case R.id.navigation_catalog:
                    return ViewDrugstoresRegionFragment_.builder().build();
                case R.id.navigation_orders:
                    return ViewDrugstoresRegionFragment_.builder().build();
                case R.id.navigation_discounts:
                    return ViewDrugstoresRegionFragment_.builder().build();
                case R.id.navigation_profile:
                    return ViewDrugstoresRegionFragment_.builder().build();

                default:
                    return ViewDrugstoresRegionFragment_.builder().build();
            }
        }
    }
}
