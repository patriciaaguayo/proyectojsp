/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alumno
 */
@Entity
@Table(name = "proyectos")
@NamedQueries({
    @NamedQuery(name = "Proyectos.findAll", query = "SELECT p FROM Proyectos p"),
    @NamedQuery(name = "Proyectos.findByIdProyecto", query = "SELECT p FROM Proyectos p WHERE p.idProyecto = :idProyecto"),
    @NamedQuery(name = "Proyectos.findByNombreProyecto", query = "SELECT p FROM Proyectos p WHERE p.nombreProyecto = :nombreProyecto"),
    @NamedQuery(name = "Proyectos.findByEstadoProyecto", query = "SELECT p FROM Proyectos p WHERE p.estadoProyecto = :estadoProyecto"),
    @NamedQuery(name = "Proyectos.findByDescripcionProyecto", query = "SELECT p FROM Proyectos p WHERE p.descripcionProyecto = :descripcionProyecto"),
    @NamedQuery(name = "Proyectos.findByFechaInicioProyecto", query = "SELECT p FROM Proyectos p WHERE p.fechaInicioProyecto = :fechaInicioProyecto"),
    @NamedQuery(name = "Proyectos.findByFechaFinProyecto", query = "SELECT p FROM Proyectos p WHERE p.fechaFinProyecto = :fechaFinProyecto")})
public class Proyectos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Proyecto")
    private Integer idProyecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Nombre_Proyecto")
    private String nombreProyecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Estado_Proyecto")
    private String estadoProyecto;
    @Size(max = 300)
    @Column(name = "Descripcion_Proyecto")
    private String descripcionProyecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Fecha_Inicio_Proyecto")
    private String fechaInicioProyecto;
    @Size(max = 10)
    @Column(name = "Fecha_Fin_Proyecto")
    private String fechaFinProyecto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProyecto")
    private Collection<Tareas> tareasCollection;

    public Proyectos() {
    }

    public Proyectos(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Proyectos(Integer idProyecto, String nombreProyecto, String estadoProyecto, String fechaInicioProyecto) {
        this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;
        this.estadoProyecto = estadoProyecto;
        this.fechaInicioProyecto = fechaInicioProyecto;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getEstadoProyecto() {
        return estadoProyecto;
    }

    public void setEstadoProyecto(String estadoProyecto) {
        this.estadoProyecto = estadoProyecto;
    }

    public String getDescripcionProyecto() {
        return descripcionProyecto;
    }

    public void setDescripcionProyecto(String descripcionProyecto) {
        this.descripcionProyecto = descripcionProyecto;
    }

    public String getFechaInicioProyecto() {
        return fechaInicioProyecto;
    }

    public void setFechaInicioProyecto(String fechaInicioProyecto) {
        this.fechaInicioProyecto = fechaInicioProyecto;
    }

    public String getFechaFinProyecto() {
        return fechaFinProyecto;
    }

    public void setFechaFinProyecto(String fechaFinProyecto) {
        this.fechaFinProyecto = fechaFinProyecto;
    }

    public Collection<Tareas> getTareasCollection() {
        return tareasCollection;
    }

    public void setTareasCollection(Collection<Tareas> tareasCollection) {
        this.tareasCollection = tareasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyectos)) {
            return false;
        }
        Proyectos other = (Proyectos) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Proyectos[ idProyecto=" + idProyecto + " ]";
    }
    
}
