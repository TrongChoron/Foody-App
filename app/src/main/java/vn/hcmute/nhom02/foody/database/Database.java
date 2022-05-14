package vn.hcmute.nhom02.foody.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Tue, 4/26/2022
 * Time     : 8:56 PM
 * Filename : Database
 */
public class Database extends SQLiteOpenHelper {
    public static final String DBNAME = "test2.db";

    public Database(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    // truy vấn không trả kết quả: CREATE, INSERT, UPDATE, DELETE...
    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //Truy vấn có trả kết quả: SELECT
    public Cursor getData(String query) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(query, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        Create Table user
        sqLiteDatabase.execSQL("create table if not exists user (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name varchar(255)," +
                "email varchar(255) unique," +
                "password varchar(20)," +
                "phone varchar(10) ," +
                "address varchar(255)," +
                "avatar blob)");
//        Create Category table
        sqLiteDatabase.execSQL("create table if not exists category(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name varchar(255), " +
                "code varchar(255)"+
                ")");
//        Create Restaurant Table
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS restaurant(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name varchar(255), " +
                "address varchar(255)," +
                "pic blob,"+
                "categoryID INTEGER NOT NULL," +
                " FOREIGN KEY (categoryID) REFERENCES category(id))"
        );
//        Create Food Table
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS food(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name varchar(255), " +
                "description varchar(255)," +
                "price float,"+
                "pic blob,"+
                "restaurantID INTEGER NOT NULL," +
                " FOREIGN KEY (restaurantID) REFERENCES restaurant(id))"
        );
//        Create Order Table
        sqLiteDatabase.execSQL("create table if not exists orders (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "photo_food blob, " +
                "count INTEGER, " +
                "food_name varchar(255), " +
                "food_description varchar(255), " +
                "price float, " +
                "product_id INTEGER, " +
                "user_id INTEGER" +
                ")");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

}
