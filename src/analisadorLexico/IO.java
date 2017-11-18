package analisadorLexico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/*Esta classe lida com a leitura do codigo fonte e passa os caracteres lidos para um arquivo fonte*/
public class IO {
	
	private static ArrayList<Character> buffer;
	private static int posicaoBuffer = 0;
	private static BufferedReader br;

	/*Metodo que lê o arquivo fonte de um diretorio*/
	public static void lerArquivo(String path) throws Exception{
		buffer = new ArrayList<Character>();
		br = new BufferedReader(new FileReader(path));
		char c;
		/*Um caracter em forma de int é lido e caso não seja o fim do arquivo ele é convertido para
		 * caracter normal e adicionado ao buffer */
		int i =  br.read();
		while(i != -1){
			c = (char) i;
			buffer.add(c);
			i = br.read();
		}
		/*O caracter $ representa o fim do buffer */
		buffer.add('$');
	}
	
	/*Método que retorna o próximo caracter no buffer e move a posição do buffer*/
	public static char getNextChar(){
		char nextChar = buffer.get(posicaoBuffer);
		posicaoBuffer += 1;
		return nextChar;
	}
	
	/*Métodos que constituem o mecanismo para recarregar o buffer*/
	public static void setPosicaoBuffer(int posicao){
		posicaoBuffer = posicao;
	}
	
	public static void resetPosicaoBuffer(){
		posicaoBuffer = 0;
	}
	
	public static void escreverSaida(ArrayList<Token> listaTokens) throws IOException {
		FileWriter arq = new FileWriter("src/lexico.txt");
	    PrintWriter gravarArq = new PrintWriter(arq);
	    for(Token t: listaTokens){
	    	gravarArq.printf(t.toFile());
		}
	    arq.close();
	}

}
