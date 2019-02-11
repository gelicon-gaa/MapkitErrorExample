package biz.gelicon.artfarm.app.view.dialog;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import biz.gelicon.artfarm.app.R;

/**
 * Класс для создания диалогового окна
 * <p>
 * Created by vva2@gelicon.biz
 */
public class Dialog extends DialogFragment {

    private static final String TITLE_DIALOG_KEY = "TITLE_DIALOG_KEY";
    private static final String MESSAGE_DIALOG_KEY = "MESSAGE_DIALOG_KEY";
    private static final String CANCELABLE_DIALOG_KEY = "CANCELABLE_DIALOG_KEY";
    private static final String POSITIVE_TEXT_DIALOG_KEY = "POSITIVE_TEXT_DIALOG_KEY";
    private static final String POSITIVE_ACTION_DIALOG_KEY = "POSITIVE_ACTION_DIALOG_KEY";
    private static final String NEGATIVE_TEXT_DIALOG_KEY = "NEGATIVE_TEXT_DIALOG_KEY";
    private static final String NEGATIVE_ACTION_DIALOG_KEY = "NEGATIVE_ACTION_DIALOG_KEY";

    @IntDef({DialogInterface.BUTTON_POSITIVE, DialogInterface.BUTTON_NEGATIVE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Action {
    }

    @SuppressLint("ParcelCreator")
    public interface ActionListener extends Parcelable {

        void actionClick(@Action int action);

        @Override
        default int describeContents() {
            return 0;
        }

        @Override
        default void writeToParcel(Parcel dest, int flags) {

        }
    }

    public static class Builder {

        private String title;
        private String message;
        private boolean isCancelable;
        private String positiveActionText;
        private ActionListener positiveListener;
        private String negativeActionText;
        private ActionListener negativeListener;

        public Builder setTitle(@NonNull String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(@NonNull String message) {
            this.message = message;
            return this;
        }

        public Builder setCancelable(Boolean cancelable) {
            isCancelable = cancelable;
            return this;
        }

        public Builder setPositiveListener(@NonNull String text, @NonNull ActionListener positiveListener) {
            this.positiveActionText = text;
            this.positiveListener = positiveListener;
            return this;
        }

        public Builder setNegativeListener(@NonNull String text, @NonNull ActionListener negativeListener) {
            this.negativeActionText = text;
            this.negativeListener = negativeListener;
            return this;
        }

        public Dialog build() {
            return new Dialog(this);
        }
    }

    public Dialog() {
    }

    @SuppressLint("ValidFragment")
    private Dialog(Builder builder) {
        title = builder.title;
        message = builder.message;
        isCancelable = builder.isCancelable;
        positiveActionText = builder.positiveActionText;
        positiveListener = builder.positiveListener;
        negativeActionText = builder.negativeActionText;
        negativeListener = builder.negativeListener;
    }

    private String title;
    private String message;
    private boolean isCancelable;
    private String positiveActionText;
    private ActionListener positiveListener;
    private String negativeActionText;
    private ActionListener negativeListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_DeviceDefault_Light_Dialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        if (!isCancelable && (positiveListener == null || negativeListener == null)){
//            throw new RuntimeException()
//        }

        if (savedInstanceState != null) {
            title = savedInstanceState.getString(TITLE_DIALOG_KEY, null);
            message = savedInstanceState.getString(MESSAGE_DIALOG_KEY, null);
            isCancelable = savedInstanceState.getBoolean(CANCELABLE_DIALOG_KEY, true);
            positiveActionText = savedInstanceState.getString(POSITIVE_TEXT_DIALOG_KEY, null);
            positiveListener = savedInstanceState.getParcelable(POSITIVE_ACTION_DIALOG_KEY);
            negativeActionText = savedInstanceState.getString(NEGATIVE_TEXT_DIALOG_KEY, null);
            negativeListener = savedInstanceState.getParcelable(NEGATIVE_ACTION_DIALOG_KEY);
        }

        Window window = getDialog().getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        View rootView = getView();
        if (rootView != null) {
            rootView.<TextView>findViewById(R.id.title_holder).setText(title);
            rootView.<TextView>findViewById(R.id.content_holder).setText(message);

            Button positiveBtn = rootView.findViewById(R.id.positive_btn_holder);
            if (positiveListener != null) {
                positiveBtn.setVisibility(View.VISIBLE);
                positiveBtn.setText(positiveActionText);
                positiveBtn.setOnClickListener(thisView -> {
                    positiveListener.actionClick(DialogInterface.BUTTON_POSITIVE);
                    dismiss();
                });
            } else {
                positiveBtn.setVisibility(View.GONE);
            }


            Button negativeBtn = rootView.findViewById(R.id.negative_btn_holder);
            if (negativeListener != null) {
                negativeBtn.setVisibility(View.VISIBLE);
                negativeBtn.setText(negativeActionText);
                negativeBtn.setOnClickListener(thisView -> {
                    negativeListener.actionClick(DialogInterface.BUTTON_NEGATIVE);
                    dismiss();
                });
            } else {
                negativeBtn.setVisibility(View.GONE);
            }

            getDialog().setCancelable(isCancelable);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(TITLE_DIALOG_KEY, title);
        outState.putString(MESSAGE_DIALOG_KEY, message);
        outState.putBoolean(CANCELABLE_DIALOG_KEY, isCancelable);
        outState.putString(POSITIVE_TEXT_DIALOG_KEY, positiveActionText);
        outState.putParcelable(POSITIVE_ACTION_DIALOG_KEY, positiveListener);
        outState.putString(NEGATIVE_TEXT_DIALOG_KEY, negativeActionText);
        outState.putParcelable(NEGATIVE_ACTION_DIALOG_KEY, negativeListener);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean isCancelable() {
        return isCancelable;
    }

    @Override
    public void setCancelable(boolean cancelable) {
        isCancelable = cancelable;
    }

    public String getPositiveActionText() {
        return positiveActionText;
    }

    public void setPositiveActionText(String positiveActionText) {
        this.positiveActionText = positiveActionText;
    }

    public ActionListener getPositiveListener() {
        return positiveListener;
    }

    public void setPositiveListener(ActionListener positiveListener) {
        this.positiveListener = positiveListener;
    }

    public String getNegativeActionText() {
        return negativeActionText;
    }

    public void setNegativeActionText(String negativeActionText) {
        this.negativeActionText = negativeActionText;
    }

    public ActionListener getNegativeListener() {
        return negativeListener;
    }

    public void setNegativeListener(ActionListener negativeListener) {
        this.negativeListener = negativeListener;
    }
}
