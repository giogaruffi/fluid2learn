package pt.c02classes.s01knowledge.s02app.actors;

import java.util.Stack;

import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerMaze implements IEnquirer {

	IResponder responder;
	Stack <String> movimento;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	boolean buscaSaida (){
		
		if (responder.ask("aqui") == "saida") 
			return true; 
		
		boolean achei = false;
		
		if(!responder.ask("norte").equalsIgnoreCase("parede") 
				&& !responder.ask("norte").equalsIgnoreCase("entrada")
				&& !responder.ask("norte").equalsIgnoreCase("mundo")){
			if(movimento.empty() || !movimento.peek().equalsIgnoreCase("sul")){
				responder.move("norte");
				System.out.println(" Movendo para: Norte ");
				movimento.push("norte");
				
				achei = buscaSaida();
				
				movimento.pop();
			}
		}
		if(!responder.ask("sul").equalsIgnoreCase("parede") 
				&& !responder.ask("sul").equalsIgnoreCase("entrada") 
				&& !responder.ask("sul").equalsIgnoreCase("mundo") && !achei){
			if(movimento.empty() || !movimento.peek().equalsIgnoreCase("norte")){
				responder.move("sul");
				System.out.println(" Movendo para: Sul ");
				movimento.push("sul");
				
				achei = buscaSaida();
				
				movimento.pop();
			}
		}
		if(!responder.ask("leste").equalsIgnoreCase("parede") 
				&& !responder.ask("leste").equalsIgnoreCase("entrada") 
				&& !responder.ask("leste").equalsIgnoreCase("mundo") && !achei){
			if(movimento.empty() || !movimento.peek().equalsIgnoreCase("oeste")){
				responder.move("leste");
				System.out.println(" Movendo para: Leste ");
				movimento.push("leste");
				
				achei = buscaSaida();
				
				movimento.pop();
			}
		}
		if(!responder.ask("oeste").equalsIgnoreCase("parede") 
				&& !responder.ask("oeste").equalsIgnoreCase("entrada") 
				&& !responder.ask("oeste").equalsIgnoreCase("mundo") && !achei){
			if(movimento.empty() || !movimento.peek().equalsIgnoreCase("leste")){
				responder.move("oeste");
				System.out.println(" Movendo para: Oeste ");
				movimento.push("oeste");
				
				achei = buscaSaida();
				
				movimento.pop();
			}
		}
					 
		return achei; 
	}
	
	public boolean discover() {

		movimento = new Stack<String> ();
			
		if (buscaSaida())
			System.out.println("Voc� encontrou a saida!");
		else
			System.out.println("Fu�m fu�m fu�m!");

		movimento.clear();
		
		return true;
	}
}