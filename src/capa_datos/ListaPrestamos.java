/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capa_datos;

import basicas.Consola;
import capa_logica.EstadoPrestamo;
import capa_logica.Prestamo;
import capa_logica.TipoMoneda;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author Rafael
 */

public class ListaPrestamos {
    
    private static ArrayList<Prestamo> prestamos = new ArrayList<>();
    private static Consola objConsola=new Consola();
    
    public static void agregar(Prestamo objP)
    {
        prestamos.add(objP);
    }
    
    public static ArrayList<Prestamo> obtener()
    {
        return prestamos;
    }
   public static ArrayList<Prestamo> modificarPrestamo(String auxCodigo){
        int op;
        boolean band=false;
        
        ArrayList Aux=new ArrayList();
        
        Iterator it=prestamos.iterator();
        while(it.hasNext()){
            Prestamo P=(Prestamo)it.next();
            
            if(P.getCodigo().equalsIgnoreCase(auxCodigo)){
                band=true;
                do{
                    op=menu();
                    switch(op){
                        case 1: String Codigo=objConsola.leer("\t-Ingrese nuevo Codigo : ");
                                P.setCodigo(Codigo); break;
                        case 2: Date fechaAux = new Date();
                                P.setFecha(fechaAux);break;
                        case 3: float montoSolicitado=objConsola.leer("Ingrese monto solicitado[0-100.000] : ", 0,100000);
                                P.setMontoSolicitado(montoSolicitado);
                                P.setMontoCuota(((P.getMontoSolicitado())/P.getMeses())+P.getInteres());break;
                        case 4:  System.out.println("1."+TipoMoneda.SOLES.name()+"\n2."+TipoMoneda.DOLARES.name()+
                                "\n3."+TipoMoneda.EUROS.name());
                                 int op1=objConsola.leer("Ingrese tipo de moneda[1-3] : ", 1, 3);
                                    switch(op1){
                                    case 1: P.setMoneda(TipoMoneda.SOLES); break;
                                    case 2: P.setMoneda(TipoMoneda.DOLARES); break;
                                     case 3:P.setMoneda(TipoMoneda.EUROS); break;
                                    } break;
                        case 5:  int meses=objConsola.leer("Ingrese cantidad de meses[1-50] : ",1,50);
                                   P.setMeses(meses);
                                   P.setMontoCuota(((P.getMontoSolicitado())/P.getMeses())+P.getInteres());break;
                        case 6: float interes=objConsola.leer("Ingrese intere[0.0-1.0] : ",0.0f,1.0f);
                                P.setInteres(interes);
                                P.setMontoCuota(((P.getMontoSolicitado())/P.getMeses())+P.getInteres());break;
                        case 7: String dniBeneficiario=objConsola.leer("Ingrese DNI de beneficiario: ");
                                P.setDniBeneficiario(dniBeneficiario);break;
                       
   
                    }
                }while(op!=8);
                Aux.add(P);
            }
        }
        if(!band)
            System.out.println("\n* Prestamo no registrado con ese Codigo");
        return Aux;
    }
   
    public static void pagar(String auxCodigo){
        boolean band = false;
        boolean band1 = false;
        
        Iterator<Prestamo> it = prestamos.iterator();
        while(it.hasNext()){
            Prestamo p = it.next();
            
            if(p.getCodigo().equalsIgnoreCase(auxCodigo)){
                band = true;
                
                if(p.getEstado() == EstadoPrestamo.ACTIVO){
                    band1 = true;
                    
                    System.out.println("1. Pagar");
                    System.out.println("2. Salir");
                    int op1 = objConsola.leer("Ingrese opcion[1-2] : ", 1, 2);
                    
                    if(op1 == 1){
                        p.registrarPago();
                        System.out.println("\n* Pago realizado");
                    }
                }
            }
        }
        
        if(!band){
            System.out.println("\n* Prestamo no registrado con ese Codigo");
        }
        
        if(!band1){
            System.out.println("\n* Prestamo finalizado");
        }
    }
    
