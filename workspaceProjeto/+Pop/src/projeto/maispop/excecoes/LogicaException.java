package projeto.maispop.excecoes;

@SuppressWarnings("serial")
public class LogicaException extends MaisPopException {
	public LogicaException() {
		super("Logica invalida!");
	}
	
	public LogicaException(String mensagem) {
		super(mensagem);
	}
	//Teste
	public LogicaException(String mensagem, Throwable erro) {
		super(mensagem, erro);
	}
	
	public LogicaException(Throwable erro) {
		super(erro);
	}
	
	
	

}
