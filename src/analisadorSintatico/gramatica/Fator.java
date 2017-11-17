package analisadorSintatico.gramatica;

import analisadorSintatico.Global;
import analisadorSintatico.Match;
import analisadorSintatico.arvore.Expr;
import analisadorSintatico.arvore.Id;
import analisadorSintatico.arvore.Num;

/*Classe que representa a regra sintatica 'Fator' da gramatica*/
public class Fator {

	/*Método que inicializa a regra*/
	public static Expr run() {
		//Caso o token seja "ID"
		if(Global.tokenAtual.getNomeToken().equals("ID")) { 
			//Cria nó do tipo ID
			Id id_node = new Id(Global.tokenAtual, null);
			
			//Compara com o token com o token "ID"
			Match.match("ID");
			
			return id_node;
		}	
		
		//Caso o token seja "INTEGER_CONST"
		if(Global.tokenAtual.getNomeToken().equals("INTEGER_CONST")) {
			//Cria nó do tipo Num
			Num num_node = new Num(Global.tokenAtual,null);
			
			//Compara com o token com o token "INTEGER_CONST"
			Match.match("INTEGER_CONST");
			
			return num_node;
		}	
		
		//Caso o token seja "FLOAT_CONST"
		if(Global.tokenAtual.getNomeToken().equals("FLOAT_CONST")) {
			//Cria nó do tipo Num
			Num num_node = new Num(Global.tokenAtual,null);
			
			//Compara com o token com o token "FLOAT_CONST"
			Match.match("FLOAT_CONST");
			
			return num_node;
		}
		
		//Caso o token seja "LBRACKET"
		if(Global.tokenAtual.getNomeToken().equals("LBRACKET")) { 
			
			//Compara com o token com o token "LBRACKET"
			Match.match("LBRACKET");
			
			//Chama a regra 'Expressao' para expr_ndoe
			Expr expr_node = Expressao.run();
			
			//Compara com o token com o token "RBRACKET"
			Match.match("RBRACKET");
			
			return expr_node;
		}
		
		return null;
	}
}
