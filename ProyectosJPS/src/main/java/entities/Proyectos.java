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
    @NamedQuery(name = "Proyectos.findByIdProyecto", query = "SELECT p FROM Proyectos p WHERE p.Id_Proyecto = :Id_Proyecto"),
    @NamedQuery(name = "Proyectos.findByNombreProyecto", query = "SELECT p FROM Proyectos p WHERE p.Nombre_Proyecto = :Nombre_Proyecto"),
    @NamedQuery(name = "Proyectos.findByEstadoProyecto", query = "SELECT p FROM Proyectos p WHERE p.Estado_Proyecto = :Estado_Proyecto"),
    @NamedQuery(name = "Proyectos.findByDescripcionProyecto", query = "SELECT p FROM Proyectos p WHERE p.Descripcion_Proyecto = :Descripcion_Proyecto"),
    @NamedQuery(name = "Proyectos.findByFechaInicioProyecto", query = "SELECT p FROM Proyectos p WHERE p.Fecha_Inicio_Proyecto = :Fecha_Inicio_Proyecto"),
    @NamedQuery(name = "Proyectos.findByFechaFinProyecto", query = "SELECT p FROM Proyectos p WHERE p.Fecha_Fin_Proyecto = :Fecha_Fin_Proyecto")
})
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
    @Column(name = "Fecha_Inicio_Proyecto")
    private LocalDate Fecha_Inicio_Proyecto;

    @NotNull
    @Column(name = "Fecha_Fin_Proyecto")
    private LocalDate Fecha_Fin_Proyecto;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Id_Proyecto")
    private Collection<Tareas> tareasCollection;

    // Constructores
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

    // Getters y Setters con _ (igual que los atributos)
    public Integer getId_Proyecto() {
        return Id_Proyecto;
    }

    public void setId_Proyecto(Integer Id_Proyecto) {
        this.Id_Proyecto = Id_Proyecto;
    }

    public String getNombre_Proyecto() {
        return Nombre_Proyecto;
    }

    public void setNombre_Proyecto(String Nombre_Proyecto) {
        this.Nombre_Proyecto = Nombre_Proyecto;
    }

    public String getEstado_Proyecto() {
        return Estado_Proyecto;
    }

    public void setEstado_Proyecto(String Estado_Proyecto) {
        this.Estado_Proyecto = Estado_Proyecto;
    }

    public String getDescripcion_Proyecto() {
        return Descripcion_Proyecto;
    }

    public void setDescripcion_Proyecto(String Descripcion_Proyecto) {
        this.Descripcion_Proyecto = Descripcion_Proyecto;
    }

    public LocalDate getFecha_Inicio_Proyecto() {
        return Fecha_Inicio_Proyecto;
    }

    public void setFecha_Inicio_Proyecto(LocalDate Fecha_Inicio_Proyecto) {
        this.Fecha_Inicio_Proyecto = Fecha_Inicio_Proyecto;
    }

    public LocalDate getFecha_Fin_Proyecto() {
        return Fecha_Fin_Proyecto;
    }

    public void setFecha_Fin_Proyecto(LocalDate Fecha_Fin_Proyecto) {
        this.Fecha_Fin_Proyecto = Fecha_Fin_Proyecto;
    }

    public Collection<Tareas> getTareas_Collection() {
        return tareasCollection;
    }

    public void setTareas_Collection(Collection<Tareas> tareasCollection) {
        this.tareasCollection = tareasCollection;
    }

    // Métodos hashCode, equals y toString
    @Override
    public int hashCode() {
        return (Id_Proyecto != null ? Id_Proyecto.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Proyectos)) {
            return false;
        }
        Proyectos other = (Proyectos) object;
        return (this.Id_Proyecto != null || other.Id_Proyecto == null) && (this.Id_Proyecto == null || this.Id_Proyecto.equals(other.Id_Proyecto));
    }

    @Override
    public String toString() {
        return "entities.Proyectos[ Id_Proyecto=" + Id_Proyecto + " ]";
    }
}

