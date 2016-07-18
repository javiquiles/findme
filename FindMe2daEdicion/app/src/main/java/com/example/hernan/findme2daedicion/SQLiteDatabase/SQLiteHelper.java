package com.example.hernan.findme2daedicion.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.example.hernan.findme2daedicion.Contacto;
import com.example.hernan.findme2daedicion.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Hernan on 18/7/2016.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    private String name = "FindMe";
    private int version = 1;
    private Context context;
    private static SQLiteHelper sqLiteHelper = null;

    private String contactos = "CREATE TABLE contactos(idContacto INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, numCelular TEXT, fotoPerfil INTEGER, activo BOOLEAN)";

    private SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    public static SQLiteHelper getDatabase(Context context){
        if(sqLiteHelper == null)
            sqLiteHelper = new SQLiteHelper(context, "FindMe", null, 2);
        return sqLiteHelper;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(contactos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contactos");

    }

    public void init(){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues[] contactos = importarContactos();

        //for (ContentValues aux : contactos) {
        //    db.insert("contactos", null, aux);
        //}

    }

    public ContentValues[] importarContactos(){

        String[] projection = new String[]{
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        String selectionClause = ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
                + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";

        Cursor contactsCursor = this.context.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                projection,
                selectionClause,
                null,
                ContactsContract.Data.DISPLAY_NAME + " ASC");

        int nombre = contactsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int tel = contactsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

        ContentValues[] contactoss = new ContentValues[contactsCursor.getCount()];
        int i = 0;
        if(contactsCursor.moveToFirst()){
            while(contactsCursor.moveToNext()){

                contactoss[i].put("nombre", contactsCursor.getString(0));
                contactoss[i].put("numCelular", contactsCursor.getString(1));
                i++;

            }
        }

        return contactoss;
    }

    public List<Contacto> getContactos(){
        SQLiteDatabase db = getReadableDatabase();
        List<Contacto> contactos = new LinkedList<>();
        Cursor c;

        if(db != null){
            c = db.rawQuery("SELECT * FROM contactos", null);
            if(c.moveToFirst()){
                while(c.moveToNext()){
                    contactos.add(new Contacto(R.drawable.forma_circular, c.getString(c.getColumnIndex("nombre")), c.getString(c.getColumnIndex("numCelular"))));
                }
            }
        }
        return contactos;
    }
}
