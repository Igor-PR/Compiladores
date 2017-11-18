package analisadorSintatico.arvore;

import analisadorSintatico.Global;
import codigoTresEnderecos.ArquivoSaida;

/*
	Classe que representa um nó RellOp da árvore sintatica.
	Esta classe precisa de uma operação, nó pai, nó do operando da esquerda e nó do operando da direita
	para ser criada.
* */
public class RelOp extends Expr {
	
	public RelOp(Expr left,String op,Expr right,ASTnode father) {
		super("RelOp",left, op, right, father);
		if(Global.verboso)
			System.out.println("Criando um nó do tipo RelOp com operador " + op.toString());
	}
	
	/* Método que imprime a árvore em formato XML. 
	 * Para tal, é impresso a tag com o nome da class(tipo de nó) e também a operação . 
	 * Depois chama a mesma função para os filhos então, a tag de fechamento é impressa*/
	public void printArvore(int level) {
		String deslocamento = tabs(level);
		
		System.out.println(deslocamento + "<RelOp op='" +this.op + "'>");
		for(ASTnode child : children) {
			if(child != null)
				child.printArvore(level + 1);
		}
		System.out.println(deslocamento + "</RelOp>");
	}

	/* Método que realiza a avaliação sintatida do nó.
	 * Neste caso, o método avalia diferente a partir do tipo de operação relacional.
	 * Para todos os tipos de operação, o objeto é convertido para string depois float e a operação relacional
	 * é aplicada para os dois operandos da classe*/
	public Float evaluate() {
		if(this.op.equals("==")) {
            if (Float.parseFloat((String)this.left.evaluate().toString()) == 
            		Float.parseFloat((String)this.right.evaluate().toString()))
				return 1f;
			else
				return 0f;
		} 
        if(this.op.equals("!=")) {
            if(Float.parseFloat((String)this.left.evaluate().toString()) != 
            		Float.parseFloat((String)this.right.evaluate().toString()))
        		return 1f;
        	else
        		return 0f;
        }    
		if(this.op.equals(">") ) {
            if (Float.parseFloat((String)this.left.evaluate().toString()) > 
            		Float.parseFloat((String)this.right.evaluate().toString()))
            	return 1f;
        	else
        		return 0f;
		}    		
        if(this.op.equals(">=")) {
            if(Float.parseFloat((String)this.left.evaluate().toString()) >= 
            		Float.parseFloat((String)this.right.evaluate().toString()))
            	return 1f;
        	else
        		return 0f;
        }    
        if(this.op.equals("<")) {
            if(Float.parseFloat((String)this.left.evaluate().toString()) < 
            		Float.parseFloat((String)this.right.evaluate().toString()))
            	return 1f;
        	else
        		return 0f;
        }    
        if(this.op.equals("<=")) {
            if(Float.parseFloat((String)this.left.evaluate().toString()) <= 
            		Float.parseFloat((String)this.right.evaluate().toString()))
            	return 1f;
        	else
        		return 0f;
        }    
        return null;
	}
	
	public void generateBranchCode() {
	    this.children.get(0).generateBranchCode() ;
	    this.children.get(1).generateBranchCode() ;
	    String test = this.children.get(0).getAddress().getName()  + this.op + 
	    			  this.children.get(1).getAddress().getName();
	    ArquivoSaida.escreveArquivo("if " + test + " goto " +  this.true_label.getName());
	    ArquivoSaida.escreveArquivo("goto " + this.false_label.getName());
		}
}
