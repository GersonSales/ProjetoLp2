package projeto.maispop.usuario;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import projeto.maispop.excecoes.DataException;
import projeto.maispop.excecoes.EmailException;
import projeto.maispop.excecoes.EntradaException;
import projeto.maispop.excecoes.ImagemException;
import projeto.maispop.excecoes.ItemInexistenteException;
import projeto.maispop.excecoes.LogicaException;
import projeto.maispop.excecoes.NomeException;
import projeto.maispop.excecoes.SenhaException;
import projeto.maispop.excecoes.UsuarioInexistenteException;

/**
 * Classe <code>Usuario</code> representa individualmente um integrande do
 * sistema de usuarios da rede social <i>+Pop</i>.
 * 
 * @author Gerson Sales
 * @version 0.3
 * @see ListaDeAmigos
 * @see MuralUsuario
 */
public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private String dataNascimento;
    private String imagemPerfil;

    private ListaDeAmigos listaDeAmigos;
    private Notificacoes notificacoes;

    private MuralUsuario mural;

    private static final String IMG_PERFIL_PADRAO = "resources/default.jpg";

    /**
     * Construtor sobrecarrecado da classe <code>Usuario</code>.<br>
     * que cria uma nova instancia de <code>Usuario</code> recebendo todas as
     * informacoes necessarias para a acriacao do mesmo.
     * 
     * @param nome
     *            String que define o nome do usuario.
     * @param email
     *            String que define o e-mail do usuario.
     * @param senha
     *            String que define a senha do usuario.
     * @param dataNascimento
     *            String que define a data de nascimento do usuario.
     * @param imagemPerfil
     *            String que indica o caminho da imagem que sera usada no
     *            perfil.
     * @throws EntradaException. Caso
     *             o seja recebido como parametro algum atributo de formatacao
     *             invalida.
     * @throws LogicaException. Caso
     *             haja algum erro durante o processo de gerenciamento de
     *             informacoes.
     */
    public Usuario(String nome, String email, String senha,
	    String dataNascimento, String imagemPerfil) throws EntradaException {

	validaNome(nome);
	validaEmail(email);
	validaSenha(senha);
	validaDataNascimento(dataNascimento);
	validaImagem(imagemPerfil);

	this.nome = nome;
	this.email = email;
	this.senha = senha;
	atribuiData(dataNascimento);
	this.imagemPerfil = imagemPerfil;

	this.listaDeAmigos = new ListaDeAmigos();
	this.notificacoes = new Notificacoes();
	this.mural = new MuralUsuario(nome);
    }

    /**
     * Construtor sobrecarrecado da classe <code>Usuario</code>.<br>
     * que cria uma nova instancia de <code>Usuario</code> recebendo quase todas
     * as informacoes necessarias para a acriacao do mesmo, com excecao da
     * imagem, sendo assim passada uma imagem_padrao como parametro
     * 
     * @param nome
     *            String que define o nome do usuario.
     * @param email
     *            String que define o e-mail do usuario.
     * @param senha
     *            String que define a senha do usuario.
     * @param dataNascimento
     *            String que define a data de nascimento do usuario.
     * @throws EntradaException. Caso
     *             o seja recebido como parametro algum atributo de formatacao
     *             invalida.
     * @throws LogicaException. Caso
     *             haja algum erro durante o processo de gerenciamento de
     *             informacoes.
     */
    public Usuario(String nome, String email, String senha,
	    String dataNascimento) throws EntradaException {
	this(nome, email, senha, dataNascimento, IMG_PERFIL_PADRAO);
    }

    // REVISAR ESTE METODO!
    /**
     * Metodo <i>atribuiData</i> responsavel por receber um String como
     * parametro representando a data de nascimento de um <code>Usuadio</code>
     * realizando algumas verificacoes de validade e formatando-a para um novo
     * padrao. <br>
     * <b>Exemplo de 'entrada'</b>: 12/01/1993 <br>
     * <b>Exemplo de 'saida'</b>: 1993-01-12
     * 
     * @param dataNascimento
     *            . String representante da data que sera manipulada.
     * @throws DataException. Caso
     *             seja recebido uma data invalida como parametro.
     */

    private void atribuiData(String dataNascimento) throws DataException {
	try {
	    DateTimeFormatter formatador = DateTimeFormatter
		    .ofPattern("dd/MM/yyyy");
	    LocalDate sData = LocalDate.parse(dataNascimento, formatador);
	    sData = LocalDate.of(sData.getYear(), sData.getMonth(),
		    Integer.parseInt(dataNascimento.substring(0, 2)));
	    this.dataNascimento = sData.toString();

	} catch (DateTimeException erro) {
	    if (erro.getMessage().contains("Invalid")) {
		throw new DataException("Data nao existe.");
	    } else {
		throw new DataException("Formato de data esta invalida.");
	    }
	}
    }

    /**
     * Metodo <i>getNome</i> responsavel por retornar a String que representa o
     * nome do <code>Usuario</code>.
     * 
     * @return nome. String representante do nome de <code>Usuario</code>.
     */
    public String getNome() {
	return nome;
    }

    /**
     * Metodo <i>setNome</i> responsavel tentar atribuir um novo nome ao
     * <code>Usuario</code>. Recebendo uma String(novo nome) como parametro e
     * verificando-o.
     * 
     * @param nome
     *            . String representante do novo nome do <code>Usuario</code>.
     * @throws NomeException. Caso
     *             nao seja recebido uma nome valido.
     */
    public void setNome(String nome) throws NomeException {
	validaNome(nome);
	this.nome = nome;
    }

    /**
     * Metodo <i>getEmail</i> responsavel por retornar a String que representa o
     * email do <code>Usuario</code>.
     * 
     * @return email. String representante do email do <code>Usuario</code>.
     */
    public String getEmail() {
	return email;
    }

    /**
     * Metodo <i>setEmail</i> responsavel tentar atribuir um novo email ao
     * <code>Usuario</code>. Recebendo uma String(novo email) como parametro e
     * verificando-o.
     * 
     * @param email
     *            . String representante do novo email do <code>Usuario</code>.
     * @throws EmailException. Caso
     *             nao seja recebido uma email valido.
     */
    public void setEmail(String email) throws EmailException {
	validaEmail(email);
	this.email = email;
    }

    /**
     * Metodo <i>getSenha</i> responsavel por retornar a String que representa a
     * senha do <code>Usuario</code>.
     * 
     * @return senha. String representante da senha do <code>Usuario</code>.
     */
    public String getSenha() {
	return senha;
    }

    /**
     * Metodo <i>setSenha</i> responsavel tentar atribuir uma nova senha ao
     * <code>Usuario</code>. Recebendo uma String(novo senha) como parametro e
     * verificando-a.
     * 
     * @param senha
     *            . String representante do novo senha do <code>Usuario</code>.
     * @throws SenhaException. Caso
     *             nao seja recebido uma senha valido.
     */
    public void setSenha(String senha) throws SenhaException {
	validaSenha(senha);
	this.senha = senha;
    }

    /**
     * Metodo <i>getDataNascimento</i> responsavel por retornar a String que
     * representa a data de nascimento do <code>Usuario</code>.
     * 
     * @return dataDeNascimento. String representante da data em que o
     *         <code>Usuario</code> nasceu.
     */
    public String getDataNascimento() {
	return dataNascimento;
    }

    /**
     * Metodo <i>setDataNascimento</i> responsavel tentar atribuir uma nova data
     * de nascimento <code>Usuario</code>. Recebendo uma String(nova
     * dataDeNascimento) como parametro e verificando-a.
     * 
     * @param dataDeNascimento
     *            . String representante do nova data de nascimento do
     *            <code>Usuario</code>.
     * @throws DataException. Caso
     *             nao seja recebido uma data de nascimento valida.
     */
    public void setDataNascimento(String dataNascimento) throws DataException {
	atribuiData(dataNascimento);
    }

    /**
     * Metodo <i>getImagemPerfil</i> responsavel por retornar a String que
     * representa o caminho da imagem de perfil do <code>Usuario</code>.
     * 
     * @return imagemPerfil. String representante a imagem de perfil do
     *         <code>Usuario</code>.
     */
    public String getImagemPerfil() {
	return imagemPerfil;
    }

    /**
     * Metodo <i>setImagemPerfil</i> responsavel tentar atribuir uma nova imagem
     * de perfil ao <code>Usuario</code>. Recebendo uma String(nova
     * imagemPerfil) como parametro e verificando-a.
     * 
     * @param imagemPerfil
     *            . String representante da nova imagem de perfil do
     *            <code>Usuario</code>.
     * @throws ImagemException. Caso
     *             nao seja recebido uma imagem valida.
     */
    public void setImagemPerfil(String imagemPerfil) throws ImagemException {
	validaImagem(imagemPerfil);
	this.imagemPerfil = imagemPerfil;
    }

    /**
     * Metodo <code>removeImagemPerfil</code> responsavel por modificar a imagem
     * do perfil para uma imagem definida como padrao.
     */
    public void removeImagemPerfil() {
	this.imagemPerfil = IMG_PERFIL_PADRAO;
    }

    /**
     * Metodo <i>postar</i> responsavel por delegar ao
     * <code>MuralDeUsuario</code> uma criacao de uma nova <code>Postagem</code>
     * .
     * 
     * @param texto
     *            . String representante do texto da postagem.
     * @param dataPostagem
     *            . String que representa a data da postagem.
     * @throws EntradaException. Caso
     *             seja recebido alguma String com informacoes invalidas.
     */
    public void postar(String texto, String dataPostagem)
	    throws EntradaException {
	this.mural.postar(texto, dataPostagem);
    }

    /**
     * Metodo sobrecarregado <i>getPostagem</i> responsavel por receber um
     * String como parametro que representa o atributo a ser pesquisado e um
     * Inteiro que representa o indice da lista de postagens a ser pesquisado.
     * Retornando assim a postagem e sua respectiva informacao. Delegando toda a
     * tarefa a <code>MuralDeUsuario</code>.
     * 
     * @param atributo
     *            . String que representa o atributo a ser pesquisado.
     * @param indice
     *            . Inteiro que identificara qual postagem sera escolhida.
     * @return mensagem/data/hashtag. String de informacoes cotida em
     *         <code>Postagem</code>.
     * @see <i>getPostagem(int indice);</i>.
     */
    public String getPostagem(String atributo, int indice) {
	return this.mural.getPostagem(atributo, indice);
    }

    /**
     * Metodo sobrecarregado <i>getPostagem</i> responsavel por receber como
     * parametro um Inteiro que representa um indice a ser escolhido da lista de
     * postagens. Delegando toda a
     * tarefa a <code>MuralDeUsuario</code>.
     * 
     * @param indice
     *            . Inteiro represntando um indice.
     * @return <i>Postagem</i>. String de uma <i>Postagem</i>.
     */
    public String getPostagem(int indice) {
	return this.mural.getPostagem(indice);
    }

    /**
     * Metodo <i>getConteudoPost</i> responsavel por receber dois Inteiros como
     * parametro, um representando a postagem escolhida da lista de postagens e
     * outro representando o conteudo da postagem escolhida. Delegando toda a
     * tarefa a <code>MuralDeUsuario</code>.
     * 
     * @param indice
     *            . Inteiro representante do conteudo da postagem.
     * @param postagem
     *            . Inteiro representando a postagem a ser escolhida da lista de
     *            postagens.
     * @return conteudo. String representante do conteudo escolhido pelo indice.
     * 
     * @throws EntradaException. Caso
     *             o usuario digite uma uma entrada desconhecida.
     * @throws ItemInexistenteException. Caso
     *             o item nao exista na <code>Postagem</code>.
     */
    public String getConteudoPost(int indice, int postagem)
	    throws EntradaException, ItemInexistenteException {
	return this.mural.getConteudoPost(indice, postagem);
    }

    
    
    
    // METODOS INUTILIZADOS:

    public String getNotificacoes() {
	return notificacoes.toString();
    }

    /**
     * Metodo <code>solicitaAmizade</code> responsavel por enviar uma
     * solicitacao de amizade para o usuario recebido como parametro.
     * 
     * @param usuario
     *            <i>Usuario</i> que recebera a solicitacao.
     */
    public void solicitaAmizade(Usuario usuario) {
	if (!(listaDeAmigos.contemAmigo(usuario))) {
	    usuario.recebeSolicitacao(this);
	} else {
	    notificaMe(notificacoes.amizadeExistente(usuario.getNome()));
	}
    }

    /**
     * Metodo <code>recebeSolicitacao</code> responsavel por receber uma
     * solicitacao de amizade enviada por terceiros(outro usuario) e receber uma
     * notificacao sobre tal evento.
     * 
     * @param usuario
     *            <i>Usuario</i> que enviou a solicitacao.
     */
    public void recebeSolicitacao(Usuario usuario) {
	this.listaDeAmigos.recebeSolicitacao(usuario);
	notificaMe(notificacoes.pedidoAmizade(usuario.getNome()));
    }

    /**
     * Metodo <code>aceitaAmizade</code> responsavel por aceitar a solicitacao
     * de amizade enviada por terceiros(outro usuario) e adiciona-lo na lista de
     * amigos.
     * 
     * @param usuario
     *            <i>Usuario</i> que sera adicionado na lista de amigos.
     */
    public void aceitaAmizade(Usuario usuario) {
	this.listaDeAmigos.aceitaSolicitacao(usuario);
	usuario.adicionaAmigo(this);
	usuario.notificaMe(notificacoes.amizadeAceita(getNome()));
    }

    /**
     * Metodo <code>recusaAmizade</code> responsavel por rejeitar uma
     * solicitacao de amizade enviada por terceiros(outro usuario) e notificar o
     * mesmo sobre tal rejeicao.
     * 
     * @param usuario
     *            <i>Usuario</i> que tera a solicitacao de amizade rejeitada.
     */
    public void recusaAmizade(Usuario usuario) {
	this.listaDeAmigos.recusaSolicitacao(usuario);
	usuario.notificaMe(notificacoes.amizadeRejeitada(getNome()));
    }

    /**
     * Metodo <code>notificaMe</code> responsavel por adicionar uma notificacao
     * recebida a lista de notificacoes.
     * 
     * @param notificacao
     *            String recebida como notificacao.
     */
    public void notificaMe(String notificacao) {
	this.notificacoes.recebeNotificacao(notificacao);
    }

    /**
     * Metodo <code>adicionaAmigo</code> adiciona um tipo <i>Usuario</i> a
     * <i>ListaDeAmigos</i> listaDeAmigos e adiciona a si mesmo da lista de
     * amigos do amigo. <b>onde:</b><br>
     * 
     * @param amigo
     *            tipo <i>Usuario</i> para identificar o amigo a ser adicionado
     *            a lista.
     */
    public void adicionaAmigo(Usuario amigo) {
	this.listaDeAmigos.adicionaAmigo(amigo);
    }

    /**
     * Metodo <code>removeAmigo</code> remove um tipo <i>Usuario</i> da
     * <i>ListaDeAmigos</i> listaDeAmigos e remove a si mesmo da lista de amigos
     * do amigo. <b>onde:</b><br>
     * 
     * @param amigo
     *            <i>Usuario</i> para identificar o amigo a ser removido da
     *            lista.
     */
    public void removeAmigo(Usuario amigo) {
	this.listaDeAmigos.removeAmigo(amigo);
	amigo.serRemovido(this);
    }

    /**
     * Metodo <code>serRemovido</code> responsavel por remover o amigo da lista
     * de amigos e receber a notificacao de tal acao.
     * 
     * @param usuario
     *            <i>Usuario</i> a ser removido da lista de amigos.
     */
    public void serRemovido(Usuario usuario) {
	this.listaDeAmigos.removeAmigo(usuario);
	notificaMe(notificacoes.remocaoAmizade(usuario.getNome()));
    }

    /**
     * Metodo <code>buscaAmigo</code> responsavel por realizar uma busca na
     * lista de amigo a procura de um usuario com mesmo e-mail passado como
     * parametro.
     * 
     * @param email
     *            String a ser considerada como objeto de busca.
     * @return <i>Usuario</i> caso seja encontrado.
     * @throws UsuarioInexistenteException
     */
    public Usuario buscaAmigo(String email) throws UsuarioInexistenteException {
	return this.listaDeAmigos.buscaAmigo(email);
    }

    // Teste de criacao do mural;
    /**
     * Metodo <code>postar</code> responsavel por publicar uma nova atividade no
     * mural de noticias do usuario.
     * 
     * @param texto
     *            String relacionada ao texto que o usuario ira digitar na
     *            postagem.
     * @param audio
     *            String relacionada ao caminho do audio que o usuario colocara
     *            em sua postagem.
     * @param musica
     *            String relacionada ao caminho da musica que o usuario colocara
     *            em sua postagem.
     * @param video
     *            String relacionada ao caminho do video que o usuario colocara
     *            em sua postagem.
     */
    // public void postar(String texto, String audio, String musica, String
    // video) {
    // this.mural.postar(listaDeAmigos, texto, audio, musica, video);
    // }

    // METODOS SEM DOCUMENTACAO. POSSIVEL SINGLETON:

    private void validaNome(String nome) throws NomeException {
	String erro = "Nome dx usuarix nao pode ser vazio.";

	if (nome == null) {
	    throw new NomeException(erro);
	} else if (nome.trim().length() == 0) {
	    throw new NomeException(erro);
	}
    }

    private void validaEmail(String email) throws EmailException {
	String erro = "Formato de e-mail esta invalido.";

	if (email == null) {
	    throw new EmailException(erro);
	} else if (!(email.contains("@"))) {
	    throw new EmailException(erro);
	} else if (!(email.contains(".com"))) {
	    throw new EmailException(erro);
	} else if (email.trim().length() == 0) {
	    throw new EmailException(erro);
	}
    }

    private void validaSenha(String senha) throws SenhaException {
	if (senha == null) {
	    throw new SenhaException();
	} else if (senha.trim().length() == 0) {
	    throw new SenhaException();
	}
    }

    private void validaDataNascimento(String dataNascimento)
	    throws DataException {
	String erro = "Formato de data esta invalida.";

	if (dataNascimento == null) {
	    throw new DataException(erro);
	} else if (dataNascimento.trim().length() == 0) {
	    throw new DataException(erro);
	}
    }

    private void validaImagem(String imagem) throws ImagemException {
	if (imagem == null) {
	    throw new ImagemException();
	} else if (imagem.trim().length() == 0) {
	    throw new ImagemException();
	} else if (!(imagem.contains(".jpg") && !(imagem.contains(".png")))) {
	    throw new ImagemException();
	}
    }

}
