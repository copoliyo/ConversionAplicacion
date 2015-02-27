/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import general.DatosComunes;

/**
 *
 * @author Txus
 */
public class LineaMovimientoContable {

    private String empresa;
    private String cuenta;    
    private long fechaAsientoApunte;
    private int fecha;
    private int asiento;
    private int apunte;
    private int centro;
    private int documento;
    private int clave;   
    private String concepto;
    private double importe;


    
    public LineaMovimientoContable(){
        empresa = DatosComunes.eEmpresa;
        centro = DatosComunes.centroCont;
        fechaAsientoApunte = 0L;
        fecha = 0;
        asiento = 0;
        apunte = 0;
        cuenta = "";
        documento = 0;
        clave = 0;
        importe = 0.0;        
    }
    
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public long getFechaAsientoApunte() {
        return fechaAsientoApunte;
    }

    public void setFechaAsientoApunte(long fechaAsientoApunte) {
        this.fechaAsientoApunte = fechaAsientoApunte;
        String strFechaAsientoApunte = String.valueOf(fechaAsientoApunte);
        this.setFecha(Integer.valueOf(strFechaAsientoApunte.substring(0, 8)));
        this.setAsiento(Integer.valueOf(strFechaAsientoApunte.substring(8, 13)));
        this.setApunte(Integer.valueOf(strFechaAsientoApunte.substring(13, 18)));
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
        String claveFechaAsientoApunte = String.format("%08d", this.fecha)
                + String.format("%05d", this.getAsiento())
                + String.format("%05d", this.getApunte());
        this.fechaAsientoApunte = Long.valueOf(claveFechaAsientoApunte);
    }

    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
        String claveFechaAsientoApunte = String.format("%08d", this.getFecha())
                + String.format("%05d", this.asiento)
                + String.format("%05d", this.getApunte());
        this.fechaAsientoApunte = Long.valueOf(claveFechaAsientoApunte);
    }

    public int getApunte() {
        return apunte;
    }

    public void setApunte(int apunte) {
        this.apunte = apunte;
        String claveFechaAsientoApunte = String.format("%08d", this.getFecha())
                + String.format("%05d", this.getAsiento())
                + String.format("%05d", this.apunte);
        this.fechaAsientoApunte = Long.valueOf(claveFechaAsientoApunte);
    }

    public int getCentro() {
        return centro;
    }

    public void setCentro(int centro) {
        this.centro = centro;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }    
    
    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
}
