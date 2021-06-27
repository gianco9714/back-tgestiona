package com.gian.tgestiona.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "sucursal", schema = "tgestiona")
public class SucursalEntity {
    private String codSucursal;
    private String nombre;
    @JsonIgnore
    private Collection<UsuarioEntity> usuariosByCodSucursal;

    @Id
    @Column(name = "cod_sucursal")
    public String getCodSucursal() {
        return codSucursal;
    }

    public void setCodSucursal(String codSucursal) {
        this.codSucursal = codSucursal;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SucursalEntity that = (SucursalEntity) o;
        return Objects.equals(codSucursal, that.codSucursal) &&
                Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codSucursal, nombre);
    }

    @OneToMany(mappedBy = "sucursalByCodSucursal")
    public Collection<UsuarioEntity> getUsuariosByCodSucursal() {
        return usuariosByCodSucursal;
    }

    public void setUsuariosByCodSucursal(Collection<UsuarioEntity> usuariosByCodSucursal) {
        this.usuariosByCodSucursal = usuariosByCodSucursal;
    }
}
