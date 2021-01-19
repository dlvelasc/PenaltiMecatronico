package com.example.penaltimecatronico;

public class Sesion {
    private String fecha;
    private String goles;
    private String intentos;

    public Sesion(String fecha, String goles, String intentos) {
        this.fecha = fecha;
        this.goles = goles;
        this.intentos = intentos;
    }


    public String getFecha() {
        return fecha;
    }

    public String getGoles() {
        return goles;
    }

    public String getIntentos() {
        return intentos;
    }
}
