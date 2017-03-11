package com.zopzoob.taskstack;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by avparmar on 2/17/17.
 */



public class CreateTask extends AppCompatActivity {

    private SQLiteDatabase mastermind;
    private int numTags;
    private boolean[] checked;
    private String[] tagsList;

    protected void getHi(View view) {
        try {
            Cursor c = mastermind.rawQuery("SELECT * FROM TasksV1 ORDER BY id DESC LIMIT 1", null);

            c.moveToFirst();
            while(c != null) {
                Log.i("dbdbd - name", c.getString(c.getColumnIndex("title")));
                Log.i("dbdbd - description", c.getString(c.getColumnIndex("description")));
                Log.i("dbdbd - id", c.getString(c.getColumnIndex("id")));
                c.moveToNext();
            }
            c.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void clearStack(View view) {
        EditText tDesc = (EditText) findViewById(R.id.enterDesc);

        try {
           int i = Integer.parseInt(tDesc.getText().toString());
            mastermind.execSQL("DELETE FROM TasksV1 WHERE id = " + i);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void makeLogs(View view) {

        try {
            //    Cursor c = mastermind.rawQuery("SELECT * FROM TasksV1 WHERE title = " + name, null);
            Cursor c = mastermind.rawQuery("SELECT * FROM TasksV1", null);

            c.moveToFirst();
            while(c != null) {
                Log.i("dbdbd - name", c.getString(c.getColumnIndex("title")));
                Log.i("dbdbd - description", c.getString(c.getColumnIndex("description")));
                Log.i("dbdbd - id", c.getString(c.getColumnIndex("id")));
                c.moveToNext();
            }
            c.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void selectTags(View view) {


        new AlertDialog.Builder(this)
                .setMultiChoiceItems(tagsList, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checked[which] = isChecked;
                    }
                })
                .setTitle("Select Tags")
                .setPositiveButton("Done",null)
                .show();


    }

    protected void makeTask(View view) {
        EditText tName = (EditText) findViewById(R.id.enterName);
        EditText tDesc = (EditText) findViewById(R.id.enterDesc);

        String name = tName.getText().toString();
        String desc = tDesc.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Task must contain at least a name", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            mastermind.execSQL("INSERT INTO TasksV1 (title,description) VALUES ('" + name + "','" + desc + "')");

            Cursor cid = mastermind.rawQuery("SELECT id FROM TasksV1 ORDER BY id DESC LIMIT 1", null);

            cid.moveToFirst();
            int id = cid.getInt(cid.getColumnIndex("id"));
            cid.close();

            for (int i = 0; i < numTags; i++) {
                if (checked[i]) {
                    tagsList[i].replace(' ','_');
                    mastermind.execSQL("INSERT INTO " + tagsList[i] + " (taskID) VALUES (" + id + ")");
                }
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }



        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        mastermind = openOrCreateDatabase("Mastermind", MODE_PRIVATE, null);
        Intent intent = getIntent();


        try {
            Cursor c = mastermind.rawQuery("SELECT COUNT(name) num FROM tags", null);
            c.moveToFirst();
            numTags = c.getInt(c.getColumnIndex("num"));
            checked = new boolean[numTags];

            tagsList = new String[numTags];
            Cursor c2 = mastermind.rawQuery("SELECT * FROM tags", null);

            c2.moveToFirst();
            int i = 0;
            while (c != null) {
                tagsList[i] = c2.getString(c2.getColumnIndexOrThrow("name"));
                tagsList[i].replace('_', ' ');
                c2.moveToNext();
                i++;
            }
            Arrays.fill(checked, false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }
}
