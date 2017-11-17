package analisadorSintatico.arvore;

import analisadorSintatico.Global;
import analisadorSintatico.Token;
import analisadorSintatico.tabela.TabelaDeSimbolos;
import analisadorSintatico.tabela.TableEntry;
import codigoTresEnderecos.Operand;

/*
	Classe que representa um nó Id da árvore sintatica.
	Esta classe precisa do nó pai e Token para ser criada.
* */
public class Id extends Expr{
	
	private TableEntry te;

	public Id(Token token, ASTnode father) {
		super("Id", father);
		if(Global.verboso)
			System.out.println("Criando um nó do tipo Id");
		this.token = token;
		this.te = TabelaDeSimbolos.getInstance().getTabela().get(token.getLexema());
		this.value = te.getReferencia();
	}
	
	/* Método que imprime a árvore em formato XML. 
	 * Para tal, é impresso a tag com o nome da class(tipo de nó) junto com o lexam e o tipo.
	 * Esta tag já é fechada*/
	public void printArvore() {
		if(this.te.getTipo().equals("INT"))
			System.out.println("<Id lexema='" +this.token.getLexema()+"' tipo='integer'" + "/>");
		else
			System.out.println("<Id lexema='" +this.token.getLexema()+"' tipo='float'" + "/>");
	}
	

	/* Método que realiza a avaliação sintatida do nó.
	 * Neste caso, o método procura pelo lexema do ID na tabela de simbolos.
	 * Caso este não seja nulo, o valor vai ser retornado, caso o contrario, retorna o inteiro 0*/
	public Object evaluate() {
		this.te  = TabelaDeSimbolos.getInstance().getTabela().get(this.token.getLexema());
		
		if(Global.verboso)
			System.out.println("Avaliando nó Id("+this.token.getLexema() +"). Valor armazenado: " + te.getReferencia().toString());
		
		if(te.getReferencia() != null){
			return te.getReferencia();
		}
		else
			return 0;
	}
	
	public void generateCode()
	{
	   Operand operand = new Operand();
	   operand.setTableEntry(this.te);
	   operand.setName(this.te.getToken().getLexema());
	   this.address = operand;  
	}
	public void generateRValueCode()
	{
	   generateCode();
	}
	public void generateBranchCode()
	{
	   generateCode();
	}
}
