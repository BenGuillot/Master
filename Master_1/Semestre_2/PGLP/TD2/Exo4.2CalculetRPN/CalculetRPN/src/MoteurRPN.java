
import java.util.EmptyStackException;
import java.util.Stack;

public class MoteurRPN {
	public Stack<Double> pile;
	
	// Constructeur initialisant la pile vide
	public MoteurRPN() {
		this.pile = new Stack<Double>();
	}

	public void push(double a){
		pile.push(a);
	}
	
	public double pop(){
		try{
        	return pile.pop();
		} catch(EmptyStackException e) {
        	return 0;
        }
	}
	
	public Stack<Double> calcul(Operation a){
		if(this.pile.isEmpty()){
			throw new PileVideException();
		}
		else { 
			push(a.eval(pop(), pop()));
			System.out.print(this.pile + "\n");
			return this.pile;
		}
	}
}
	
	
	
	
	

