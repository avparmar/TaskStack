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
        status          3 (to do real agile-y stuff by "working" on several tasks for a period of time
        reward          4

         */
        mastermind.execSQL("CREATE TABLE IF NOT EXISTS TasksV1 (title VARCHAR, description VARCHAR, id INTEGER PRIMARY KEY)");


        /*
        creates a table representing a tag (all tag tables will have a same format but will have a different name)

        CURRENT STRUCTURE:
        taskID          INT, the id of the task

        THINGS I WANT:
        some way to uniquely identify what the tag should be called based on the table name
        example: table is called due_in_one_day --> tag is called "due in one day"

        wait maybe I should just remove underscores and put in spaces...
         */
        mastermind.execSQL("CREATE TABLE IF NOT EXISTS due_today (taskID INT)");
        mastermind.execSQL("CREATE TABLE IF NOT EXISTS school (taskID INT)");
        mastermind.execSQL("CREATE TABLE IF NOT EXISTS misc (taskID INT)");

    }

}
