package projeto.maispop.usuario;

import java.util.ArrayList;
import java.util.List;

import projeto.maispop.excecoes.ItemInexistenteException;

//CLASSE INUTILIZADA, FAVOR DESCONSIDERAR.
public class Notificacoes {

    private List<String> notificacoes;

    public Notificacoes() {
	this.notificacoes = new ArrayList<>();
    }

    public void recebeNotificacao(String notificacao) {
	this.notificacoes.add(notificacao);
    }

    public int getNotificacoes() {
	return this.notificacoes.size();
    }

    public String getProxNotificacao() throws ItemInexistenteException {
	if (this.notificacoes.size() == 0) {
	    throw new ItemInexistenteException("Nao ha mais notificacoes.");
	}
	return notificacoes.remove(0);
    }

    
    
    
    
    
    // ENVIA NOTIFICACOES:
    public String amizadeAceita(String usuario) {
	return usuario + " aceitou sua solicitacao de amizade.";
    }

    public String amizadeRejeitada(String usuario) {
	return usuario + " rejeitou sua solicitacao de amizade.";
    }

    public String pedidoAmizade(String usuario) {
	return usuario + " deseja lhe adicionar como amigo.";
    }

    public String remocaoAmizade(String usuario) {
	return usuario + " lhe removeu de sua lista de amigos.";
    }

    public String amizadeExistente(String usuario) {
	return "Voce ja possui uma amizade com " + usuario + ".";
    }

    @Override
    public String toString() {
	String fdl = System.getProperty("line.separator");
	String notificacoes = "";
	for (String string : this.notificacoes) {
	    notificacoes = notificacoes + string + fdl;
	}
	return notificacoes;
    }

}
