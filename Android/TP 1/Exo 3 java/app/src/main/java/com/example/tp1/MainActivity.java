package com.example.tp1;

import static com.google.android.material.internal.ViewUtils.dpToPx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint({"RestrictedApi", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Create the root ConstraintLayout
        ConstraintLayout rootConstraintLayout = new ConstraintLayout(this);
        rootConstraintLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootConstraintLayout.setId(View.generateViewId());

        // Add ScrollView to ConstraintLayout
        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));
        scrollView.setId(View.generateViewId());
        rootConstraintLayout.addView(scrollView);

        // Add an inner LinearLayout to ScrollView
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);

        // Add TextView to LinearLayout for form title
        TextView formTitleTextView = new TextView(this);
        formTitleTextView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, (int) dpToPx(this, 44)));
        formTitleTextView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
        formTitleTextView.setText(R.string.form_title);
        formTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        formTitleTextView.setTypeface(null, Typeface.BOLD_ITALIC);
        linearLayout.addView(formTitleTextView);

        // Add LinearLayout to LinearLayout for last name field
        LinearLayout linearLayoutLastName = new LinearLayout(this);
        linearLayoutLastName.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, (int) dpToPx(this, 50)));
        linearLayoutLastName.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutLastName.setId(View.generateViewId());
        linearLayout.addView(linearLayoutLastName);

        // Add TextView to linearLayoutLastName for last name prompt
        TextView lastNamePromptTextView = new TextView(this);
        lastNamePromptTextView.setLayoutParams(new LinearLayout.LayoutParams(
                (int) dpToPx(this, 280), ViewGroup.LayoutParams.WRAP_CONTENT, 3));
        lastNamePromptTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        lastNamePromptTextView.setText(R.string.lastNamePromptTextViewText);
        linearLayoutLastName.addView(lastNamePromptTextView);

        // Add EditText to linearLayoutLastName for last name input
        EditText lastNameEditText = new EditText(this);
        lastNameEditText.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 3));
        lastNameEditText.setHint(R.string.lastNameEditTextHintText);
        lastNameEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        linearLayoutLastName.addView(lastNameEditText);

        // Add LinearLayout to LinearLayout for first name field
        LinearLayout linearLayoutFirstName = new LinearLayout(this);
        linearLayoutFirstName.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, (int) dpToPx(this, 50)));
        linearLayoutFirstName.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutFirstName.setId(View.generateViewId());
        linearLayout.addView(linearLayoutFirstName);

        // Add TextView to linearLayoutFirstName for first name prompt
        TextView firstNamePromptTextView = new TextView(this);
        firstNamePromptTextView.setLayoutParams(new LinearLayout.LayoutParams(
                (int) dpToPx(this, 280), ViewGroup.LayoutParams.WRAP_CONTENT, 3));
        firstNamePromptTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        firstNamePromptTextView.setText(R.string.firstNamePromptTextViewText);
        linearLayoutFirstName.addView(firstNamePromptTextView);

        // Add EditText to linearLayoutLastName for last name input
        EditText firstNameEditText = new EditText(this);
        firstNameEditText.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 3));
        firstNameEditText.setHint(R.string.firstNameEditTextHintText);
        firstNameEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        linearLayoutFirstName.addView(firstNameEditText);

        // Add EditText to linearLayerLayoutLastName for age input
        // Create the age LinearLayout and add it to the LinearLayout
        LinearLayout linearLayoutAge = new LinearLayout(this);
        linearLayoutAge.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) dpToPx(this, 50)));
        linearLayoutAge.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutAge.setPadding(5, 5, 5, 5);
        linearLayout.addView(linearLayoutAge);

        TextView agePromptTextView = new TextView(this);
        agePromptTextView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        agePromptTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        agePromptTextView.setText(getString(R.string.agePromptTextViewText));
        linearLayoutAge.addView(agePromptTextView);

        EditText ageEditTextNumber = new EditText(this);
        ageEditTextNumber.setLayoutParams(new LinearLayout.LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()), ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        ageEditTextNumber.setHint(getString(R.string.ageEditTextNumberText));
        ageEditTextNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
        ageEditTextNumber.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        linearLayoutAge.addView(ageEditTextNumber);


        // Create the gender radio buttons and add them to a radio group and the gender linear layout
        LinearLayout genderLinearLayout = new LinearLayout(this);
        genderLinearLayout.setId(View.generateViewId());
        genderLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        genderLinearLayout.setOrientation(LinearLayout.VERTICAL);
        genderLinearLayout.setPadding((int) dpToPx(this,5), (int) dpToPx(this,5), (int) dpToPx(this,5), (int) dpToPx(this,5));
        linearLayout.addView(genderLinearLayout);

        TextView genderPromptTextView = new TextView(this);
        genderPromptTextView.setId(View.generateViewId());
        genderPromptTextView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1));
        genderPromptTextView.setText(R.string.genderPromptTextViewHintText);
        genderLinearLayout.addView(genderPromptTextView);

        RadioGroup genderRadioGroup = new RadioGroup(this);
        genderRadioGroup.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1));
        genderRadioGroup.setOrientation(RadioGroup.HORIZONTAL);
        genderLinearLayout.addView(genderRadioGroup);

        RadioButton femaleRadioButton = new RadioButton(this);
        femaleRadioButton.setId(View.generateViewId());
        femaleRadioButton.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1));
        femaleRadioButton.setText(R.string.femaleRadioButtonLabel);
        genderRadioGroup.addView(femaleRadioButton);

        RadioButton maleRadioButton = new RadioButton(this);
        maleRadioButton.setId(View.generateViewId());
        maleRadioButton.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1));
        maleRadioButton.setText(R.string.maleRadioButtonLabel);
        genderRadioGroup.addView(maleRadioButton);

        // Domain
        LinearLayout domainLayout = new LinearLayout(this);
        domainLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, (int) dpToPx(this, 40)));
        domainLayout.setOrientation(LinearLayout.HORIZONTAL);
        domainLayout.setPadding(5, 5, 5, 5);
        linearLayout.addView(domainLayout);

        TextView domainPromptTextView = new TextView(this);
        domainPromptTextView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        domainPromptTextView.setText(R.string.domainPromptTextViewText);
        domainPromptTextView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.FILL_VERTICAL);
        domainLayout.addView(domainPromptTextView);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.competency_domain, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        Spinner domainDropdownSpinner = new Spinner(this);
        domainDropdownSpinner.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        domainDropdownSpinner.setAdapter(adapter);
        domainLayout.addView(domainDropdownSpinner);

        LinearLayout phoneNumberLayout = new LinearLayout(this);
        phoneNumberLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 50));
        phoneNumberLayout.setOrientation(LinearLayout.HORIZONTAL);
        phoneNumberLayout.setPadding(5, 5, 5, 5);
        linearLayout.addView(phoneNumberLayout);

