package projeto.maispop.sistema;

import projeto.maispop.excecoes.EntradaException;
import projeto.maispop.excecoes.ItemInexistenteException;
import projeto.maispop.excecoes.LogarDeslogarException;
import projeto.maispop.excecoes.LogicaException;
import projeto.maispop.excecoes.SenhaException;
import projeto.maispop.excecoes.SolicitacaoException;
import projeto.maispop.excecoes.UsuarioExistenteException;
import projeto.maispop.excecoes.UsuarioInexistenteException;
import projeto.maispop.usuario.Usuario;
import projeto.maispop.postagem.Postagem;



public class Controller {

    private Usuario usuarioLogado;
    private BancoDeUsuarios bancoDeUsuarios;

    public Controller() {
	this.usuarioLogado = null;
	this.bancoDeUsuarios = new BancoDeUsuarios();
    }

    public void cadastraUsuario(String nome, String email, String senha,
	    String dataNascimento, String imagem) throws EntradaException,
	    UsuarioExistenteException {
	this.bancoDeUsuarios.cadastraUsuario(nome, email, senha,
		dataNascimento, imagem);
    }

    public void cadastraUsuario(String nome, String email, String senha,
	    String dataNascimento) throws EntradaException,
	    UsuarioExistenteException {
	this.bancoDeUsuarios
		.cadastraUsuario(nome, email, senha, dataNascimento);
    }

    public void removeUsuario(String email) throws UsuarioInexistenteException {
	this.bancoDeUsuarios.removeUsuario(email);
    }

    public String getInfoUsuario(String atributo) throws EntradaException,
	    LogicaException {
	return this.bancoDeUsuarios.getInfoUsuario(atributo,
		this.usuarioLogado.getEmail());
    }

    public String getInfoUsuario(String atributo, String email)
	    throws EntradaException, LogicaException {
	return this.bancoDeUsuarios.getInfoUsuario(atributo, email);
    }

    public void atualizaPerfil(String atributo, String valor, String antigoValor)
	    throws UsuarioInexistenteException, EntradaException {
	if (this.usuarioLogado == null) {
	    throw new UsuarioInexistenteException(
		    "Nenhum usuarix esta logadx no +pop.");
	}
	this.bancoDeUsuarios.atualizaPerfil(atributo, valor, antigoValor,
		this.usuarioLogado.getEmail());
    }

    public void atualizaPerfil(String atributo, String valor)
	    throws UsuarioInexistenteException, EntradaException {
	if (this.usuarioLogado == null) {
	    throw new UsuarioInexistenteException(
		    "Nenhum usuarix esta logadx no +pop.");
	}
	this.bancoDeUsuarios.atualizaPerfil(atributo, valor,
		this.usuarioLogado.getEmail());
    }

    public void login(String email, String senha) throws LogicaException,
	    SenhaException {
	String erro = "Nao foi possivel realizar login.";

	if (this.usuarioLogado != null) {
	    throw new LogarDeslogarException("Um usuarix ja esta logadx: "
		    + this.usuarioLogado.getNome() + ".");
	}

	Usuario usuario = this.bancoDeUsuarios.getUsuario(email);

	if (usuario.getSenha().equals(senha)) {
	    this.usuarioLogado = usuario;
	} else {
	    throw new SenhaException(erro + " Senha invalida.");
	}

    }

    public void postar(String texto, String dataPostagem)
	    throws EntradaException {
	this.usuarioLogado.postar(texto, dataPostagem);
    }

    public String getPostagem(int postagem) {
	return this.usuarioLogado.getPostagem(postagem).toString();
    }

    public String getPostagem(String atributo, int indice) {
	return this.usuarioLogado.getPostagem(atributo, indice);
    }

    public String getConteudo(int indice, int postagem)
	    throws EntradaException, ItemInexistenteException {
	return this.usuarioLogado.getConteudoPost(indice, postagem);
    }

