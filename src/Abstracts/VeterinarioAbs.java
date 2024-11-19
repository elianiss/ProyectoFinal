package Abstracts;

import Interfaces.Gestioon;
public  abstract class VeterinarioAbs implements Gestioon {

    protected int idVeterinario;
    protected String nombre;
    protected String especialidad;
    protected String telefono;

    public VeterinarioAbs(int idVeterinario, String nombre, String especialidad, String telefono) {
        this.idVeterinario = idVeterinario;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.telefono = telefono;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
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
