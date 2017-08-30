package com.bsupits.library;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Button;

import static android.view.View.VISIBLE;

/**
 * Created by prathanbomb on 1/14/2017 AD.
 */

public class IOSAlertDialogueSingleButton extends IOSAlertDialogue<IOSAlertDialogueSingleButton> {

    public static final int NEUTRAL_BUTTON = R.id.btn_neutral_alert_dialog;

    private Button neutralButton;

    {
        neutralButton = findView(NEUTRAL_BUTTON);
    }

    public IOSAlertDialogueSingleButton(Context context) {
        super(context);
    }

    public IOSAlertDialogueSingleButton(Context context, int theme) {
        super(context, theme);
    }

    public IOSAlertDialogueSingleButton setNeutralButton(@StringRes int text, View.OnClickListener listener) {
        return setNeutralButton(string(text), listener);
    }

    public IOSAlertDialogueSingleButton setNeutralButton(String text, @Nullable View.OnClickListener listener) {
        neutralButton.setVisibility(VISIBLE);
        neutralButton.setText(text);
        neutralButton.setOnClickListener(new DismissAfterClick(listener));
        return this;
    }

    public IOSAlertDialogueSingleButton setOnButtonClickListener(View.OnClickListener listener) {
        return setOnButtonClickListener(true ,listener);
    }

    public IOSAlertDialogueSingleButton setOnButtonClickListener(boolean closeOnClick, View.OnClickListener listener) {
        View.OnClickListener clickHandler = closeOnClick ?
                new DismissAfterClick(listener) :
                listener;
        neutralButton.setOnClickListener(clickHandler);
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_ios_dialogue_single_button;
    }
}
