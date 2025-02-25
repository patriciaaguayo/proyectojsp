/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
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
    @NamedQuery(name = "Tareas.findByIdTarea", query = "SELECT t FROM Tareas t WHERE t.idTarea = :idTarea"),
    @NamedQuery(name = "Tareas.findByDescripcionTarea", query = "SELECT t FROM Tareas t WHERE t.descripcionTarea = :descripcionTarea"),
    @NamedQuery(name = "Tareas.findByResponsable", query = "SELECT t FROM Tareas t WHERE t.responsable = :responsable"),
    @NamedQuery(name = "Tareas.findByEstadoTarea", query = "SELECT t FROM Tareas t WHERE t.estadoTarea = :estadoTarea"),
    @NamedQuery(name = "Tareas.findByFechaInicioTarea", query = "SELECT t FROM Tareas t WHERE t.fechaInicioTarea = :fechaInicioTarea"),
    @NamedQuery(name = "Tareas.findByFechaFinTarea", query = "SELECT t FROM Tareas t WHERE t.fechaFinTarea = :fechaFinTarea")})
public class Tareas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Tarea")
    private Integer idTarea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "Descripcion_Tarea")
    private String descripcionTarea;
    @Size(max = 36)
    @Column(name = "Responsable")
    private String responsable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Estado_Tarea")
    private String estadoTarea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Fecha_Inicio_Tarea")
    private String fechaInicioTarea;
    @Size(max = 10)
    @Column(name = "Fecha_Fin_Tarea")
    private String fechaFinTarea;
    @JoinColumn(name = "Id_Proyecto", referencedColumnName = "Id_Proyecto")
    @ManyToOne(optional = false)
    private Proyectos idProyecto;

    public Tareas() {
    }

    public Tareas(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public Tareas(Integer idTarea, String descripcionTarea, String estadoTarea, String fechaInicioTarea) {
        this.idTarea = idTarea;
        this.descripcionTarea = descripcionTarea;
        this.estadoTarea = estadoTarea;
        this.fechaInicioTarea = fechaInicioTarea;
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(String estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

    public String getFechaInicioTarea() {
        return fechaInicioTarea;
    }

    public void setFechaInicioTarea(String fechaInicioTarea) {
        this.fechaInicioTarea = fechaInicioTarea;
    }

    public String getFechaFinTarea() {
        return fechaFinTarea;
    }

    public void setFechaFinTarea(String fechaFinTarea) {
        this.fechaFinTarea = fechaFinTarea;
    }

    public Proyectos getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyectos idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTarea != null ? idTarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tareas)) {
            return false;
        }
        Tareas other = (Tareas) object;
        if ((this.idTarea == null && other.idTarea != null) || (this.idTarea != null && !this.idTarea.equals(other.idTarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tareas[ idTarea=" + idTarea + " ]";
    }
    
}
