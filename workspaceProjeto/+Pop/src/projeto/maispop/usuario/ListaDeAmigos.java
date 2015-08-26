package projeto.maispop.usuario;


import java.util.HashSet;
import java.util.Set;

import projeto.maispop.excecoes.UsuarioInexistenteException;

/**
 * CLASSE ATUALMENTE INUTILIZADA!
 * Classe <code>ListaDeAmigos</code> que serve wrapper da classe <i>HashSet<i>
 * para que o <i>Usuario<i> possia manipular suas amizades na rede social <i>+Pop</i>.
 * 
 * @author Adson Cesar.
 * @author Gerson Sales.
 * @version 0.3
 * @see Usuario
 *
 */
public class ListaDeAmigos {
	private Set<Usuario> listaDeAmigos;
	private Set<Usuario> pendentes;
	
	/**
	 * Construtor da classe <i>ListaDeAmigos</i> que ao ser chamado apenas inicializa o
	 * <i>Set</i> listaDeAmigos como <i>HashSet</>
	 */
	public ListaDeAmigos() {
		this.listaDeAmigos = new HashSet<>();
		this.pendentes = new HashSet<>();

	}
	
	/**
	 * Metodo <code>adicionaAmigo</code> adiciona um tipo <i>Usuario</i>
	 * ao <i>HashSet</i> listaDeAmigos
	 * <b>onde:</b><br>
	 * @param amigo tipo <i>Usuario</i> para identificar o amigo a ser adicionado a lista.
	 */
	public void adicionaAmigo(Usuario amigo) {
		this.listaDeAmigos.add(amigo);
	}
	
	/**
	 * Metodo <code>removeAmigo</code> remove um tipo <i>Usuario</i>
	 * ao <i>HashSet</i> listaDeAmigos
	 * <b>onde:</b><br>
	 * @param amigo tipo <i>Usuario</i> para identificar o amigo a ser removido da lista.
	 */
	public void removeAmigo(Usuario amigo) {
		this.listaDeAmigos.remove(amigo);
	}
	
	/**
	 * Metodo <code>recebeSolicitacao</code> resposanvel por adicionar a lista de 
	 * pedidos amizades pendentes uma nova solicitacao.
	 * @param usuario <i>Usuario</i> que sera adicionado a lista de pendencias.
	 */
	public void recebeSolicitacao(Usuario usuario) {
		this.pendentes.add(usuario);
	}
	
	/**
	 * Metodo <code>aceitaSolicitacao</code> responsavel por remover da lista de 
	 * amizades pendentes e adicionao o usuario a lista de amizades.
	 * @param usuario <i>Usuario</i> a ser adicionado na lista de amigos.
	 */
	public void aceitaSolicitacao(Usuario usuario) {
		this.listaDeAmigos.add(usuario);
		this.pendentes.remove(usuario);
	}
	
	/**
	 * Metodo <code>recusaSolicitacao</code> responsavel por remover o usuario da lista de 
	 * amizades pendentes. 
	 * @param usuario <i>Usuario</i> a ser removido das amizades pendentes.
	 */
	public void recusaSolicitacao(Usuario usuario) {
		this.pendentes.remove(usuario);
	}
	
	/**
	 * Metodo <code>bucaAmigo</code> que recebe uma <i>String</i> como parametro
	 * e se encontrar um tipo <i>Usuario<i> com o atributo email identico ao passado
	 * como parametro retorna-o.
	 * <b>Onde<b><br>
	 * @param email String usada para idetentificar o e-mail de busca.
	 * @return <code>Usuario</code> caso encontre.
	 */
	public Usuario buscaAmigo(String email) throws UsuarioInexistenteException {
		for (Usuario usuario : listaDeAmigos) {
			if (usuario.getEmail().equals(email)) {
				return usuario;
			}
		}
		
		throw new UsuarioInexistenteException();
	}

	/**
	 * Metodo <code>contemAmigo</code> responsavel por avaliar se o 
	 * usuario passado como parametro existe na na lista de amigos.
	 * @param amigo <i>Usuario</i> a ser considerado como objeto de busca.
	 * @return <i>boolean</i> representando a veracidade da informacao.
	 */
	public boolean contemAmigo(Usuario amigo) {
		if (listaDeAmigos.contains(amigo)) {
			return true;
		}
		return false;
	}
	

}
