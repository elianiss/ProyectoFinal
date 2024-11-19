package Abstracts;

import Interfaces.Gestioon;

import java.sql.Date;

public  abstract class EmpleadoAbs implements Gestioon  {
    protected int idEmpleado;
    protected String nombre;
    protected String posicion;
    protected java.sql.Date fechaContratacion;

    public EmpleadoAbs(int idEmpleado, String nombre, String posicion, Date fechaContratacion) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.posicion = posicion;
        this.fechaContratacion = fechaContratacion;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public abstract void insertar();
    public abstract void actualizar();
    public abstract void eliminar();
    public abstract void consultar();
}
