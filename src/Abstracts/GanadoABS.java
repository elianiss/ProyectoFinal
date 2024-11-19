package Abstracts;

import java.sql.Date;

public abstract class GanadoABS {
        protected int idGanado;
        protected String nombre;
        protected java.sql.Date fechaNacimiento;
        protected String raza;
        protected String estadoSalud;
        protected double peso;
        protected int idFinca;

        public GanadoABS(int idGanado, String nombre, java.sql.Date fechaNacimiento, String raza, String estadoSalud, double peso, int idFinca) {
            this.idGanado = idGanado;
            this.nombre = nombre;
            this.fechaNacimiento = fechaNacimiento;
            this.raza = raza;
            this.estadoSalud = estadoSalud;
            this.peso = peso;
            this.idFinca = idFinca;
        }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdFinca() {
        return idFinca;
    }

    public void setIdFinca(int idFinca) {
        this.idFinca = idFinca;
    }

    public int getIdGanado() {
        return idGanado;
    }

    public void setIdGanado(int idGanado) {
        this.idGanado = idGanado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public abstract void insertar();
    public abstract void actualizar();
    public abstract void eliminar();
    public abstract void consultar();
}


