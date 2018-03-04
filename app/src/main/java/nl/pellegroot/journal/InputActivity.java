package nl.pellegroot.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void addEntry (View view){
        EntryDatabase entrydb = EntryDatabase.getInstance(this);
        JournalEntry entry = new JournalEntry();

        EditText title = (EditText) findViewById(R.id.putEntryTitle);
        EditText content = (EditText) findViewById(R.id.putEntry);
        EditText mood = (EditText) findViewById(R.id.putEntryMood);

        entry.setTitle(title.getText().toString());
        entry.setContent(content.getText().toString());
        entry.setMood(mood.getText().toString());
        entrydb.insert(entry);

        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
