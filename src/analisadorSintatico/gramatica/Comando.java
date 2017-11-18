package analisadorSintatico.gramatica;

import analisadorSintatico.Global;
import analisadorSintatico.Match;
import analisadorSintatico.arvore.ASTnode;
import analisadorSintatico.arvore.Attr;
import analisadorSintatico.arvore.Bloco;
import analisadorSintatico.arvore.Expr;
import analisadorSintatico.arvore.Id;
import analisadorSintatico.arvore.If;
import analisadorSintatico.arvore.Print;
import analisadorSintatico.arvore.Read;
import analisadorSintatico.arvore.While;
import analisadorSintatico.tabela.TabelaDeSimbolos;
import analisadorSintatico.tabela.TableEntry;

/*Classe que representa a regra sintatica 'Comando' da gramatica*/
public class Comando {
	
	/*Método que inicializa a regra e recebe um nó da árvore como argumento*/
	public static ASTnode run(ASTnode lista) {
		
		/*Os comandos IFs testam qual regra sintatica seguir a partir do conjunto First(Comando)*/
		
		/*Atribuicao*/
		if(Global.tokenAtual.getNomeToken().equals("ID")) {
			//Cria um nó ID
			Id id_node = new Id(Global.tokenAtual, null);
			
			if(Global.verboso)
				System.out.println("Procurando lexema " + Global.tokenAtual.getLexema());
			
			//Caso esse nó exista na tabela, imprime informações do mesmo na tela
			TableEntry te = TabelaDeSimbolos.getInstance().getTabela().get(Global.tokenAtual.getLexema());
			if(te != null) {
				if(Global.verboso) {
					System.out.println("Econtrado o lexema na tabela de simbolos");
					System.out.println("Tipo do identificador: " + te.getTipo());
					System.out.println("Valor do identificador: " + te.getReferencia());
				}
			}
			//Compara com o token com o token "ID"
			Match.match("ID");
			//Compara com o token com o token "ATTR"
			Match.match("ATTR");
			
			//Chama a regra Expressao e atribui a expr_node
			Expr expr_node = Expressao.run();
			//Adiciona um filho de Attr ao nó 
			lista.children.add(new Attr(id_node,"=",expr_node,null));
			
			//Compara com o token com o token "PCOMMA"
			Match.match("PCOMMA");
			return lista;
		}
		
		/*Bloco*/
		if(Global.tokenAtual.getNomeToken().equals("LBRACE")) {
			//Compara com o token com o token "LBRACE"
			Match.match("LBRACE");
			
			ASTnode bloco = new ASTnode("bloco",null);
			
			while(!Global.tokenAtual.getNomeToken().equals("RBRACE")) {
				//Recursividade da regra Comando
				bloco = Comando.run(lista);
			}
			//Compara com o token com o token "RBRACE"
			Match.match("RBRACE");

			return bloco;
		}
		
		/*ComandoSe*/
		if(Global.tokenAtual.getNomeToken().equals("IF")) {
			//Compara com o token com o token "IF"
			Match.match("IF");
			//Compara com o token com o token "LBRACKET"
			Match.match("LBRACKET");
			
			//Chama a regra de expressao para saber a condição do IF e atribui a expr_node
			Expr expr_node = Expressao.run();
			
			//Compara com o token com o token "RBRACKET"
			Match.match("RBRACKET");
			
			//Cria um nó generico para saber as funções dentro do IF
			ASTnode lista2 = new ASTnode("if",null);
			//Cria um nó generico para saber as funções dentro do ELSE, caso exista
			ASTnode listaElse = new ASTnode("else",null);
			
			//Recursividade da regra comando para o IF
			lista2 = Comando.run(lista2);
			
			/*ComandoSenao*/
			if(Global.tokenAtual.getNomeToken().equals("ELSE")) {
				//Compara com o token com o token "ELSE"
				Match.match("ELSE");
				//Recursividade da regra comando para o ELSE
				listaElse = Comando.run(listaElse);
			}
			//Adiciona um nó filho do tipo IF a lista
			lista.children.add(new If(expr_node,lista2.children,listaElse.children,null));
			return lista;
		}
		
		/*ComandoEnquanto*/
		if(Global.tokenAtual.getNomeToken().equals("WHILE")) {
			//Compara com o token com o token "WHILE"
			Match.match("WHILE");
			//Compara com o token com o token "LBRACKET"
			Match.match("LBRACKET");
			
			//Chama a regra de expressao para saber a condição do while e atribui a expr_node
			Expr expr_node = Expressao.run();
			
			//Compara com o token com o token "RBRACKET"
			Match.match("RBRACKET");
			
			//Cria um nó generico para saber as funções dentro do while
			ASTnode lista2 = new ASTnode("while",null);
//			lista2.children.add(expr_node);
			//Recursividade da regra comando para o WHILE
			lista2 = Comando.run(lista2);
			
			//Adiciona um nó filho do tipo WHILE a lista
//			lista.children.add(lista2);
			lista.children.add(new While(expr_node,lista2.children,null));
			return lista;
		}	
		
		/*ComandoRead*/
		if(Global.tokenAtual.getNomeToken().equals("READ")) {
			//Compara com o token com o token "READ"
			Match.match("READ");
			
			//Cria um nó ID
			Id id_node = new Id(Global.tokenAtual, null);
			
			if(Global.verboso)
				System.out.println("Procurando lexema " + Global.tokenAtual.getLexema());
			
			//Caso esse nó exista na tabela, imprime informações do mesmo na tela
			TableEntry te =  TabelaDeSimbolos.getInstance().getTabela().get(Global.tokenAtual.getLexema());
			if(te != null) {
				if(Global.verboso) {
					System.out.println("Econtrado o lexema na tabela de simbolos");
					System.out.println("Tipo do identificador: " + te.getTipo());
					System.out.println("Valor do identificador: " + te.getReferencia());
				}
			}
			//Compara com o token com o token "ID"
			Match.match("ID");
			
			//Cria um nó READ
			Read read = new Read(id_node,null);
			//Adiciona um nó filho do tipo READ a lista
			lista.children.add(read);
			
			//Compara com o token com o token "PCOMMA"
			Match.match("PCOMMA");
			return lista;
		}
		
		/*ComandoPrint*/
		if(Global.tokenAtual.getNomeToken().equals("PRINT")) {
			//Compara com o token com o token "PRINT"
			Match.match("PRINT");
			//Compara com o token com o token "LBRACKET"
			Match.match("LBRACKET");
			
			//Chama a regra de expressao para saber o que será impresso
			Expr expr_node = Expressao.run();
			
			//Cria um nó PRINT
			Print print = new Print(expr_node,null);
			//Adiciona um nó filho do tipo PRINT a lista
			lista.children.add(print);
			
			//Compara com o token com o token "RBRACKET"
			Match.match("RBRACKET");
			//Compara com o token com o token "PCOMMA"
			Match.match("PCOMMA");
			return lista;
		}
		return lista;
	}

}
