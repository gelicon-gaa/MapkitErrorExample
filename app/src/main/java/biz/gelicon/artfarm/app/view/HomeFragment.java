package biz.gelicon.artfarm.app.view;


import org.androidannotations.annotations.DataBound;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import biz.gelicon.artfarm.app.R;
import biz.gelicon.artfarm.app.view.utils.FragmentUtils;

@SuppressWarnings("WeakerAccess")
@DataBound
@EFragment(R.layout.view_home_fragment)
@OptionsMenu(R.menu.main_menu_type2)
public class HomeFragment extends Fragment {

    private void showAsDialog(@NonNull Fragment fragment, String title) {
        FragmentUtils.showAsDialog(getChildFragmentManager(), fragment, title);
    }
}
