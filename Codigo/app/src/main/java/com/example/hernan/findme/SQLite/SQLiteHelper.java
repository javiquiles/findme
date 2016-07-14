package com.example.hernan.findme.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Hernan on 13/7/2016.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    private String name = "FindMe";
    private int version = 1;
    private Context context;

    private String contactos = "CREATE TABLE contactos(idContacto INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, numCelular TEXT, fotoPerfil INTEGER, activo BOOLEAN)";

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(contactos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contactos");

    }

    public void init(SQLiteDatabase db){

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

        String selectionClause = ContactsContract.Contacts.LOOKUP_KEY+" = ? ";

        Cursor contactsCursor = context.getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,   // URI de contenido para los contactos
                projection,                        // Columnas a seleccionar
                selectionClause,                   // Condición del WHERE
                null,                     // Valores de la condición
                ContactsContract.Contacts.Data.MIMETYPE);

    }
}
