package com.example.tp1;

import static android.widget.AdapterView.INVALID_ROW_ID;
import static com.example.tp1.R.color.orange_500;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.tp1.models.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button submitButton;
    private boolean submissionChoice;

    private EditText lastNameEditText;
    private EditText firstNameEditText;
    private EditText editTextPhoneNumber;
    private EditText ageEditTextNumber;
    private RadioGroup genderRadioButton;
    private Spinner domainDropdownSpinner;
    private String selectedDomain;


    private Person person;

    private static final int REQUEST_CODE_SUBMISSION_CONFIRMATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        person = (Person) getIntent().getSerializableExtra("person") != null ? person = (Person) getIntent().getSerializableExtra("person") : new Person();

        submitButton = (Button) findViewById(R.id.submitButton);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        ageEditTextNumber =(EditText) findViewById(R.id.ageEditTextNumber);
        domainDropdownSpinner = (Spinner) findViewById(R.id.domainDropdownSpinner);
//        genderRadioButton = (RadioGroup) findViewById(R.id.genderRadioButton);
//        System.out.println(genderRadioButton);
        domainDropdownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               selectedDomain = parent.getItemAtPosition(position).toString();
                System.out.println("SPINNER SELECTED: "+selectedDomain);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                parent.setBackgroundColor(getResources().getColor(R.color.warning_red));
                selectedDomain = "Autres";

            }
        });
