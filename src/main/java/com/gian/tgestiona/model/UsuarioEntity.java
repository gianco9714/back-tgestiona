package com.gian.tgestiona.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "tgestiona")
public class UsuarioEntity {
    private String codUsuario;
    private String nombre;
    private String user;
    private String password;
    private String codSucursal;
    @JsonIgnore
    private SucursalEntity sucursalByCodSucursal;

    @Id
    @Column(name = "cod_usuario")
    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "user")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return Objects.equals(codUsuario, that.codUsuario) &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(user, that.user) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codUsuario, nombre, user, password);
    }

    @Basic
    @Column(name = "cod_sucursal")
    public String getCodSucursal() {
        return codSucursal;
    }

    public void setCodSucursal(String codSucursal) {
        this.codSucursal = codSucursal;
    }

    @ManyToOne
    @JoinColumn(name = "cod_sucursal", referencedColumnName = "cod_sucursal", nullable = false, insertable=false ,updatable=false)
    public SucursalEntity getSucursalByCodSucursal() {
        return sucursalByCodSucursal;
    }

    public void setSucursalByCodSucursal(SucursalEntity sucursalByCodSucursal) {
        this.sucursalByCodSucursal = sucursalByCodSucursal;
    }
}
