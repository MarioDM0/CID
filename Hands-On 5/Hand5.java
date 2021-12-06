package examples.behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import java.util.ArrayList;
import java.util.List;

public class OneShotAgent extends Agent {

  protected void setup() {
    System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new MyOneShotBehaviour());
  } 

  private class MyOneShotBehaviour extends OneShotBehaviour {

    public double predecirY(double x, double beta0, double beta1){
        double resultado = 0;
        resultado = beta0 + (beta1 * x);
        return resultado;
    }
      
    public void action() {
        System.out.println("Agent's action method executed");
        lastError = 0;
        List<DataSet> dataset = new ArrayList<DataSet>();
        dataset = inicializarPrograma(dataset);
        double beta_0 = 0, beta_1 = 0, alfa = 0.0005f;
        double[] betas = calcularBetas(dataset,beta_0,beta_1, alfa);
        double dato = predecirY(65,betas[0],betas[1]);
        System.out.println("Beta_0: " + betas[0] + " , Beta_1: " + betas[1]);
        System.out.println("Solucion Final: " + dato );
    }
    
    public List<DataSet> inicializarPrograma(List<DataSet> dataset){
        DataSet datos = new DataSet();
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
        return dataset;
    }
    
    public double lastError;
    
    public double[] calcularBetas(List<DataSet> dataset, double beta_0, double beta_1, double alfa)
    {
        while( calcularSumatoriaPerdida(dataset,beta_0,beta_1) > 0.0001 ){
            //Calcular valor de beta_0
            beta_0 -= (alfa * sumatoriaBeta_0(dataset,beta_0,beta_1));
            
            //Calcular valor de beta_1
            beta_1 -= (alfa * sumatoriaBeta_1(dataset,beta_0,beta_1));
        }
        double[] betas = {beta_0, beta_1};
        return betas;
    }
    
    public double sumatoriaBeta_0(List<DataSet> dataset, double beta_0 , double beta_1)
    {
        double resultado = 0;
        for(DataSet dato : dataset)
        {
            resultado += (dato.getY() - ( beta_0 + beta_1 * dato.getX() ));
        }
        return ( -2.0 / dataset.size() ) * resultado ;
    }
    
    public double sumatoriaBeta_1(List<DataSet> dataset, double beta_0, double beta_1)
    {
        double resultado = 0;
        for(DataSet dato : dataset)
        {
            resultado += dato.getX()* (dato.getY() - ( beta_0 + beta_1 * dato.getX())) ;
        }
        return ( -2.0 /dataset.size()) * resultado ;
    }
    
    public double calcularSumatoriaPerdida(List<DataSet> dataset, double beta_0, double beta_1)
    {
        double resultado = 0;
        for(DataSet dato : dataset)
        {
            resultado += (dato.getY() - (beta_0 + beta_1 * dato.getX())) *  (dato.getY() - beta_0 + (beta_1 * dato.getX()));
        }
        resultado = ( 1.0 / dataset.size() ) * resultado;
        
        double aux = Math.abs(lastError - resultado);
        
        lastError = resultado; 
        
        System.out.println("Error: " + resultado );
        return aux;
    }
    
    public int onEnd() {
      myAgent.doDelete();
      return super.onEnd();
    } 
  }
  
    class DataSet{
        private double x;
        private double y;
        DataSet(double vX, double vY){
            x = vX;
            y = vY;
        }
        DataSet(){}
        void setX(double vX){
            x = vX;
        }
        double getX(){
            return x;
        }
        void setY(double vY){
            y = vY;
        }
        double getY(){
            return y;
        }
    }// END of inner class ...Behaviour
}
