package codigoTresEnderecos;

public class Temp {

	public static int n = 0;
	public String name;
	public Temp() {
		String s = String.valueOf(n++);
		this.name = "t" + s;
	}
	public String getName() {
		return this.name;
	}
	
}
