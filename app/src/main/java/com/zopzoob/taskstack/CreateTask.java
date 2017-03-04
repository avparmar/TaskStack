package com.zopzoob.taskstack;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by avparmar on 2/17/17.
 */



public class CreateTask extends AppCompatActivity {

    private SQLiteDatabase mastermind;

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


    }
}
