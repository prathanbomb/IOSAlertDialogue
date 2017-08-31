package com.bsupits.iosalertdialogue;

import android.app.Activity;
import android.os.Bundle;

import com.bsupits.library.IOSAlertDialogDoubleButton;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showAlertDialog();
    }

    private void showAlertDialog() {
        new IOSAlertDialogDoubleButton(this)
                .setTitle("Title")
                .setMessage("message")
                .setNegativeButton("Delete", false, true, null)
                .setPositiveButton("Open", true, false, null)
                .show();
    }

}
