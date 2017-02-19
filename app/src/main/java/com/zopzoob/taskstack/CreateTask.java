package com.zopzoob.taskstack;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by avparmar on 2/17/17.
 */

public class CreateTask extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        Intent intent = getIntent();
    }
}
