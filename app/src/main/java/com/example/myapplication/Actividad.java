package com.example.myapplication;

public class Actividad {

    private String tipoActividad;
    private double distancia;
    private int tiempo;

    // Constructor vacío requerido para Firebase
    public Actividad() {
    }

    // Constructor
    public Actividad(String tipoActividad, double distancia, int tiempo) {
        this.tipoActividad = tipoActividad;
        this.distancia = distancia;
        this.tiempo = tiempo;
    }

    // Métodos getters y setters (asegúrate de tener estos métodos)
    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipoActividad + ", Distancia: " + distancia + " km, Tiempo: " + tiempo + " minutos";
    }
}
