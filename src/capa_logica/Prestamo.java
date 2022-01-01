/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capa_logica;

import java.util.Date;

/**
 *
 * @author Rafael
 */
public class Prestamo {
    private String codigo;
    private Date fecha;
    private float montoSolicitado;
    private TipoMoneda moneda;
    private int meses;
    private float interes;
    private float montoCuota;
    private String dniBeneficiario;
    private EstadoPrestamo estado;
    private int numCuota;

    public Prestamo(String codigo, Date fecha, float montoSolicitado, TipoMoneda moneda, int meses, float interes, float montoCuota, String dniBeneficiario, EstadoPrestamo estado, int numCuota) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.montoSolicitado = montoSolicitado;
        this.moneda = moneda;
        this.meses = meses;
        this.interes = interes;
        this.montoCuota = montoCuota;
        this.dniBeneficiario = dniBeneficiario;
        this.estado = estado;
        this.numCuota = numCuota;
    }
    
    public Prestamo() {
        codigo = "";
        fecha = null;
        montoSolicitado = 0.0F;
        moneda = null;
        meses = 0;
        interes = 0.0F;
        montoCuota = 0.0F;
        dniBeneficiario = "";
        estado = EstadoPrestamo.ACTIVO;
        numCuota = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(float montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    public TipoMoneda getMoneda() {
        return moneda;
    }

    public void setMoneda(TipoMoneda moneda) {
        this.moneda = moneda;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public float getInteres() {
        return interes;
    }

    public void setInteres(float interes) {
        this.interes = interes;
    }

    public float getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(float montoCuota) {
        this.montoCuota = montoCuota;
    }

    public String getDniBeneficiario() {
        return dniBeneficiario;
    }

    public void setDniBeneficiario(String dniBeneficiario) {
        this.dniBeneficiario = dniBeneficiario;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

    public int getNumCuota() {
        return numCuota;
    }

    public void setNumCuota(int numCuota) {
        this.numCuota = numCuota;
    }
     
    @Override
    public String toString(){
        String texto;
            texto = codigo + "\t" + fecha + "\t" + montoSolicitado + "\t" + moneda + "\t" +meses+"\t" +interes+"\t" +montoCuota+"\t" +dniBeneficiario;
       return texto;
    }  
    
    public void registrarPago(){
        numCuota++;
        
        if(numCuota == meses){
            estado = EstadoPrestamo.FINALIZADO;
        }
    }
}
