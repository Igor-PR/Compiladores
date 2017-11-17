package analisadorSintatico.gramatica;

import analisadorSintatico.Global;
import analisadorSintatico.Match;
import analisadorSintatico.arvore.Expr;
import analisadorSintatico.arvore.RelOp;

/*Classe que representa a regra sintatica 'Igualdade' da gramatica*/
public class Igualdade {

	/*Método que inicializa a regra*/
	public static Expr run() {
		//Chama a regra 'Relacao' e atruibui o resultado a left
		Expr left = Relacao.run();
		
		//Caso o token seja "EQ"
		if(Global.tokenAtual.getNomeToken().equals("EQ")) {
			//Compara com o token com o token "EQ"
			Match.match("EQ");
			
			//Chama a regra 'Igualdade' para right
			Expr right = Igualdade.run();
			
			//Cria um nó RelOp de igualdade
			return new RelOp(left,"==",right,null);
		}
		
		//Caso o token seja "NE"
		if(Global.tokenAtual.getNomeToken().equals("NE")) {
			//Compara com o token com o token "NE"
			Match.match("NE");
			
			//Chama a regra 'Igualdade' para right
			Expr right = Igualdade.run();
			
			//Cria um nó RelOp de desigualdade
			return new RelOp(left,"!=",right,null);
		}

		
		//retorna o nó left
		return left;
	}
}
