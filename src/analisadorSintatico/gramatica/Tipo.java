package analisadorSintatico.gramatica;

import analisadorSintatico.Global;
import analisadorSintatico.Match;

/*Classe que representa a regra sintatica 'Tipo' da gramatica*/
public class Tipo {
	
	/*Método que inicializa a regra*/
	public static void run() {
		
		//Caso o token seja INT
		if(Global.tokenAtual.getNomeToken().equals("INT")) {
			//Compara com o token com o token "INT"
			Match.match("INT");
			//Atualiza o tipo atual para INT
			Global.currentType = "INT";
		}
		
		//Caso o token seja FLOAT
		if(Global.tokenAtual.getNomeToken().equals("FLOAT")) {
			//Compara com o token com o token "FLOAT"
			Match.match("FLOAT");
			//Atualiza o tipo atual para FLOAT
			Global.currentType = "FLOAT";
		}
	}

}
