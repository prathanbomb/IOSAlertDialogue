package com.bsupits.library;

import android.os.Bundle;
import android.util.SparseArray;

import java.lang.ref.WeakReference;

/**
 * Created by prathanbomb on 1/14/2017 AD.
 */

public class IOSAlertDialogueSaveStateHandler {
    private static final String KEY_DIALOG_ID = "id";

    private SparseArray<WeakReference<IOSAlertDialogue<?>>> handledDialogs;

    public IOSAlertDialogueSaveStateHandler() {
        handledDialogs = new SparseArray<>();
    }

    public void saveInstanceState(Bundle outState) {
        for (int index = handledDialogs.size() - 1; index >= 0; index--) {
            WeakReference<IOSAlertDialogue<?>> dialogRef = handledDialogs.valueAt(index);
            if (dialogRef.get() == null) {
                handledDialogs.remove(index);
                continue;
            }
            IOSAlertDialogue<?> dialog = dialogRef.get();
            if (dialog.isShowing()) {
                dialog.onSaveInstanceState(outState);
                outState.putInt(KEY_DIALOG_ID, handledDialogs.keyAt(index));
                return;
            }
        }
    }

    void handleDialogStateSave(int id, IOSAlertDialogue<?> dialog) {
        handledDialogs.put(id, new WeakReference<IOSAlertDialogue<?>>(dialog));
    }

    public static boolean wasDialogOnScreen(Bundle savedInstanceState) {
        return savedInstanceState.keySet().contains(KEY_DIALOG_ID);
    }

    public static int getSavedDialogId(Bundle savedInstanceState) {
        return savedInstanceState.getInt(KEY_DIALOG_ID, -1);
    }
}