//        domainDropdownSpinner.getSelectedItemId();
//        domainDropdownSpinner = (Spinner) findViewById(R.id.domainDropdownSpinner);

        // Action on click event to submit
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    HashMap<String, List<View>> allViwes = validation();
                    List<View> nonValidViews =  allViwes.get("nonValidViews");
                    List<View> validViews =  allViwes.get("validViews");
                    if (nonValidViews.size()>0) {
                        for (View view : nonValidViews) {
                            System.out.println();
                                Timer timer = new Timer();
                                int start=0;
                                int delay = 2000;
                                TemporaryBackgroundTimerTask temporaryBackgroundTimerTask = new TemporaryBackgroundTimerTask(view,  R.color.warning_red, delay);
                                timer.schedule(temporaryBackgroundTimerTask, delay);
                                temporaryBackgroundTimerTask.run();


                        }
                        if (validViews.size()>0) {
                            for (View view : validViews) {
                                Timer timer = new Timer();
                                int start=0;
                                int delay = 2000;
                                TemporaryBackgroundTimerTask temporaryBackgroundTimerTask = new TemporaryBackgroundTimerTask(view,  R.color.white, delay);
                                timer.schedule(temporaryBackgroundTimerTask, delay);
                                temporaryBackgroundTimerTask.run();
                            }
                        }


                    }else  {
//                        showAlertDialog();
                        Intent dialogIntent = new Intent(MainActivity.this, IntermediateDialogActivity.class);
                        Person person = new Person();
                        person.setLastName(lastNameEditText.getText().toString());
                        person.setFirstName(firstNameEditText.getText().toString());
                        person.setPhoneNumber(editTextPhoneNumber.getText().toString());
                        person.setProfessionalDomain(selectedDomain);
                        dialogIntent.putExtra("person",  person);
                        startActivity(dialogIntent);
                        finish();

                    }

               }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SUBMISSION_CONFIRMATION && resultCode == Activity.RESULT_OK) {
            String choice = data.getStringExtra("choice");
            System.out.println("LE RESULTAT DE LA BOITE DE DIALOGUE: "+choice);
        }

    }


    private class TemporaryBackgroundTimerTask extends TimerTask {
        private final View view;
        private final int colorResourceId;
        private final int delay;
        private Timer timer;

        TemporaryBackgroundTimerTask resetBackgroundTimerTask;

        public TemporaryBackgroundTimerTask(View view, int colorResourceId) {
            this.view = view;
            this.colorResourceId = colorResourceId;
            delay = 0;
        }

        public TemporaryBackgroundTimerTask(View view, int colorResourceId, int delay) {
            this.view = view;
            this.colorResourceId = colorResourceId;
            this.delay = delay;
        }

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (view instanceof EditText ) {
                        EditText editText = (EditText) view;
                        System.out.println("Coloration des editext");
                        editText.setBackgroundColor(getResources().getColor(colorResourceId));
                    }

                    if (view instanceof Spinner) {
                        Spinner spinner = (Spinner) view;
                        for (int i=0; i<spinner.getChildCount(); i++) {
                            spinner.getChildAt(i).setBackgroundColor(getResources().getColor(colorResourceId));
                        }

                    }

//                   else if (view instanceof RadioGroup) {
//                        RadioGroup radioGroup = (RadioGroup) view;
//                        System.out.println("APPEL DE COLORATION DES RADIO GROUP");
//                        for (int i = 0; i < radioGroup.getChildCount(); i++) {
//                            // Get the child view at the current index
//                            View childView = radioGroup.getChildAt(i);
//
//                            // Set the background of the child view
//                            childView.setBackgroundColor(getResources().getColor(colorResourceId));
//                        }
//                    }
                   else {}
                }
            });


        }
    }



    private boolean isValidRangeIncludedBoundsIntegerValueOFEditTextVue(EditText view, int minimum, int maximum) {
        String vueStringContent = view.getText().toString().trim();
        int value;
        try {
           value = Integer.parseInt(vueStringContent);
            if (value>=minimum && value<=maximum)
            {
                System.out.println("LA VALEUR EST: "+value);
                return true;
            }
        } catch (NumberFormatException exception) {
            return false;
        }

        return false;
    }

    private int hasAChosenOption(RadioGroup radioGroup) {
        try {
            System.out.println("Un button radio select");
            return radioGroup.getCheckedRadioButtonId();
        } catch (Exception exception) {
            System.out.println("Aucun button radio select");
            return -1;
        }



    }

    private boolean isValidDigitsPhoneNumberOFEditTextVue(EditText view) {
        String vueStringContent = view.getText().toString().replace(" ","").trim();
        System.out.println("NUMERO DE PHONE: "+vueStringContent);
//        System.out.println("Phone codition: "+((vueStringContent.length() == 10 && vueStringContent.matches("\\d+"))
//                || (vueStringContent.length() > 10 && vueStringContent.startsWith("+") && vueStringContent.substring(1).matches("\\d+"))));
        return (vueStringContent.length() == 10 && vueStringContent.matches("\\d+"))
                || (vueStringContent.length() > 10 && vueStringContent.startsWith("+") && vueStringContent.substring(1).matches("\\d+")) ;
    }

    private boolean isNotEmptyOFEditTextVue(EditText view) {
        String vueStringContent = view.getText().toString().trim();
        if (!vueStringContent.isEmpty())
            return true;

        return false;
    }


    private HashMap<String, List<View>>  validation() {
        HashMap<String, List<View>> allViwes = new HashMap<>();
        List<View> nonValidViews = new ArrayList<View>();
        List<View> validViews = new ArrayList<View>();
        if (!isValidRangeIncludedBoundsIntegerValueOFEditTextVue(ageEditTextNumber,1,99))
        {
            nonValidViews.add(ageEditTextNumber);
        }
        else {
            validViews.add(ageEditTextNumber);
        }

        if (!isNotEmptyOFEditTextVue(lastNameEditText))
        { nonValidViews.add(lastNameEditText); }
        else {
            validViews.add(lastNameEditText);
        }

        if (!isNotEmptyOFEditTextVue(firstNameEditText))
        { nonValidViews.add(firstNameEditText); }
        else {
            validViews.add(firstNameEditText);
        }

        if (!isValidDigitsPhoneNumberOFEditTextVue(editTextPhoneNumber))
        { nonValidViews.add(editTextPhoneNumber); }
        else {
            validViews.add(editTextPhoneNumber);
        }

        if (domainDropdownSpinner.getSelectedItemId() ==  INVALID_ROW_ID)
        { nonValidViews.add(domainDropdownSpinner); }
        else {
            validViews.add(domainDropdownSpinner);
        }
//        if (hasAChosenOption(genderRadioButton) == -1)
//        {
//            nonValidViews.add(genderRadioButton);
//            System.out.println("APPEL DE RADIOBUTTON NON VALIDE, Type: "+genderRadioButton.getClass());}
//        else {
//            validViews.add(genderRadioButton);
//        }
        allViwes.put("validViews", validViews);
        allViwes.put("nonValidViews", nonValidViews);
        return allViwes;
    }

    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage(R.string.alert_dialog_confirm_submission_message);
        builder.setTitle(R.string.alert_dialog_confirm_submission_title).setPositiveButton(R.string.alert_dialog_confirm_submission_yes_option, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                lastNameEditText.setBackgroundColor(getResources().getColor(R.color.orange_500));
                firstNameEditText.setBackgroundColor(getResources().getColor(orange_500));
                editTextPhoneNumber.setBackgroundColor(getResources().getColor(R.color.orange_500));
                ageEditTextNumber.setBackgroundColor(getResources().getColor(R.color.orange_500));
                launchAlertDialog();
                returnResult("Oui");
            }
        });
        builder.setNegativeButton(R.string.alert_dialog_confirm_submission_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setOnDismissListener( new DialogInterface.OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
//                launchAlertDialog();
//                returnResult("closed");
            }
        });
        dialog.show();
    }


    private void returnResult(String choice) {
        // Use an Intent to return the result to the calling activity
        Intent returnIntent = new Intent();
        returnIntent.putExtra("choice", choice);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }


    private void launchAlertDialog() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SUBMISSION_CONFIRMATION);
    }

}





