package nl.pellegroot.journal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {

    public static final String dbName = "journalDB";
    public static final int dbVersion = 1;
    private static EntryDatabase instance;

    public static EntryDatabase getInstance(Context context){
        if (instance == null){
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
        db.execSQL("CREATE TABLE entries (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, mood INTEGER, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)");
        db.execSQL("INSERT INTO entries (title, content, mood) VALUES (Test, Testing, 2)");
    }

  @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE entries");
        onCreate(db);
    }

    public Cursor selectAll(SQLiteDatabase db) {
        db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM entries", null, null);
    }

//    public insert(Entry){
        // open a connection with the db
        // create new ContentValues
        // use Put method to add values (title, content, mood)
        // call insert on DB (nullColumnHack may be null)
//    }

}

