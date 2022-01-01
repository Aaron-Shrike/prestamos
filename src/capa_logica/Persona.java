/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capa_logica;

/**
 *
 * @author Rafael
 */
public class Persona {
    private String DNI;
    private String nombre; 
    private String direccion;
    private String telefono; 
    private int edad;
    private String ocupacion;
    private float ingresoMensual;
    private int cantidadPrestamos;

    public Persona(String DNI, String nombre, String direccion, String telefono, int edad, String ocupacion, float ingresoMensual,int cantidadPrestamos) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.edad = edad;
        this.ocupacion = ocupacion;
        this.ingresoMensual = ingresoMensual;
        this.cantidadPrestamos=cantidadPrestamos;
    }

    public Persona() {
        DNI = "";
        nombre = "";
        direccion = "";
        telefono = "";
        edad = 0;
        ocupacion = "";
        ingresoMensual = 0.0F;
        cantidadPrestamos=0;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public float getIngresoMensual() {
        return ingresoMensual;
    }

    public void setIngresoMensual(float ingresoMensual) {
        this.ingresoMensual = ingresoMensual;
    }
    
    public int getCantidadPrestamos() {
        return cantidadPrestamos;
    }
    
    public void setCantidadPrestamos(int cantidadPrestamos) {
        this.cantidadPrestamos = cantidadPrestamos;
    }
    
    @Override
    public String toString(){
        String texto;
            texto = DNI + "\t" + nombre + "\t" + direccion + "\t" + telefono + "\t" +edad+"\t" +ocupacion+"\t" +ingresoMensual;
       return texto;
    } 
}
