package analisadorSintatico.gramatica;

import analisadorSintatico.Global;
import analisadorSintatico.Match;
import analisadorSintatico.arvore.Expr;
import analisadorSintatico.arvore.RelOp;

/*Classe que representa a regra sintatica 'Relacao' da gramatica*/
public class Relacao {
	
	/*Método que inicializa a regra*/
	public static Expr run() {
		//Chama a regra 'Adicao' e atruibui o resultado a left
		Expr left = Adicao.run();
		
		//Caso o token seja "LT"
		if(Global.tokenAtual.getNomeToken().equals("LT")) {
			//Compara com o token com o token "LT"
			Match.match("LT");
			
			//Chama a regra 'Relacao' para right
			Expr right = Relacao.run();
			
			//Cria um nó RelOp de <
			return new RelOp(left,"<",right,null);
		}
		
		//Caso o token seja "LE"
		if(Global.tokenAtual.getNomeToken().equals("LE")) {
			//Compara com o token com o token "LE"
			Match.match("LE");
			
			//Chama a regra 'Relacao' para right
			Expr right = Relacao.run();
			
			//Cria um nó RelOp de <=
			return new RelOp(left,"<=",right,null);
		}
		
		//Caso o token seja "GT"
		if(Global.tokenAtual.getNomeToken().equals("GT")) {
			//Compara com o token com o token "GT"
			Match.match("GT");
			
			//Chama a regra 'Relacao' para right
			Expr right = Relacao.run();
			
			//Cria um nó RelOp de >
			return new RelOp(left,">",right,null);
		}
		
		//Caso o token seja "GE"
		if(Global.tokenAtual.getNomeToken().equals("GE")) {
			//Compara com o token com o token "GE"
			Match.match("GE");
			
			//Chama a regra 'Relacao' para right
			Expr right = Relacao.run();
			
			//Cria um nó RelOp de >=
			return new RelOp(left,">=",right,null);
		}
		
		//retorna o nó left
		return left;
	}

}
