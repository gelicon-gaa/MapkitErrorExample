package biz.gelicon.artfarm.app.view.dialog;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import biz.gelicon.artfarm.app.R;

/**
 * Created by vva2@gelicon.biz on 20.12.2018.
 * <p>
 * Реализация полноэкранного диалога с возможностью сохранения состояния
 */
public class FullScreenDialog extends DialogFragment {

    private static final String NESTED_INFO_KEY = "NESTED_INFO_KEY";

    public static FullScreenDialog newInstance() {
        return new FullScreenDialog();
    }

    @Nullable
    private NestedInfo nested;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_Dialog_Fullscreen);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fullscreen_dialog_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View rootView = getView();
        if (rootView != null) {
            Toolbar toolbar = rootView.findViewById(R.id.fullscreen_toolbar);
            toolbar.hideOverflowMenu();
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
            toolbar.setNavigationOnClickListener(view -> {
                Fragment parentFragment = getParentFragment();
                if (parentFragment != null) {
                    parentFragment.getChildFragmentManager().popBackStack();
                }
            });

            if (savedInstanceState != null) {
                nested = savedInstanceState.getParcelable(NESTED_INFO_KEY);
            }

            if (nested != null) {
                toolbar.setTitle(nested.title);

                if (savedInstanceState == null) {
                    getChildFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fullscreen_content, nested.fragment)
                            .commit();
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(NESTED_INFO_KEY, nested);
    }

    public DialogFragment setNested(@Nullable NestedInfo nested) {
        this.nested = nested;
        return this;
    }

    public static class NestedInfo implements Parcelable {

        private Fragment fragment;
        private final String title;

        public NestedInfo(Fragment fragment, String title) {
            this.fragment = fragment;
            this.title = title;
        }

        NestedInfo(Parcel in) {
            title = in.readString();
        }

        public static final Creator<NestedInfo> CREATOR = new Creator<NestedInfo>() {
            @Override
            public NestedInfo createFromParcel(Parcel in) {
                return new NestedInfo(in);
            }

            @Override
            public NestedInfo[] newArray(int size) {
                return new NestedInfo[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(title);
        }
    }
}
