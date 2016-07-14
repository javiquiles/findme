package com.example.hernan.findme;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hernan on 11/7/2016.
 */
public class CustomAdapter extends BaseAdapter{

    private int layoutMolde;
    private List<Contacto> lista;
    private Activity activity;

    public CustomAdapter(Activity activity, List<Contacto> lista, int layout){
        this.activity = activity;
        this.lista = lista;
        layoutMolde = layout;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(layoutMolde, null);
        MyViewHolder Holder = new MyViewHolder(convertView);

        Holder.fotoPerfil.setImageResource(lista.get(position).getFotoPerfil());
        Holder.nombreCompleto.setText(lista.get(position).getNombre() + " " + lista.get(position).getApellido());

        return convertView;
    }

    private class MyViewHolder {
        TextView nombreCompleto;
        ImageView fotoPerfil;

        public MyViewHolder(View item) {
            nombreCompleto = (TextView) item.findViewById(R.id.nomCompleto);
            fotoPerfil = (ImageView) item.findViewById(R.id.fotoPerfil);
        }
    }

}
