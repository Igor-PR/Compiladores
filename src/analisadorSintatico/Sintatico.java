package analisadorSintatico;
import java.util.ArrayList;

import analisadorSintatico.arvore.ASTnode;
import analisadorSintatico.gramatica.Programa;
import analisadorSintatico.tabela.TabelaDeSimbolos;


public class Sintatico {
	private static ArrayList<Token> listaTokens;
	private static String path;
	private static ASTnode root;

	public static void run() throws Exception {
		/*
		 * 	Os argumentos passados para a main são o nome do arquivo e a flag --v para ativar
		 * o modo verboso, ou simplesmente --v
		 * */
		
		path = "src/lexico.txt";
		
//		if(args.length > 0) {
//			if (args[0].equals("--v")) {
//				Global.verboso = true;		
//			}	
//			else {
//				if(args[1].equals("--v"))
//					Global.verboso = true;		
//			}
//		}	

		
		//Inicia a lista de tokens a partir de um arquivo
		listaTokens = new ArrayList<Token>(IO.lerArquivo(path));
		
		//Atualiza o token atual pegando o token referente a posição global do index
		Global.tokenAtual = listaTokens.get(Global.index);
		
		//Cria um nó raiz da árvore e chama a primeira definição sintática da gramatica(Programa)
		root = Programa.run();

		//Caso não exista erros
		if(!Global.flag) {
			//Imprime a árvore a partir da raiz
			root.printArvore(0);
			//e também avalia as funções da mesma
			root.evaluate();
			
			//Imprime a tabela de símbolos, que contém as variáveis e seus valores
			System.out.println("__Tabela De Simbolos__");
			System.out.println(TabelaDeSimbolos.getInstance().getTabela().toString());
			System.out.println("______________________");
		}
		else {
			System.out.println("Ocorreu um erro sintatico, a analise não pode continuar");
		}
		
	}

	public static ArrayList<Token> getListaTokens() {
		return listaTokens;
	}

	public static void setListaTokens(ArrayList<Token> listaTokens) {
		Sintatico.listaTokens = listaTokens;
	}
	
	public static ASTnode getAST() {
		return root;
	}
	
	
}
