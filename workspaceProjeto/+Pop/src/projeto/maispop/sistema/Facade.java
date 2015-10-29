package projeto.maispop.sistema;

import projeto.maispop.excecoes.EntradaException;
import projeto.maispop.excecoes.ItemInexistenteException;
import projeto.maispop.excecoes.LogarDeslogarException;
import projeto.maispop.excecoes.LogicaException;
import projeto.maispop.excecoes.SenhaException;
import projeto.maispop.excecoes.SolicitacaoException;
import projeto.maispop.excecoes.UsuarioExistenteException;
import projeto.maispop.excecoes.UsuarioInexistenteException;

public class Facade {

	private Controller controller;

	public Facade() {
		this.controller = new Controller();
	}

	public void iniciaSistema() {

	}

	public void fechaSistema() throws LogicaException {
		this.controller.fechaSistema();

	}

	public String cadastraUsuario(String nome, String email, String senha,
			String dataNascimento, String imagem) throws EntradaException,
			UsuarioExistenteException {
		try {
			this.controller.cadastraUsuario(nome, email, senha, dataNascimento,
					imagem);
		} catch (EntradaException erro) {
			throw new EntradaException("Erro no cadastro de Usuarios. "
					+ erro.getMessage(), erro);
		} catch (UsuarioExistenteException erro) {
			throw erro;
		}

		return email;
	}

	public String cadastraUsuario(String nome, String email, String senha,
			String dataNascimento) throws EntradaException,
			UsuarioExistenteException {
		try {
			this.controller.cadastraUsuario(nome, email, senha, dataNascimento);
		} catch (EntradaException erro) {
			throw new EntradaException("Erro no cadastro de Usuarios. "
					+ erro.getMessage(), erro);
		} catch (UsuarioExistenteException erro) {
			throw erro;
		}
		return email;
	}

	public void removeUsuario(String email) throws UsuarioInexistenteException {
		this.controller.removeUsuario(email);
	}

	public String getInfoUsuario(String atributo) throws EntradaException,
			LogicaException {
		return this.controller.getInfoUsuario(atributo);

	}

	public String getInfoUsuario(String atributo, String email)
			throws EntradaException, LogicaException {
		return this.controller.getInfoUsuario(atributo, email);
	}

	public void atualizaPerfil(String atributo, String valor, String antigoValor)
			throws UsuarioInexistenteException, EntradaException {
		try {
			this.controller.atualizaPerfil(atributo, valor, antigoValor);
		} catch (EntradaException erro) {
			throw new EntradaException("Erro na atualizacao de perfil. "
					+ erro.getMessage(), erro);
		} catch (UsuarioInexistenteException erro) {
			throw new UsuarioInexistenteException(
					"Nao eh possivel atualizar um perfil. " + erro.getMessage(),
					erro);
		}
	}

	public void atualizaPerfil(String atributo, String valor)
			throws UsuarioInexistenteException, EntradaException {
		try {
			this.controller.atualizaPerfil(atributo, valor);
		} catch (EntradaException erro) {
			throw new EntradaException("Erro na atualizacao de perfil. "
					+ erro.getMessage(), erro);
		} catch (UsuarioInexistenteException erro) {
			throw new UsuarioInexistenteException(
					"Nao eh possivel atualizar um perfil. " + erro.getMessage(),
					erro);
		}
	}

	public void criaPost(String texto, String dataPostagem)
			throws EntradaException {
		try {
			this.controller.postar(texto, dataPostagem);
		} catch (EntradaException erro) {
			throw new EntradaException("Nao eh possivel criar o post. "
					+ erro.getMessage(), erro);
		}
	}

	public String getPost(int postagem) {
		return this.controller.getPostagem(postagem);
	}

	public String getPost(String atributo, int indice) {
		return this.controller.getPostagem(atributo, indice);
	}

	public String getConteudoPost(int indice, int postagem)
			throws EntradaException, ItemInexistenteException {
		try {
			return this.controller.getConteudo(indice, postagem);
		} catch (ItemInexistenteException erro) {
			throw new EntradaException("Item #" + indice
					+ " nao existe nesse post, ele possui apenas "
					+ erro.getMessage() + " itens distintos.");

		} catch (EntradaException erro) {
			throw new ItemInexistenteException("Requisicao invalida. "
					+ erro.getMessage(), erro);
		}
	}

	public void adicionaAmigo(String emailUsuario)
			throws UsuarioInexistenteException {
		this.controller.adicionaAmigo(emailUsuario);
	}

	public void removeAmigo(String emailUsuario)
			throws UsuarioInexistenteException {
		this.controller.removeAmigo(emailUsuario);
	}

	public void aceitaAmizade(String emailUsuario)
			throws UsuarioInexistenteException, SolicitacaoException {
		this.controller.aceitaAmizade(emailUsuario);
	}

	public void rejeitaAmizade(String emailUsuario)
			throws UsuarioInexistenteException, SolicitacaoException {
		this.controller.rejeitaAmizade(emailUsuario);
	}

	public int getQtdAmigos() {
		return this.controller.getQtdAmigos();
	}

	public int getNotificacoes() {
		return this.controller.getNotificacoes();
	}

	public String getNextNotificacao() throws ItemInexistenteException {
		return this.controller.getProxNotificacao();
	}

	public void curtirPost(String emailUsuario, int postagem)
			throws UsuarioInexistenteException, EntradaException {
		this.controller.curtirPost(emailUsuario, postagem);
	}

	public void login(String email, String senha) throws LogicaException,
			SenhaException {
		try {
			this.controller.login(email, senha);
		} catch (UsuarioInexistenteException erro) {
			throw new UsuarioInexistenteException(
					"Nao foi possivel realizar login. " + erro.getMessage(),
					erro);
		} catch (LogarDeslogarException erro) {
			throw new LogarDeslogarException(
					"Nao foi possivel realizar login. " + erro.getMessage(),
					erro);
		}
	}

	public void logout() throws LogarDeslogarException {
		this.controller.logout();
	}
	
	
	
	//teste de ranking 
	public void get3Melhores() {
		this.controller.get3Melhores();
	}
	
	public void get3Piores() {
		this.controller.get3Piores();
	}

}
