package com.youthdiversion.calendar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    public final String FIRSTNAME = "firstnamekey";
    public final String LASTNAME = "lastnamekey";
    public final String EMAIL = "emailkey";
    public final String ID = "idkey";
    public final String PHONE = "phonekey";
    public final String PASSWORD = "passwordkey";
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHandler(getApplicationContext());



        String calFile = "TestCalendar.ics";

        //start time
        java.util.Calendar startCal = java.util.Calendar.getInstance();
        startCal.set(2012, 04, 23, 20, 00);

        //end time
        java.util.Calendar endCal = java.util.Calendar.getInstance();
        endCal.set(2012, 04, 23, 20, 30);

        String subject = "Meeting Subject";
        String location = "Location - Mumbai";
        String description = "This goes in decription section of the metting like agenda etc.";

        String hostEmail = "admin@javaxp.com";

        //Creating a new calendar
        net.fortuna.ical4j.model.Calendar calendar = new net.fortuna.ical4j.model.Calendar();
        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);

        SimpleDateFormat sdFormat =  new SimpleDateFormat("yyyyMMdd'T'hhmmss'Z'");
        String strDate = sdFormat.format(startCal.getTime());

        net.fortuna.ical4j.model.Date startDt = null;
        try {
            startDt = new net.fortuna.ical4j.model.Date(strDate,"yyyyMMdd'T'hhmmss'Z'");
        } catch (Exception e) {
            e.printStackTrace();
        }

        long diff = endCal.getTimeInMillis() - startCal.getTimeInMillis();
        int min = (int)(diff / (1000 * 60));

        Dur dur = new Dur(0,0,min,0);

        //Creating a meeting event
        VEvent meeting = new VEvent(startDt,dur,subject);

        meeting.getProperties().add(new Description());

        try {
            meeting.getProperties().getProperty(Property.DESCRIPTION).setValue(description);
        } catch (Exception e) {
            e.printStackTrace();
        }

        calendar.getComponents().add(meeting);

        FileOutputStream fout = null;

        try {
            fout = new FileOutputStream(calFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        CalendarOutputter outputter = new CalendarOutputter();
        outputter.setValidating(false);

        try {
            outputter.output(calendar, fout);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        System.out.println(meeting);



























        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String name = sharedpreferences.getString(FIRSTNAME, "");
        if (name == "") {
            Intent intent = new Intent(this, CreateAccount.class);
            startActivity(intent);
            finish();
        }

        
       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.remove(FIRSTNAME);
                editor.remove(LASTNAME);
                editor.remove(EMAIL);
                editor.remove(ID);
                editor.remove(PHONE);
                editor.remove(PASSWORD);
                editor.commit();
            }
        });
    }

    public static String format(GregorianCalendar calendar){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
