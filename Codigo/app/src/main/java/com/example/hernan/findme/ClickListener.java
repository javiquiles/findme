package com.example.hernan.findme;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import android.provider.ContactsContract.Data;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.example.hernan.findme.SQLite.SQLiteHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Hernan on 13/7/2016.
 */
public class ClickListener implements OnClickListener {
    ListView listView;
    CustomAdapter adapter;
    Context context;
    List<Contacto> lista;

    public ClickListener(Context context, ListView listView){
        this.context = context;
        this.listView = listView;
        lista = new LinkedList<Contacto>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recientes:

                lista.add(new Contacto(R.drawable.forma_circular, "Hernan Peñalver", "2604341191"));

                adapter = new CustomAdapter((Activity) context, lista, R.layout.molde_lista);
                listView.setAdapter(adapter);

                break;

            case R.id.contactos:

                adapter = new CustomAdapter((Activity) context, SQLiteHelper.getDatabase(context).getContactos(), R.layout.molde_lista);
                listView.setAdapter(adapter);

                break;

            default:
                break;
        }
    }


}
