package Abstracts;
import Interfaces.Gestioon;

import java.sql.Date;

public abstract class ProduccionLecheABS implements Gestioon {
        protected int idProduccion;
        protected int idGanado;
        protected java.sql.Date fecha;
        protected String turno;
        protected double cantidadLeche;

        public ProduccionLecheABS(int idProduccion, int idGanado, java.sql.Date fecha, String turno, double cantidadLeche) {
            this.idProduccion = idProduccion;
            this.idGanado = idGanado;
            this.fecha = fecha;
            this.turno = turno;
            this.cantidadLeche = cantidadLeche;
        }

    public int getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(int idProduccion) {
        this.idProduccion = idProduccion;
    }

    public int getIdGanado() {
        return idGanado;
    }

    public void setIdGanado(int idGanado) {
        this.idGanado = idGanado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public double getCantidadLeche() {
        return cantidadLeche;
    }

    public void setCantidadLeche(double cantidadLeche) {
        this.cantidadLeche = cantidadLeche;
    }
    public abstract void insertar();
    public abstract void actualizar();
    public abstract void eliminar();
    public abstract void consultar();
}

