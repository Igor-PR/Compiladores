package analisadorSintatico.arvore;
import analisadorSintatico.Global;
import codigoTresEnderecos.ArquivoSaida;
import codigoTresEnderecos.Operand;
import codigoTresEnderecos.Temp;
/*
	Classe que representa um nó ArithOp da árvore sintatica.
	Esta classe precisa de uma operação, nó pai, nó do operando da esquerda e nó do operando da direita
	para ser criada.
* */
public class ArithOp extends Expr {
	
	public ArithOp(Expr left,String op,Expr right,ASTnode father) {
		super("ArithOp",left, op, right, father);
		if(Global.verboso)
			System.out.println("Criando um nó do tipo ArithOp com operador " + op.toString() );
	}
	
	/* Método que realiza a avaliação sintatida do nó.
	 * Neste caso, o método avalia diferente a partir do tipo de operação aritmética.
	 * Para todos os tipos de operação, o objeto é convertido para string depois float e a operação aritmética
	 * é aplicada para os dois operandos da classe*/
	
	public Float evaluate() {
		if(this.op.equals("+") )
            return Float.parseFloat((String)this.left.evaluate().toString()) + 
            		Float.parseFloat((String)this.right.evaluate().toString());
        if(this.op.equals("-"))
            return Float.parseFloat((String)this.left.evaluate().toString()) - 
            		Float.parseFloat((String)this.right.evaluate().toString());
        if(this.op.equals("*"))
            return Float.parseFloat((String)this.left.evaluate().toString()) * 
            		Float.parseFloat((String)this.right.evaluate().toString());
        if(this.op.equals("/"))
            return Float.parseFloat((String)this.left.evaluate().toString()) / 
            		Float.parseFloat((String)this.right.evaluate().toString());
        return null;
	}
	
	/* Método que imprime a árvore em formato XML. 
	 * Para tal, é impresso a tag com o nome da class(tipo de nó) e também a operação . 
	 * Depois chama a mesma função para os filhos então, a tag de fechamento é impressa*/
	public void printArvore(int level) {
		String deslocamento = tabs(level);
		
		System.out.println(deslocamento + "<ArithOp op='" +this.op + "'>");
		for(ASTnode child : children) {
			if(child != null)
				child.printArvore(level + 1);
		}
		System.out.println(deslocamento + "</ArithOp>");
	}

	public void generateRValueCode()
	{
	   this.children.get(0).generateRValueCode();
	   this.children.get(1).generateRValueCode();
	   Temp temp = new Temp();
	   this.address = new Operand();
	   this.address.setTemporary(temp);
	   this.address.setName(temp.name);
	   ArquivoSaida.escreveArquivo(temp.name + " = " + this.children.get(0).
			   					   getAddress().getName() + " " + this.op + " " +
			   					   this.children.get(1).getAddress().getName());
	   
	}
	
	public void generateBranchCode() {
	    this.children.get(0).generateBranchCode();
	    this.children.get(1).generateBranchCode();
	    Temp temp = new Temp();
	    this.address  = new Operand();
	    this.address.setTemporary(temp);
	    this.address.setName(temp.name);
	    ArquivoSaida.escreveArquivo(temp.name  + " = " +
	    							this.children.get(0).getAddress().getName()  + " " + this.op + " " + 
	    							this.children.get(1).getAddress().getName());
	}
	
}
