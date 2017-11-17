package analisadorLexico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*Classe que contém o analisador lexico*/
public class AnalisadorLexico {
	
	private String lexema = new String();
	private Map<String,String> hashMap = new HashMap<String,String>();
	private int linhaCodigoFonte = 1;
	private ArrayList<Token> listaTokens = new ArrayList<>();
	
	/*Método que inicializa um mapa em hash contendo todos os TOKENS aceitos pela linguagem*/
	public void criaMapaTokens(){
		hashMap.put("main", "MAIN");
		hashMap.put("int", "INT");
		hashMap.put("float", "FLOAT");
		hashMap.put("if", "IF");
		hashMap.put("else", "ELSE");
		hashMap.put("while", "WHILE");
		hashMap.put("read", "READ");
		hashMap.put("print", "PRINT");
		hashMap.put("(", "LBRACKET");
		hashMap.put(")", "RBRACKET");
		hashMap.put("{", "LBRACE");
		hashMap.put("}", "RBRACE");
		hashMap.put(",", "COMMA");
		hashMap.put(";", "PCOMMA");
		hashMap.put("=", "ATTR");
		hashMap.put("<", "LT");
		hashMap.put("<=", "LE");
		hashMap.put(">", "GT");
		hashMap.put(">=", "GE");
		hashMap.put("==", "EQ");
		hashMap.put("!=", "NE");
		hashMap.put("||", "OR");
		hashMap.put("&&", "AND");
		hashMap.put("+", "PLUS");
		hashMap.put("-", "MINUS");
		hashMap.put("*", "MULT");
		hashMap.put("/", "DIV");
		hashMap.put("^[a-zA-Z][a-zA-Z0-9]*$", "ID");
		hashMap.put("^[0-9][0-9]*$", "INTEGER_CONST");
		hashMap.put("^[0-9][0-9]*[.][0-9][0-9]*$", "FLOAT_CONST");
	}
	
	/*Método que gera um token a partir de um lexema dado*/
	public Token getToken(String lexema){
		/*Expressões regulares que representam o token ID,Constante INT e Constante FLOAT*/
		String idRegex = "^[a-zA-Z][a-zA-Z0-9]*$";
		String intConstRegex = "^[0-9][0-9]*$";
		String floatConstRegex = "^[0-9][0-9]*[.][0-9][0-9]*$";
		
		String token = "";
		
		/*Testa se o lexema gera um Token diretamente no hash e retorna esse token caso sim*/
		if(hashMap.containsKey(lexema)){
			token = hashMap.get(lexema);
			return new Token(token,lexema,linhaCodigoFonte);
		}
		/*Caso não possa ser encontrado diretamente, as expressões regulares são testadas
		 * e os Tokens são criados caso aceitos*/
		else{
			if(lexema.matches(idRegex)){
				token = hashMap.get(idRegex);
				return new Token(token,lexema,linhaCodigoFonte);
			}	
			if(lexema.matches(intConstRegex)){
				token = hashMap.get(intConstRegex);
				return new Token(token,lexema,linhaCodigoFonte);
			}	
			if(lexema.matches(floatConstRegex)){	
				token = hashMap.get(floatConstRegex);
				return new Token(token,lexema,linhaCodigoFonte);
			}
			/*Cria um Token para representar o fim da lista de tokens*/
			if(lexema.contains("$"))
				return new Token("$","",linhaCodigoFonte + 1);
			
		}
		return null;
	}