//
//
//    TimerTask resetBackgroundColrTask = new TimerTask() {
//        @Override
//        public void run() {
//            if (view instanceof EditText ) {
//                EditText editText = (EditText) view;
//                editText.setBackgroundColor(getResources().getColor(R.color.white));
//            }
//
//            if (view instanceof RadioGroup) {
//                RadioGroup radioGroup = (RadioGroup) view;
//                radioGroup.setBackgroundColor(getResources().getColor(R.color.white));
//            }
//        }
//    };
//    Timer timer = new Timer();
//            timer.schedule(resetBackgroundColrTask, delay);
////            TemporaryBackgroundTimerTask  temporaryBackgroundBackTimerTask = new TemporaryBackgroundTimerTask(view, R.color.white);
////            Timer timer = new Timer();
////            int delay = 2000;
////            timer.schedule(temporaryBackgroundBackTimerTask, delay);


// Method to change the system language
//    private void setLocale(String languageCode) {
//        Locale locale = new Locale(languageCode);
//        Configuration config = getBaseContext().getResources().getConfiguration();
//        config.setLocale(locale);
//        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
//    }

//    private boolean checkLanguageAvailability(LocaleListCompat localeListCompat, String lang) {
//        for (int i=0; i < localeListCompat.size(); i++) {
////            System.out.println("LANGUE CHARGEE DISPO: "+localeListCompat.get(i).getLanguage());
//           if (localeListCompat.get(i).getLanguage().contentEquals(lang))
//               return true;
//        }
//        return false;
//    }



//        LocaleListCompat localeListCompat = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
//        for (int i=0; i < localeListCompat.size(); i++) {
//            System.out.println("LANGUE CHARGEE DISPO: "+localeListCompat.get(i).getLanguage());
//        }


// Retrieving initial settings
//        languageSwitch = findViewById(R.id.switchLanguage);
//        languageSwitchState = languageSwitch.isChecked();
//        defaulLanguage = Locale.getDefault().getLanguage();
//        currentLanguage = defaulLanguage;
////        System.out.println("LA LANGUE PAR DEFAUT EST: "+defaulLanguage);
//        languageSwitchState = getIntent().getBooleanExtra("switchState", languageSwitchState) ? getIntent().getBooleanExtra("switchState", languageSwitchState) : getIntent().getBooleanExtra("switchState", false);
//        languageSwitch.setChecked(languageSwitchState);
////        languageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked) {
//                    if(defaulLanguage.contentEquals("fr")) {
//                        setLocale("en");
//                        currentLanguage = "en";
//
//                    } else {
//                        setLocale("fr");
//                        currentLanguage = "en";
//                    }
//
//                }
//                else  {
//                    setLocale(defaulLanguage);
//                    currentLanguage = defaulLanguage;
//                }
////                recreate();
//                Intent intent = getIntent();
//                intent.putExtra("switchstate",isChecked);
//                finish();
//                startActivity(intent);
//
////                System.out.println("LANGUE ACTUELLE: "+currentLanguage);
////                languageSwitch = findViewById(R.id.switchLanguage);
////                languageSwitch.setChecked(true);
////                languageSwitch.setText(R.string.back_to_default_language);
//            }
//        });
