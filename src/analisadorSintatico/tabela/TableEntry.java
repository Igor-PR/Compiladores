package analisadorSintatico.tabela;

import analisadorSintatico.Token;

/*Classe que representa uma instância da tabela*/
public class TableEntry {
	
	private Token token;
	private String lexema;
	private String tipo;
	private int linhaCodigo;
	private Object referencia;
	
	/*O objeto é instânciado recebendo um token, o tipo e seu valor */
	public TableEntry(Token token, String tipo, Object referencia) {
		
		this.lexema = token.getLexema();
		this.linhaCodigo = token.getLinhaCodigoFonte();
		this.tipo = tipo;
		this.referencia = referencia;
		
	}
	
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Object getReferencia() {
		return referencia;
	}
	public void setReferencia(Object referencia) {
		this.referencia = referencia;
	}
	
	public String toString() {
		return tipo + " " + lexema + " " + referencia.toString();
	}

}
