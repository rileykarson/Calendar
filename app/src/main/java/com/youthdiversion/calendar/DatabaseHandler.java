package com.youthdiversion.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Console;

/**
 * Created by Christopher on 2/6/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    SQLiteDatabase db;


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "YouthDiversion";

    // Table Names
    private static final String TABLE_MEMBER = "member";
    private static final String TABLE_AVAILABILITY = "availability";


    //member columns
    private static final String MEMBER_KEY = "member_id";
    private static final String MEMBER_LNAME = "lName";
    private static final String MEMBER_FNAME = "fName";
    private static final String MEMBER_PHONE = "phone";
    private static final String MEMBER_EMAIL = "email";
    private static final String MEMBER_PASSWORD = "password";


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
            + " DATETIME," + AVAIL_START + " DATETIME," + AVAIL_END + " DATETIME,"+ AVAIL_FOREIGN + " INTEGER" + ")";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("DATABASE on Create");
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

    //creating an input into the availability table
    public long InsertAvailability(Availability availability)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AVAIL_KEY, availability.getId());
        values.put(AVAIL_FOREIGN, availability.getMember_id());
        db.execSQL("INSERT INTO availability(date, startTime, endtime, member_id) VALUES('" + availability.getDate() +
                "', '" + availability.getStartTime() + "', " + availability.getEndTime() + "'");

        long availability_id = db.insert(TABLE_AVAILABILITY, null, values);

        return availability_id;
    }

    //update an input in the availability table
    public void UpdateAvailability(Availability availability)
    {
        //TODO: IMPLEMENT AVAIALBILITY UPDATE ONCE DATE CONFIGURED
    }

    //deleting an input in the availability table
    public void DeleteAvailability(long availabilityID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete( TABLE_AVAILABILITY, AVAIL_KEY + " = ?", new String[] {String.valueOf(availabilityID)});
    }


    //creating an input into the member table
    public long InsertMember(Member member)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MEMBER_KEY, member.getId());
        values.put(MEMBER_LNAME, member.getlName());
        values.put(MEMBER_FNAME, member.getfName());
        values.put(MEMBER_PHONE, member.getPhone());
        values.put(MEMBER_EMAIL, member.getEmail());
        values.put(MEMBER_PASSWORD, member.getPassword());


        long member_id = db.insert(TABLE_MEMBER, null, values);

        return member_id;
    }

    //update an input in the member table
    public int updateMember(Member member)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MEMBER_LNAME, member.getlName());
        values.put(MEMBER_FNAME, member.getfName());
        values.put(MEMBER_PHONE, member.getPhone());
        values.put(MEMBER_EMAIL, member.getEmail());
        values.put(MEMBER_PASSWORD, member.getPassword());

        return db.update(TABLE_MEMBER, values, MEMBER_KEY + "= ?", new String[] {String.valueOf(member.getId())});

    }

    //deleting an input in the member table
    public void DeleteMember(long memberID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete( TABLE_MEMBER, MEMBER_KEY + " = ?", new String[] {String.valueOf(memberID)});
    }

}
