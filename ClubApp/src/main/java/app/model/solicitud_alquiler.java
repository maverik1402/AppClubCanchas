/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.util.Date;

/**
 *
 * @author Personal
 */
public class solicitud_alquiler {
    private Long id;
    private String hora_inicio;
    private String hora_fin;
    private Date dia;
    private String servicios;
    private Integer estado;
    private Socio socio;
    private Campo campo;

    public Long getId() {
        return id;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

  
    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Long getId_socio() {
        return id_socio;
    }

    public void setId_socio(Long id_socio) {
        this.id_socio = id_socio;
    }

    public Long getId_campo() {
        return id_campo;
    }

    public void setId_campo(Long id_campo) {
        this.id_campo = id_campo;
    }
    
    
}
    
