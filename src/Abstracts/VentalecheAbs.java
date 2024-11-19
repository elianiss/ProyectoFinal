package Abstracts;

import Interfaces.Gestioon;

import java.sql.Date;

public abstract class VentalecheAbs implements Gestioon {

    protected int idVenta;
    protected int idCliente;
    protected java.sql.Date fechaVenta;
    protected double cantidadVendida;
    protected double precioPorLitro;
    protected double totalVenta;

    public VentalecheAbs() {
        this.cantidadVendida = cantidadVendida;
        this.fechaVenta = fechaVenta;
        this.idCliente = idCliente;
        this.idVenta = idVenta;
        this.precioPorLitro = precioPorLitro;
        this.totalVenta = totalVenta;
    }

    public double getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(double cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public double getPrecioPorLitro() {
        return precioPorLitro;
    }

    public void setPrecioPorLitro(double precioPorLitro) {
        this.precioPorLitro = precioPorLitro;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public abstract void insertar();
    public abstract void actualizar();
    public abstract void eliminar();
    public abstract void consultar();



}
