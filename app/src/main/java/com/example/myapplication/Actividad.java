package com.example.myapplication;

public class Actividad {

    private String tipoActividad;
    private double distancia;
    private String tiempo;

    public Actividad() {
    }

    public Actividad(String tipoActividad, double distancia, String tiempo) {
        this.tipoActividad = tipoActividad;
        this.distancia = distancia;
        this.tiempo = tiempo;
    }

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

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipoActividad + ", Distancia: " + distancia + " km, Tiempo: " + tiempo + " minutos";
    }
}
