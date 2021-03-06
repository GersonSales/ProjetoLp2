package projeto.maispop.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import projeto.maispop.excecoes.EntradaException;
import projeto.maispop.excecoes.EscritaException;
import projeto.maispop.excecoes.ItemInexistenteException;
import projeto.maispop.postagem.Postagem;
import projeto.maispop.sistema.GerenciadorES;

/**
 * Classe <code>MuralUsuario</code> representa a <i>linha do tempo</i> de um
 * usuario na rede social <i>+Pop<i>. Onde ele podera <i>postar</i> mensagens e
 * midias.
 * 
 * @author Gerson Sales.
 * @see Postagem
 *
 */
public class MuralUsuario implements Serializable {

	private static final long serialVersionUID = 8920493615005290571L;
	private List<Postagem> postagens;
	private int popularidadeBonus;

	/**
	 * Construtor da classe <code>MuralUsuario</code> tem como funcao inicialiar
	 * a lista de postagens.
	 */
	public MuralUsuario() {
		this.postagens = new ArrayList<>();
		this.popularidadeBonus = 0;
	}

	public void adicionaPops(int popBonus) {
		this.popularidadeBonus = this.popularidadeBonus + popBonus;
	}

	public int dimensaoMural() {
		return this.postagens.size();
	}

	/**
	 * Metodo <i>getConteudoPost</i> responsavel por receber dois Inteiros como
	 * parametro, um representando a postagem escolhida da lista de postagens e
	 * outro representando o conteudo da postagem escolhida.
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
		return this.postagens.get(postagem).getConteudo(indice);
	}

	public int getPopsPost(int indice) {
		return this.postagens.get(indice).getPopularidade();
	}

	public int getPopularidade() {
		int popularidadeTotal = 0;
		for (Postagem postagem : postagens) {
			popularidadeTotal = popularidadeTotal + postagem.getPopularidade();
		}

		return popularidadeTotal + this.popularidadeBonus;
	}

	/**
	 * Metodo sobrecarregado <i>getPostagem</i> responsavel por receber como
	 * parametro um Inteiro que representa um indice a ser escolhido da lista de
	 * postagens.
	 * 
	 * @param indice
	 *            . Inteiro represntando um indice.
	 * @return <i>Postagem</i>. String de uma <i>Postagem</i>.
	 */
	public Postagem getPostagem(int indice) {
		return this.postagens.get(indice);
	}

	public List<Postagem> getPostagens(int quandidade) {
		List<Postagem> postagens = new ArrayList<Postagem>();
		for (int i = 0; i < dimensaoMural(); i++) {
			postagens.add(this.postagens.get(i));
		}

		return postagens;
	}

	/**
	 * Metodo sobrecarregado <i>getPostagem</i> responsavel por receber um
	 * String como parametro que representa o atributo a ser pesquisado e um
	 * Inteiro que representa o indice da lista de postagens a ser pesquisado.
	 * Retornando assim a postagem e sua respectiva informacao.
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

		Postagem postagem = this.postagens.get(indice);
		switch (atributo) {
		case "Mensagem":
			return postagem.getMensagem();
		case "Data":
			return postagem.getData();
		case "Hashtags":
			return postagem.getHashTags();
		default:
			return "dafault";
		}
	}

	/**
	 * Metodo <i>postar</i> responsavel por criar uma nova instancia de
	 * <code>Postagem</code> e adiciona-la a lista de postagens.
	 * 
	 * @param texto
	 *            . String recebida como parametro que representa o texto que
	 *            contera a postagem
	 * @param dataPostagem
	 *            . String que representa a Data e Hora da postagem.
	 * @throws EntradaException. Caso
	 *             seja recebido alguma String(texto, DataPostagem) incorreta.
	 */
	public void postar(String texto, String dataPostagem)
			throws EntradaException {
		Postagem novaPostagem = new Postagem(texto, dataPostagem);
		this.postagens.add(novaPostagem);
	}

	public int qtdCurtidasDePost(int indice) throws ItemInexistenteException {
		if (indice > dimensaoMural()) {
			throw new ItemInexistenteException("Usuarix possui apenas "
					+ dimensaoMural() + " post(s).");
		}
		return this.postagens.get(indice).getCurtir();
	}

	public int qtdRejeicoesDePost(int indice) {
		return this.postagens.get(indice).getDescurtir();
	}

	public void salvarPostagens(String titulo) throws EscritaException {
		StringBuilder postagensString = new StringBuilder();
		String sl = System.getProperty("line.separator");

		StringBuilder postagemString;
		int contador = 0;

		for (Postagem postagem : postagens) {
			contador++;
			postagemString = new StringBuilder();
			postagemString.append("Post #" + contador + " "
					+ postagem.toStringPostagem());

			postagensString.append(postagemString.toString() + sl);

		}
		GerenciadorES.gravarTexto("./postagens/", titulo,
				postagensString.toString());

	}

}
