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
@Table(name = "usuarios")
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuarios.findByNombreUsuario", query = "SELECT u FROM Usuarios u WHERE u.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "Usuarios.findByPassword", query = "SELECT u FROM Usuarios u WHERE u.password = :password"),
    @NamedQuery(name = "Usuarios.findByTipoUsuario", query = "SELECT u FROM Usuarios u WHERE u.tipoUsuario = :tipoUsuario")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Usuario")
    private Integer Id_Usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Nombre_Usuario")
    private String Nombre_Usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Password")
    private String Password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Tipo_Usuario")
    private String Tipo_Usuario;

    public Usuarios() {
    }

    public Usuarios(Integer idUsuario) {
        this.Id_Usuario = idUsuario;
    }

    public Usuarios(Integer idUsuario, String nombreUsuario, String password, String tipoUsuario) {
        this.Id_Usuario = idUsuario;
        this.Nombre_Usuario = nombreUsuario;
        this.Password = password;
        this.Tipo_Usuario = "User";
    }
    
    public Usuarios(String nombreUsuario, String password, String tipoUsuario) {
        this.Nombre_Usuario = nombreUsuario;
        this.Password = password;
        this.Tipo_Usuario = "User";
    }

    public Integer getIdUsuario() {
        return Id_Usuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.Id_Usuario = idUsuario;
    }

    public String getNombreUsuario() {
        return Nombre_Usuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.Nombre_Usuario = nombreUsuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getTipoUsuario() {
        return Tipo_Usuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.Tipo_Usuario = tipoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Id_Usuario != null ? Id_Usuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.Id_Usuario == null && other.Id_Usuario != null) || (this.Id_Usuario != null && !this.Id_Usuario.equals(other.Id_Usuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Usuarios[ idUsuario=" + Id_Usuario + " ]";
    }
    
}
