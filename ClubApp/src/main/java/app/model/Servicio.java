/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

/**
 *
 * @author jose
 */
public class Servicio {

    private long id;
    private String descripcion;
    private double costoHora;
    private double costoHora2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(double costoHora) {
        this.costoHora = costoHora;
    }
    
    
}