    public static Prestamo buscarPrestamo(String auxCodigo){
        boolean band = false;
        boolean band1 = false;
        
        Prestamo aux = null;
        
        Iterator<Prestamo> it = prestamos.iterator();
        while(it.hasNext()){
            Prestamo p = it.next();
            
            if(p.getCodigo().equalsIgnoreCase(auxCodigo)){
                band = true;
                
                if(p.getEstado() == EstadoPrestamo.ACTIVO){
                    band1 = true;
                    
                    aux = p;
                }
            }
        }
        
        if(!band){
            System.out.println("\n* Prestamo no registrado con ese Codigo");
        }
        
        if(!band1){
            System.out.println("\n* Prestamo finalizado");
        }
        
        return aux;
    }
    
    public static ArrayList<Prestamo> prestamosFinalizados(){
        boolean band=false;
        
        ArrayList<Prestamo> Aux = new ArrayList<>();
        
        Iterator<Prestamo> it = prestamos.iterator();
        while(it.hasNext()){
            Prestamo p = it.next();
            
            if(p.getEstado() == EstadoPrestamo.FINALIZADO){
                band=true;
                
                Aux.add(p);
            }
        }
        if(!band){
            System.out.println("\n* No hay prestamos finalizados");
        }
        
        return Aux;
    }
    
    public static ArrayList<Prestamo> prestamosPorPersona(String auxDni){
        boolean band=false;
        
        ArrayList<Prestamo> Aux = new ArrayList<>();
        
        Iterator<Prestamo> it = prestamos.iterator();
        while(it.hasNext()){
            Prestamo p = it.next();
            
            if(p.getDniBeneficiario().equalsIgnoreCase(auxDni)){
                band = true;
                
                Aux.add(p);
            }
        }
        
        if(!band){
            System.out.println("\n* No hay prestamos registrado con ese DNI");
        }
        
        return Aux;
    }
    
    public static ArrayList<Prestamo> prestamosSegunMesAño(int auxMes, int auxAño){
        boolean band=false;
        
        ArrayList<Prestamo> Aux = new ArrayList<>();
        
        Iterator<Prestamo> it = prestamos.iterator();
        while(it.hasNext()){
            Prestamo p = it.next();
            
            if(p.getFecha().getMonth() == auxMes && p.getFecha().getYear() == auxAño){
                band = true;
                
                Aux.add(p);
            }
        }
        
        if(!band){
            System.out.println("\n* No hay prestamos en el mes y año indicado");
        }
        
        return Aux;
    }
    
    public static ArrayList<Prestamo> prestamosSegunEstado(EstadoPrestamo auxEstado){
        boolean band=false;
        
        ArrayList<Prestamo> Aux = new ArrayList<>();
        
        Iterator<Prestamo> it = prestamos.iterator();
        while(it.hasNext()){
            Prestamo p = it.next();
            
            if(p.getEstado() == auxEstado){
                band = true;
                
                Aux.add(p);
            }
        }
        
        if(!band){
            System.out.println("\n* Prestamo no registrado con ese Codigo");
        }
        
        return Aux;
    }
    
   
    public static int menu(){
        int num;//String codigo, Date fecha, float montoSolicitado, TipoMoneda moneda, int meses, float interes, float montoCuota, String dniBeneficiario
        System.out.println("\tAtributo a modificar");
        System.out.println("\t[1] Codigo");
        System.out.println("\t[2] Fecha");
        System.out.println("\t[3] montoSolicitado");
        System.out.println("\t[4] TipoMoneda");
        System.out.println("\t[5] Meses");
        System.out.println("\t[6] Intereses");
        System.out.println("\t[7] DNI Beneficiario");
        System.out.println("\t[8] Volver");
        num=objConsola.leer("Seleccione atributo a modificar[1-8] : ",1,8);
        return num;
    }
    
}
