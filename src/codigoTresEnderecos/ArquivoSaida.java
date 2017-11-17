package codigoTresEnderecos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class ArquivoSaida {
	
	public static FileWriter arq; 
    public static PrintWriter gravarArq; 
    
    public static void openArq() throws IOException {
    	arq = new FileWriter("TresEnderecos.txt");
    	gravarArq = new PrintWriter(arq);
    }
    
    public static void closeArq() throws IOException {
    	arq.close();
    }
    
    public static void escreveArquivo(String texto) {
    	gravarArq.printf(texto);
    }

}
