package analisadorSintatico.gramatica;

import analisadorSintatico.Global;
import analisadorSintatico.arvore.ASTnode;

/*Classe que representa a regra sintatica 'Decl_Comando' da gramatica*/
public class Decl_Comando {
	
	/*Método que inicializa a regra e recebe um nó da árvore como argumento*/
	public static ASTnode run(ASTnode lista) {
		/*Os IFs testam qual regra será escolhida através do conjunto first de Decl_Comando*/
		
		if(Global.tokenAtual.getNomeToken().equals("INT") || Global.tokenAtual.getNomeToken().equals("FLOAT") ) {
			/*Caso seja um INT ou FLOAT irá chamar a regra de declaracoes e depois irá realizar
			  uma recursividade em Decl_Comando*/ 
			ASTnode lista2 = Declaracoes.run(lista);
			return Decl_Comando.run(lista2);
		}
		
		if(Global.tokenAtual.getNomeToken().equals("ID") ||
				Global.tokenAtual.getNomeToken().equals("IF") ||
				Global.tokenAtual.getNomeToken().equals("WHILE") ||
				Global.tokenAtual.getNomeToken().equals("PRINT") ||
				Global.tokenAtual.getNomeToken().equals("READ")) {
			/*Caso seja um ID, IF, WHILE, PRINT ou READ irá chamar a regra de comandos e depois irá realizar
			  uma recursividade em Decl_Comando*/ 
			ASTnode lista2 = Comando.run(lista);
			return Decl_Comando.run(lista2);
		}
		
		//Se não for nenhum, retorna o nó
		return lista;
	}
}
