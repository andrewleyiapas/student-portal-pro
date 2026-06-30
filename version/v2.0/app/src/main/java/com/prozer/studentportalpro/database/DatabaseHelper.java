package com.prozer.studentportalpro.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.prozer.studentportalpro.models.Student;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "student_portal.db";
    public static final String TABLE_STUDENTS = "students";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 4); // Bumped to version 4 to fix missing Attendance table
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE " + TABLE_STUDENTS + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT, " +
                        "email TEXT, " +
                        "password TEXT, " +
                        "course TEXT, " +
                        "regNo TEXT)"
        );

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS Attendance (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "studentName TEXT, " +
                        "course TEXT, " +
                        "status TEXT)"
        );

        seedData(db);
    }
    private void seedData(SQLiteDatabase db) {
        insertStudentSeed(db, "Pauline", "pauline@mku.ac.ke", "1234", "BBIT", "BIT/2024/001");
        insertStudentSeed(db, "Michael", "michael@mku.ac.ke", "1234", "Computer Science", "BCS/2024/042");
        insertStudentSeed(db, "John Peter", "john@mku.ac.ke", "1234", "Data Science", "BDS/2024/088");
        insertStudentSeed(db, "Paul", "paul@mku.ac.ke", "1234", "Software Engineering", "BSE/2024/015");
        insertStudentSeed(db, "Sarah Jane", "sarah@mku.ac.ke", "1234", "Cyber Security", "BCS/2024/099");
        insertStudentSeed(db, "David Mark", "david@mku.ac.ke", "1234", "Web Development", "BWD/2024/033");
        insertStudentSeed(db, "Mercy Anne", "mercy@mku.ac.ke", "1234", "IT Management", "BIT/2024/077");
    }

    private void insertStudentSeed(SQLiteDatabase db, String name, String email, String pass, String course, String reg) {
        ContentValues v = new ContentValues();
        v.put("name", name);
        v.put("email", email);
        v.put("password", pass);
        v.put("course", course);
        v.put("regNo", reg);
        db.insert(TABLE_STUDENTS, null, v);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        db.execSQL("DROP TABLE IF EXISTS Attendance");
        onCreate(db);
    }

    public boolean registerStudent(String name, String email, String password, String course, String regNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("password", password);
        values.put("course", course);
        values.put("regNo", regNo);
        long result = db.insert(TABLE_STUDENTS, null, values);
        db.close();
        return result != -1;
    }

    public Student loginStudent(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENTS + " WHERE email=? AND password=?", new String[]{email, password});
        if (cursor != null && cursor.moveToFirst()) {
            Student student = new Student(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(4),
                    cursor.getString(5)
            );
            cursor.close();
            return student;
        }
        return null;
    }

    public boolean updatePassword(int id, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", newPassword);
        int result = db.update(TABLE_STUDENTS, values, "id=?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }

    public boolean insertStudent(String name, String email, String course, String regNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("password", "1234"); // Default password for manual entry
        values.put("course", course);
        values.put("regNo", regNo);
        long result = db.insert(TABLE_STUDENTS, null, values);
        db.close();
        return result != -1;
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENTS, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(new Student(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(4),
                        cursor.getString(5)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public Cursor getAllStudentsCursor() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_STUDENTS, null);
    }

    public Cursor searchStudents(String keyword) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_STUDENTS + " WHERE name LIKE ? OR regNo LIKE ?", new String[]{"%" + keyword + "%", "%" + keyword + "%"});
    }

    public int getStudentCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_STUDENTS, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public boolean updateStudent(int id, String name, String email, String course, String regNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("course", course);
        values.put("regNo", regNo);
        int result = db.update(TABLE_STUDENTS, values, "id=?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }

    public boolean deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_STUDENTS, "id=?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }
    public int getPresentCount() {

        SQLiteDatabase db =
                this.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT COUNT(*) FROM Attendance WHERE status='Present'",
                        null);

        int count = 0;

        if(cursor.moveToFirst())
            count = cursor.getInt(0);

        cursor.close();

        return count;
    }

    public int getAbsentCount() {

        SQLiteDatabase db =
                this.getReadableDatabase();

        Cursor cursor =
                db.rawQuery(
                        "SELECT COUNT(*) FROM Attendance WHERE status='Absent'",
                        null);

        int count = 0;

        if(cursor.moveToFirst())
            count = cursor.getInt(0);

        cursor.close();

        return count;
    }
    public boolean addAttendance(
            String name,
            String course,
            String status) {

        SQLiteDatabase db =
                this.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put("studentName", name);
        values.put("course", course);
        values.put("status", status);

        long result =
                db.insert(
                        "Attendance",
                        null,
                        values);

        db.close();

        return result != -1;
    }public Cursor getAllAttendance() {

        SQLiteDatabase db =
                this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM Attendance",
                null);
    }
}
