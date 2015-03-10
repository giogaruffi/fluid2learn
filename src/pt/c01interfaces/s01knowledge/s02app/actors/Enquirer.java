package pt.c01interfaces.s01knowledge.s02app.actors;

import java.util.ArrayList;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IDeclaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;

public class Enquirer implements IEnquirer
{
    IObjetoConhecimento obj;
	
	public Enquirer()
	{
	}
	
	
	@Override
	public void connect(IResponder responder)
	{
        IBaseConhecimento bc = new BaseConhecimento();
		boolean acertei = true;
        
		String[] listaAnimais;
		
		listaAnimais = bc.listaNomes();
	
		ArrayList<String> perguntadas = new ArrayList<String>();
		ArrayList<String> respondidas = new ArrayList<String>();
		
		boolean animalEsperado = false;
		
		int i = 0;
		
		while (!animalEsperado) {
			
			obj = bc.recuperaObjeto(listaAnimais[i]);
			
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
				}
					
				else{
					resposta = respondidas.get(perguntadas.indexOf(pergunta));				
				}
				
				if (resposta.equalsIgnoreCase(respostaEsperada)){
					decl = obj.proxima();
				}else{
					animalEsperado = false;
					i++;
				}
			}
			
		}
		
		if (animalEsperado)
			acertei = responder.finalAnswer(listaAnimais[i]);
		
		if (acertei)
			System.out.println("Oba! Acertei!");
		else
			System.out.println("fuem! fuem! fuem!");

	}

}
