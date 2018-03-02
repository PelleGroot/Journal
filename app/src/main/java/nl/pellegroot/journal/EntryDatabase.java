package nl.pellegroot.journal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EntryDatabase extends SQLiteOpenHelper {

    public static final String dbName = "journalDB";
    public static final int dbVersion = 2;
    private static EntryDatabase instance;

    public static EntryDatabase getInstance(Context context){
        if (instance == null){
            Log.d("database", "getInstance: ");
            instance = new EntryDatabase(context, dbName, null, dbVersion);
        }
        return instance;
    }

    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create DB
        Log.d("database", "onCreate: ");
        db.execSQL("CREATE TABLE entries (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, mood INTEGER, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)");
        db.execSQL("INSERT INTO entries (title, content, mood) VALUES ('Test', 'Testing', 2)");
    }

  @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
      Log.d("database", "onUpgrade: ");
        db.execSQL("DROP TABLE entries");
        onCreate(db);
    }

    public Cursor selectAll() {
        Log.d("database", "SelectAll: ");
        return this.getWritableDatabase().rawQuery("SELECT * FROM entries", null, null);
    }

//    public insert(Entry){
        // open a connection with the db
        // create new ContentValues
        // use Put method to add values (title, content, mood)
        // call insert on DB (nullColumnHack may be null)
//    }

}

