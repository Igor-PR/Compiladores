package analisadorSintatico.arvore;
import java.util.ArrayList;

import analisadorSintatico.Global;
import codigoTresEnderecos.ArquivoSaida;
import codigoTresEnderecos.Label;

/*
Classe que representa um nó While da árvore sintatica.
Esta classe precisa do nó pai, uma lista para blocos caso a expressão para o loop
e um nó expressão para ser criada.
* */

public class While extends ASTnode{

	private Expr exp;
	private ArrayList<ASTnode> commands;


	public While(Expr exp,ArrayList<ASTnode> commands,ASTnode father) {
		super("While",father);
		
		if(Global.verboso)
			System.out.println("Criando um nó do tipo While.");
		
		this.children.add(exp);
		this.children.addAll(commands);
		this.exp = exp;
		this.commands = new ArrayList<ASTnode>(commands);
	}
	
	/* Método que imprime a árvore em formato XML. 
	 * Para tal, é impresso a tag com o nome da class(tipo de nó) junto com o lexam e o tipo.
	 * Esta tag já é fechada*/
	public void printArvore(int level) {
		String deslocamento = tabs(level);
		
		System.out.println(deslocamento + "<While>");
		for(ASTnode child : children) {
			if(child != null)
				child.printArvore(level + 1);
		}
		System.out.println(deslocamento + "</While>");
	}
	
	/* Método que realiza a avaliação sintatida do nó.
	 * Neste caso, o método avalia a expressão e converte o resultado para Boolean.
	 * Caso este seja true, a lista de nós será avaliada. 
	 * Entrará em loop até que a expressão seja avaliada como false*/
	public Float evaluate() {
		
		Float resultado = exp.evaluate();
		
		while(resultado != 0f) {
			if(Global.verboso)
				System.out.println("Condição do while - True");
			
			for(ASTnode n: commands)
				n.evaluate();
			
			resultado = exp.evaluate();
		}
		if(Global.verboso)
			System.out.println("Condição do while - False");
		
		return null;
	}
	
	public void generateCode() {
		   this.begin  = new Label();
		   this.children.get(0).true_label  = new Label();
		   this.children.get(0).false_label = this.next  = new Label();
		   this.children.get(1).next  = this.begin;
		   ArquivoSaida.escreveArquivo(this.begin.getName()  + ":");
		   this.children.get(0).generateBranchCode() ; //Gera código para a expressão
		   if (this.children.get(0).getAddress()  != null)
			   ArquivoSaida.escreveArquivo("if " + this.children.get(0).getAddress().getName()  + " == 0 goto " +
					   						this.children.get(0).false_label.getName());
		   else
			   ArquivoSaida.escreveArquivo(this.children.get(0).true_label.getName()  + ":");
		   this.children.get(1).generateCode() ; //Gera código para a lista de comandos
		   ArquivoSaida.escreveArquivo("goto " + this.begin.getName());
		   ArquivoSaida.escreveArquivo(this.next.getName()  + ":");
	}

}
