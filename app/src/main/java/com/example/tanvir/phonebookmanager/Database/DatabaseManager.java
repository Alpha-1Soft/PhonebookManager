package com.example.tanvir.phonebookmanager.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tanvir.phonebookmanager.models.ContactsInfo;

import java.util.ArrayList;

public class DatabaseManager {

    DatabaseHelper databaseHelper;

    public DatabaseManager(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public long addContactInfo(ContactsInfo contactsInfo) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CONTACT_NAME, contactsInfo.getContactName());
        contentValues.put(DatabaseHelper.CONTACT_NUMBER, contactsInfo.getContactNumber());
        contentValues.put(DatabaseHelper.CONTACT_EMAIL, contactsInfo.getContactEmail());
        contentValues.put(DatabaseHelper.CONTACT_DESCRIPTION, contactsInfo.getContactDescription());
        contentValues.put(DatabaseHelper.CONTACT_RATING, contactsInfo.getContactRating());
        long insertedRow = sqLiteDatabase.insert(DatabaseHelper.CONTACT_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return insertedRow;
    }


    public long updateContactInfo(ContactsInfo contactsInfo) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.CONTACT_NAME, contactsInfo.getContactName());
        contentValues.put(DatabaseHelper.CONTACT_NUMBER, contactsInfo.getContactNumber());
        contentValues.put(DatabaseHelper.CONTACT_EMAIL, contactsInfo.getContactEmail());
        contentValues.put(DatabaseHelper.CONTACT_DESCRIPTION, contactsInfo.getContactDescription());
        contentValues.put(DatabaseHelper.CONTACT_RATING, contactsInfo.getContactRating());
        long updatedRow = sqLiteDatabase.update(DatabaseHelper.CONTACT_TABLE, contentValues,
                DatabaseHelper.CONTACT_ID + " =? ", new String[]{String.valueOf(contactsInfo.getId())});
        return updatedRow;
    }

    public long deleteContactInfo(int id) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        long deletedRow = sqLiteDatabase.delete(DatabaseHelper.CONTACT_TABLE, DatabaseHelper.CONTACT_ID + " =? ",
                new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return deletedRow;
    }


    public ArrayList<ContactsInfo> getAllContacts() {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        ArrayList<ContactsInfo> contacts = new ArrayList<>();
        String selectQuery = "select * from " + DatabaseHelper.CONTACT_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.CONTACT_ID));
                String contactName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_NAME));
                String contactNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_NUMBER));
                String contactEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_EMAIL));
                String contactDescription = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_DESCRIPTION));
                String contactRating = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_RATING));
                ContactsInfo contactsInfo = new ContactsInfo(id, contactName, contactNumber, contactEmail, contactDescription, contactRating);
                contacts.add(contactsInfo);
            } while (cursor.moveToNext());
        }
        return contacts;
    }

    public ContactsInfo getContactsById(int id) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        String selectQuery = "select * from " + DatabaseHelper.CONTACT_TABLE
                + " where id = " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        ContactsInfo contactsInfo = null;
        if (cursor.moveToFirst()) {
            String contactName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_NAME));
            String contactNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_NUMBER));
            String contactEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_EMAIL));
            String contactDescription = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_DESCRIPTION));
            String contactRating = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_RATING));
            contactsInfo = new ContactsInfo(id, contactName, contactNumber, contactEmail, contactDescription, contactRating);
        }
        return contactsInfo;
    }

    public ArrayList<ContactsInfo> getContactByFavorite(){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        ArrayList<ContactsInfo> contacts = new ArrayList<>();
        String selectQuery = "select * from " + DatabaseHelper.CONTACT_TABLE
                + " where rating = " + "1";
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery,null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.CONTACT_ID));
            String contactName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_NAME));
            String contactNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_NUMBER));
            String contactEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_EMAIL));
            String contactDescription = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_DESCRIPTION));
            String contactRating = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_RATING));
            ContactsInfo contactsInfo = new ContactsInfo(id,contactName, contactNumber, contactEmail, contactDescription, contactRating);
            contacts.add(contactsInfo);
        }
        return contacts;
    }
}
