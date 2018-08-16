package com.example.tanvir.phonebookmanager.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.tanvir.phonebookmanager.models.ContactsInfo;

import java.util.ArrayList;

public class DatabaseManager {

    DatabaseHelper databaseHelper;

    public DatabaseManager(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    long addContactInfo(ContactsInfo contactsInfo) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CONTACT_NAME,contactsInfo.getContactName());
        contentValues.put(DatabaseHelper.CONTACT_NUMBER,contactsInfo.getContactNumber());
        contentValues.put(DatabaseHelper.CONTACT_EMAIL,contactsInfo.getContactEmail());
        contentValues.put(DatabaseHelper.CONTACT_EMAIL,contactsInfo.getContactEmail());
        long insertedRow=sqLiteDatabase.insert(DatabaseHelper.CONTACT_TABLE,null,contentValues);
        sqLiteDatabase.close();
        return insertedRow;
    }

    public ArrayList<ContactsInfo> getAllStudent(){
        SQLiteDatabase sqLiteDatabase=databaseHelper.getReadableDatabase();
        ArrayList<ContactsInfo>contacts=new ArrayList<>();
        String selectQuery="select * from "+DatabaseHelper.CONTACT_TABLE;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.CONTACT_ID));
                String contactName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_NAME));
                String contactNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_NUMBER));
                String contactEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_EMAIL));
                String contactDescription = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_DESCRIPTION));
                ContactsInfo contactsInfo=new ContactsInfo(id,contactName,contactNumber,contactEmail,contactDescription);
                contacts.add(contactsInfo);
            }while(cursor.moveToNext());
        }
        return contacts;
    }


}
