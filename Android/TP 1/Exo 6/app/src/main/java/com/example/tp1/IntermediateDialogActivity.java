package com.example.tp1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tp1.models.Person;

public class IntermediateDialogActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SUCCESS = 1;

    private Button back;
    private Button ok;

    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_dialog);

        // Get the passed object
        person = (Person) getIntent().getSerializableExtra("person");
        back = (Button) findViewById(R.id.cancelOption);
        ok = (Button) findViewById(R.id.yesOption);

        System.out.println("Personne passee: "+ person.getFirstName()+ " "+person.getLastName());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EX0 6
                Intent backIntent = new Intent(IntermediateDialogActivity.this, MainActivity.class);
                backIntent.putExtra("person",  person);
                startActivity(backIntent);
                finish();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(IntermediateDialogActivity.this, DisplayDataActivity.class);
                continueIntent.putExtra("person",  person);
                startActivity(continueIntent);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}