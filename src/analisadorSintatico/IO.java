package analisadorSintatico;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/*Classe que controla a entrada do programa, no caso a leitura de um arquivo*/
public class IO {

	private static ArrayList<Token> tokens;
	private static BufferedReader br;
	private static Token token;

	/*Metodo que lê o arquivo fonte de um diretorio*/
	public static ArrayList<Token> lerArquivo(String path) throws Exception{
		tokens = new ArrayList<Token>();
		br = new BufferedReader(new FileReader(path));

		while(br.ready()){
			String linha = br.readLine();
			String[] arrayValores = linha.split(" ");
			token = new Token(arrayValores[0],arrayValores[1],Integer.parseInt(arrayValores[2]));
			tokens.add(token);
		}
		
		br.close();
		
		return tokens;
	}
}
