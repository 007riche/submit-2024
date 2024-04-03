package com.example.tp1;

import static com.example.tp1.R.color.orange_500;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button submitButton;
    private boolean submissionChoice;

    private EditText lastNameEditText;
    private EditText firstNameEditText;
    private EditText editTextPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitButton = (Button) findViewById(R.id.submitButton);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        // Action on click event to submit
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 confirmAlertDialog();
                System.out.println("Le CHOIX DE LA CONFIRMATION EST: "+ submissionChoice);
            }
        });
    }

    private void validation() {

    }

    public void confirmAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(false);
        builder.setMessage(R.string.alert_dialog_confirm_submission_message);
        builder.setTitle(R.string.alert_dialog_confirm_submission_title).setPositiveButton(R.string.alert_dialog_confirm_submission_yes_option, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                lastNameEditText.setBackgroundColor(getResources().getColor(R.color.orange_500));
                firstNameEditText.setBackgroundColor(getResources().getColor(R.color.orange_500));
                editTextPhoneNumber.setBackgroundColor(getResources().getColor(R.color.orange_500));
                submissionChoice = true;

            }
        });
                builder.setNegativeButton(R.string.alert_dialog_confirm_submission_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                submissionChoice = false;
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}


