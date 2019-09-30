package com.ndevelopers.sincronizacion_sqlite_mysql.Persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentsDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Students.db";

    private static StudentsDbHelper sInstance;

    private StudentsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Nos aseguramos de que solo haya una instancia para evitar errores.
    // Mas detalles:
    // http://www.androiddesignpatterns.com/2012/05/correctly-managing-your-sqlite-database.html
    public static synchronized StudentsDbHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new StudentsDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.Students.SQL_CREATE_STUDENTS_TABLE);
    }

    // Cambia la version del esquema en caso de haber modificaciones.
    // Por simplicidad asumimos que esto no va a pasar y tan solo se resetea la db.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.Students.SQL_DELETE_STUDENTS);
        onCreate(db);
    }

}
