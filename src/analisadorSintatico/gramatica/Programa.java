package analisadorSintatico.gramatica;

import analisadorSintatico.Global;
import analisadorSintatico.Match;
import analisadorSintatico.arvore.ASTnode;

/*Classe que representa a primeira regra sintatica da gramatica*/
public class Programa {

	public static ASTnode run() {
		/*-- Porcura por Tokens que inicializam um programa em Csmall*/
		Match.match("INT");
		Match.match("MAIN");
		Match.match("LBRACKET");
		Match.match("RBRACKET");
		Match.match("LBRACE");
		/* --  -- */
		
		//Cria um nó generico da árvore e chama a proxima regra sintatica com esse nó. Este é armazenado
		//no nó que será retornado pela função
		ASTnode lista = new ASTnode("decl_comando",null);
		ASTnode ast = Decl_Comando.run(lista);
		
		//Procura por um token que finzaliza um programa em Csmall
		Match.match("RBRACE");
		
		//Caso encontro EOF, finaliza a analise
		if(Global.tokenAtual.getNomeToken().equals("$")) {
			Match.match("$"); //EOF
			System.out.println("Fim da analise sintatica");
		}
		return ast;
	}
}
