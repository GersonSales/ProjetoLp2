package projeto.maispop.excecoes;

@SuppressWarnings("serial")
public class DataException extends EntradaException {
	public DataException() {
		super("Data invalida!");
	}
	
	public DataException(String mensagem) {
		super(mensagem);
	}
	

}
