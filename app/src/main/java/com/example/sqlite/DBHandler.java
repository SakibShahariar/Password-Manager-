package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "password_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "passwords";
    private static final String ID_COL = "id";
    private static final String WEBSITE_COL = "website";
    private static final String NAME_COL = "name";
    private static final String PASSWORD_COL = "password";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WEBSITE_COL + " TEXT,"
                + NAME_COL + " TEXT,"
                + PASSWORD_COL + " TEXT)";

        db.execSQL(query);
    }


    public void addNewPass(String websiteName, String userName, String passWord) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(WEBSITE_COL, websiteName);
        values.put(NAME_COL, userName);
        values.put(PASSWORD_COL, passWord);


        db.insert(TABLE_NAME, null, values);

        db.close();
    }


    public ArrayList<PasswordModal> readPass() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorPasswords = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<PasswordModal> passwordModalArrayList = new ArrayList<>();

        if (cursorPasswords.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                passwordModalArrayList.add(new PasswordModal(cursorPasswords.getString(1),
                        cursorPasswords.getString(3),
                        cursorPasswords.getString(2)));
            } while (cursorPasswords.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorPasswords.close();
        return passwordModalArrayList;
    }

    // below is the method for deleting our course.
//    public void deleteCourse(String courseName) {
//
//        // on below line we are creating
//        // a variable to write our database.
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // on below line we are calling a method to delete our
//        // course and we are comparing it with our course name.
//        db.delete(TABLE_NAME, "name=?", new String[]{courseName});
//        db.close();
//    }
//
//    public void updateCourse(String originalCourseName, String courseName, String courseDescription,
//                             String courseTracks, String courseDuration) {
//
//        // calling a method to get writable database.
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        // on below line we are passing all values
//        // along with its key and value pair.
//        values.put(NAME_COL, courseName);
//        values.put(WEBSITE_COL, courseDuration);
//        values.put(PASSWORD_COL, courseTracks);
//
//        // on below line we are calling a update method to update our database and passing our values.
//        // and we are comparing it with name of our course which is stored in original name variable.
//        db.update(TABLE_NAME, values, "name=?", new String[]{originalCourseName});
//        db.close();
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
