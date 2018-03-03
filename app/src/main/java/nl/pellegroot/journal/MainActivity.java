package nl.pellegroot.journal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        ListView LV = (ListView) findViewById(R.id.LVmain);
        Cursor cursor = db.selectAll();
        EntryAdapter entryAdapter = new EntryAdapter(this, cursor);
        LV.setAdapter(entryAdapter);
        LV.setOnItemLongClickListener(new ListViewLongClicked());
    }

    public void addButtonClicked(View view){

        // onclick start new activity input
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    private class ListViewClicked implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        }
    }

    private class ListViewLongClicked implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.d("clicked? ", "onItemLongClick: ");
            EntryDatabase entryDB = EntryDatabase.getInstance(getApplicationContext());
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            long id = cursor.getLong(0);
            Log.d("before delete", "onItemLongClick: " + id);
            entryDB.delete(id);
            return false;
        }
    }
}