	/*Método que simula o automato da linguagem*/
	public ArrayList<Token> geraTokens(){
		int estado = 1;
		
		/*Recebe o próximo Char*/
		char c = IO.getNextChar();
		
		String s;
		Token token = null;
		
		/*Enquanto o caracter não seja o que representa o fim do buffer */
		while (c!='$'){
			switch(estado){
				/*Estado 1, representa o inicio e fim do automato*/
				case(1):
					/*Neste estado, o char é convertido para string e então comparado
					 * com varias strings e expressões para saber qual o próximo estado.
					 * O lexema é concatenado a cada caracter*/
					
					/*Testa se o lexema está vazio, caso sim, procura-se o próximo estado*/
					if(lexema.isEmpty()){
						s = String.valueOf(c);
						
						if(s.matches("^[a-zA-Z]*$")){ //Contém apenas letras
							lexema = lexema.concat(s);
							estado = 2;
							break;
						}
						if(s.matches("^[0-9]*$")){ //Contém apenas digitos
							lexema = lexema.concat(s);
							estado = 3;
							break;
						}
						if(s.contains("=")){
							lexema = lexema.concat(s);
							estado = 6;
							break;
						}
						if(s.contains("!")){
							lexema = lexema.concat(s);
							estado = 7;
							break;
						}
						if(s.contains("&")){
							lexema = lexema.concat(s);
							estado = 8;
							break;
						}
						if(s.contains("|")){
							lexema = lexema.concat(s);
							estado = 9;
							break;
						}
						if(s.contains("<")){
							lexema = lexema.concat(s);
							estado = 10;
							break;
						}
						if(s.contains(">")){
							lexema = lexema.concat(s);
							estado = 11;
							break;
						}
						/*Caso o fim de linha seja reconhecido, o contador de linhas é incrementado*/
						if(s.contains("\n"))
							linhaCodigoFonte +=1;
						lexema = lexema.concat(s);
					}
					/*Se o lexima não estiver vazio, um token é gerado*/
					else{
						token = getToken(lexema);
						lexema = "";
						continue;
					}	
				
					break;
				case(2):
					s = String.valueOf(c);
					if(s.matches("^[a-zA-Z0-9]*$")) //Contém letras e digitos apenas
						lexema = lexema.concat(s);
					else{
						estado = 1;
						continue;
					}	
					break;
				case(3):
					s = String.valueOf(c);
					if(s.matches("^[0-9]*$")) //Contém apenas digitos
						lexema = lexema.concat(s);
					else{
						if(s.contains(".")){
							lexema = lexema.concat(s);
							estado = 4;
						}
						else{
							estado = 1;
							continue;
						}	
					}
					break;
				case(4):
					s = String.valueOf(c);
					if(s.matches("^[0-9]*$")){ //Contém apenas digitos
						lexema = lexema.concat(s);
						estado = 5;
					}
					else
						System.out.println("Erro!");
					break;
				case(5):
					s = String.valueOf(c);
					if(s.matches("^[0-9]*$")) //Contém apenas digitos
						lexema = lexema.concat(s);
					else{
						estado = 1;
						continue;
					}	
					break;
				case(6):
					s = String.valueOf(c);
					estado = 1;
					if(s.contains("=")){
						lexema = lexema.concat(s);
						break;
					}
					else{
						token = getToken(lexema);
						lexema = "";
						continue;
					}
					
				case(7):
					s = String.valueOf(c);
					estado = 1;
					if(s.contains("="))
						lexema = lexema.concat(s);
					else
						System.out.println("Erro!");
					break;
				case(8):
					s = String.valueOf(c);
					estado = 1;
					if(s.contains("&"))
						lexema = lexema.concat(s);
					else
						System.out.println("Erro!");
					break;
				case(9):
					s = String.valueOf(c);
					estado = 1;
					if(s.contains("|"))
						lexema = lexema.concat(s);
					else
						System.out.println("Erro!");
					break;
				case(10):
					s = String.valueOf(c);
					estado = 1;
					if(s.contains("=")){
						lexema = lexema.concat(s);
						break;
					}
					continue;
				case(11):
					s = String.valueOf(c);
					estado = 1;
					if(s.contains("=")){
						lexema = lexema.concat(s);
						break;
					}	
					continue;
			}
			/*Ao final de cada teste de estado, um novo caracter é lido e testa se
			 * o token retornado é nulo, caso não seja, ele é adicionado a lista que irá
			 * ser retornado*/
			c = IO.getNextChar();
			if (token != null){
				listaTokens.add(token);
				token = null;
			}	
		}
		/*Adiciona o token que representa o fim de linha e depois retorna a lista*/
		listaTokens.add(getToken("$"));
		return this.listaTokens;
	}
}
