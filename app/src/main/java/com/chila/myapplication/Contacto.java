package com.chila.myapplication;

import java.util.Calendar;
import java.util.Date;

public class Contacto {

    private String nombre;
    private Calendar fechaNac = Calendar.getInstance();
    private String telefono;
    private String email;
    private String desc;

    public Contacto(String nombre, long fechaNac, String telefono, String email, String desc) {
        this.nombre = nombre;
        this.fechaNac.setTimeInMillis(fechaNac);
        this.telefono = telefono;
        this.email = email;
        this.desc = desc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getFechaNac() {
        return fechaNac.getTimeInMillis();
    }

    public void setFechaNac(long fechaNac) {
        this.fechaNac.setTimeInMillis(fechaNac);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesc() {
        return desc;
    }

    public Calendar getCalFecha(){
        return fechaNac;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
