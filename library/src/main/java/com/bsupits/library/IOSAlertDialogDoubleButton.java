package com.bsupits.library;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Button;

import static android.view.View.VISIBLE;

/**
 * Created by prathanbomb on 1/15/2017 AD.
 */

public class IOSAlertDialogDoubleButton extends IOSAlertDialog<IOSAlertDialogDoubleButton> {

    public static final int POSITIVE_BUTTON = R.id.btn_pos_alert_dialog;
    public static final int NEGATIVE_BUTTON = R.id.btn_neg_alert_dialog;

    private Button positiveButton;
    private Button negativeButton;

    {
        positiveButton = findView(POSITIVE_BUTTON);
        negativeButton = findView(NEGATIVE_BUTTON);
    }

    public IOSAlertDialogDoubleButton(Context context) {
        super(context);
    }

    public IOSAlertDialogDoubleButton(Context context, int theme) {
        super(context, theme);
    }

    public IOSAlertDialogDoubleButton setPositiveButton(@StringRes int text, boolean textBold, boolean redButton, View.OnClickListener listener) {
        return setPositiveButton(string(text), textBold, redButton, listener);
    }

    public IOSAlertDialogDoubleButton setNegativeButton(@StringRes int text, boolean textBold, boolean redButton, View.OnClickListener listener) {
        return setNegativeButton(string(text), textBold, redButton, listener);
    }

    public IOSAlertDialogDoubleButton setPositiveButton(String text, boolean textBold, boolean redButton, @Nullable View.OnClickListener listener) {
        positiveButton.setVisibility(VISIBLE);
        positiveButton.setText(text);
        if (textBold) {
            positiveButton.setTypeface(positiveButton.getTypeface(), Typeface.BOLD);
        }
        if (redButton) {
            positiveButton.setTextColor(getContext().getResources().getColor(R.color.colorRed));
        }
        positiveButton.setOnClickListener(new DismissAfterClick(listener));
        return this;
    }

    public IOSAlertDialogDoubleButton setNegativeButton(String text, boolean textBold, boolean redButton, @Nullable View.OnClickListener listener) {
        negativeButton.setVisibility(VISIBLE);
        negativeButton.setText(text);
        if (textBold) {
            negativeButton.setTypeface(negativeButton.getTypeface(), Typeface.BOLD);
        }
        if (redButton) {
            negativeButton.setTextColor(getContext().getResources().getColor(R.color.colorRed));
        }
        negativeButton.setOnClickListener(new DismissAfterClick(listener));
        return this;
    }

    public IOSAlertDialogDoubleButton setOnButtonClickListener(View.OnClickListener listener) {
        return setOnButtonClickListener(true ,listener);
    }

    public IOSAlertDialogDoubleButton setOnButtonClickListener(boolean closeOnClick, View.OnClickListener listener) {
        View.OnClickListener clickHandler = closeOnClick ?
                new IOSAlertDialog.DismissAfterClick(listener) :
                listener;
        positiveButton.setOnClickListener(clickHandler);
        negativeButton.setOnClickListener(clickHandler);
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_ios_dialogue_double_button;
    }
}
