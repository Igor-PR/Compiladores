package analisadorSintatico.arvore;

import analisadorSintatico.Global;
import codigoTresEnderecos.ArquivoSaida;
import codigoTresEnderecos.Label;
import codigoTresEnderecos.Operand;
import codigoTresEnderecos.Temp;

/*
	Classe que representa um nó LogicalOp da árvore sintatica.
	Esta classe precisa de uma operação, nó pai, nó do operando da esquerda e nó do operando da direita
	para ser criada.
* */
public class LogicalOp extends Expr{

	
	public LogicalOp(Expr left,String op,Expr right,ASTnode father) {
		super("LogicalOp",left, op, right, father);
		if(Global.verboso)
			System.out.println("Criando um nó do tipo LogicalOp com operador " + op.toString());
	}
	
	/* Método que imprime a árvore em formato XML. 
	 * Para tal, é impresso a tag com o nome da class(tipo de nó) e também a operação . 
	 * Depois chama a mesma função para os filhos então, a tag de fechamento é impressa*/
	public void printArvore(int level) {
		String deslocamento = tabs(level);
		
		System.out.println(deslocamento + "<LogicalOp op='" +this.op + "'>");
		for(ASTnode child : children) {
			if(child != null)
				child.printArvore(level + 1);
		}
		System.out.println(deslocamento + "</LogicalOp>");
	}
	

	/* Método que realiza a avaliação sintatida do nó.
	 * Neste caso, o método avalia diferente a partir do tipo de operação logica.
	 * Para todos os tipos de operação, o objeto é convertido para Boolean e a operação logica
	 * é aplicada para os dois operandos da classe*/
	public Float evaluate() {
		if(this.op.equals("&&") ) {
            if (this.left.evaluate() != 0f && this.right.evaluate() != 0f)
            	return 1f;
            else
            	return 0f;
		}    
        if(this.op.equals("||")) {
            if (this.left.evaluate() == 1f || this.right.evaluate() == 1f)
            	return 1f;
            else
            	return 0f;
        }    
        return null;
	}
	
	public void generateBranchCode() {
		if (this.op == "||"){
			this.children.get(0).true_label = this.true_label;
			this.children.get(0).false_label = new Label();
			this.children.get(1).true_label = this.true_label;
			this.children.get(1).false_label = this.false_label;
			this.children.get(0).generateBranchCode();
			if (this.children.get(0).getAddress() != null)
			    ArquivoSaida.escreveArquivo("if " + this.children.get(0).getAddress().getName() + " != 0 goto " +  
			    this.children.get(0).true_label.getName());
			else
			    ArquivoSaida.escreveArquivo(this.children.get(0).false_label.getName() + ":");
			this.children.get(1).generateBranchCode();
			if (this.children.get(1).getAddress() != null){
				ArquivoSaida.escreveArquivo("if " + this.children.get(1).getAddress().getName() + " == 0 goto " +  
			    this.children.get(1).false_label.getName());
			    ArquivoSaida.escreveArquivo("goto " + this.children.get(1).true_label.getName());
			}
		}
		else 
			if (this.op == "&&"){
				this.children.get(0).true_label  = new Label();
			    this.children.get(0).false_label  = this.false_label;
			    this.children.get(1).true_label  = this.true_label;
			    this.children.get(1).false_label  = this.false_label;
			    this.children.get(0).generateBranchCode() ;
			    if (this.children.get(0).getAddress()  != null) //Verifica se o nó filho é um Id, Num ou ArithOp verificando se ele possui endereço
			    	ArquivoSaida.escreveArquivo("if " + this.children.get(0).getAddress().getName()  + " == 0 goto " +  
			    	this.children.get(0).false_label.getName());
			    else
			    	ArquivoSaida.escreveArquivo(this.children.get(0).true_label.getName()  + ":");
			    this.children.get(1).generateBranchCode() ;
			    if (this.children.get(1).getAddress()  != null)
			    {
			    	ArquivoSaida.escreveArquivo("if " + this.children.get(1).getAddress().getName()  + " == 0 goto " +  
			        this.children.get(1).false_label.getName());
			    	ArquivoSaida.escreveArquivo("goto " + this.children.get(1).true_label.getName());
			    }    
			}
	}
	
	public void generateRValueCode() {
		/*No inicio do metedo, criar um objeto Operand e setar o atributo temporary dele com t*/
		Label true_label = new Label();	
		Label false_label = new Label();
		Label next_label = new Label();
		Temp t = new Temp();
		
		Operand operand = new Operand();
		operand.setTemporary(t);
		operand.setName(t.getName());
		this.address = operand;
		
		if (this.op == "||"){
			this.children.get(0).generateRValueCode();
			if (this.children.get(0).getAddress() != null) {
				ArquivoSaida.escreveArquivo("goto " + true_label.getName());
				ArquivoSaida.escreveArquivo(t.getName() + " = 0");
				ArquivoSaida.escreveArquivo("goto " + next_label.getName());
			}	
			else {
				this.children.get(1).generateRValueCode();
				if (this.children.get(1).getAddress() != null)
					ArquivoSaida.escreveArquivo("goto " + true_label.getName());
				else {
					ArquivoSaida.escreveArquivo(t.getName() + " = 0");
					ArquivoSaida.escreveArquivo("goto " + next_label.getName());
				}	
			}	
			ArquivoSaida.escreveArquivo(true_label.getName() + ":" + t.getName() + " = 1");
			ArquivoSaida.escreveArquivo(next_label.getName()+":");
		}
		else 
			if (this.op == "&&"){
			    this.children.get(0).generateRValueCode();
			    if (this.children.get(0).getAddress()  == null) {
			    	ArquivoSaida.escreveArquivo("goto" + false_label.getName());
			    	ArquivoSaida.escreveArquivo(t.getName() + " = 1");
					ArquivoSaida.escreveArquivo("goto " + next_label.getName());
			    }	
			    else {
				    this.children.get(1).generateRValueCode();
				    if (this.children.get(1).getAddress()  == null)
				    {
				    	ArquivoSaida.escreveArquivo("goto" + false_label.getName());
				    }
				    else {
				    	ArquivoSaida.escreveArquivo(t.getName() + " = 1");
						ArquivoSaida.escreveArquivo("goto " + next_label.getName());
				    }	
			    }
			    ArquivoSaida.escreveArquivo(next_label.getName()+":");
			}
	}
	
}
