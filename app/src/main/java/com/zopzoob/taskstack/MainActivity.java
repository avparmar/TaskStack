package com.zopzoob.taskstack;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static boolean checkFirstTime = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase mastermind = this.openOrCreateDatabase("Mastermind", MODE_PRIVATE, null);

        /*
        creates the tasks table

        CURRENT STRUCTURE:
        title           VARCHAR
        description     VARCHAR
        id              INT primary key unique all that stuff

            PLAN TO ADD:
        THINGY          PRIORITY(1=highest)
        tags            1
        duration        2 (with type int, representing multiple of thirty minutes)
        deadline        2
        reward          3


         */
        mastermind.execSQL("CREATE TABLE IF NOT EXISTS TasksV1 (title VARCHAR, description VARCHAR, id INTEGER PRIMARY KEY)");

    }

    public void tapped(View view) {
        Log.i("button", "success");
    }
}
