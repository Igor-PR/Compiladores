package codigoTresEnderecos;

public class Label {
	
	public static int n = 0;
	public String name;
	public Label() {
		String s = String.valueOf(n++);
		this.name = "L" + s;
	}
	public String getName() {
		return this.name;
	}

}
