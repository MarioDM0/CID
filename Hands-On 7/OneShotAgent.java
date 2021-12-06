package examples.behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class OneShotAgent extends Agent {

  protected void setup() {
    System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new MyOneShotBehaviour());
  } 

  private class MyOneShotBehaviour extends OneShotBehaviour {

    double[] x1 = {1,1,1}; 
    double[] x2 = {1,4,2};
    double[] x3 = {1,2,4};
    double[][] xAll = { {1,1,1} , {1,4,2} , {1,2,4}};
    double[] w = {0,0,0};
    double[] y = {0,1,1};
    double[] aux = {0,0,0};
    double alfa = 0.1;
      
    public void action() {
        System.out.println("Agent's action method executed");
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
    
    public double calculoW(double wCurrent, int wNum, double[] wAll){
        double result = 0;
        //Cambiar i < 3 para la cantidad de elementos en dataset
        for(int i = 0 ; i < 3 ; i++)
        {
            result += (sigmoid(xAll[i],wAll) - y[i] ) * xAll[wNum][i];
        }
        return wCurrent - (alfa * result);
    }
    
    public double sigmoid(double[] x, double[] w){
        return 1 / ( 1 + Math.exp(extractW(x,w)));
    }
    
    public double extractW(double[] xi, double[] w)
    {
        double result = 0;
        for( int i = 0 ; i < xi.length ; i++ )
        {
            result += xi[i] * w[i];
        }
        return -result;
    }
    
    public int onEnd() {
      myAgent.doDelete();
      return super.onEnd();
    } 
  }
}
