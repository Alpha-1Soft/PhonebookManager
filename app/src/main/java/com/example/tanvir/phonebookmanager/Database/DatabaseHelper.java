package com.example.tanvir.phonebookmanager.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "contacts.db";
    public static final int DATABASE_VERSION = 1;

    //database table components
    public static final String CONTACT_TABLE = "contact";
    public static final String CONTACT_ID = "id";
    public static final String CONTACT_NAME = "name";
    public static final String CONTACT_NUMBER = "number";
    public static final String CONTACT_EMAIL = "email";
    public static final String CONTACT_DESCRIPTION = "description";

    //query for create table
    public static final String CREATE_TABLE_QUERY = "create table "+CONTACT_TABLE
            +"("+CONTACT_ID+" integer primary key autoincrement,"
            +CONTACT_NAME+" text,"+CONTACT_NUMBER+" text,"
            +CONTACT_EMAIL+" text,"+CONTACT_DESCRIPTION+" text);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if EXISTS "+CONTACT_TABLE);
        onCreate(sqLiteDatabase);
    }
}
