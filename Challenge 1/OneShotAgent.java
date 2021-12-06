package examples.behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class OneShotAgent extends Agent {

  protected void setup() {
    System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new MyOneShotBehaviour());
  } 

  private class MyOneShotBehaviour extends OneShotBehaviour {

    public float objective(float data){
        return data * data;
    }
    
    public float derivative(float data){
        return data * 2;
    }
      
    public void action() {
        System.out.println("Agent's action method executed");
        float dato = gradient_descent(30,0.1f);
        System.out.println("Solucion Final: " + dato );
    } 
    
    public float gradient_descent(int n_iter, float step_size){
        int aux = (int)(Math.random()*1000+1);
        int helper = ((int)(Math.random()*2+1)) == 1 ? 1 : -1;
        float solution = aux * 0.001f * helper;
        float gradient;
        float solution_eval;
        // run the gradient descent
        for (int i = 0 ; i < n_iter ; i++){
            // calculate gradient
            gradient = derivative(solution);
            // take a step
            solution = solution - step_size * gradient;
            // evaluate candidate point
            solution_eval = objective(solution);
            // store solution
            // PENDIENTES
            //solutions.append(solution)
            //scores.append(solution_eval)
            // report progress
            System.out.println("Solucion: " + solution + "  Evaluacion: " + solution_eval);
        }
        return solution;
    }
    
    public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
    } 
  }    // END of inner class ...Behaviour
}
