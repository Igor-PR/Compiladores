package analisadorSintatico.gramatica;

import analisadorSintatico.Global;
import analisadorSintatico.Token;
import analisadorSintatico.Match;
import analisadorSintatico.arvore.ASTnode;
import analisadorSintatico.arvore.Attr;
import analisadorSintatico.arvore.Id;
import analisadorSintatico.tabela.TabelaDeSimbolos;
import analisadorSintatico.tabela.TableEntry;

/*Classe que representa a regra sintatica 'Decl2' da gramatica*/
public class Decl2 {
	
	/*Método que inicializa a regra e recebe um nó da árvore como argumento*/
	public static ASTnode run(ASTnode lista) {
		/*Os IFs testam qual regra será escolhida através do conjunto first de Decl2*/
		
		if(Global.tokenAtual.getNomeToken().equals("COMMA")) {
			//Compara com o token com o token "COMMA"
			Match.match("COMMA");
			
			if(Global.tokenAtual.getNomeToken().equals("ID")) {
				//Cria uma entrada na tabela e atualiza as variáveis globais
				Global.tokenAux = Global.tokenAtual;
				TableEntry te = new TableEntry(Global.tokenAtual,Global.currentType,null);
				Global.currentTableEntry = te;
				
				//Atualiza a tabela de símbolos
				TabelaDeSimbolos tabSimbolos = TabelaDeSimbolos.getInstance();
				tabSimbolos.getTabela().put(Global.tokenAtual.getLexema(), te);
				
				//Compara com o token com o token "ID"
				Match.match("ID");
				
				//Chama a regra 'Decl2'
				return Decl2.run(lista);
			}
		}
		if(Global.tokenAtual.getNomeToken().equals("PCOMMA")) {
			//Compara com o token com o token "PCOMMA"
			Match.match("PCOMMA");
			
			//retorna o nó
			return lista;
		}
		if(Global.tokenAtual.getNomeToken().equals("ATTR")) {
			//Cria um nó ID
			Id id_node = new Id(Global.tokenAux,null);
			
			//Compara com o token com o token "ATTR"
			Match.match("ATTR");
			
			//Chama a regra 'Expressao' e atribui o nó retornado a expr_node
			ASTnode expr_node = Expressao.run();
			
			//Cria um nó ATTR com o ID e a Expressão
			Attr attr_node = new Attr(id_node,"=",expr_node,null);
			
			//Adiciona o nó ATTR aos filhos do nó lista
			lista.children.add(attr_node);
			
			//Chama a regra 'Decl2'
			return Decl2.run(lista);
			
		}
		
		//retorna o nó
		return lista;
	}

}
