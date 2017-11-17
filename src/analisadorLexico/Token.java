package analisadorLexico;


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
		return "Token: " + nomeToken + "\nLexema: " + lexema + "\nLinha: " + linhaCodigoFonte;	
	}
	
	public String toFile() {
		return nomeToken + " " + lexema + " " + linhaCodigoFonte+"\n";
	}
	
}
