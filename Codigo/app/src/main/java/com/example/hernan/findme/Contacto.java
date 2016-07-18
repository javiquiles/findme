package com.example.hernan.findme;

/**
 * Created by Hernan on 13/7/2016.
 */
public class Contacto {
    private int fotoPerfil;
    private String nombre;
    private String apellido;
    private String numCel;

    public Contacto(int fotoPerfil, String nombre, String numCel){
        this.fotoPerfil = fotoPerfil;
        this.nombre = nombre;
        this.numCel = numCel;
    }

    public int getFotoPerfil() {
        return fotoPerfil;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumCel() {
        return numCel;
    }
}
