package com.zopzoob.taskstack;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by avparmar on 3/9/17.
 */

public class CreateTag extends AppCompatActivity {
    private SQLiteDatabase mastermind;

    protected void makeLogs(View view) {

        try {
            //    Cursor c = mastermind.rawQuery("SELECT * FROM TasksV1 WHERE title = " + name, null);
            EditText tName = (EditText) findViewById(R.id.tagName);
            String name = tName.getText().toString();
            Cursor c = mastermind.rawQuery("SELECT * FROM " + name, null);

            c.moveToFirst();
            while(c != null) {
                Log.i("dbdbd - id", c.getString(c.getColumnIndex("taskID")));
                c.moveToNext();
            }
            c.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void getTags(View view) {
        try {
            Cursor c = mastermind.rawQuery("SELECT * FROM tags", null);

            c.moveToFirst();
            while(c != null) {
                Log.i("dbdbd - tags", c.getString(c.getColumnIndex("name")));
                c.moveToNext();
            }
            c.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void getTables(View view) {
        try {
            Cursor c = mastermind.rawQuery("SELECT * FROM tags", null);

            c.moveToFirst();
            while(c != null) {
                Log.i("dbdbd - tables", c.getString(c.getColumnIndex("name")));
                c.moveToNext();
            }
            c.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void makeTag(View view) {
        EditText tName = (EditText) findViewById(R.id.tagName);

        String name = tName.getText().toString();
        name.replace(' ', '_');

        Cursor c = mastermind.rawQuery("SELECT name FROM sqlite_master WHERE type='table' ORDER BY name;", null);

        c.moveToFirst();
        while(c != null) {
            if(c.getString(c.getColumnIndex("name")).equals(name)) {
                Toast.makeText(getApplicationContext(),"invalid tag name", Toast.LENGTH_LONG).show();
                return;
            }
            c.moveToNext();
        }
        c.close();

        if (name.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Tag must have a name", Toast.LENGTH_LONG).show();
            return;
        }


        try {



            Log.i("dbdbd - create try", "0");
            mastermind.execSQL("CREATE TABLE IF NOT EXISTS " + name + " (taskID INT)");
            mastermind.execSQL("INSERT INTO tags (name) VALUES ('" + name +  "')");
            Log.i("dbdbd - create success", "0");

        }
        catch (Exception e) {
            e.printStackTrace();
        }



        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tag);

        mastermind = openOrCreateDatabase("Mastermind", MODE_PRIVATE, null);
        Intent intent = getIntent();


    }
}
