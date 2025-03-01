/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "proyectos")
@NamedQueries({
    @NamedQuery(name = "Proyectos.findAll", query = "SELECT p FROM Proyectos p"),
    @NamedQuery(name = "Proyectos.findByIdProyecto", query = "SELECT p FROM Proyectos p WHERE p.idProyecto = :idProyecto"),
    @NamedQuery(name = "Proyectos.findByNombreProyecto", query = "SELECT p FROM Proyectos p WHERE p.nombreProyecto = :nombreProyecto"),
    @NamedQuery(name = "Proyectos.findByEstadoProyecto", query = "SELECT p FROM Proyectos p WHERE p.estadoProyecto = :estadoProyecto"),
    @NamedQuery(name = "Proyectos.findByDescripcionProyecto", query = "SELECT p FROM Proyectos p WHERE p.descripcionProyecto = :descripcionProyecto"),
    @NamedQuery(name = "Proyectos.findByFechaInicioProyecto", query = "SELECT p FROM Proyectos p WHERE p.fechaInicioProyecto = :fechaInicioProyecto"),
    @NamedQuery(name = "Proyectos.findByFechaFinProyecto", query = "SELECT p FROM Proyectos p WHERE p.fechaFinProyecto = :fechaFinProyecto")
})
public class Proyectos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Proyecto")
    public Integer idProyecto;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Nombre_Proyecto")
    public String nombreProyecto;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Estado_Proyecto")
    public String estadoProyecto;

    @NotNull
    @Size(max = 300)
    @Column(name = "Descripcion_Proyecto")
    public String descripcionProyecto;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Inicio_Proyecto")
    public LocalDate fechaInicioProyecto;

    @NotNull
    @Column(name = "Fecha_Fin_Proyecto")
    public LocalDate fechaFinProyecto;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProyecto")
    public Collection<Tareas> tareasCollection;

    // Constructores
    public Proyectos() {
    }

    public Proyectos(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Proyectos(Integer idProyecto, String nombreProyecto, String descripcionProyecto, String estadoProyecto, LocalDate fechaFinProyecto) {
        this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;
        this.descripcionProyecto = descripcionProyecto;
        this.estadoProyecto = "En curso";
        this.fechaInicioProyecto = LocalDate.now();
        this.fechaFinProyecto = fechaFinProyecto;
    }

    public Proyectos(String nombreProyecto, String descripcionProyecto, LocalDate fechaFinProyecto) {
        this.nombreProyecto = nombreProyecto;
        this.descripcionProyecto = descripcionProyecto;
        this.estadoProyecto = "En curso";
        this.fechaInicioProyecto = LocalDate.now();
        this.fechaFinProyecto = fechaFinProyecto;
    }

    // Getters y Setters con _ (igual que los atributos)
    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer Id_Proyecto) {
        this.idProyecto = Id_Proyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String Nombre_Proyecto) {
        this.nombreProyecto = Nombre_Proyecto;
    }

    public String getEstadoProyecto() {
        return estadoProyecto;
    }

    public void setEstadoProyecto(String Estado_Proyecto) {
        this.estadoProyecto = Estado_Proyecto;
    }

    public String getDescripcionProyecto() {
        return descripcionProyecto;
    }

    public void setDescripcionProyecto(String Descripcion_Proyecto) {
        this.descripcionProyecto = Descripcion_Proyecto;
    }

    public LocalDate getFechaInicioProyecto() {
        return fechaInicioProyecto;
    }

    public void setFechaInicioProyecto(LocalDate Fecha_Inicio_Proyecto) {
        this.fechaInicioProyecto = Fecha_Inicio_Proyecto;
    }

    public LocalDate getFechaFinProyecto() {
        return fechaFinProyecto;
    }

    public void setFechaFinProyecto(LocalDate Fecha_Fin_Proyecto) {
        this.fechaFinProyecto = Fecha_Fin_Proyecto;
    }

    public Collection<Tareas> getTareasCollection() {
        return tareasCollection;
    }

    public void setTareasCollection(Collection<Tareas> tareasCollection) {
        this.tareasCollection = tareasCollection;
    }

    // Métodos hashCode, equals y toString
    @Override
    public int hashCode() {
        return (idProyecto != null ? idProyecto.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Proyectos)) {
            return false;
        }
        Proyectos other = (Proyectos) object;
        return (this.idProyecto != null || other.idProyecto == null) && (this.idProyecto == null || this.idProyecto.equals(other.idProyecto));
    }

    @Override
    public String toString() {
        return "entities.Proyectos[ Id_Proyecto=" + idProyecto + " ]";
    }
}

