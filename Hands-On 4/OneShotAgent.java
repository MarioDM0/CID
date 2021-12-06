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

    public float predecirY(float x, float beta0, float beta1){
        float resultado = 0;
        resultado = beta0 + (beta1 * x);
        return resultado;
    }
    
    public float sum(List<DataSet> lista, int opcion){
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
      
    public void action() {
        System.out.println("Agent's action method executed");
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
        float beta1 = obtenerBeta1(dataset);
        float beta0 = obtenerBeta0(dataset,beta1);
        float dato = predecirY(65,beta0,beta1);
        System.out.println("Solucion Final: " + dato );
    } 
    
    public float obtenerBeta0(List<DataSet> lista, float beta1){
        float result = 0;
        result = (sum(lista,2) - (beta1 * sum(lista,1)))/lista.size();
        return result;
    }
    
    public float obtenerBeta1(List<DataSet> lista){
        float result = 0;
        result = ((lista.size() * sum(lista,3)) - (sum(lista,1) * sum(lista,2)))
                /( (lista.size() * sum(lista,4)) - (sum(lista,1) * sum(lista,1)) );
        return result;
    }
    
    public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
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
    }// END of inner class ...Behaviour
}
