package com.alssimpleapps.customdialogexample;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CustomDialog.CustomDialogListener {

    // Activity resources
    TextView tvName, tvSurname;
    Button btnOpenDialog, btnResetDefaultInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link XML - Java
        tvName = findViewById(R.id.tv_name);
        tvSurname = findViewById(R.id.tv_surname);
        btnOpenDialog = findViewById(R.id.btn_open_custom_dialog);
        btnResetDefaultInformation = findViewById(R.id.btn_reset_default_info);

        // OnClickListenr
        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {
                openDialog();
            }
        });
        btnResetDefaultInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {
                AlertDialog simpleDialog = getResetDialog();
                simpleDialog.show();
            }
        });
    }

    /**
     * Open and iteract with a custom Dialog
     */
    private void openDialog() {
        CustomDialog myDialog = new CustomDialog();
        myDialog.show(getSupportFragmentManager(), "My Costum Dialog");
    }

    /**
     *
     * @param name String obtained by taking data inserted in the custom dialog
     * @param surname String obtained by taking data inserted in the custom dialog
     */
    @Override
    public void applyText(String name, String surname) {
        tvName.setText(name);
        tvSurname.setText(surname);
    }

    /**
     *
     * @return an instance of AlertDialog to reset the default information (name & surname)
     */
    private AlertDialog getResetDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Reset information")
                .setMessage("Do you really want to reset the information?")
                .setNegativeButton("No, thanks", null)
                .setPositiveButton("Sure!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvName.setText("Name: John");
                        tvSurname.setText("Surname: Doe");
                    }
                });

        return builder.create();

    }
}


