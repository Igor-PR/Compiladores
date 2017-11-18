package analisadorSintatico.arvore;
import analisadorSintatico.Global;
import analisadorSintatico.Token;
import analisadorSintatico.tabela.TabelaDeSimbolos;
import analisadorSintatico.tabela.TableEntry;
import codigoTresEnderecos.ArquivoSaida;
/*
	Classe que representa um nó ATTR da árvore sintatica.
	Esta classe precisa de uma operação, nó pai, nó do operando da esquerda e nó do operando da direita
	para ser criada.
* */
public class Attr extends ASTnode {
	private Token token;
	private ASTnode left;
	private ASTnode right;
	private Id id;
	private Expr e;
	private String op;

	public Attr(ASTnode left,String op, ASTnode right, ASTnode father) {
		super("Attr",father);
		if(Global.verboso)
			System.out.println("Criando um nó do tipo Attr");
		this.children.add(left);
		this.children.add(right);
		this.left = left;
		this.right = right;
		this.op = op;
	}
	
	/* Método que imprime a árvore em formato XML. 
	 * Para tal, é impresso a tag com o nome da class(tipo de nó) e chama a mesma função para os filhos
	 * Então, a tag de fechamento é impressa*/	
	public void printArvore(int level) {
		String deslocamento = tabs(level);
		
		System.out.println(deslocamento + "<Attr>");
		for(ASTnode child : children) {
			if(child != null)
				child.printArvore(level + 1);
		}
		System.out.println(deslocamento + "</Attr>");
	}
	
	/* Método que realiza a avaliação sintatida do nó.
	 * Neste caso, o ID, primeiro filho, recebe o valor da expressão, segundo filho.
	 * Antes de realizar essa atribuição, o nó que contém a expressão é avaliado*/
	public Float evaluate() {
		
		if(Global.verboso)
			System.out.println("Avaliando atribuição");
		
		Id id_node = (Id) this.children.get(0);
		String lex = id_node.token.getLexema();
		
		if(Global.verboso)
			System.out.println("Lexema do lado esquerdo: " + lex);
		
		TableEntry te = TabelaDeSimbolos.getInstance().getTabela().get(lex);
		Float expr_value = this.children.get(1).evaluate();
		
		if(Global.verboso)
			System.out.println("Valor da expressão do lado direito: " + expr_value.toString());
		
		te.setReferencia(expr_value);
		
		id_node.value = expr_value;
		
		if(Global.verboso)
			System.out.println("Valor do lexema " + lex + ": " + te.getReferencia().toString());
		
		return te.getReferencia();
	}
	
	public void generateCode() {
		
		  //Em uma atribuição, eu defino o endereço do id do lado esquerdo como sendo o endereço do temporário no lado direito
		  this.children.get(0).generateCode();
		  this.children.get(1).generateRValueCode();
		  this.children.get(0).getAddress().setTemporary(this.children.get(1).getAddress().getTemporary());
		  ArquivoSaida.escreveArquivo(this.children.get(0).getAddress().getName() + " = " + 
				  					  this.children.get(1).getAddress().getName());
		
	}
	
}
