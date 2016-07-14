package com.example.hernan.findme;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ListView;


public class MainActivity extends Activity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        findViewById(R.id.recientes).setOnClickListener(new ClickListener(this, listView));
        findViewById(R.id.contactos).setOnClickListener(new ClickListener(this, listView));

    }

}