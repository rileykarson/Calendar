package com.youthdiversion.calendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Christopher on 2/6/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "YouthDiversion";

    // Table Names
    private static final String TABLE_MEMBER = "member";
    private static final String TABLE_AVAILABILITY = "availability";


    //member columns
    private static final String MEMBER_LNAME = "lName";
    private static final String MEMBER_FNAME = "fName";
    private static final String MEMBER_PHONE = "phone";
    private static final String MEMBER_EMAIL = "email";
    private static final String MEMBER_PASSWORD = "password";
    private static final String MEMBER_KEY = "member_id";

    //Availability column
    private static final String AVAIL_KEY = "avail_id";
    private static final String AVAIL_DATE = "date";
    private static final String AVAIL_START = "startTime";
    private static final String AVAIL_END = "endTime";
    private static final String AVAIL_FOREIGN = "member_id";



    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_MEMBER = "CREATE TABLE "
            + TABLE_MEMBER + "(" + MEMBER_KEY + " INTEGER PRIMARY KEY," + MEMBER_EMAIL
            + " TEXT," + MEMBER_PASSWORD + " TEXT," + MEMBER_PHONE + " TEXT,"+ MEMBER_FNAME + " TEXT," + MEMBER_LNAME
            + " TEXT" + ")";

    // Tag table create statement
    private static final String CREATE_TABLE_AVAILABILITY = "CREATE TABLE "
            + TABLE_AVAILABILITY + "(" + AVAIL_KEY + " INTEGER PRIMARY KEY," + AVAIL_DATE
            + " DATETIME," + AVAIL_START + " DATETIME," + AVAIL_END + " DATETIME,"+ AVAIL_FOREIGN + " INTEGER FOREIGN KEY," + ")";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MEMBER);
        db.execSQL(CREATE_TABLE_AVAILABILITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AVAILABILITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);

        // create new tables
        onCreate(db);
    }
}
