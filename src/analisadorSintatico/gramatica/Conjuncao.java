package analisadorSintatico.gramatica;

import analisadorSintatico.Global;
import analisadorSintatico.Match;
import analisadorSintatico.arvore.Expr;
import analisadorSintatico.arvore.LogicalOp;

/*Classe que representa a regra sintatica 'Conjucao' da gramatica*/
public class Conjuncao {
	
	/*Método que inicializa a regra*/
	public static Expr run() {
		//Chama a regra 'Igualdade' e atruibui o resultado a left
		Expr left = Igualdade.run();
		
		//Caso o token seja "AND"
		if(Global.tokenAtual.getNomeToken().equals("AND")) {
			//Compara com o token com o token "AND"
			Match.match("AND");
			
			//Chama a regra 'Conjuncao' para right
			Expr right = Conjuncao.run();
			
			//Cria um nó LogicalOp de AND
			return new LogicalOp(left,"&&",right,null);
		}
		
		//retorna o nó left
		return left;
	}

}
