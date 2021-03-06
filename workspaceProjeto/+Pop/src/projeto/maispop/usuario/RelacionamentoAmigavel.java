package projeto.maispop.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import projeto.maispop.postagem.Postagem;

public class RelacionamentoAmigavel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5810837237730036538L;
	private ListaDeAmigos listaDeAmigos;
	private List<Postagem> postagens;

	public RelacionamentoAmigavel() {
		this.listaDeAmigos = new ListaDeAmigos();
		this.postagens = new ArrayList<>();
	}

	public void aceitaAmizade(Amigavel amigo) {
		this.listaDeAmigos.aceitaAmizade(amigo);
	}

	public void adicionaAmigo(Amigavel amigo) {
		this.listaDeAmigos.adicionaAmigo(amigo);
	}

	public void atualizarFeed() {
		this.postagens.clear();
		for (Amigavel amigo : this.listaDeAmigos) {
			this.postagens.addAll(amigo.getFeedPostagem());
		}

	}

	public boolean contemAmigo(String emailUsuario) {
		return this.listaDeAmigos.contemAmigo(emailUsuario);
	}

	public boolean contemPendencia(String emailUsuario) {
		return this.listaDeAmigos.contemPendencia(emailUsuario);
	}

	public int getQtdAmigos() {
		return this.listaDeAmigos.getQtdAmigos();
	}

	public void ordenarPorData() {
		Collections.sort(this.postagens,
				(postagem, outraPostagem) -> (postagem.getDataReal().compareTo(outraPostagem.getDataReal())));
	}

	public void ordenarPorPop() {
		Collections.sort(this.postagens,
				(postagem, outraPostagem) -> (postagem.getPopularidade() - outraPostagem.getPopularidade()));
	}

	public void rejeitaAmizade(Amigavel amigo) {
		this.listaDeAmigos.rejeitaAmizade(amigo);
	}

	public void removeAmigo(Amigavel amigo) {
		this.listaDeAmigos.removeAmigo(amigo);
	}

	public String getPostFeedNoticiasRecentes(int indice) {
		ordenarPorData();
		return this.postagens.get(indice).toString();
	}

	public String getPostFeedNoticiasMaisPopulares(int indice) {
		ordenarPorPop();
		return this.postagens.get(indice).toString();
	}

}
