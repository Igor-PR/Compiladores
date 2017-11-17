package analisadorSintatico.arvore;

import analisadorSintatico.Global;
import analisadorSintatico.Token;
import analisadorSintatico.tabela.TabelaDeSimbolos;
import codigoTresEnderecos.Operand;

/*
	Classe que representa um nó Num da árvore sintatica.
	Esta classe precisa do nó pai e Token para ser criada.
* */
public class Num extends Expr{

	private Object value;
	
	public Num(Token token,ASTnode father) {
		super("Num", father);
		if(Global.verboso)
			System.out.println("Criando um nó do tipo Num");
		this.token = token;
		this.value = TabelaDeSimbolos.getInstance().getTabela().get(token.getLexema());
		if(token.getLexema().contains(".")) {
			this.valorPontoFlutuante = Float.parseFloat(token.getLexema());
			this.type = "FLOAT";
		}	
		else {
			this.valorInteiro = Integer.parseInt(token.getLexema());
			this.type = "INT";
		}	
		
		
	}
	
	/* Método que realiza a avaliação sintatida do nó.
	 * Neste caso, o lexema é retornado */
	public Object evaluate() {
		return this.token.getLexema();
	}
	
	/* Método que imprime a árvore em formato XML. 
	 * Para tal, é impresso a tag com o nome da class(tipo de nó) junto com o lexam e o tipo.
	 * Esta tag já é fechada*/
	public void printArvore() {
		if(this.type.equals("INT"))
			System.out.println("<Num valor='" +this.valorInteiro+"' tipo='integer'" + "/>");
		else
			System.out.println("<Num valor='" +this.valorPontoFlutuante+"' tipo='float'" + "/>");
	}
	
	public void generateCode()
	{
		Operand operand = new Operand();
		operand.setName(this.token.getLexema());
		this.address = operand;
	}
	public void generateRValueCode(){
		generateCode();
	}
	public void generateBranchCode(){
		generateCode();
	}

}
