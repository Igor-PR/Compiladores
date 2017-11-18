package codigoTresEnderecos;

import java.io.IOException;

import analisadorSintatico.Sintatico;
import analisadorSintatico.arvore.ASTnode;

public class CodigoTresEnderecos {
	
	ArquivoSaida arquivoSaida;
	
	public static void run() throws IOException {
		ASTnode root = Sintatico.getAST();
		ArquivoSaida.openArq();
		root.generateCode();
		ArquivoSaida.closeArq();
		System.out.println("Arquivo de três endereços criado!");
	}

}
