package pt.c02classes.s01knowledge.s02app.actors;

import java.util.ArrayList;
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
        IBaseConhecimento bc = new BaseConhecimento();
    	IObjetoConhecimento obj;

        boolean acertei = true;
		
		ArrayList<String> perguntadas = new ArrayList<String>();
		ArrayList<String> respondidas = new ArrayList<String>();

		boolean animalEsperado = false;

		for(String escolhido : bc.listaNomes()){
			
			obj = bc.recuperaObjeto(escolhido);
			IDeclaracao decl = obj.primeira();
			animalEsperado = true;

			String resposta;

			while (decl != null && animalEsperado) {
				String pergunta = decl.getPropriedade();
				String respostaEsperada = decl.getValor();

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
			if (animalEsperado)
				acertei = responder.finalAnswer(escolhido);
			if (acertei)
				System.out.println("Oba! Acertei!");
			else
				System.out.println("fuem! fuem! fuem!");
		}
		
		return acertei;
	}
}
