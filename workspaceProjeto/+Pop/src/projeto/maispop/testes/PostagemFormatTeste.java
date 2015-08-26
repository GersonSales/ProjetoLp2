package projeto.maispop.testes;


import org.junit.Assert;
import org.junit.Test;

import projeto.maispop.excecoes.EntradaException;
import projeto.maispop.usuario.Postagem;
import projeto.maispop.usuario.PostagemFormat;

public class PostagemFormatTeste {

	@Test
	public void test() throws EntradaException {

		PostagemFormat formatador = PostagemFormat.getInstancia();
		String postagem2 = "Hoje o sol me acordou. Foi muito cansativo sair da cama pois ainda estava com muito sono. Gostaria ter mais tempo para dormir. Ainda bem que tinha tapioca e cuscuz no cafe da manha para dar energia. #cafe #acorda";
		postagem2 = formatador.getMensagem(postagem2);
		
		
		
		String casa ="Hoje o sol me acordou. Foi muito cansativo sair da cama pois ainda estava com muito sono. Gostaria ter mais tempo para dormir. Ainda bem que tinha tapioca e cuscuz no cafe da manha para dar a energia. #cafe #acorda";
		
		System.out.println(casa);
		casa  = removeEspaco(casa);
		System.out.println(casa);
		

	}
	
	private String removeEspaco(String texto) {
		if (texto.charAt(texto.length() - 1) != ' ') {
			return texto;
		}
		
		texto = texto.substring(0, texto.length() - 1);
		return removeEspaco(texto);
	}
	
}