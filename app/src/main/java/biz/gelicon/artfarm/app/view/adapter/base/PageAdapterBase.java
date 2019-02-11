package biz.gelicon.artfarm.app.view.adapter.base;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by vva2@gelicon.biz on 29.12.2018.
 */
@SuppressWarnings("SpellCheckingInspection")
public class PageAdapterBase extends FragmentPagerAdapter {

    protected List<Page> list;

    public PageAdapterBase(FragmentManager fm) {
        super(fm);
    }

    @Override
    @Nullable
    public Fragment getItem(int position) {
        if (list == null) {
            return null;
        }

        return list.get(position).fragment;
    }

    public void addPage(String title, Fragment fragment) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(new Page(title, fragment));
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (list == null) {
            return null;
        }
        return list.get(position).title;
    }

    public class Page {

        protected final CharSequence title;
        protected final Fragment fragment;

        Page(CharSequence title, Fragment fragment) {
            this.title = title;
            this.fragment = fragment;
        }
    }
}
