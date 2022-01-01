/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capa_datos;

import capa_logica.Persona;
import java.util.ArrayList;
import java.util.Iterator;
import basicas.Consola;

/**
 *
 * @author Rafael
 */
public class ListaPersonas {
    private static ArrayList<Persona> personas = new ArrayList<>();
    private static Consola objConsola = new Consola();
    
    public static void agregar(Persona objP)
    {
        personas.add(objP);
    }
    
    public static ArrayList<Persona> obtener()
    {
        return personas;
    }
    public static boolean vertificaDNI(String auxDni){
        boolean band=false;
        
        Iterator elemento=personas.iterator();
        while(elemento.hasNext()){
            Persona objP=(Persona)elemento.next();
            
            if(objP.getDNI().equalsIgnoreCase(auxDni))
                band=true;
        }
        
        return band;
    }
    public static boolean verificaMinimo2PrestamosxPersona(String auxDni){
        boolean band=false;
        
        Iterator elemento=personas.iterator();
        while(elemento.hasNext()){
            Persona objP=(Persona)elemento.next();
            
            if(objP.getDNI().equalsIgnoreCase(auxDni)){
                if(objP.getCantidadPrestamos()<2){
                        objP.setCantidadPrestamos(objP.getCantidadPrestamos()+1);
                        band=true;
                }
            }
        }
        
        return band;
   }
    public static ArrayList<Persona> modificarPersona(String auxDNI){
        int op;
        boolean band=false;
        
        ArrayList<Persona> Aux=new ArrayList<>();
        
        Iterator it=personas.iterator();
        while(it.hasNext()){
            Persona P=(Persona)it.next();
            
            if(P.getDNI().equalsIgnoreCase(auxDNI)){
                band=true;
                do{
                    op=menu();
                    switch(op){
                        case 1: String Dni=objConsola.leer("\t-Ingrese nuevo Dni : ");
                                P.setDNI(Dni);
                                P.setCantidadPrestamos(0); break;
                        case 2: String nombre=objConsola.leer("\t-Ingrese nuevo Nombre : ");
                                P.setNombre(nombre);    break;
                        case 3: String direccion=objConsola.leer("\t-Ingrese nueva Direccion : ");
                                P.setDireccion(direccion); break;
                        case 4:  String telefono=objConsola.leer("\t-Ingrese nuevo Telefono : ");
                                P.setTelefono(telefono); break;
                        case 5: int edad=objConsola.leer("Ingrese nueva edad[18-25] : ",18, 85);
                                P.setEdad(edad);break;
                        case 6: String ocupacion=objConsola.leer("\t-Ingrese nueva Ocupacion: ");
                                P.setOcupacion(ocupacion); break;
                        case 7: float ingresoMensual=objConsola.leer("Ingrese nuevo ingreso mensual[0-10.000](S/.) : ",0, 10000);
                                P.setIngresoMensual(ingresoMensual);break;
                       
   
                    }
                }while(op!=8);
                Aux.add(P);
            }
        }
        
        if(!band){
            System.out.println("\n* Persona no registrada con ese DNI");
        }

        return Aux;
    }
    public static int menu(){
        int num;//String DNI, String nombre, String direccion, String telefono, int edad, String ocupacion, float ingresoMensual,int cantidadPrestamos
        
        System.out.println("\tAtributo a modificar");
        System.out.println("\t[1] DNI");
        System.out.println("\t[2] Nombre");
        System.out.println("\t[3] Direccion");
        System.out.println("\t[4] Telefono");
        System.out.println("\t[5] Edad");
        System.out.println("\t[6] Ocupacion");
        System.out.println("\t[7] Ingreso Mensual");
        System.out.println("\t[8] Volver");
        
        num=objConsola.leer("Seleccione atributo a modificar[1-8] : ",1,8);
        
        return num;
    }
}
