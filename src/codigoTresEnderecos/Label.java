package codigoTresEnderecos;

public class Label {
	
	public static int n;
	public String name;
	public Label() {
		String s = String.valueOf(n++);
		this.name = "L" + s;
	}
	public String getName() {
		return this.name;
	}

}
