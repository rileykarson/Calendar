package com.youthdiversion.calendar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccount extends AppCompatActivity {

    public final String NAME = "namekey";
    public final String EMAIL = "emailkey";
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void onClickSubmitButton(View v) {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String restoredText = sharedpreferences.getString(NAME, "LOL");
        ((Button)v).setText(restoredText);
        EditText nameField=(EditText)findViewById(R.id.nameField);
        EditText emailField=(EditText)findViewById(R.id.emailField);
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        if (name.isEmpty() || email.isEmpty()) {
            Snackbar.make(v, "Please fill in the fields.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(NAME, name);
            editor.putString(EMAIL, email);
            editor.commit();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
