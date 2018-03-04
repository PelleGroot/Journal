package nl.pellegroot.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Timestamp;

public class EntryDatabase extends SQLiteOpenHelper {

    public static final String dbName = "journalDB";
    public static final int dbVersion = 5;
    private static EntryDatabase instance;

    public static EntryDatabase getInstance(Context context){
        // check if the instance is empty, and create a new DB of the instance is
        if (instance == null){
            instance = new EntryDatabase(context, dbName, null, dbVersion);
        }
        return instance;
    }

    //constructor of the EntryDatabase
    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create DB tables
        db.execSQL("CREATE TABLE entries (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, mood INTEGER, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)");
        // fill with a test entry
        db.execSQL("INSERT INTO entries (title, content, mood) VALUES ('Test', 'Testing', 2)");
    }

  @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // drop DB table and call the oncreate
        db.execSQL("DROP TABLE entries");
        onCreate(db);
    }

    public Cursor selectAll() {
        // select all the entries in the DB
        return this.getWritableDatabase().rawQuery("SELECT * FROM entries", null, null);
    }

    public void insert(JournalEntry entry){
        // open a connection with the db
        SQLiteDatabase db = getWritableDatabase();

        // create new ContentValues
        ContentValues contentValues = new ContentValues();

        // use Put method to add values (title, content, mood)
        contentValues.put("title", entry.getTitle());
        contentValues.put("content", entry.getContent());
        contentValues.put("mood", entry.getMood());

        // call insert on DB (nullColumnHack may be null)
        db.insert("entries",null,contentValues);
    }
    public void delete(long id){
        Log.d("in delete", "delete: " + id);
        // create a query with the right id
        String SQL = String.format("DELETE FROM entries where _id = %d", id);
        Log.d("in delete", "SQL: " + SQL);
        // delete the entry using the query above
        this.getWritableDatabase().rawQuery(SQL, null, null);
    }

    public String getDatetime(int id){
        String SQL = String.format("SELECT timestamp FROM entries WHERE _id = " + id);
        Cursor cursor = this.getWritableDatabase().rawQuery(SQL, null, null);
        String datetime = cursor.getString(0);
        return datetime;
    }

}