    public void curtirPost(String emailUsuario, int postagem)
	    throws UsuarioInexistenteException, EntradaException {
	Usuario amigo = bancoDeUsuarios.getUsuario(emailUsuario);

	if (!(this.usuarioLogado.contemAmigo(emailUsuario))) {
	    throw new UsuarioInexistenteException();
	}

	Postagem postagemAmigo = amigo.getPostagem(postagem);
	this.usuarioLogado.curtir(postagemAmigo);
	amigo.notificaMe(this.usuarioLogado.getNome() + " curtiu seu post de "
		+ postagemAmigo.getData() + ".");

	amigo.atualizaTipo();
    }

    public void descurtirPost(String emailUsuario, int postagem)
	    throws UsuarioInexistenteException, EntradaException {

	Usuario amigo = bancoDeUsuarios.getUsuario(emailUsuario);

	if (!(this.usuarioLogado.contemAmigo(emailUsuario))) {
	    throw new UsuarioInexistenteException();
	}

	Postagem postagemAmigo = amigo.getPostagem(postagem);
	this.usuarioLogado.descurtir(postagemAmigo);
	amigo.notificaMe(this.usuarioLogado.getNome()
		+ " descurtiu seu post de " + postagemAmigo.getData() + ".");

	amigo.atualizaTipo();
    }

    // RELACIONEMTO ENTRE USUARIOS:

    public void adicionaAmigo(String emailUsuario)
	    throws UsuarioInexistenteException {
	this.usuarioLogado.adicionaAmigo(emailUsuario);

	Usuario amigo = this.bancoDeUsuarios.getUsuario(emailUsuario);
	amigo.adicionaAmigo(this.usuarioLogado.getEmail());

	amigo.notificaMe(this.usuarioLogado.getNome() + " quer sua amizade.");
    }

    public void removeAmigo(String emailUsuario)
	    throws UsuarioInexistenteException {
	this.usuarioLogado.removeAmigo(emailUsuario);

	Usuario amigo = this.bancoDeUsuarios.getUsuario(emailUsuario);
	amigo.removeAmigo(this.usuarioLogado.getEmail());
	amigo.notificaMe(this.usuarioLogado.getNome()
		+ " removeu a sua amizade.");
    }

    public void rejeitaAmizade(String emailUsuario)
	    throws UsuarioInexistenteException, SolicitacaoException {
	Usuario amigo = this.bancoDeUsuarios.getUsuario(emailUsuario);

	if (!(this.usuarioLogado.contemPendencia(emailUsuario))) {
	    throw new SolicitacaoException(amigo.getNome()
		    + " nao lhe enviou solicitacoes de amizade.");
	}

	this.usuarioLogado.rejeitaAmizade(emailUsuario);

	amigo.notificaMe(this.usuarioLogado.getNome()
		+ " rejeitou sua amizade.");
    }

    public void aceitaAmizade(String emailUsuario)
	    throws UsuarioInexistenteException, SolicitacaoException {
	Usuario amigo = this.bancoDeUsuarios.getUsuario(emailUsuario);

	if (!(this.usuarioLogado.contemPendencia(emailUsuario))) {
	    throw new SolicitacaoException(amigo.getNome()
		    + " nao lhe enviou solicitacoes de amizade.");
	}

	this.usuarioLogado.aceitaAmizade(emailUsuario);

	amigo.aceitaAmizade(this.usuarioLogado.getEmail());

	amigo.notificaMe(this.usuarioLogado.getNome() + " aceitou sua amizade.");
    }

    public int getQtdAmigos() {
	return this.usuarioLogado.getQtdAmigos();
    }

    public int getNotificacoes() {
	return this.usuarioLogado.getNotificacoes();
    }

    public String getProxNotificacao() throws ItemInexistenteException {
	return this.usuarioLogado.getProxNotificacao();
    }

    public void logout() throws LogarDeslogarException {
	String erro = "Nao eh possivel realizar logout.";

	if (this.usuarioLogado == null) {
	    throw new LogarDeslogarException(erro
		    + " Nenhum usuarix esta logadx no +pop.");
	}

	this.usuarioLogado = null;
    }

    public void fechaSistema() throws LogicaException {
	if (this.usuarioLogado != null) {
	    throw new LogicaException(
		    "Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
	}
    }

}
