/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hands.on.pkg3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author mario
 */
public class HandsOn3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Declaracion de Dataset
        DataSet datos = new DataSet();
        List<DataSet> dataset = new ArrayList<DataSet>();
        datos = new DataSet(23,651);
        dataset.add(datos);
        datos = new DataSet(26,762);
        dataset.add(datos);
        datos = new DataSet(30,856);
        dataset.add(datos);
        datos = new DataSet(34,1063);
        dataset.add(datos);
        datos = new DataSet(43,1190);
        dataset.add(datos);
        datos = new DataSet(48,1298);
        dataset.add(datos);
        datos = new DataSet(52,1421);
        dataset.add(datos);
        datos = new DataSet(57,1440);
        dataset.add(datos);
        datos = new DataSet(58,1518);
        dataset.add(datos);
        
        //recibir datos de pantalla
        Scanner in = new Scanner(System.in);
        System.out.print("Ingresa valor dependiente: ");
        float x = in.nextFloat();
        
        //calcular datos
        float beta0 = 0, beta1 = 0;
        beta1 = obtenerBeta1(dataset);
        beta0 = obtenerBeta0(dataset,beta1);
        float resultado = predecirY(x,beta0,beta1);
        //mostrar resultados
        System.out.println("La prediccion para: " + x + " es de: " + resultado);
    }
    
    public static float predecirY(float x, float beta0, float beta1){
        float resultado = 0;
        resultado = beta0 + (beta1 * x);
        return resultado;
    }
    
    public static float obtenerBeta0(List<DataSet> lista, float beta1){
        float result = 0;
        result = (sum(lista,2) - (beta1 * sum(lista,1)))/lista.size();
        return result;
    }
    
    public static float obtenerBeta1(List<DataSet> lista){
        float result = 0;
        result = ((lista.size() * sum(lista,3)) - (sum(lista,1) * sum(lista,2)))
                /( (lista.size() * sum(lista,4)) - (sum(lista,1) * sum(lista,1)) );
        return result;
    }
    
    public static float sum(List<DataSet> lista, int opcion){
        float result = 0;
        for(DataSet datos:lista){
            switch(opcion){
                case 1:
                    result += datos.getX();
                    break;
                case 2:
                    result += datos.getY();
                    break;
                case 3: 
                    result += datos.getX() * datos.getY();
                    break;
                case 4:
                    result += datos.getX() * datos.getX();
                    break;
            }
        }
        return result;
    }
}

class DataSet{
    private float x;
    private float y;
    DataSet(float vX, float vY){
        x = vX;
        y = vY;
    }
    DataSet(){}
    void setX(float vX){
        x = vX;
    }
    float getX(){
        return x;
    }
    void setY(float vY){
        y = vY;
    }
    float getY(){
        return y;
    }
}
