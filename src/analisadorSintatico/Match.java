package analisadorSintatico;
/*Classe responsável por conferir se um token é o mesmo que o esperado*/
public class Match {

	/*Método que recebe um token esperado pela gramatica e compara com o token atual na lista*/
	public static void match(String token) {
		if(Global.tokenAtual.getNomeToken().equals(token)) {
			
			if(Global.verboso)
				System.out.println("Token: "+ Global.tokenAtual.toString()+ " reconhecido na entrada");
			
			Global.index++; //Atualiza o index
			
			//Caso a lista não tenha terminado, atualiza o token
			if(Global.index < Sintatico.getListaTokens().size())
				Global.tokenAtual = Sintatico.getListaTokens().get(Global.index);
		}
		else {
			//Marca a flag de erro como true
			Global.flag = true;
			
			System.out.println("Erro sintatico! Token " + Global.tokenAtual.toString()+ " não esperado!");
			
			if(Global.verboso)
				System.out.println(token + " Esperado");
		}	
	}
}
