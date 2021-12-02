package prim;

import java.io.*;
import java.util.StringTokenizer;

class Grafo {
    ListaOrdenada b = new ListaOrdenada();
    //si esta lleno el aem
    //cola de prioridad
    int[] aux;
    int costo=0;
    int AEM[][] = new int[20][20];//arbol de extension minima
    int NN; //numero de nodos
    int M[][] = new int[20][20];//matriz del grafo
    boolean ll= false;
    
    public Grafo(){
        aux = new int[20];
        for(int i = 0; i <=NN; i++){
            for(int j = 0; j<=NN; j++){
                AEM[i][j] = 0;
            }
            aux[i]=0;
        }
    }
    
    
    public int grado(){
        int aux=0;
        for(int i =1;i<=NN;i++){
            for(int j =1;j<=NN;j++){
                if(aux<M[i][j]){
                    aux = M[i][j];
                }
            }
        }
        System.out.println("grado: "+aux);
        return aux;
        /*
        ***  donde:
        ***  aux es un auxiliar del grado
        ***  NN es el numero de nodos
        ***  M[][] es la matriz de adyacencia con los datos
        */
    }
    
    public void LeeGrafo(String Arc) throws IOException{//Lee archivo del grafo
        FileInputStream In;
        DataInputStream On;
        String linea = null;
        int token1, token2,token3,i,j;
        try {
            In = new FileInputStream(Arc);
            On = new DataInputStream(In);
            linea = On.readLine();
            
            NN = Integer.parseInt(linea);
            System.out.println("Numero de nodos: "+NN);
            
            for(i = 1;i <= NN;i++){
                for(j = 1; j <= NN;j++){
                    M[i][j] = 0;
                }
            }
            //leer archivo
            do{
                linea = On.readLine();
                if(linea != null){
                    StringTokenizer tokens = new StringTokenizer(linea);
                    token1 = Integer.parseInt(tokens.nextToken());
                    token2 = Integer.parseInt(tokens.nextToken());
                    token3 = Integer.parseInt(tokens.nextToken());
                    System.out.println(token1+" "+token2+" "+token3);
                    M[token1][token2]=token3;
                    M[token2][token1]=token3;
                }
            }
            while(linea != null);
            In.close();
        } catch (FileNotFoundException e) {
        }
    }
    
    public void EscribeM(){
        int i,j;
        System.out.println("NODOS = "+NN);
        System.out.println("Matriz de adyacencias con valor de arco");
        System.out.printf("%3s","");
        for(i = 1; i <= NN; i++){
            System.out.printf("%3s",i);
        }
        System.out.println("");
        for(i = 1; i <= NN; i++){
            System.out.printf("%3s",i);
            for(j = 1;j <= NN; j++){
                System.out.printf("%3s",M[i][j]);
            }
            System.out.println("");
        }
    }
    
    public void EscribeAEM(){
        int i,j;
        System.out.println("NODOS = "+NN);
        for(i=0;i<=NN;i++){
            for(j=0;j<=NN;j++){
                costo = costo + AEM[i][j];
            }
        }
        costo = (costo+1)/2;
        System.out.println("COSTO = "+costo);
        System.out.println("Matriz de adyacencias con valor de arco");
        System.out.printf("%3s","");
        for(i = 1; i <= NN; i++){
            System.out.printf("%3s",i);
        }
        System.out.println("");
        for(i = 1; i <= NN; i++){
            System.out.printf("%3s",i);
            for(j = 1;j <= NN; j++){
                System.out.printf("%3s",AEM[i][j]);
            }
            System.out.println("");
        }
    }
    
    public void AEMlleno(){
        int a=0;
        for(int i = 1;i <= NN;i++){
            if(aux[i] == 1){
                a++;
            }
        }
        if(a == NN){
            ll = true;
        }
    }
    
    public void ayacentes(nodo a){
        AEM[a.info][a.info]=-1;//nos representa el origen, nodo inicial
        aux[a.info] = 1;//en el vector aux lo usamos para saber si esta ingresado en el AEM
//while
        while (ll == false) {//si ll es falso, significa que faltan nodos por ingresar            
            System.out.print("NODO "+a.info+" "+a.arco+" ->");//nodo a
            for(int i = 1;i <= NN;i++){//checamos los adyacentes de a
                if(M[a.info][i]!=0){//si si es ady, con valor
                    if(AEM[a.info][i]==0){//si no esta en AEM, con 0
                        if(aux[i]==0){//si no ha sido ingresado
                            b.Insertar_O_a(i, M[a.info][i]);//lo insertamos
                        }
                    }
                }
            }
            //b.recorrer();
            /*for(int i = 1; i <=NN; i++){
                for(int j =1;j<=NN;j++){
                    System.out.print(AEM[i][j]);
                }
                System.out.println("");                                 NO HACER CASO
            }
            for(int i =1;i<=NN;i++){
                System.out.print(aux[i]+" ");
            }
            System.out.println("");*/
            
            //metimos a aem el nodo y en la cola los ady del nodo
            //while
            do {            
                a = b.eliminarC();//sacamos el nodo de la cola
                if(a == null){
                    break;//si ya no hay mas nodos salimos
                }
            } while (aux[a.info]==1);

            if(a != null){//si hay nodo en a hacemos
                
                for(int i = 1;i<=NN;i++){
                    //System.out.println("Nodo "+a.info+" arco "+a.arco+" ingresado? "+aux[a.info]+" i "+i);//nodo, valor del arco, has sido ingresado?
                    if(M[a.info][i]==a.arco && aux[i]==1){//si en el grafo esta conectado con i Y si ha sido ingresado
                        AEM[a.info][i]= a.arco;//lo metemos al AEM
                        AEM[i][a.info]= a.arco;//inversa
                        //System.out.println("valor ingresado al AEM "+AEM[a.info][i]);//checamos que valor esta en el AEM
                        aux[a.info]=1;//marcamos como que si ha sido ingresado
                        break;//salimos
                    }
                }
            }
            AEMlleno();//verificamos que si llenamos el AEM*/
        }
        System.out.println("NODO "+a.info+" "+a.arco+" ->");
    }
}

