package analisadorSintatico.gramatica;

import analisadorSintatico.Global;
import analisadorSintatico.Match;
import analisadorSintatico.arvore.Expr;
import analisadorSintatico.arvore.LogicalOp;

/*Classe que representa a regra sintatica 'Expressao' da gramatica*/
public class Expressao {

	/*Método que inicializa a regra*/
	public static Expr run() {
		//Chama a regra 'Conjuncao' e atruibui o resultado a left
		Expr left = Conjuncao.run();
		
		//Caso o token seja "OR"
		if(Global.tokenAtual.getNomeToken().equals("OR")) {
			//Compara com o token com o token "OR"
			Match.match("OR");
			
			//Chama a regra 'Expressao' para right
			Expr right = Expressao.run();
			
			//Cria um nó LogicalOp de OR
			return new LogicalOp(left,"||",right,null);
		}
		
		//retorna o nó left
		return left;
	}
	
}
