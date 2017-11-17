package analisadorSintatico.gramatica;

import analisadorSintatico.Global;
import analisadorSintatico.Match;
import analisadorSintatico.arvore.ArithOp;
import analisadorSintatico.arvore.Expr;

/*Classe que representa a regra sintatica 'Adicao' da gramatica*/
public class Adicao {
	
	/*Método que inicializa a regra*/
	public static Expr run() {

		//Chama a regra 'Termo' e atruibui o resultado a left
		Expr left = Termo.run();

		//Caso o token seja "PLUS"
		if(Global.tokenAtual.getNomeToken().equals("PLUS")) {
			//Compara com o token com o token "PLUS"
			Match.match("PLUS");
			
			//Chama a regra 'Adicao' para right
			Expr right = Adicao.run();
			
			//Cria um nó ArithOp de +
			return new ArithOp(left,"+",right,null);
		}
		
		//Caso o token seja "MINUS"
		if(Global.tokenAtual.getNomeToken().equals("MINUS")) {
			//Compara com o token com o token "MINUS"
			Match.match("MINUS");
			
			//Chama a regra 'Adicao' para right
			Expr right = Adicao.run();
			
			//Cria um nó ArithOp de -
			return new ArithOp(left,"-",right,null);
		}
		
		//retorna o nó left
		return left;
	}

}
