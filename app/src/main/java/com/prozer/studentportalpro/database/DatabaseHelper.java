package com.prozer.studentportalpro.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.prozer.studentportalpro.models.Student;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "student_portal.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_STUDENTS = "students";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_COURSE = "course";
    private static final String COLUMN_REG_NO = "reg_no";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_STUDENTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_COURSE + " TEXT, " +
                COLUMN_REG_NO + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }

    public boolean registerStudent(String name, String email, String password, String course, String regNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_COURSE, course);
        values.put(COLUMN_REG_NO, regNo);

        long result = db.insert(TABLE_STUDENTS, null, values);
        return result != -1;
    }

    public Student loginStudent(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDENTS,
                null,
                COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{email, password},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Student student = new Student();
            student.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            student.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
            student.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)));
            student.setCourse(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE)));
            student.setRegNo(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_REG_NO)));
            cursor.close();
            return student;
        }

        if (cursor != null) cursor.close();
        return null;
    }
}