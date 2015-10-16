package projeto.maispop.midia;

import java.util.ArrayList;
import java.util.List;

import projeto.maispop.excecoes.EntradaException;

public enum FabricaMidia {

	IMAGEM {
		@Override
		public String getMarcacao() {
			return Imagem.getMarcacao();
		}

		@Override
		public Midia getMidia(String conteudo) {
			return new Imagem(conteudo);
		}
	},

	AUDIO {
		@Override
		public Midia getMidia(String conteudo) {
			return new Audio(conteudo);
		}

		@Override
		public String getMarcacao() {
			return Audio.getMarcacao();
		}
	},
	HASHTAG {

		@Override
		public Midia getMidia(String conteudo) {
			return new HashTag(conteudo);
		}

		@Override
		public String getMarcacao() {
			return HashTag.getMarcacao();
		}
	},
	
	MENSAGEM{

		@Override
		public Midia getMidia(String conteudo) throws EntradaException {
			return new Mensagem(conteudo); 
		}

		@Override
		public String getMarcacao() {
			return Mensagem.getMarcacao();
		}};

	public abstract Midia getMidia(String conteudo) throws EntradaException;
	public abstract String getMarcacao();

	
	public static List<Midia> getListaMidia(String conteudo) throws EntradaException {
		List<Midia> listaMidia = new ArrayList<>();
		String[] postagemSplit = conteudo.split(" ");
		String texto = "";

		boolean adiciona = true;
		for (String string : postagemSplit) {

			for (FabricaMidia fabMidia : FabricaMidia.values()) {
				if (string.contains(fabMidia.getMarcacao())) {
					listaMidia.add(fabMidia.getMidia(string));
					adiciona = false;
					break;
				}
				if (fabMidia == FabricaMidia.HASHTAG && !adiciona) {
					throw new EntradaException(
							"As hashtags devem comecar com '#'. Erro na hashtag: '"
									+ string + "'.");
				}
			}
			if (adiciona) {
				texto = texto == "" ? texto + string : texto + " " + string;
				continue;
			}
		}
		listaMidia.add(0, FabricaMidia.MENSAGEM.getMidia(texto));
		
		return listaMidia;
	}
	
	
	
	
	
	
	
	
	
	

}
