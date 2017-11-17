package analisadorSintatico.arvore;

import analisadorSintatico.Global;

/*
	Classe que representa um nó Print da árvore sintatica.
	Esta classe precisa do nó pai e um nó Expr.
* */
public class Print extends ASTnode{

	private Expr e;

	
	public Print(Expr exp,ASTnode father) {
		super("Print",father);
		if(Global.verboso)
			System.out.println("Criando um nó do tipo Print");
		this.children.add(exp);
		this.e = exp;
	}
	
	
	/* Método que imprime a árvore em formato XML. 
	 * Para tal, é impresso a tag com o nome da class(tipo de nó) e chama a mesma função para os filhos
	 * Então, a tag de fechamento é impressa*/
	public void printArvore() {
		System.out.println("<Print>");
		for(ASTnode child : children) {
			if(child != null)
				child.printArvore();
		}
		System.out.println("</Print>");
	}

}
