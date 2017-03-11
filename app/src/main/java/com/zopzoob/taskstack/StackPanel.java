package com.zopzoob.taskstack;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


/**
 * Created by avparmar on 2/21/17.
 */

public class StackPanel extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);

        ListView tList = (ListView) findViewById(R.id.tasklist);

        SQLiteDatabase mastermind = openOrCreateDatabase("Mastermind", MODE_PRIVATE, null);

        Cursor c = mastermind.rawQuery("SELECT title, description, id _id FROM TasksV1", null);

        CursorAdapter cursorAdapter = new TwoLineDBAdaptor(this, c, 0);

        tList.setAdapter(cursorAdapter);

        /*
        tList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        */

        Intent intent = getIntent();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.task_list_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        super.onOptionsItemSelected(menuItem);

        switch(menuItem.getItemId()) {
            case R.id.filter:


                return true;

            default:
                return false;
        }


    }
}
