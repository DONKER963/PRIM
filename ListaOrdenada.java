/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prim;

class nodo{//nodo
    public int info,arco;
    public nodo liga;
    public nodo (int dato, int ar){
        info=dato;
        liga = null;
        arco = ar;
    }
}


/**
 *
 * @author cuent
 */
public class ListaOrdenada{
    private nodo primero;
    private nodo ultimo;
    nodo ant = null;
    nodo pos = null;
    
    public void Insertar_O_a(int v, int ar){
        nodo temp = new nodo(v, ar);
        if(primero == null){
            primero = ultimo = temp;
        }
        else if(temp.arco < primero.arco){
            temp.liga = primero;
            primero = temp;
        }
        else if(temp.arco >= ultimo.arco){
            ultimo.liga = temp;
            ultimo = temp;
        }
        else{
            ant = null;
            pos = primero;
            
            while (pos.arco <= temp.arco) {                
                ant = pos;
                pos = pos.liga;
            }
            ant.liga = temp;
            temp.liga = pos;
        }
        
    }
    
    public nodo eliminarC(){
        nodo ret;
        if(primero == null){
            System.out.println("vacia");
            ret = null;
        }
        else{
            ret = primero;
            primero = primero.liga;
        //una cola funciona como:
        //primero que entra, primero que sale...
        }
        return ret;
    }
    
    public void recorrer(){
        nodo aux = primero;
        System.out.println("lista");
        while(aux != null){
            System.out.print(aux.info+"/"+aux.arco+"   ");
            aux = aux.liga;
        }
        System.out.println("");
    }
}