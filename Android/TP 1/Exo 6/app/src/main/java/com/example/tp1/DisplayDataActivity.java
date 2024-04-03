package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tp1.models.Person;

public class DisplayDataActivity extends AppCompatActivity {

    private Person person;
    private TextView first_name;
    private TextView last_name;
    private TextView phoneNumber;
    private TextView domain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        // Retrieve all the views
        first_name = (TextView) findViewById(R.id.first_name);
        last_name = (TextView) findViewById(R.id.last_name);
        phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        domain = (TextView) findViewById(R.id.domain);

        person = (Person) getIntent().getSerializableExtra("person");


        // Fill the fields with the object passed through the intent
        first_name.setText(person.getFirstName());
        last_name.setText(person.getLastName());
        domain.setText(person.getProfessionalDomain());
        phoneNumber.setText(person.getPhoneNumber());
    }
}