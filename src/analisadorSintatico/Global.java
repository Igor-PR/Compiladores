package analisadorSintatico;

import analisadorSintatico.tabela.TableEntry;

/*Classe que contem as variáveis globais do programa*/
public class Global {

	public static Token tokenAtual = new Token(null, null, 0);
	public static int index = 0;
	public static Token tokenAux;
	public static String currentType;
	public static TableEntry currentTableEntry;
	
	/*-- Variavéis que indicam propriedades do programa. Flag é utilizada quando há erro e verboso 
	 * quando o modo verboso for ativado --*/
	public static Boolean flag = false;
	public static Boolean verboso = false;
}
