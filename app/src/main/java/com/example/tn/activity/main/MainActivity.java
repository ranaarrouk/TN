package com.example.tn.activity.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tn.R;
import com.example.tn.activity.editor.EditorActivity;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton) findViewById(R.id.addButton);
        fab.setOnClickListener(view ->
                startActivity(new Intent(this, EditorActivity.class))
                );
    }
}
