package execucao;
import analisadorLexico.Lexico;
import analisadorSintatico.Sintatico;
import codigoTresEnderecos.CodigoTresEnderecos;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Lexico.run(args);
		Sintatico.run(args);
		CodigoTresEnderecos.run();
	}
}
