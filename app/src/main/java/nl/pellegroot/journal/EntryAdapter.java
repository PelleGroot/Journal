package nl.pellegroot.journal;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, Cursor c) {
        super(context, R.layout.entry_row, c);

    }
    public void bindView(View view, Context context, Cursor c){
        // get the title and timestamp from the DB
        String title = c.getString(c.getColumnIndex("title"));
        String timestamp = c.getString(c.getColumnIndex("timestamp"));

        // get the view from the entry_row
        TextView entryDate = view.findViewById(R.id.entryDate);
        TextView entryTitle = view.findViewById(R.id.entryTitle);

        // set the text of the entry_row
        entryDate.setText(timestamp);
        entryTitle.setText(title);
    }
}
