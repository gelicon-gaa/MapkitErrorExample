package biz.gelicon.artfarm.app.view.drugstoresregion;

import android.content.Context;
import android.widget.ListView;

import org.androidannotations.annotations.DataBound;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import biz.gelicon.artfarm.app.R;

/**
 * Фрагмент, хранящий в себе список аптек в регионе
 */
@SuppressWarnings("WeakerAccess")
@EFragment(R.layout.drugstores_list_fragment)
public class ViewDrugstoresListFragment extends Fragment {

    @ViewById(R.id.view_drugstores_regions_list_root_widget)
    SwipeRefreshLayout root;

}
