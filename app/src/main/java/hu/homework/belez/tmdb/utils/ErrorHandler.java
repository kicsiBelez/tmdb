package hu.homework.belez.tmdb.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import hu.homework.belez.tmdb.R;

/**
 * Created by Belez on 2017. 07. 02..
 */

public class ErrorHandler {

    public static AlertDialog handleException(String errorMessage, Integer errorCode, Context context,
                                              DialogInterface.OnClickListener onClickListenerForOk,
                                              DialogInterface.OnClickListener onClickListenerForCancel) {



        return createErrorDialog("Error", errorMessage, context, onClickListenerForOk, onClickListenerForCancel);
    }

    private static AlertDialog createErrorDialog(String errorTitle, String errorMessage, Context context,
                                                 DialogInterface.OnClickListener onClickListenerForOk,
                                                 DialogInterface.OnClickListener onClickListenerForCancel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(errorMessage)
                .setTitle(errorTitle);

        // Add Ok button
        builder.setPositiveButton(R.string.error_dialog_ok, onClickListenerForOk);
        // Add Cancel button
        if (onClickListenerForCancel != null) {
            builder.setNegativeButton(R.string.error_dialog_cancel, onClickListenerForCancel);
        }

        // Create the AlertDialog
        return builder.create();
    }

}
