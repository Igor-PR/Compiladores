package analisadorSintatico.arvore;
import java.util.ArrayList;

import analisadorSintatico.Global;
import codigoTresEnderecos.Label;
import codigoTresEnderecos.Operand;

/*
	Classe que representa um nó generico da árvore sintatica. Todos as outras classes são herdeiras desta.
	Esta classe precisa de um nome e um nó pai para ser instanciada.
	Apresenta nome, lista de filhos, referencia do pai, tipo e referencia do valor como atributos
* */
public class ASTnode {
	
	protected String nome;
	public ArrayList<ASTnode> children;
	protected Object father;
	protected String tipo;
	protected Object value;
	
	protected Operand address;
	protected Label begin;
	protected Label next;
	protected Label true_label;
	protected Label false_label;
	
	public ASTnode(String nome,ASTnode father) {
		this.nome = nome;
		this.father = father;
		children = new ArrayList<>();
		tipo = "";
		value = null;		
	}
	
	/* Método que realiza a avaliação sintatida do nó.
	 * Neste caso, o método apenas chama o método de avaliação para todos os fillhos*/
	public Object evaluate() {
		if(Global.verboso)
			System.out.println("Avaliando nó " + this.nome);
		for(ASTnode child : children) {
			if(child != null)
				child.evaluate();
		}
		return null;
	}
	
	/* Método que imprime a árvore em formato XML. 
	 * Para tal, é impresso a tag com o nome da class(tipo de nó) e chama a mesma função para os filhos
	 * Então, a tag de fechamento é impressa*/
	public void printArvore() {
		System.out.println("<ASTnode>");
		for(ASTnode child : children) {
			if(child != null)
				child.printArvore();
		}
		System.out.println("</ASTnode>");
	}
	
	
	public void generateCode() {
//		if(Global.verboso)
//			System.out.println("Avaliando nó " + this.nome);
		for(ASTnode child : children) {
			if(child != null)
				child.generateCode();
		}
	}
	
	public void generateRValueCode() {}
	
	public void generateBranchCode() {}
	
	public Operand getAddress() {
		return this.address;
	}
}
