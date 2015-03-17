package pt.c02classes.s01knowledge.s02app.actors;

//Importa as bibliotecas de ArrayList e Vector
import java.util.ArrayList;
import java.util.Vector;
import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IDeclaracao;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerAnimals implements IEnquirer {

	IResponder responder;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public boolean discover() {
		//Cria a base de conhecimento e coloca o cenario como "animals"
        IBaseConhecimento bc = new BaseConhecimento();
        bc.setScenario("animals");
    	IObjetoConhecimento obj;

        boolean acertei = false;
		
        //Cria ArrayLists para armazenar as perguntas realizadas e suas respostas
		ArrayList<String> perguntadas = new ArrayList<String>();
		ArrayList<String> respondidas = new ArrayList<String>();
		//Cria Vector para armazenar todos os possiveis animais
		Vector<String> listaAnimais = bc.listaNomes();
		
		boolean animalEsperado = false;

		String animal_teste;
		//Enquanto nao sao testados todos os animais ou o correto nao eh encontrado
		while(!listaAnimais.isEmpty() && !acertei){
			//Pega o primeiro elemento do Vector e testa se eh o animal pensado
			animal_teste = listaAnimais.firstElement();
			obj = bc.recuperaObjeto(animal_teste);
			listaAnimais.remove(0);
			IDeclaracao decl = obj.primeira();
			animalEsperado = true;

			String resposta;
			//Realiza as perguntas do animal a ser testado como o pensado
			while (decl != null && animalEsperado) {
				String pergunta = decl.getPropriedade();
				String respostaEsperada = decl.getValor();

				//Testa o caso de ser uma pergunta repetida
				if (!perguntadas.contains(pergunta)){
					perguntadas.add(pergunta);
				
					resposta = responder.ask(pergunta);
					
					respondidas.add(resposta);
				}else{
					resposta = respondidas.get(perguntadas.indexOf(pergunta));
				}
				if (resposta.equalsIgnoreCase(respostaEsperada)){
					decl = obj.proxima();
				}else{
					animalEsperado = false;
				}
			}
			//Verifica se o animal testado eh de fato o pensado
			if (animalEsperado)
				acertei = responder.finalAnswer(animal_teste);
		}
		//Imprime resultado final
		if (acertei)
			System.out.println("Oba! Acertei!");
		else
			System.out.println("fuem! fuem! fuem!");
		
		listaAnimais.clear();
		perguntadas.clear();
		respondidas.clear();
		
		return acertei;
	}
}
