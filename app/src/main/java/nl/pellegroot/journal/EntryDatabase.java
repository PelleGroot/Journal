package nl.pellegroot.journal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create DB, For assigning current datestamp to date use: DATETIME DEFAULT CURRENT_TIMESTAMP
        db.execSQL("CREATE TABLE entries (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, MOOD INTEGER, timestamp DATETIME)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
}
