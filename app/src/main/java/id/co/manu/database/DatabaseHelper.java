package id.co.manu.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ManuLocalDB";
    private static final int DATABASE_VERSION = 1;

    SQLiteDatabase writeableDb;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        writeableDb = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE transactionHistory (" +
                "id text primary key," +
                "userId text,"+
                "name text,"+
                "category text,"+
                "address text,"+
                "quantity text,"+
                "totalPrice string,"+
                "transactionDate sttring)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getAllTransaction(String userId){
        Cursor cursor = writeableDb.rawQuery("SELECT * FROM transactionHistory WHERE userId =?", new String[] {userId});
        return cursor;
    }

    public void deleteAllTransaction(){
        writeableDb.execSQL("DELETE FROM transactionHistory;", null);
    }

}
