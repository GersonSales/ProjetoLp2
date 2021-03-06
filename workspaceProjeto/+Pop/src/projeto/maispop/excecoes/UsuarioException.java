package projeto.maispop.excecoes;

@SuppressWarnings("serial")
public class UsuarioException extends LogicaException {
	public UsuarioException() {
		super("Usuario invalido!");
	}
	
	public UsuarioException(String mensagem) {
		super(mensagem);
	}
	
	//Teste
	public UsuarioException(String mensagem, Throwable erro) {
		super(mensagem, erro);
	}
	public UsuarioException(Throwable erro) {
		super(erro);
	}
	
}
