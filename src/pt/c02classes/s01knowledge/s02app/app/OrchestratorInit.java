package pt.c02classes.s01knowledge.s02app.app;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.impl.Statistics;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.inter.IStatistics;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerAnimals;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerMaze;
import pt.c02classes.s01knowledge.s02app.actors.ResponderAnimals;
import pt.c02classes.s01knowledge.s02app.actors.ResponderMaze;

import java.util.Vector;
import java.util.Scanner;

public class OrchestratorInit
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		IEnquirer enq;
		IResponder resp;
		IStatistics stat;
		
		
		boolean jogo = false;
		boolean rolou = false;
		//Pergunta qual jogo
		while(!jogo){
			System.out.print("(A)nimals ou (M)aze?");
			String tipo = scanner.nextLine();
			
			if(tipo.equalsIgnoreCase("A")){
				jogo = true;
				//Pergunta qual animal
				while(!rolou){
					System.out.print("Qual o nome do animal a ser adivinhado?");
					String animal_type = scanner.nextLine();
			
					IBaseConhecimento base = new BaseConhecimento();
					base.setScenario("animals");
					
					Vector<String> listaAnimais = base.listaNomes();
					//Roda o jogo para o animal escolhido
					if(listaAnimais.contains(animal_type)){
						System.out.println("Enquirer com " + animal_type + "...");
						stat = new Statistics();
						resp = new ResponderAnimals(stat, animal_type);
						enq = new EnquirerAnimals();
						enq.connect(resp);
						enq.discover();
						System.out.println("----------------------------------------------------------------------------------------\n");
						
						rolou = true;
					//Caso o animal seja invalido
					}else{
						System.out.println("Animal inexistente, tente novamente\n");
					}
				}
			}
			else if(tipo.equalsIgnoreCase("M")){
				jogo = true;
				//Pergunta o nome do labirinto
				while(!rolou){
					System.out.print("Qual o nome do labirinto a ser usado?");
					String maze_type = scanner.nextLine();
					
					System.out.println("Enquirer com " + maze_type + "...");
					stat = new Statistics();
					resp = new ResponderMaze(stat, maze_type);
					enq = new EnquirerMaze();
					enq.connect(resp);
					enq.discover();
					System.out.println("----------------------------------------------------------------------------------------\n");
				
					rolou = true;
				}
			//Caso o labirinto seja invalido
			}else{
				System.out.print("Jogo inexistente, tente novamente\n");
			}
		}

		scanner.close();
	}
}
