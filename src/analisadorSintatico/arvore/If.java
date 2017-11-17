package analisadorSintatico.arvore;
import java.util.ArrayList;

import analisadorSintatico.Global;
import codigoTresEnderecos.ArquivoSaida;
import codigoTresEnderecos.Label;

/*
Classe que representa um nó IF da árvore sintatica.
Esta classe precisa do nó pai, uma lista para blocos caso a expressão for verdadeira e
uma lista para caso a expressão for falsa e um nó expressão para ser criada.
* */

public class If extends ASTnode{

	private Expr exp;
	private ArrayList<ASTnode> c_true;
	private ArrayList<ASTnode> c_false;

	
	public If(Expr exp,ArrayList<ASTnode> c_true,ArrayList<ASTnode> c_false,ASTnode father) {
		super("If",father);
		this.children.add(exp);
		if(c_true != null)
			this.children.addAll(c_true);
		if(c_false != null)
			this.children.addAll(c_false);
		if(Global.verboso)
			System.out.println("Criando um nó do tipo If");
		this.exp = exp;
		this.c_true = c_true;
		this.c_false = c_false;
	}
	
	/* Método que imprime a árvore em formato XML. 
	 * Para tal, é impresso a tag com o nome da class(tipo de nó) e chama a mesma função para os filhos
	 * Então, a tag de fechamento é impressa*/
	public void printArvore() {
		System.out.println("<If>");
		for(ASTnode child : children) {
			if(child != null)
				child.printArvore();
		}
		System.out.println("</If>");
	}
	
	/* Método que realiza a avaliação sintatida do nó.
	 * Neste caso, o método avalia a expressão e converte o resultado para Boolean.
	 * Caso este seja true, a lista da condição verdadeira será avaliada, 
	 * caso o contrario, a lista da condição falsa será avaliada*/
	public Object evaluate() {
		
		Boolean resultado = (Boolean)exp.evaluate();
		
		if(resultado) {
			if(Global.verboso)
				System.out.println("Condição de If - Verdadeira");
			for(ASTnode n: c_true)
				n.evaluate();
		}
		else {
			if(Global.verboso)
				System.out.println("Condição de If - Falsa");
			for(ASTnode n: c_false)
				n.evaluate();
		}	
		
		return null;
	}
	
	public void generateCode() {
		  this.next = new Label();
		  if (this.children.size() == 3) //Tem else
		  {
		    this.children.get(0).true_label = new Label();
		    this.children.get(0).false_label = new Label();
		    this.children.get(0).next = this.children.get(1).next = this.next;
		    this.children.get(0).generateBranchCode(); //Gera código da expressão
		    if (this.children.get(0).getAddress() != null)
		    	ArquivoSaida.escreveArquivo("if " + this.children.get(0).getAddress().getName() + " == 0 goto " 
		    								+ this.children.get(0).false_label.getName());
		    else
		    {
		    	ArquivoSaida.escreveArquivo(this.children.get(0).true_label.getName() + ":");
		    }
		    this.children.get(1).generateCode();
		    ArquivoSaida.escreveArquivo("goto " + this.next.getName());
		    ArquivoSaida.escreveArquivo(this.children.get(0).false_label.getName() + ":");
		    this.children.get(2).generateCode();
		  }
		  else
		  {
		    this.children.get(0).true_label = new Label();
		    this.children.get(0).false_label = this.children.get(1).next = this.next;
		    this.children.get(0).generateBranchCode(); //Gera código da expressão
		    if (this.children.get(0).getAddress() != null)
		    	ArquivoSaida.escreveArquivo("if " + this.children.get(0).getAddress().getName() + " == 0 goto " 
		    								+ this.children.get(0).false_label.getName());
		    else
		    {
		    	ArquivoSaida.escreveArquivo(this.children.get(0).true_label.getName() + ":");
		    }
		    this.children.get(1).generateCode();
		  }
		  ArquivoSaida.escreveArquivo(this.next.getName() + ":");
	}

}
