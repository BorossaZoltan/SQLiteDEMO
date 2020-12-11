package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "tanulo.db";
    public static final int DB_VERSION = 1;

    public static final String TANULO_TABLE = "tanulo";

    public static final String COL_ID = "id";
    public static final String COL_NEV = "nev";
    public static final String COL_EMAIL = "emial";
    public static final String COL_JEGY = "jegy";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TANULO_TABLE + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NEV + " VARCHAR(255) NOT NULL, " +
                COL_EMAIL + " VARCHAR(255) NOT NULL, " +
                COL_JEGY + " INTEGER NOT NULL " +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TANULO_TABLE;
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean adatRogzites(String nev, String email, String jegy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NEV, nev);
        values.put(COL_EMAIL, email);
        values.put(COL_JEGY, jegy);
        return db.insert(TANULO_TABLE, null, values) != -1;

    }

    public Cursor adatLekerdezes() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(TANULO_TABLE, new String[]{COL_ID, COL_NEV, COL_EMAIL, COL_JEGY}, null,
                null, null, null, null);
        //db.rawQuery("SELECT * FROM "+TANULO_TABLE, null);

    }

    public boolean idEllenorzes(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TANULO_TABLE + " WHERE " + COL_ID + " = ?", new String[]{id});
        return result.getCount() == 1;
    }

    public boolean adatModositas(String id, String nev, String email, String jegy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NEV, nev);
        values.put(COL_EMAIL, email);
        values.put(COL_JEGY, jegy);
        return db.update(TANULO_TABLE, values, COL_ID + " = ?", new String[]{id}) == 1;
    }

    public boolean adatTorles(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TANULO_TABLE, COL_ID + " = ?", new String[]{id}) == 1;
    }
}
