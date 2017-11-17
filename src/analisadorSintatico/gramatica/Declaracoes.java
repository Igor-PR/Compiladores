package analisadorSintatico.gramatica;

import analisadorSintatico.Global;
import analisadorSintatico.Match;
import analisadorSintatico.arvore.ASTnode;
import analisadorSintatico.tabela.TabelaDeSimbolos;
import analisadorSintatico.tabela.TableEntry;

/*Classe que representa a regra sintatica 'Declaracoes' da gramatica*/
public class Declaracoes {
	
	/*Método que inicializa a regra e recebe um nó da árvore como argumento*/
	public static ASTnode run(ASTnode lista) {
		//Chama a regra sintatica 'Tipo'
		Tipo.run();
		
		//Caso o token seja o ID 
		if(Global.tokenAtual.getNomeToken().equals("ID")) {
			//Cria uma entrada na tabela e atualiza as variáveis globais
			Global.tokenAux = Global.tokenAtual;
			TableEntry tableEntry = new TableEntry(Global.tokenAtual,Global.currentType,null);
			Global.currentTableEntry = tableEntry;
			
			//Atualiza a tabela de símbolos
			TabelaDeSimbolos tabSimbolos = TabelaDeSimbolos.getInstance();
			tabSimbolos.getTabela().put(Global.tokenAtual.getLexema(), tableEntry);
			
			//Compara com o token com o token "ID"
			Match.match("ID");
			
			//Chama a regra 'Decl2'
			return Decl2.run(lista);
		}
		
		//retorna o nó
		return lista;
	}	

}
