/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alumno
 */
@Entity
@Table(name = "tareas")
@NamedQueries({
    @NamedQuery(name = "Tareas.findAll", query = "SELECT t FROM Tareas t"),
    @NamedQuery(name = "Tareas.findByIdTarea", query = "SELECT t FROM Tareas t WHERE t.Id_Tarea = :Id_Tarea"),
    @NamedQuery(name = "Tareas.findByDescripcionTarea", query = "SELECT t FROM Tareas t WHERE t.Descripcion_Tarea = :Descripcion_Tarea"),
    @NamedQuery(name = "Tareas.findByResponsable", query = "SELECT t FROM Tareas t WHERE t.Responsable = :Responsable"),
    @NamedQuery(name = "Tareas.findByEstadoTarea", query = "SELECT t FROM Tareas t WHERE t.Estado_Tarea = :Estado_Tarea"),
    @NamedQuery(name = "Tareas.findByFechaInicioTarea", query = "SELECT t FROM Tareas t WHERE t.Fecha_Inicio_Tarea = :Fecha_Inicio_Tarea"),
    @NamedQuery(name = "Tareas.findByFechaFinTarea", query = "SELECT t FROM Tareas t WHERE t.Fecha_Fin_Tarea = :Fecha_Fin_Tarea")})
public class Tareas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Tarea")
    private Integer Id_Tarea;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "Descripcion_Tarea")
    private String Descripcion_Tarea;
    
    @NotNull
    @Size(max = 36)
    @Column(name = "Responsable")
    private String Responsable;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Estado_Tarea")
    private String Estado_Tarea;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Fecha_Inicio_Tarea")
    private LocalDate Fecha_Inicio_Tarea;
    
    @NotNull
    @Size(max = 10)
    @Column(name = "Fecha_Fin_Tarea")
    private LocalDate Fecha_Fin_Tarea;
    
    @NotNull
    @JoinColumn(name = "Id_Proyecto", referencedColumnName = "Id_Proyecto")
    @ManyToOne(optional = false)
    private Proyectos Id_Proyecto;

    public Tareas() {
    }

    public Tareas(Integer idTarea) {
        this.Id_Tarea = idTarea;
    }

    public Tareas(Integer idTarea, String descripcionTarea, String estadoTarea, LocalDate fechaFinTarea) {
        
        this.Id_Tarea = idTarea;
        this.Descripcion_Tarea = descripcionTarea;
        this.Estado_Tarea = "Pendiente";
        this.Fecha_Inicio_Tarea = LocalDate.now();
        this.Fecha_Fin_Tarea = fechaFinTarea;
    }
    
    public Tareas(String descripcionTarea, String estadoTarea, LocalDate fechaFinTarea) {
        
        this.Descripcion_Tarea = descripcionTarea;
        this.Estado_Tarea = "Pendiente";
        this.Fecha_Inicio_Tarea = LocalDate.now();
        this.Fecha_Fin_Tarea = fechaFinTarea;
    }

    public Integer getIdTarea() {
        return Id_Tarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.Id_Tarea = idTarea;
    }

    public String getDescripcionTarea() {
        return Descripcion_Tarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.Descripcion_Tarea = descripcionTarea;
    }

    public String getResponsable() {
        return Responsable;
    }

    public void setResponsable(String responsable) {
        this.Responsable = responsable;
    }

    public String getEstadoTarea() {
        return Estado_Tarea;
    }

    public void setEstadoTarea(String estadoTarea) {
        this.Estado_Tarea = estadoTarea;
    }

    public LocalDate getFechaInicioTarea() {
        return Fecha_Inicio_Tarea;
    }

    public void setFechaInicioTarea(LocalDate fechaInicioTarea) {
        this.Fecha_Inicio_Tarea = fechaInicioTarea;
    }

    public LocalDate getFechaFinTarea() {
        return Fecha_Fin_Tarea;
    }

    public void setFechaFinTarea(LocalDate fechaFinTarea) {
        this.Fecha_Fin_Tarea = fechaFinTarea;
    }

    public Proyectos getIdProyecto() {
        return Id_Proyecto;
    }

    public void setIdProyecto(Proyectos idProyecto) {
        this.Id_Proyecto = idProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Id_Tarea != null ? Id_Tarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tareas)) {
            return false;
        }
        Tareas other = (Tareas) object;
        if ((this.Id_Tarea == null && other.Id_Tarea != null) || (this.Id_Tarea != null && !this.Id_Tarea.equals(other.Id_Tarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tareas[ idTarea=" + Id_Tarea + " ]";
    }
    
}
