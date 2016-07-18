package com.example.hernan.findme;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.ListView;

import com.example.hernan.findme.SQLite.SQLiteHelper;


public class MainActivity extends Activity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_CONTACTS }, 1);

        SQLiteHelper sqLiteHelper = SQLiteHelper.getDatabase(this);
        sqLiteHelper.init();

        listView = (ListView) findViewById(R.id.listView);
        findViewById(R.id.recientes).setOnClickListener(new ClickListener(this, listView));
        findViewById(R.id.contactos).setOnClickListener(new ClickListener(this, listView));

    }

}