package analisadorSintatico.arvore;
import analisadorSintatico.Global;
/*
	Classe que representa um nó Bloco da árvore sintatica.
	Esta classe precisa de um nó pai e nome para ser criada.
* */
public class Bloco extends ASTnode {
	
	public Bloco(ASTnode father) {
		super("Bloco",father);
		if(Global.verboso)
			System.out.println("Criando um nó do tipo Bloco");
	}

	/* Método que imprime a árvore em formato XML. 
	 * Para tal, é impresso a tag com o nome da class(tipo de nó) e chama a mesma função para os filhos
	 * Então, a tag de fechamento é impressa*/
	public void printArvore(int level) {
		String deslocamento = tabs(level);
		
		System.out.println(deslocamento + "<Bloco>");
		for(ASTnode child : children) {
			if(child != null)
				child.printArvore(level + 1);
		}
		System.out.println(deslocamento + "</Bloco>");
	}
	
}
