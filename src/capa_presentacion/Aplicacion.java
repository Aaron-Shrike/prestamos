/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capa_presentacion;

import LibMenu.Menu;
import LibMenu.Opcion;
import basicas.Consola;
import capa_datos.ListaPersonas;
import capa_datos.ListaPrestamos;
import capa_logica.EstadoPrestamo;
import capa_logica.Persona;
import capa_logica.Prestamo;
import capa_logica.TipoMoneda;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author GARCIA LOPEZ ALEJANDRA
 *          RAMIREZ BENITES RAFAEL
 *          ROJAS VERA AARON
 */
public class Aplicacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Menu  objMenu = new Menu();
        
        int opc;
        
        definirmenu(objMenu);  
        
        do{ 
            opc = objMenu.display();
            
            switch(opc){                
                case 11:
                        Persona objP = new Persona();
                        Consola objConsola = new Consola();
                        
                        objP.setDNI(objConsola.leer("Ingrese DNI : "));
                        objP.setNombre(objConsola.leer("Ingrese nombre : "));
                        objP.setDireccion(objConsola.leer("Ingrese direccion : "));
                        objP.setTelefono(objConsola.leer("Ingrese telefono : "));
                        objP.setEdad(objConsola.leer("Ingrese edad[18-85] : ",18,85));
                        objP.setOcupacion(objConsola.leer("Ingrese ocupacion : "));
                        objP.setIngresoMensual(objConsola.leer("Ingrese ingreso mensual[0-10.000](S/.) : ", 0, 10000));
                        
                        ListaPersonas.agregar(objP);
                        
                        System.out.println("\n* Persona registrada exitosamente"); 
                    break;
                case 12:
                        Consola objConsola1 = new Consola();
                    
                        //String DNI, String nombre, String direccion, String telefono, int edad, String ocupacion, float ingresoMensual,int cantidadPrestamos
                        System.out.println("------------------------------------");
                        System.out.println("MODIFICAR ALGUN DATO DE LA PERSONA");
                         
                        String auxDni=objConsola1.leer("Ingrese DNI de la persona a modificar : ");
                        
                        ArrayList listadoPersonas = ListaPersonas.modificarPersona(auxDni);
                        
                        Iterator it1=listadoPersonas.iterator();
                        while(it1.hasNext()){
                            Persona P=(Persona)it1.next();
                            
                            System.out.println("Comprobacion:");
                            System.out.println(" -DNI : "+P.getDNI());
                            System.out.println(" -Nombre : "+P.getNombre());
                            System.out.println(" -Direccion : "+P.getDireccion());
                            System.out.println(" -Telefono : "+P.getTelefono());
                            System.out.println(" -Edad : "+P.getEdad());
                            System.out.println(" -Ocupacion : "+P.getOcupacion());
                            System.out.println(" -Ingreso Mensual : "+P.getIngresoMensual());
                        }
                        System.out.println("------------------------------------");
                    break;
                case 13: 
                        ArrayList listadoPersonas1 = ListaPersonas.obtener();
                        Iterator itper= listadoPersonas1.iterator();
                        
                        System.out.println("LISTADO DE PERSONAS\n");
                        System.out.println("Dni\tNombre\tDireccion\tTelefono\tEdad\tOcupacion\tIngreso Mensual");
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");
                        
                        while(itper.hasNext()){
                            Persona objPer = (Persona)itper.next();
                            
                            System.out.println(objPer.toString());
                        }
                        
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");
                        
                    break;
                    
                case 21:
                        //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        //System.out.println(dateFormat.format(fechaAux)); //2016/11/16 12:08:43
                        Prestamo objPre = new Prestamo();
                        Consola objCon = new Consola();
                        
                        objPre.setCodigo(objCon.leer("Ingrese nuevo codigo : "));
                        objPre.setFecha(new Date());
                        objPre.setMontoSolicitado(objCon.leer("Ingrese monto solicitado[0-100.000] : ", 0, 100000));
                        System.out.println("1."+TipoMoneda.SOLES.name()+"\n2."+TipoMoneda.DOLARES.name()+
                                "\n3."+TipoMoneda.EUROS.name());
                        int op=objCon.leer("Ingrese tipo de moneda[1-3] : ", 1, 3);
                        switch(op){
                            case 1: objPre.setMoneda(TipoMoneda.SOLES); break;
                            case 2: objPre.setMoneda(TipoMoneda.DOLARES); break;
                            case 3: objPre.setMoneda(TipoMoneda.EUROS); break;
                        }
                        objPre.setMeses(objCon.leer("Ingrese cantidad de meses[1-50] : ",1,50));
                        objPre.setInteres(objCon.leer("Ingrese interes(decimales)[0.0-1.0] : " ,0.0f, 1.1f));
                        objPre.setMontoCuota(((objPre.getMontoSolicitado())/objPre.getMeses())+objPre.getInteres());
                        objPre.setDniBeneficiario(objCon.leer("Ingrese DNI de beneficiario : "));
                        if(ListaPersonas.vertificaDNI(objPre.getDniBeneficiario())){
                            
                            if(ListaPersonas.verificaMinimo2PrestamosxPersona(objPre.getDniBeneficiario())){
                                ListaPrestamos.agregar(objPre);
                                System.out.println("\n* Prestamo registrado exitosamente\n"); 
                            }else{
                                System.out.println("\n* Minimo de prestamos 2");
                            }
                        }else{
                            System.out.println("\n* Cliente no registrado");
                        }   
                    break;
                case 22:
                        Consola objConsola2 = new Consola();
                        //String codigo, Date fecha, float montoSolicitado, TipoMoneda moneda, int meses, float interes, float montoCuota, String dniBeneficiario
                        System.out.println("------------------------------------");
                        System.out.println("MODIFICAR ALGUN DATO DEL PRESTAMO");
                         
                        String auxCodigo=objConsola2.leer("Ingrese codigo del prestamo a modificar : ");
                        
                        ArrayList listadoPrestamos=ListaPrestamos.modificarPrestamo(auxCodigo);
                        
                        Iterator it2=listadoPrestamos.iterator();
                        while(it2.hasNext()){
                            Prestamo P=(Prestamo)it2.next();
                            
                            System.out.println("Comprobacion:");
                            System.out.println("-Codigo : "+P.getCodigo());
                            System.out.println("-Fecha : "+P.getFecha());
                            System.out.println("-Monto solicitado : "+P.getMontoSolicitado());
                            System.out.println("-Tipo Moneda : "+P.getMoneda());
                            System.out.println("-Meses : "+P.getMeses());
                            System.out.println("-Intereses : "+P.getInteres());
                            System.out.println("-MontoCuota : "+P.getMontoCuota());
                            System.out.println("-DNI Beneficiario : "+P.getDniBeneficiario());
                        }
                        System.out.println("------------------------------------");
                     break;
                case 23:
                        ArrayList listadoPrestamos1 = ListaPrestamos.obtener();
                        Iterator itpre1= listadoPrestamos1.iterator();
                        
                        System.out.println("LISTADO DE PRESTAMOS\n");
                        System.out.println("Codigo\tFecha\tMonto Solicitado\tTipo de Moneda\tMeses\tIntereses\tMonto Cuota\tDNI Beneficiario");                      
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");
                        
                        while(itpre1.hasNext()){
                            Prestamo objPre1= (Prestamo)itpre1.next();
                            
                            System.out.println(objPre1.toString());
                        } 
                        
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");
                    break;
                    
                case 31:
                        Consola objConsola3 = new Consola();
                        
                        String auxCod = objConsola3.leer("Ingrese codigo del prestamo : ");
                        
                        ListaPrestamos.pagar(auxCod);
                     
                    break;
                
                case 41:
                        Consola objConsola4 = new Consola();
                        
                        String auxCod1 = objConsola4.leer("Ingrese Codigo del prestamo : ");
                        
                        Prestamo objPres = ListaPrestamos.buscarPrestamo(auxCod1);
                        
                        if(objPres != null){
                            System.out.println("Codigo\tFecha\tMonto solicitado\tTipo de Moneda\tMeses\tIntereses\tMonto Cuota\tDNI Beneficiario");                      
                            System.out.println("----------------------------------------------------------------------------------------------------------------------");
                        
                            System.out.println(objPres.toString());
                        
                            System.out.println("Numero de cuota va pagando : " + objPres.getNumCuota());
                            System.out.println("Estado del prestamo : " + objPres.getEstado());
                        }
                    break;
                case 42:
                        ArrayList<Prestamo> listadoPrestamos2 = ListaPrestamos.prestamosFinalizados();
                        
                        System.out.println("PRESTAMOS FINALIZADOS\n");
                        System.out.println("Codigo\tFecha\tMonto solicitado\tTipo de Moneda\tMeses\tIntereses\tMonto Cuota\tDNI Beneficiario");                      
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");
                        
                        Iterator<Prestamo> elemento = listadoPrestamos2.iterator();
                        while(elemento.hasNext()){
                            Prestamo objPre1 = elemento.next();
                            
                            System.out.println(objPre1.toString());
                        } 
                        
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");                  
                    break;
                case 43:
                        Consola objConsola5 = new Consola();
                        
                        String auxDni1 = objConsola5.leer("Ingrese DNI de la persona a consultar : ");
                        ArrayList<Prestamo> listadoPrestamos3 = ListaPrestamos.prestamosPorPersona(auxDni1);
                        
                        System.out.println("PRESTAMOS DE UNA PERSONA\n");
                        System.out.println("Codigo\tFecha\tMonto solicitado\tTipo de Moneda\tMeses\tIntereses\tMonto Cuota\tDNI Beneficiario");                      
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");
                        
                        Iterator<Prestamo> elemento1 = listadoPrestamos3.iterator();
                        while(elemento1.hasNext()){
                            Prestamo objPre1 = elemento1.next();
                            
                            System.out.println(objPre1.toString());
                        } 
                        
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");                  
                    break;
                case 44:
                        Consola objConsola6 = new Consola();
                        
                        int auxMes = objConsola6.leer("Ingrese mes[1-12] : ",1,12);
                        int auxAño = objConsola6.leer("Ingrese año[2010-2035] : ",2010,2035);
                        
                        ArrayList<Prestamo> listadoPrestamos4 = ListaPrestamos.prestamosSegunMesAño(auxMes, auxAño);
                        
                        System.out.println("PRESTAMOS SEGUN MES Y AÑO\n");
                        System.out.println("Codigo\tFecha\tMonto solicitado\tTipo de Moneda\tMeses\tIntereses\tMonto Cuota\tDni Beneficiario");                      
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");
                        
                        Iterator<Prestamo> elemento2 = listadoPrestamos4.iterator();
                        while(elemento2.hasNext()){
                            Prestamo objPre1 = elemento2.next();
                            
                            System.out.println(objPre1.toString());
                        } 
                        
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");                                   
                    break;
                case 45:
                        Consola objConsola7 = new Consola();
                        
                        EstadoPrestamo auxEstado = null;
                        
                        System.out.println("1. "+EstadoPrestamo.ACTIVO.name());
                        System.out.println("2. "+EstadoPrestamo.FINALIZADO.name());
                        int opcion = objConsola7.leer("Ingrese estado[1-2] : ",1,2);
                        switch(opcion){
                            case 1: auxEstado = EstadoPrestamo.ACTIVO; break;
                            case 2: auxEstado = EstadoPrestamo.FINALIZADO; break;
                        }
                        
                        ArrayList<Prestamo> listadoPrestamos5 = ListaPrestamos.prestamosSegunEstado(auxEstado);
                        
                        System.out.println("PRESTAMOS SEGUN ESTADO\n");
                        System.out.println("Codigo\tFecha\tMonto solicitado\tTipo de Moneda\tMeses\tIntereses\tMonto Cuota\tDNI Beneficiario");                      
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");
                        
                        Iterator<Prestamo> elemento3 = listadoPrestamos5.iterator();
                        while(elemento3.hasNext()){
                            Prestamo objPre1 = elemento3.next();
                            
                            System.out.println(objPre1.toString());
                        } 
                        
                        System.out.println("----------------------------------------------------------------------------------------------------------------------");                                   
                    break;
            }            
        }while(opc != 99);
        
    }
    public static void definirmenu(Menu objMenu)
    {
        objMenu.agregar(new Opcion(11,"Registrar persona"));
        objMenu.agregar(new Opcion(12,"Modificar persona"));
        objMenu.agregar(new Opcion(13,"Listado de personas"));
        
        objMenu.agregar(new Opcion(21,"Registrar prestamo"));   
        objMenu.agregar(new Opcion(22,"Modificar prestamo"));   
        objMenu.agregar(new Opcion(23,"Listado de prestamos"));
        
        objMenu.agregar(new Opcion(31,"Registrar el pago de una cuota")); 
        
        objMenu.agregar(new Opcion(41,"Mostrar informacion de prestamo"));   
        objMenu.agregar(new Opcion(42,"Prestamos finalizados"));   
        objMenu.agregar(new Opcion(43,"Buscar prestamos de una persona"));   
        objMenu.agregar(new Opcion(44,"Prestamos segun mes y año"));   
        objMenu.agregar(new Opcion(45,"Prestamos segun estado")); 
        
        objMenu.agregar(new Opcion(99,"Salir"));
    }
    
}
