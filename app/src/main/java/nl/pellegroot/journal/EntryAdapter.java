package nl.pellegroot.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, int layout, Cursor c) {
        super(context, R.layout.entry_row, c);

    }
        public void bindView(View view, Context context, Cursor c){
        // get the title and timestamp from the DB
        String title = c.getString(c.getColumnIndex("title"));
        String timestamp = c.getString(c.getColumnIndex("timestamp"));

        // get the view from the entry_row
        TextView entryDate = (TextView) view.findViewById(R.id.entryDate);
        TextView entryTitle = (TextView) view.findViewById(R.id.entryTitle);

        // set the text of the entry_row
        entryDate.setText(timestamp);
        entryTitle.setText(title);
    }
}
