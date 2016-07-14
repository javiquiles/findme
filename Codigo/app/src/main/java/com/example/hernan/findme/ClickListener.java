package com.example.hernan.findme;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

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

                lista.add(new Contacto(R.drawable.forma_circular, "Hernan", "Peñalver", "2604341191"));

                adapter = new CustomAdapter((Activity) context, lista, R.layout.molde_lista);
                listView.setAdapter(adapter);

                break;

            case R.id.contactos:

                adapter = new CustomAdapter((Activity) context, getContacts(), R.layout.molde_lista);
                listView.setAdapter(adapter);

                break;

            default:
                break;
        }
    }

    public List<Contacto> getContacts(){
        List<Contacto> contactos = new LinkedList<>();

        String[] projection = new String[]{
                ContactsContract.Contacts.Data._ID,
                ContactsContract.Contacts.Data.MIMETYPE,
                ContactsContract.Contacts.Data.DATA1,
                ContactsContract.Contacts.Data.DATA2,
                ContactsContract.Contacts.Data.DATA3,
                ContactsContract.Contacts.Data.DATA4,
                ContactsContract.Contacts.Data.DATA5,
                ContactsContract.Contacts.Data.DATA6,
                ContactsContract.Contacts.Data.DATA7,
                ContactsContract.Contacts.Data.DATA8,
                ContactsContract.Contacts.Data.DATA9,
                ContactsContract.Contacts.Data.DATA10,
                ContactsContract.Contacts.Data.DATA11,
                ContactsContract.Contacts.Data.DATA12,
                ContactsContract.Contacts.Data.DATA13,
                ContactsContract.Contacts.Data.DATA14,
                ContactsContract.Contacts.Data.DATA15
        };

        String selectionClause = ContactsContract.Contacts.LOOKUP_KEY+" = ?";

        Cursor contactsCursor = context.getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,   // URI de contenido para los contactos
                projection,                        // Columnas a seleccionar
                selectionClause,                   // Condición del WHERE
                null,                     // Valores de la condición
                ContactsContract.Contacts.Data.MIMETYPE);

        if(contactsCursor.moveToFirst()){
            while(contactsCursor.moveToNext()){
                contactos.add(new Contacto(R.drawable.forma_circular, contactsCursor.getString(0), contactsCursor.getString(1), contactsCursor.getString(2)));
            }
        }

        return contactos;
    }
}
