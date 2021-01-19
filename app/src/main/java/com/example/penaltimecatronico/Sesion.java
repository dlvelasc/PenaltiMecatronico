package com.example.penaltimecatronico;

public class Sesion {
    private String fecha;
    private int goles;
    private int intentos;

    public Sesion(String fecha, int goles, int intentos) {
        this.fecha = fecha;
        this.goles = goles;
        this.intentos = intentos;
    }

    public Sesion() {


    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public String getFecha() {
        return fecha;
    }

    public int getGoles() {
        return goles;
    }

    public int getIntentos() {
        return intentos;
    }
}

