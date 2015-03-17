package pt.c02classes.s01knowledge.s02app.actors;

import java.util.Stack;

import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerMaze implements IEnquirer {

	IResponder responder;
	//Declara Stack para armazenar o movimento recem realizada na classe
	Stack <String> movimento;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	boolean buscaSaida (){
		
		//Caso base para sair da recursao = encontrar saida
		if (responder.ask("aqui") == "saida") 
			return true; 
		
		boolean achei = false;
		
		//Testa se eh possivel se mover para o norte
		if(!responder.ask("norte").equalsIgnoreCase("parede") 
				&& !responder.ask("norte").equalsIgnoreCase("entrada")
				&& !responder.ask("norte").equalsIgnoreCase("mundo")){
			//Testa se nao eh o primeiro movimento e verifica que o ultimo movimento nao foi para o sul
			if(movimento.empty() || !movimento.peek().equalsIgnoreCase("sul")){
				//Realiza o movimento e armazena na Stack como o ultimo movimento valido
				responder.move("norte");
				System.out.println(" Movendo para: Norte ");
				movimento.push("norte");
				
				achei = buscaSaida();
				
				movimento.pop();
			}
		}
		//Testa se eh possivel se mover para o sul e se nao foi encontrada a saida em uma iteracao anterior
		if(!responder.ask("sul").equalsIgnoreCase("parede") 
				&& !responder.ask("sul").equalsIgnoreCase("entrada") 
				&& !responder.ask("sul").equalsIgnoreCase("mundo") && !achei){
			//Testa se nao eh o primeiro movimento e verifica que o ultimo movimento nao foi para o norte
			if(movimento.empty() || !movimento.peek().equalsIgnoreCase("norte")){
				//Realiza o movimento e armazena na Stack como o ultimo movimento valido
				responder.move("sul");
				System.out.println(" Movendo para: Sul ");
				movimento.push("sul");
				
				achei = buscaSaida();
				
				movimento.pop();
			}
		}
		//Testa se eh possivel se mover para o leste e se nao foi encontrada a saida em uma iteracao anterior
		if(!responder.ask("leste").equalsIgnoreCase("parede") 
				&& !responder.ask("leste").equalsIgnoreCase("entrada") 
				&& !responder.ask("leste").equalsIgnoreCase("mundo") && !achei){
			//Testa se nao eh o primeiro movimento e verifica que o ultimo movimento nao foi para o oeste
			if(movimento.empty() || !movimento.peek().equalsIgnoreCase("oeste")){
				//Realiza o movimento e armazena na Stack como o ultimo movimento valido
				responder.move("leste");
				System.out.println(" Movendo para: Leste ");
				movimento.push("leste");
				
				achei = buscaSaida();
				
				movimento.pop();
			}
		}
		//Testa se eh possivel se mover para o oeste e se nao foi encontrada a saida em uma iteracao anterior
		if(!responder.ask("oeste").equalsIgnoreCase("parede") 
				&& !responder.ask("oeste").equalsIgnoreCase("entrada") 
				&& !responder.ask("oeste").equalsIgnoreCase("mundo") && !achei){
			//Testa se nao eh o primeiro movimento e verifica que o ultimo movimento nao foi para o leste
			if(movimento.empty() || !movimento.peek().equalsIgnoreCase("leste")){
				//Realiza o movimento e armazena na Stack como o ultimo movimento valido
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
		
		//Saida padrao
		if (buscaSaida())
			System.out.println("Voc� encontrou a saida!");
		else
			System.out.println("Fu�m fu�m fu�m!");

		movimento.clear();
		
		return true;
	}
}