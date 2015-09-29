package projeto.maispop.testes;


import org.junit.Before;
import org.junit.Test;

import projeto.maispop.excecoes.EntradaException;
import projeto.maispop.excecoes.LogicaException;
import projeto.maispop.sistema.BancoDeUsuarios;

public class TesteBancoDeUsuario {
	
	private BancoDeUsuarios bancoDeUsuarios;
	
	@Before
	public void instancia() {
		this.bancoDeUsuarios = new BancoDeUsuarios();
	}

	@Test
	public void test() throws EntradaException, LogicaException {
		//expectError "Erro no cadastro de Usuarios. Data nao existe."  
		//cadastraUsuario nome="Fulaninho" email="alguem@email.com" senha="senha_besta" dataNasc="32/10/2010"
		//Text '1510/10/2010' could not be parsed at index 2
		//Text '32/10/2010' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 32

		//<Nao foi possivel realizar login. Um usuarix ja esta logadx: Fatima Bernardes.>
		//<Nao foi possivel realizar login.  Um usuarix ja esta logadx: Fatima Bernardes.>


		//try {
			this.bancoDeUsuarios.cadastraUsuario("Fulaninho", "alguem@email.com", "senha_besta", "32/10/2010");
		//}catch (EmailException e) {
		//	System.out.println();
		//	Assert.fail();
		//}catch (Exception e) {
		//	System.out.println(e.getMessage());
		//}
		
	}

}
