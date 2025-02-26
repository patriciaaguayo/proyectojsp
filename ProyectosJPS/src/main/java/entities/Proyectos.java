/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.time.LocalDate;
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
    private Integer Id_Proyecto;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Nombre_Proyecto")
    private String Nombre_Proyecto;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Estado_Proyecto")
    private String Estado_Proyecto;
    
    @NotNull
    @Size(max = 300)
    @Column(name = "Descripcion_Proyecto")
    private String Descripcion_Proyecto;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Fecha_Inicio_Proyecto")
    private LocalDate Fecha_Inicio_Proyecto;
    
    @NotNull
    @Size(max = 10)
    @Column(name = "Fecha_Fin_Proyecto")
    private LocalDate Fecha_Fin_Proyecto;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProyecto")
    private Collection<Tareas> tareasCollection;

    public Proyectos() {
    }

    public Proyectos(Integer idProyecto) {
        this.Id_Proyecto = idProyecto;
    }

    public Proyectos(Integer idProyecto, String nombreProyecto, String estadoProyecto, LocalDate fechaFinProyecto) {
        
        this.Id_Proyecto = idProyecto;
        this.Nombre_Proyecto = nombreProyecto;
        this.Estado_Proyecto = "En curso";
        this.Fecha_Inicio_Proyecto = LocalDate.now();
        this.Fecha_Fin_Proyecto = fechaFinProyecto;
    }
    
    public Proyectos(String nombreProyecto, String estadoProyecto, LocalDate fechaFinProyecto) {
        
        this.Nombre_Proyecto = nombreProyecto;
        this.Estado_Proyecto = "En curso";
        this.Fecha_Inicio_Proyecto = LocalDate.now();
        this.Fecha_Fin_Proyecto = fechaFinProyecto;
    }

    public Integer getIdProyecto() {
        return Id_Proyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.Id_Proyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return Nombre_Proyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.Nombre_Proyecto = nombreProyecto;
    }

    public String getEstadoProyecto() {
        return Estado_Proyecto;
    }

    public void setEstadoProyecto(String estadoProyecto) {
        this.Estado_Proyecto = estadoProyecto;
    }

    public String getDescripcionProyecto() {
        return Descripcion_Proyecto;
    }

    public void setDescripcionProyecto(String descripcionProyecto) {
        this.Descripcion_Proyecto = descripcionProyecto;
    }

    public LocalDate getFechaInicioProyecto() {
        return Fecha_Inicio_Proyecto;
    }

    public void setFechaInicioProyecto(LocalDate fechaInicioProyecto) {
        this.Fecha_Inicio_Proyecto = fechaInicioProyecto;
    }

    public LocalDate getFechaFinProyecto() {
        return Fecha_Fin_Proyecto;
    }

    public void setFechaFinProyecto(LocalDate fechaFinProyecto) {
        this.Fecha_Fin_Proyecto = fechaFinProyecto;
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
        hash += (Id_Proyecto != null ? Id_Proyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyectos)) {
            return false;
        }
        Proyectos other = (Proyectos) object;
        if ((this.Id_Proyecto == null && other.Id_Proyecto != null) || (this.Id_Proyecto != null && !this.Id_Proyecto.equals(other.Id_Proyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Proyectos[ idProyecto=" + Id_Proyecto + " ]";
    }
    
}
