package com.youthdiversion.calendar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccount extends AppCompatActivity {

    public final String FIRSTNAME = "firstnamekey";
    public final String LASTNAME = "lastnamekey";
    public final String EMAIL = "emailkey";
    public final String ID = "idkey";
    public final String PHONE = "phonekey";
    public final String PASSWORD = "passwordkey";
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    DatabaseHandler db;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        db = new DatabaseHandler(getApplicationContext());
    }

    public void onClickSubmitButton(View v) {
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        EditText firstNameField=(EditText)findViewById(R.id.firstNameField);
        EditText lastNameField=(EditText)findViewById(R.id.lastNameField);
        EditText emailField=(EditText)findViewById(R.id.emailField);
        EditText idField=(EditText)findViewById(R.id.idField);
        EditText phoneField=(EditText)findViewById(R.id.phoneField);
        EditText passwordField=(EditText)findViewById(R.id.passwordField);
        String firstName = firstNameField.getText().toString();
        String lastName = lastNameField.getText().toString();
        String email = emailField.getText().toString();
        String id = idField.getText().toString();
        String phone = phoneField.getText().toString();
        String password = passwordField.getText().toString();
        if (firstName.isEmpty() || email.isEmpty() || lastName.isEmpty() || id.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            Snackbar.make(v, "Please fill in the fields.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(FIRSTNAME, firstName);
            editor.putString(LASTNAME, lastName);
            editor.putString(EMAIL, email);
            editor.putString(ID, id);
            editor.putString(PHONE, phone);
            editor.putString(PASSWORD, password);
            editor.commit();
            int idNumber;
            idNumber = Integer.parseInt(id);
            member = new Member(idNumber, firstName, lastName, password, email, phone);
            if(populateDB(member)) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                //do nothing????
            }
        }
    }

    public boolean populateDB(Member member) {
        if(db.selectMember(member.getId())) {
            db.InsertMember(member);
            return true;
        }
        else {
            return false;
        }
    }
}
