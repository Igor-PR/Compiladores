package analisadorSintatico.arvore;
/*
	Classe que representa um nó Expr da árvore sintatica. Todos as classes de expressão são herdeiras desta.
	Esta classe precisa de um nome e um nó pai para ser instanciada ou o nome, operação, nó pai, nó do operando 
	da esquerda e nó do operando da direita.
	Apresenta Token, expressões e valores como atributo
* */

import analisadorSintatico.Token;

public class Expr extends ASTnode {

	protected Expr left;
	protected String op;
	protected Expr right;
	protected String type;
	protected Token token;
	protected ASTnode temp;
	protected int valorInteiro;
	protected Float valorPontoFlutuante;

	protected Expr(String nome, ASTnode father) {
		super(nome, father);
	}
	
	public Expr(String nome,ASTnode left,String op,ASTnode right,ASTnode father) {
		super(nome,father);
		this.children.add(left);
		this.children.add(right);
		this.left = (Expr) left;
		this.op = op;
		this.right = (Expr) right;
	}

}
