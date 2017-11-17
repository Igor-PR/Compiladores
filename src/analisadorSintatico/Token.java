package analisadorSintatico;
/*Classe que representa um TOKEN gerado, este contém o nome do token, o lexema que o gerou e a linha
 * do codigo fonte onde foi encontrado*/
public class Token {

	private String nomeToken;
	private String lexema;
	private int linhaCodigoFonte; 
	
	public Token(String token,String lexema,int linhaCodigoFonte){
		this.nomeToken = token;
		this.lexema = lexema;
		this.linhaCodigoFonte = linhaCodigoFonte;
	}
	/*Método para retornar uma string que representa um Token*/
	public String toString(){
		return "|Token: " + nomeToken + " - Lexema: " + lexema+"|";
	}
	
	public String getValue() {
		return lexema;
	}
	public String getNomeToken() {
		return nomeToken;
	}
	public void setNomeToken(String nomeToken) {
		this.nomeToken = nomeToken;
	}
	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	public int getLinhaCodigoFonte() {
		return linhaCodigoFonte;
	}
	public void setLinhaCodigoFonte(int linhaCodigoFonte) {
		this.linhaCodigoFonte = linhaCodigoFonte;
	}
	
}
