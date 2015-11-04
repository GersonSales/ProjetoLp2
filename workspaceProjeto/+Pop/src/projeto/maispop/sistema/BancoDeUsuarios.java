package projeto.maispop.sistema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import projeto.maispop.excecoes.EntradaException;
import projeto.maispop.excecoes.LogicaException;
import projeto.maispop.excecoes.RequisicaoException;
import projeto.maispop.excecoes.SenhaException;
import projeto.maispop.excecoes.UsuarioExistenteException;
import projeto.maispop.excecoes.UsuarioInexistenteException;
import projeto.maispop.usuario.Usuario;

public class BancoDeUsuarios {

	private List<Usuario> usuarios;
	private Set<Usuario> ranking;

	public BancoDeUsuarios() {
		this.usuarios = new ArrayList<>();
		this.ranking = new HashSet<>();
	}

	public void cadastraUsuario(String nome, String email, String senha,
			String dataNascimento, String imagem) throws EntradaException,
			UsuarioExistenteException {

		if (verificaEmailExistente(email)) {
			throw new UsuarioExistenteException();
		}

		Usuario novoUsuario = new Usuario(nome, email, senha, dataNascimento,
				imagem);
		this.usuarios.add(novoUsuario);

	}

	public void cadastraUsuario(String nome, String email, String senha,
			String dataNascimento) throws EntradaException,
			UsuarioExistenteException {
		if (verificaEmailExistente(email)) {
			throw new UsuarioExistenteException();
		}

		Usuario novoUsuario = new Usuario(nome, email, senha, dataNascimento);
		this.usuarios.add(novoUsuario);
	}

	public void removeUsuario(String email) throws UsuarioInexistenteException {
		this.usuarios.remove(getUsuario(email));
	}

	private boolean verificaEmailExistente(String email) {
		for (Usuario usuario : usuarios) {
			if (usuario.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	public Usuario getUsuario(String emailUsuario)
			throws UsuarioInexistenteException {
		for (Usuario usuario : usuarios) {
			if (usuario.getEmail().equals(emailUsuario)) {
				return usuario;
			}
		}
		throw new UsuarioInexistenteException("Um usuarix com email "
				+ emailUsuario + " nao esta cadastradx.");
	}

	public String getInfoUsuario(String atributo, String email)
			throws EntradaException, LogicaException {
		Usuario usuario = getUsuario(email);
		if (usuario == null) {
			throw new UsuarioInexistenteException("Um usuarix com email "
					+ email + " nao esta cadastradx.");
		}

		switch (atributo) {
		case "Nome":
			return usuario.getNome();
		case "Email":
			return usuario.getEmail();
		case "Senha":
			throw new RequisicaoException("A senha dx usuarix eh protegida.");
		case "Data de Nascimento":
			return usuario.getDataNascimento();
		case "Foto":
			return usuario.getImagemPerfil();
		default:
			throw new EntradaException();
		}
	}

	public void atualizaPerfil(String atributo, String valor,
			String antigoValor, String email) throws SenhaException,
			UsuarioInexistenteException {
		Usuario usuario = getUsuario(email);

		if (usuario.getSenha().equals(antigoValor)) {
			usuario.setSenha(valor);
		} else {
			throw new SenhaException("A senha fornecida esta incorreta.");
		}
	}

	public void atualizaPerfil(String atributo, String valor, String email)
			throws EntradaException, UsuarioInexistenteException {
		Usuario usuario = getUsuario(email);
		if (usuario == null) {
			throw new UsuarioInexistenteException("Usuario nao logado.");
		}

		switch (atributo) {
		case "Nome":
			usuario.setNome(valor);
			break;
		case "E-mail":
			usuario.setEmail(valor);
			break;
		case "Data de Nascimento":
			usuario.setDataNascimento(valor);
			break;
		case "Foto":
			usuario.setImagemPerfil(valor);
			break;
		default:
			throw new EntradaException();
		}
	}

	public int dimensaoBanco() {
		return this.usuarios.size();
	}

	// RANKING DE USUARIOS:
	private void ordenaCrescente() {
		Collections.sort(this.usuarios);
	}

	private void ordenaDecrescente() {
		this.usuarios.sort(new Comparator<Usuario>() {
			@Override
			public int compare(Usuario usuario, Usuario outroUsuario) {
				return outroUsuario.compareTo(usuario);
			}
		});
	}

	private void poe3primeiros() {
		for (int i = 0; i < 3; i++) {
			this.ranking.add(this.usuarios.get(i));
		}
	}

	private void preencheRanking() {
		this.ranking.clear();

		ordenaDecrescente();
		poe3primeiros();

		ordenaCrescente();
		poe3primeiros();
	}

	public void imprimeRanking() {
		preencheRanking();
		int cont = 1;
		System.out.println("Melhores: ");
		for (Usuario usuario : ranking) {
			if (cont == 4) {
				cont = 1;
				System.out.println("Piores: ");
			}

			System.out.println("(" + cont + ") " + usuario.getNome() + " "
					+ usuario.getPopularidade());
			cont++;
		}
	}

}
