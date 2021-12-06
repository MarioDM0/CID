package handson6;

public class HandsOn6 {

    // Variables
    static double[] x1 = {1,1,1}; 
    static double[] x2 = {1,4,2};
    static double[] x3 = {1,2,4};
    static double[][] xAll = { {1,1,1} , {1,4,2} , {1,2,4}};
    static double[] w = {0,0,0};
    static double[] y = {0,1,1};
    static double[] aux = {0,0,0};
    static double alfa = 0.1;
    
    public static void main(String[] args) {
        //CALCULO
        // Añadir los datos necesarios para el calculo
        for(int i = 0; i < 100; i++)
        {
            aux[0] = calculoW(w[0],0, w);
            aux[1] = calculoW(w[1],1, w);
            aux[2] = calculoW(w[2],2, w);
            w[0] = aux[0];
            w[1] = aux[1];
            w[2] = aux[2];
            System.out.println((i+1) + ". W(" + w[0] + ", " + w[1] + ", " + w[2] + ")");
        }
        
        double[] xNew = {1,3.5,4};
        
        double sigmoid = sigmoid(xNew,w);
        System.out.println("Sigmoid: " + sigmoid);
        if( sigmoid > 0.5 )
        {
            System.out.println("Aproved");
        }
        else
        {
            System.out.println("Rejected");
        }
    }
    
    static double calculoW(double wCurrent, int wNum, double[] wAll){
        double result = 0;
        //Cambiar i < 3 para la cantidad de elementos en dataset
        for(int i = 0 ; i < 3 ; i++)
        {
            result += (sigmoid(xAll[i],wAll) - y[i] ) * xAll[wNum][i];
        }
        return wCurrent - (alfa * result);
    }
    
    static double sigmoid(double[] x, double[] w){
        return 1 / ( 1 + Math.exp(extractW(x,w)));
    }
    
    static double extractW(double[] xi, double[] w)
    {
        double result = 0;
        for( int i = 0 ; i < xi.length ; i++ )
        {
            result += xi[i] * w[i];
        }
        return -result;
    }
}