
package app.model;

public class servicio {
    
    private Long id;
    private String descripcion;
    private Long costo_hora;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the costo_hora
     */
    public Long getCosto_hora() {
        return costo_hora;
    }

    /**
     * @param costo_hora the costo_hora to set
     */
    public void setCosto_hora(Long costo_hora) {
        this.costo_hora = costo_hora;
    }
    
    
}
