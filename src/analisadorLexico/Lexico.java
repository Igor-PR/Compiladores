package analisadorLexico;

import java.util.ArrayList;

import analisadorSintatico.Global;

/*Classe Main que executa o programa*/
public class Lexico {
	
	private static ArrayList<Token> listaTokens;
	private static String path;

	public static void run(final String[] args) throws Exception {
		/*Chama o método de leitura do arquivo*/
		
		/*Utiliza o arquivo passado como argumento caso exista, se não, utiliza o default*/
		
		path = "src/file.txt";
		
		if(args.length > 0) {
			if (args[0].equals("--v")) {
				Global.verboso = true;		
			}	
			else {
				path = "src/" + args[0];
				if(args.length > 1 && args[1].equals("--v")) {
					Global.verboso = true;					
				}	
			}
		}	
			
		System.out.println(path);	
		
		IO.lerArquivo(path);
		
		/*Inicializa o analisador lexico e depois gera o mapa contendo todos os Tokens aceitos
		 * desta linguagem*/
		AnalisadorLexico analisador = new AnalisadorLexico();
		analisador.criaMapaTokens();
		
		/*Uma lista de tokens recebe a lista de tokens gerada pelo analisador*/
		listaTokens = new ArrayList<Token>(analisador.geraTokens());
		
		/*Imprime os Tokens na tela*/
		for(Token t: listaTokens){
			System.out.println(t.toString());
		}
		
		IO.escreverSaida(listaTokens);
		
		
	}

}
