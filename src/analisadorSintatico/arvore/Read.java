package analisadorSintatico.arvore;

import analisadorSintatico.Global;
import analisadorSintatico.tabela.TabelaDeSimbolos;
import analisadorSintatico.tabela.TableEntry;

/*
	Classe que representa um nó Read da árvore sintatica.
	Esta classe precisa do nó pai e um nó ID.
* */
public class Read extends ASTnode {

	private Id id;

	
	public Read(Id id, ASTnode father) {
		super("Read",father);
		if(Global.verboso)
			System.out.println("Criando um nó do tipo Read.");
		this.children.add(id);
		this.id = id;
	}
	
	/* Método que imprime a árvore em formato XML. 
	 * Para tal, é impresso a tag com o nome da class(tipo de nó) e chama a mesma função para os filhos
	 * Então, a tag de fechamento é impressa*/
	public void printArvore(int level) {
		String deslocamento = tabs(level);
		
		System.out.println(deslocamento + "<Read>");
		for(ASTnode child : children) {
			if(child != null)
				child.printArvore(level + 1);
		}
		System.out.println(deslocamento + "</Read>");
	}
	
	/* Método que realiza a avaliação sintatida do nó.
	 * Neste caso, o ID, primeiro filho, recebe o valor da tabela de simbolos. Se não existir,
	 * e for do tipo INT, 0 será retornado. Se for do tipo FLOAT, 0.0 será retornado */
	public Float evaluate() {
		if(Global.verboso)
			System.out.println("Avaliando read");
		Id id_node = (Id) this.children.get(0);
		String lex = id_node.token.getLexema();

		TableEntry te = TabelaDeSimbolos.getInstance().getTabela().get(lex);
		Object expr_value = te.getReferencia();

		if (expr_value == null) {
			te.setReferencia(0f);
//			if(te.getTipo().equals("INT"))
//				te.setReferencia(0);
//			else
//				te.setReferencia(0.0);
		}	
		id_node.value = te.getReferencia();
		
		if(Global.verboso)
			System.out.println("Valor do lexema " + lex + ": " + te.getReferencia().toString());
		
		return te.getReferencia();
	}
	
}
