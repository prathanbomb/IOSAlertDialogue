package com.bsupits.library;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by prathanbomb on 1/14/2017 AD.
 */

@SuppressWarnings("unchecked")
public abstract class IOSAlertDialogue<T extends IOSAlertDialogue> {

    private static final String KEY_SAVED_STATE_TOKEN = "key_saved_state_token";

    private Dialog dialog;
    private View dialogView;

    private TextView titleView;
    private TextView contentView;

    public IOSAlertDialogue(Context context) {
        init(new Dialog(context));
    }

    public IOSAlertDialogue(Context context, int theme) {
        init(new Dialog(context, theme));
    }

    @SuppressWarnings("ConstantConditions")
    private void init(Dialog dialogBuilder) {
        dialog = dialogBuilder;
        dialogView = LayoutInflater.from(dialog.getContext()).inflate(getLayout(), null);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);

        titleView = findView(R.id.tv_title_alert_dialog);
        contentView = findView(R.id.tv_content_alert_dialog);
    }

    @LayoutRes
    protected abstract int getLayout();

    public T setMessage(@StringRes int message) {
        return setMessage(string(message));
    }

    public T setMessage(CharSequence message) {
        contentView.setVisibility(View.VISIBLE);
        contentView.setText(message);
        return (T) this;
    }

    public T setTitle(@StringRes int title) {
        return setTitle(string(title));
    }

    public T setTitle(CharSequence title) {
        titleView.setVisibility(View.VISIBLE);
        titleView.setText(title);
        return (T) this;
    }

    /*
     * You should call method saveInstanceState on handler object and then use saved info to restore
     * your dialog in onRestoreInstanceState. Static methods wasDialogOnScreen and getDialogId will
     * help you in this.
     */
    public T setInstanceStateHandler(int id, IOSAlertDialogueSaveStateHandler handler) {
        handler.handleDialogStateSave(id, this);
        return (T) this;
    }

    public T setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
        return (T) this;
    }

    public T setSavedInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            boolean hasSavedStateHere =
                    savedInstanceState.keySet().contains(KEY_SAVED_STATE_TOKEN) &&
                            savedInstanceState.getSerializable(KEY_SAVED_STATE_TOKEN) == getClass();
            if (hasSavedStateHere) {
                restoreState(savedInstanceState);
            }
        }
        return (T) this;
    }

    public Dialog show() {
        dialog.show();
        return dialog;
    }

    public Dialog create() {
        return dialog;
    }

    public void dismiss() {
        dialog.dismiss();
    }

    void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_SAVED_STATE_TOKEN, getClass());
    }

    void restoreState(Bundle savedState) {
    }

    boolean isShowing() {
        return dialog != null && dialog.isShowing();
    }

    protected String string(@StringRes int res) {
        return getContext().getString(res);
    }

    protected Context getContext() {
        return dialogView.getContext();
    }

    protected  <ViewClass extends View> ViewClass findView(int id) {
        return (ViewClass) dialogView.findViewById(id);
    }

    protected class DismissAfterClick implements View.OnClickListener {

        private View.OnClickListener clickListener;

        protected DismissAfterClick(View.OnClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onClick(v);
            }
            dismiss();
        }
    }

}
