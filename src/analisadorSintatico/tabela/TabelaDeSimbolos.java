package analisadorSintatico.tabela;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/*Classe singleton que contém a tabela de símbolos, em forma de Hashtable*/
public class TabelaDeSimbolos {
	
	private Hashtable<String,TableEntry> tabela;
	private static TabelaDeSimbolos instancia;
	
	
	public TabelaDeSimbolos() {
		tabela = new Hashtable<String, TableEntry>();
	}
	
	public static TabelaDeSimbolos getInstance() {
		if (instancia == null)
			instancia = new TabelaDeSimbolos();
		return instancia;
	}
	
	public Hashtable<String, TableEntry> getTabela() {
		return tabela;
	}
	
	/*Método que transforma a tabela em string. Para isso ele percorre cada par da tabela e imprime
	 * a chave e o valor*/
	public String toString() {
		Iterator it = tabela.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        it.remove();
	    }
		
		return null;
	}

}
