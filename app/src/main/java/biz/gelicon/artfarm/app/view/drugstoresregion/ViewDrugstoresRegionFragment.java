package biz.gelicon.artfarm.app.view.drugstoresregion;

import com.google.android.material.tabs.TabLayout;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import biz.gelicon.artfarm.app.R;
import biz.gelicon.artfarm.app.view.adapter.base.PageAdapterBase;

/**
 * Фрагмент, хранящий в себе вкладки со списком аптек и картой
 */
@SuppressWarnings("WeakerAccess")
@EFragment(R.layout.view_drugstores_region_fragment)
public class ViewDrugstoresRegionFragment extends Fragment {

    @FragmentArg
    TypeMap type;

    /**
     * Варианты представления карты.
     */
    public enum TypeMap {
        /**
         * Простая карта.
         */
        TYPE_1,

        /**
         * Карта с подробной информацией.
         */
        TYPE_2,
    }

    void setupPages(@ViewById(R.id.drugstores_view_pager) ViewPager viewPager,
                    @ViewById(R.id.drugstores_tab_layout) TabLayout tabLayout) {
        PageAdapterBase adapter = new PageAdapterBase(getChildFragmentManager());

            adapter.addPage("Список", ViewDrugstoresListFragment_.builder().build());
            adapter.addPage("Карта", ViewDrugstoresMapFragment_.builder().build());


        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }
}