//        TextView phoneNumberPromptTextView = new TextView(this);
//        phoneNumberPromptTextView.setLayoutParams(new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
//        phoneNumberPromptTextView.setText(R.string.phoneNumberPromptTextViewText);
//        phoneNumberPromptTextView.setGravity(Gravity.CENTER_HORIZONTAL);
//        phoneNumberLayout.addView(phoneNumberPromptTextView);

//        EditText editTextPhoneNumber = new EditText(this);
//        editTextPhoneNumber.setLayoutParams(new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
//        editTextPhoneNumber.setEms(10);
//        editTextPhoneNumber.setHint(R.string.editTextPhoneNumberHintText);
//        editTextPhoneNumber.setInputType(InputType.TYPE_CLASS_PHONE);
//        phoneNumberLayout.addView(editTextPhoneNumber);

        // Phone
        // LinearLayout for phone number prompt and edit text
        LinearLayout phoneLayout = new LinearLayout(this);
//        phoneLayout.setId(R.id.linear_layout_phone_number);
        phoneLayout.setOrientation(LinearLayout.HORIZONTAL);
        phoneLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) dpToPx(this,50)));
        phoneLayout.setPadding(5, 5, 5, 5);
        linearLayout.addView(phoneLayout);

        // TextView for phone number prompt
        TextView phoneNumberPromptTextView = new TextView(this);
        phoneNumberPromptTextView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1));
        phoneNumberPromptTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        phoneNumberPromptTextView.setText(R.string.phoneNumberPromptTextViewText);
        phoneLayout.addView(phoneNumberPromptTextView);

        // EditText for phone number input
        EditText editTextPhoneNumber = new EditText(this);
        editTextPhoneNumber.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1));
        editTextPhoneNumber.setEms(10);
        editTextPhoneNumber.setHint(R.string.editTextPhoneNumberHintText);
        editTextPhoneNumber.setInputType(InputType.TYPE_CLASS_PHONE);
        phoneLayout.addView(editTextPhoneNumber);

        // Submit Button
        Button submitButton = new Button(this);
//        submitButton.setId(R.id.submitButton);
        submitButton.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
//        submitButton.setAlpha(0.9f);
        submitButton.setElevation(7);
        submitButton.setText(R.string.submitButtonText);
//        submitButton.setAllCaps(false);
//        submitButton.setBackgroundResource(R.color.orange_200);
        ViewGroup.MarginLayoutParams buttonsParams = (ViewGroup.MarginLayoutParams) submitButton.getLayoutParams();
        buttonsParams.setMargins((int) dpToPx(this, 25), (int) dpToPx(this, 125), (int) dpToPx(this, 25), (int) dpToPx(this, 25));
        GradientDrawable backgroundDrawable = new GradientDrawable();
        GradientDrawable foregroundDrawable = new GradientDrawable();
        ColorDrawable colorDrawable = new ColorDrawable();

        foregroundDrawable.setColor(R.color.white);
        submitButton.setForeground(foregroundDrawable);
        foregroundDrawable.setCornerRadius(75);
        backgroundDrawable.setCornerRadius(75);
        backgroundDrawable.setColor(R.color.orange_500);
        submitButton.setBackground(backgroundDrawable);
        linearLayout.addView(submitButton);
        setContentView(rootConstraintLayout);
    }

    private void validation() {

    }

}