package com.alssimpleapps.customdialogexample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class CustomDialog extends AppCompatDialogFragment {

    private static final String TAG = "CostumDialog";

    // Custom layout resources
    EditText etName, etSurname;

    // Custom dialog interface
    CustomDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (CustomDialogListener) context;
        } catch (ClassCastException cce) {
            throw new ClassCastException(TAG + "- Necessario implementare il listener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Getting the custom layout
        View view = inflater.inflate(R.layout.layout_custom_dialog, null);

        // Building the custom dialog
        builder.setView(view)
                .setTitle("Person information")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Getting data from dialog to pass to the activity which calls it
                        String name = etName.getText().toString();
                        String surname = etSurname.getText().toString();
                        // Passing data to the activity with the listener interface
                        listener.applyText(name, surname);
                    }
                });

        // Link XML - Java
        etName = view.findViewById(R.id.et_name);
        etSurname = view.findViewById(R.id.et_surname);

        return builder.create();

    }

    // Dialog lister
    public interface CustomDialogListener {

        void applyText(String name, String surname);

    }

}
