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

        // set the views
        TextView entryDateTime = findViewById(R.id.getEntryDate);
        TextView entryTitle = findViewById(R.id.getEntryTitle);
        TextView entryContent = findViewById(R.id.getEntry);
        TextView entryMood = findViewById(R.id.getEmoji);

        // get datetime from db
        EntryDatabase entryDB = EntryDatabase.getInstance(this);

        // format timestamp to string
        String timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(entry.getTimestamp());

        // set values to the view
        entryDateTime.setText(timestamp);
        entryTitle.setText(entry.getTitle());
        entryContent.setText(entry.getContent());
        entryMood.setText(entry.getMood());
    }
}
