package projeto.maispop.excecoes;

@SuppressWarnings("serial")
public class LogarDeslogarException extends LogicaException {
	public LogarDeslogarException() {
		super("Erro ao tentar logar/deslogar no sistema!");
	}
	
	public LogarDeslogarException(String mensagem) {
		super(mensagem);
	}
}
