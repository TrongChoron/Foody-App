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
    public static final String DBNAME="test.db";
    public Database(@Nullable Context context) {
        super(context, "test.db", null, 1);
    }

    // truy vấn không trả kết quả: CREATE, INSERT, UPDATE, DELETE...
    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //Truy vấn có trả kết quả: SELECT
    public Cursor getData(String query){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(query,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists user (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name varchar(255)," +
                        "email varchar(255) unique," +
                        "password varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE if exists user");
    }

    public boolean checkUserName(String userName){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from user where name = ?", new String[]{userName});
        if (cursor.getCount()>0)
            return true;
        else return false;
    }
    public  boolean checkEmailAndPassword(String email, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from user where email = ? and password = ?", new String[]{email,password});
        if (cursor.getCount()>0)
            return true;
        else return false;
    }
}
