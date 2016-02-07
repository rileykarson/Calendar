package com.youthdiversion.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

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
    private static final String AVAIL_START = "startTime";
    private static final String AVAIL_END = "endTime";
    private static final String AVAIL_FOREIGN = "member_id";
    private static final String AVAIL_SENT = "sent";



    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_MEMBER = "CREATE TABLE "
            + TABLE_MEMBER + "(" + MEMBER_KEY + " INTEGER PRIMARY KEY," + MEMBER_EMAIL
            + " TEXT," + MEMBER_PASSWORD + " TEXT," + MEMBER_PHONE + " TEXT,"+ MEMBER_FNAME + " TEXT," + MEMBER_LNAME
            + " TEXT" + ")";

    // Tag table create statement
    private static final String CREATE_TABLE_AVAILABILITY = "CREATE TABLE "
            + TABLE_AVAILABILITY + "(" + AVAIL_KEY + " INTEGER PRIMARY KEY," +
            AVAIL_START + " TEXT," + AVAIL_END + " TEXT," + AVAIL_SENT + " TEXT," + AVAIL_FOREIGN + " INTEGER" + ")";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
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

    public static String format(GregorianCalendar calendar){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }

    //creating an input into the availability table
    public long InsertAvailability(Availability availability)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AVAIL_KEY, availability.getId());
        values.put(AVAIL_FOREIGN, availability.getMember_id());
        values.put(AVAIL_START, availability.getStartTime());
        values.put(AVAIL_END, availability.getEndTime());
        values.put(AVAIL_SENT, availability.getSent());

        long availability_id = db.insert(TABLE_AVAILABILITY, null, values);

        return availability_id;
    }

    //update an input in the member table
    public int updateAvailability(Availability availability)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AVAIL_KEY, availability.getId());
        values.put(AVAIL_START, availability.getStartTime());
        values.put(AVAIL_END, availability.getEndTime());
        values.put(AVAIL_FOREIGN, availability.getMember_id());
        values.put(AVAIL_SENT, availability.getSent());

        return db.update(TABLE_AVAILABILITY, values, AVAIL_KEY + "= ?", new String[] {String.valueOf(availability.getId())});

    }

    //deleting an input in the availability table
    public void DeleteAvailability(long availabilityID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_AVAILABILITY, AVAIL_KEY + " = ?", new String[]{String.valueOf(availabilityID)});
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

    public boolean selectMember(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_MEMBER + " WHERE "
                + MEMBER_KEY + " = " + id;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            return true;
        else
            return false;
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
