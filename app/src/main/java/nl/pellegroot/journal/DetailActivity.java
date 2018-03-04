package nl.pellegroot.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // get the intent
        Intent intent = getIntent();

        // get clicked entry from the intent
        JournalEntry entry = (JournalEntry) intent.getSerializableExtra("ClickedEntry");
        int id = intent.getIntExtra("ClickedId", 0);

        // set the views
        TextView entryDateTime = (TextView) findViewById(R.id.getEntryDate);
        TextView entryTitle = (TextView) findViewById(R.id.getEntryTitle);
        TextView entryContent = (TextView) findViewById(R.id.getEntry);
        TextView entryMood = (TextView) findViewById(R.id.getEmoji);

        // get datetime from db
        EntryDatabase entryDB = EntryDatabase.getInstance(this);
        String datetime = entryDB.getDatetime(id);

        // set values to the view
        entryDateTime.setText(datetime);
        entryTitle.setText(entry.getTitle());
        entryContent.setText(entry.getTitle());
        entryMood.setText(entry.getMood());
    }
}
