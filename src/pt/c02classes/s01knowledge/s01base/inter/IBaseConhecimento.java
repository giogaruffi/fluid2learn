package pt.c02classes.s01knowledge.s01base.inter;

import java.util.Vector;

public interface IBaseConhecimento
{
	public void setScenario(String scenario);
    public Vector<String> listaNomes();
	public IObjetoConhecimento recuperaObjeto(String nome);
}