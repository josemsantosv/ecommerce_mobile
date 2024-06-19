package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "catalog.db";
    public static final String USER_TABLE_NAME = "users";
    public static final String PRODUCT_TABLE_NAME = "products";

    // Columnas para la tabla de usuarios
    public static final String USER_COLUMN_USERNAME = "username";
    public static final String USER_COLUMN_PASSWORD = "password";

    // Columnas para la tabla de productos
    public static final String PRODUCT_COLUMN_ID = "id";
    public static final String PRODUCT_COLUMN_NAME = "name";
    public static final String PRODUCT_COLUMN_PRICE = "price";
    public static final String PRODUCT_COLUMN_IMAGE = "image";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla de usuarios
        db.execSQL("CREATE TABLE " + USER_TABLE_NAME + " (" +
                USER_COLUMN_USERNAME + " TEXT PRIMARY KEY, " +
                USER_COLUMN_PASSWORD + " TEXT)");

        // Crear tabla de productos
        db.execSQL("CREATE TABLE " + PRODUCT_TABLE_NAME + " (" +
                PRODUCT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PRODUCT_COLUMN_NAME + " TEXT, " +
                PRODUCT_COLUMN_PRICE + " REAL, " +
                PRODUCT_COLUMN_IMAGE + " TEXT)");

        // Insertar datos iniciales de productos
        db.execSQL("INSERT INTO " + PRODUCT_TABLE_NAME + " (name, price, image) VALUES ('Cotton Dress', 10, 'product_four')");
        db.execSQL("INSERT INTO " + PRODUCT_TABLE_NAME + " (name, price, image) VALUES ('Denim Top', 11, 'product_four')");
        db.execSQL("INSERT INTO " + PRODUCT_TABLE_NAME + " (name, price, image) VALUES ('Denim Shorts', 8, 'product_four')");
        db.execSQL("INSERT INTO " + PRODUCT_TABLE_NAME + " (name, price, image) VALUES ('Cotton Top', 11, 'product_four')");
        db.execSQL("INSERT INTO " + PRODUCT_TABLE_NAME + " (name, price, image) VALUES ('Flare Top', 9, 'product_four')");
        db.execSQL("INSERT INTO " + PRODUCT_TABLE_NAME + " (name, price, image) VALUES ('Denim shorts', 10, 'product_four')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME);
        onCreate(db);
    }

    // Métodos para manejar la tabla de usuarios
    public Boolean insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_USERNAME, username);
        values.put(USER_COLUMN_PASSWORD, password);
        long result = db.insert(USER_TABLE_NAME, null, values);
        return result != -1;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_USERNAME + "=?", new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_USERNAME + "=? AND " + USER_COLUMN_PASSWORD + "=?", new String[]{username, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Métodos para manejar la tabla de productos
    public Boolean insertProduct(String name, double price, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_COLUMN_NAME, name);
        values.put(PRODUCT_COLUMN_PRICE, price);
        values.put(PRODUCT_COLUMN_IMAGE, image);
        long result = db.insert(PRODUCT_TABLE_NAME, null, values);
        return result != -1;
    }

    public Boolean updateProduct(int id, String name, double price, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_COLUMN_NAME, name);
        values.put(PRODUCT_COLUMN_PRICE, price);
        values.put(PRODUCT_COLUMN_IMAGE, image);
        long result = db.update(PRODUCT_TABLE_NAME, values, PRODUCT_COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        return result != -1;
    }

    public Boolean deleteProduct(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(PRODUCT_TABLE_NAME, PRODUCT_COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        return result != -1;
    }

    public Cursor getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + PRODUCT_TABLE_NAME + " WHERE " + PRODUCT_COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Cursor getAllProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + PRODUCT_TABLE_NAME, null);
    }

    // Previous version's methods

    // Insert data into users table
    public Boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long result = db.insert("users", null, values);
        return result != -1;
    }

    // Check if username exists in users table
    public Boolean checkusername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    // Check if username and password match in users table
    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
