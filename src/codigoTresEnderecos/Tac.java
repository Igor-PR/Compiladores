package codigoTresEnderecos;


/*
	Formatos das intruções:
	0 - z = x
	1 - z = x op y
	2 - if x oprel y goto L
	3 - ifnot x oprel y goto L
	4 - goto L
*/

public class Tac {

	public int number; //Número Identificador da instrução de três endereços
	public String name;
	public int format; //Especificado acima
	public Operand dst;
	public Operand src1;
	public Operand src2;
	public Label label;
	public Label dstGoto;
	public Tac() {}
	public Tac(int format, Operand dst, Operand src1, Operand src2) {
		
	}
	public Tac(int format, Label dstGoto, Operand src1, Operand src2) {
		
	}
	void setLabel(Label label) //Define o label que inicia a instrução.
	{
		this.label = label;
	}
}
