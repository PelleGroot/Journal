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
    // create private instances
    private EntryDatabase entryDB;
    private EntryAdapter entryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // connect to db
        entryDB = EntryDatabase.getInstance(getApplicationContext());
        // get all entries from db
        Cursor cursor = entryDB.selectAll();
        // set the  ListView
        ListView LV = (ListView) findViewById(R.id.LVmain);
        // set the adapter
        entryAdapter = new EntryAdapter(this, cursor);
        LV.setAdapter(entryAdapter);
        //set the longClickListener
        LV.setOnItemLongClickListener(new ListViewLongClicked());
        LV.setOnItemClickListener(new ListViewItemClick());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // update the view on resume
        updateData();
    }

    public void addButtonClicked(View view){

        // onclick start new activity input
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    private class ListViewItemClick implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            // find the entry which was clicked
            JournalEntry clickedEntry = (JournalEntry) adapterView.getItemAtPosition(i);

            // get the id of the entry that was clicked
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            int id = cursor.getInt(0);

            // send the clicked on to the new activity
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("ClickedEntry", clickedEntry);
            intent.putExtra("ClickedId", id);
            startActivity(intent);
        }
    }

    private class ListViewLongClicked implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            // connect to db
            entryDB = EntryDatabase.getInstance(getApplicationContext());
            Log.d("clicked? ", "onItemLongClick: ");
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            int id = cursor.getInt(0);
            Log.d("before delete", "onItemLongClick: " + id);
            entryDB.delete(id);
            // update the view
            updateData();
            return false;
        }
    }

    private void updateData(){
        Log.d("in", "updateData: ");
        // connect to db
        entryDB = EntryDatabase.getInstance(getApplicationContext());

        // get updated data
        Cursor newCursor = entryDB.selectAll();

        // put the updated data into the adapter
        entryAdapter.swapCursor(newCursor);
    }

}
