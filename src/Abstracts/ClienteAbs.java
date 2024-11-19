package Abstracts;

import Interfaces.Gestioon;

public abstract class ClienteAbs implements Gestioon {

    protected int idCliente;
    protected String nombre;
    protected String telefono;
    protected String direccion;

    public ClienteAbs(String direccion, int idCliente, String nombre, String telefono) {
        this.direccion = direccion;
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public abstract void insertar();
    public abstract void actualizar();
    public abstract void eliminar();
    public abstract void consultar();
}
