package analisadorSintatico.gramatica;

import analisadorSintatico.Global;
import analisadorSintatico.Match;
import analisadorSintatico.arvore.ArithOp;
import analisadorSintatico.arvore.Expr;

/*Classe que representa a regra sintatica 'Termo' da gramatica*/
public class Termo {

	/*Método que inicializa a regra*/
	public static Expr run() {
		//Chama a regra 'Fator' e atruibui o resultado a left
		Expr left = Fator.run();
		
		/*TermoOpc*/
		//Caso o token seja "MULT"
		if(Global.tokenAtual.getNomeToken().equals("MULT")) {
			//Compara com o token com o token "MULT"
			Match.match("MULT");
			
			//Chama a regra 'Termo' para right
			Expr right = Termo.run();
			
			//Cria um nó ArithOp de *
			return new ArithOp(left,"*",right,null);
		}
		
		//Caso o token seja "DIV"
		if(Global.tokenAtual.getNomeToken().equals("DIV")) {
			//Compara com o token com o token "DIV"
			Match.match("DIV");
			
			//Chama a regra 'Termo' para right
			Expr right =  Termo.run();
			
			//Cria um nó ArithOp de /
			return new ArithOp(left,"/",right,null);
		}
		
		//retorna o nó left
		return left;
	}
}
