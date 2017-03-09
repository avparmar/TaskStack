package com.zopzoob.taskstack;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by avparmar on 3/7/17.
 */

public class TwoLineDBAdaptor extends CursorAdapter {

    private LayoutInflater inflater;

    public TwoLineDBAdaptor(Context context, Cursor c, int flags) {
        super(context,c,flags);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(R.layout.two_line_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView t1 = (TextView) view.findViewById(android.R.id.text1);
        TextView t2 = (TextView) view.findViewById(android.R.id.text2);

        String t1Text = cursor.getString(cursor.getColumnIndex("title"));
        String t2Text = cursor.getString(cursor.getColumnIndex("description"));

        if (t1Text.length() > 45) t1Text = t1Text.substring(0,42) + "...";
        if (t2Text.length() > 130) t2Text = t2Text.substring(0,127) + "...";

        t1.setText(t1Text);
        t2.setText(t2Text);

    }
}
